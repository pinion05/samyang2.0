# 리팩토링 계획 - Samyang 2.0 프로젝트

## 목표
복잡한 반복 코드를 간단한 메서드 호출로 대체하여 코드량을 40-50% 줄이고, 유지보수성을 향상시킵니다.

## Phase 1: 기초 구조 개선 (즉시 시작 가능)

### 1. DTO Mapper 클래스 생성
**목적**: Controller에서 반복되는 Entity-DTO 변환 코드 제거

**구현 내용**:
- `com.farm404.samyang.mapper.dto` 패키지 생성
- 각 엔티티별 Mapper 클래스 생성 (UserDtoMapper, CropDtoMapper 등)
- 정적 메서드로 간단한 변환 로직 구현

**예시**:
```java
public class UserDtoMapper {
    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
            .userID(user.getUserID())
            .username(user.getUsername())
            .email(user.getEmail())
            .fullName(user.getFullName())
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .build();
    }
    
    public static List<UserResponse> toResponseList(List<User> users) {
        return users.stream()
            .map(UserDtoMapper::toResponse)
            .toList();
    }
}
```

### 2. ValidationUtils 유틸리티 클래스
**목적**: Service 레이어의 반복적인 검증 코드 단순화

**구현 내용**:
- `com.farm404.samyang.util.ValidationUtils` 클래스 생성
- 공통 검증 메서드 구현

**예시**:
```java
public class ValidationUtils {
    public static String requireNonEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(fieldName + "은(는) 필수입니다.");
        }
        return value.trim();
    }
    
    public static <T> T requireNonNull(T value, String fieldName) {
        if (value == null) {
            throw new ValidationException(fieldName + "은(는) 필수입니다.");
        }
        return value;
    }
}
```

### 3. 커스텀 예외 클래스
**목적**: 더 명확한 에러 처리

**구현 내용**:
- `com.farm404.samyang.exception` 패키지 생성
- `ResourceNotFoundException`, `ValidationException`, `DuplicateException` 클래스 생성

## Phase 2: 핵심 리팩토링

### 4. BaseService 제네릭 클래스
**목적**: CRUD 메서드 중복 제거

**구현 내용**:
- `com.farm404.samyang.service.BaseService<T, ID>` 추상 클래스 생성
- 공통 CRUD 메서드를 제네릭으로 구현
- 각 Service는 BaseService를 상속

**예시**:
```java
public abstract class BaseService<T, ID> {
    protected abstract BaseMapper<T, ID> getMapper();
    
    public T findById(ID id) {
        return Optional.ofNullable(getMapper().selectById(id))
            .orElseThrow(() -> new ResourceNotFoundException("리소스를 찾을 수 없습니다."));
    }
    
    public List<T> findAll() {
        return getMapper().selectAll();
    }
}
```

### 5. Builder 패턴 적용
**목적**: 객체 생성 단순화

**구현 내용**:
- 모든 Entity와 DTO에 `@Builder` 어노테이션 추가
- Controller와 Service에서 Builder 패턴 사용

### 6. Stream API 활용
**목적**: 컬렉션 처리 코드 단순화

**구현 내용**:
- 모든 for 루프를 Stream API로 변환
- 함수형 프로그래밍 스타일 적용

## 예상 효과

### Before (UserController.getAllUsers)
```java
@GetMapping
public ResponseEntity<List<UserResponse>> getAllUsers() {
    List<User> users = userService.findAll();
    List<UserResponse> responses = new ArrayList<>();
    
    for (User user : users) {
        UserResponse response = new UserResponse();
        response.setUserID(user.getUserID());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        responses.add(response);
    }
    
    return ResponseEntity.ok(responses);
}
```

### After
```java
@GetMapping
public ResponseEntity<List<UserResponse>> getAllUsers() {
    return ResponseEntity.ok(UserDtoMapper.toResponseList(userService.findAll()));
}
```

## 구현 순서

1. DTO에 @Builder 추가 (5분)
2. Mapper 클래스 생성 (30분)
3. ValidationUtils 생성 (15분)
4. 커스텀 예외 클래스 생성 (10분)
5. Controller 리팩토링 (30분)
6. Service 리팩토링 (45분)
7. BaseService 도입 (30분)

**총 예상 시간**: 2시간 45분

## 성과 지표
- 코드 라인 수: 40-50% 감소
- 중복 코드: 80% 제거
- 새 엔티티 추가 시간: 70% 단축
- 가독성: 대폭 향상