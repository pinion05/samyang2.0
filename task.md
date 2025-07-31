# Samyang 2.0 개발 Task

## 개발 순서
1. 프로젝트 초기 설정
2. 데이터베이스 설정
3. Entity 개발
4. DTO 개발
5. Mapper 개발
6. Service 개발
7. Controller 개발
8. 테스트 작성
9. 문서화

## Phase 1: 프로젝트 초기 설정

### Task 1.1: Spring Boot 프로젝트 생성
- [ ] Spring Initializr로 프로젝트 생성
- [ ] 기본 디렉토리 구조 생성
- [ ] .gitignore 설정

### Task 1.2: build.gradle 설정
```gradle
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.5.3'
    id 'io.spring.dependency-management' version '1.1.7'
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.5'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    
    runtimeOnly 'com.mysql:mysql-connector-j'
    
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

### Task 1.3: application.yml 설정
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/samyang2?useSSL=false&serverTimezone=Asia/Seoul
    username: root
    password: ${DB_PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver
  
mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.farm404.samyang.entity
  configuration:
    map-underscore-to-camel-case: false
    
logging:
  level:
    com.farm404.samyang.mapper: DEBUG
```

## Phase 2: 데이터베이스 설정

### Task 2.1: 데이터베이스 생성
```sql
CREATE DATABASE samyang2 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### Task 2.2: 테이블 생성 (schema.sql)
- [ ] User 테이블
- [ ] Crop 테이블
- [ ] FarmingDiary 테이블
- [ ] Comment 테이블
- [ ] Review 테이블
- [ ] Report 테이블
- [ ] PaymentMethod 테이블

## Phase 3: Entity 개발

### Task 3.1: BaseEntity 생성
```java
@Data
public abstract class BaseEntity {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
```

### Task 3.2: 각 Entity 클래스 생성
- [ ] User.java
- [ ] Crop.java
- [ ] FarmingDiary.java
- [ ] Comment.java
- [ ] Review.java
- [ ] Report.java
- [ ] PaymentMethod.java

## Phase 4: DTO 개발

### Task 4.1: 공통 DTO 생성
- [ ] PageRequest.java
- [ ] PageResponse.java
- [ ] ErrorResponse.java

### Task 4.2: 요청 DTO 생성
- [ ] UserCreateRequest.java
- [ ] UserUpdateRequest.java
- [ ] CropCreateRequest.java
- [ ] CropUpdateRequest.java
- [ ] FarmingDiaryCreateRequest.java
- [ ] FarmingDiaryUpdateRequest.java
- [ ] CommentCreateRequest.java
- [ ] CommentUpdateRequest.java
- [ ] ReviewCreateRequest.java
- [ ] ReviewUpdateRequest.java
- [ ] ReportCreateRequest.java
- [ ] PaymentMethodCreateRequest.java

### Task 4.3: 응답 DTO 생성
- [ ] UserResponse.java
- [ ] CropResponse.java
- [ ] FarmingDiaryResponse.java
- [ ] CommentResponse.java
- [ ] ReviewResponse.java
- [ ] ReportResponse.java
- [ ] PaymentMethodResponse.java

## Phase 5: Mapper 개발

### Task 5.1: Mapper 인터페이스 생성
- [ ] UserMapper.java
- [ ] CropMapper.java
- [ ] FarmingDiaryMapper.java
- [ ] CommentMapper.java
- [ ] ReviewMapper.java
- [ ] ReportMapper.java
- [ ] PaymentMethodMapper.java

### Task 5.2: Mapper XML 작성
각 Mapper별 CRUD 쿼리 작성:
- [ ] selectById
- [ ] selectAll
- [ ] insert
- [ ] update
- [ ] delete

## Phase 6: Service 개발

### Task 6.1: Service 인터페이스 정의
```java
public interface CrudService<T, ID> {
    T findById(ID id);
    List<T> findAll();
    T create(T entity);
    T update(ID id, T entity);
    void delete(ID id);
}
```

### Task 6.2: 각 Service 구현체 작성
- [ ] UserService
- [ ] CropService
- [ ] FarmingDiaryService
- [ ] CommentService
- [ ] ReviewService
- [ ] ReportService
- [ ] PaymentMethodService

## Phase 7: Controller 개발

### Task 7.1: BaseController 생성
```java
public abstract class BaseController<T, ID> {
    protected abstract CrudService<T, ID> getService();
    
    @GetMapping
    public List<T> findAll() { ... }
    
    @GetMapping("/{id}")
    public T findById(@PathVariable ID id) { ... }
    
    @PostMapping
    public T create(@Valid @RequestBody T entity) { ... }
    
    @PutMapping("/{id}")
    public T update(@PathVariable ID id, @Valid @RequestBody T entity) { ... }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) { ... }
}
```

### Task 7.2: 각 Controller 구현
- [ ] UserController
- [ ] CropController
- [ ] FarmingDiaryController
- [ ] CommentController
- [ ] ReviewController
- [ ] ReportController
- [ ] PaymentMethodController

### Task 7.3: Exception Handler 구현
- [ ] GlobalExceptionHandler
- [ ] Custom Exception 클래스들

## Phase 8: 테스트 작성

### Task 8.1: 단위 테스트
- [ ] Service Layer 테스트
- [ ] Mapper 테스트

### Task 8.2: 통합 테스트
- [ ] Controller 테스트 (@WebMvcTest)
- [ ] 전체 통합 테스트 (@SpringBootTest)

### Task 8.3: 테스트 데이터 준비
- [ ] test/resources/data.sql
- [ ] TestDataBuilder 클래스

## Phase 9: 추가 기능

### Task 9.1: 보안 설정
- [ ] SecurityConfig
- [ ] 패스워드 암호화
- [ ] JWT 토큰 (선택사항)

### Task 9.2: 페이징 처리
- [ ] PageRequest DTO
- [ ] PageResponse DTO
- [ ] 페이징 쿼리 추가

### Task 9.3: 로깅 및 모니터링
- [ ] 로깅 설정
- [ ] API 요청/응답 로깅
- [ ] 성능 모니터링

## Phase 10: 문서화

### Task 10.1: API 문서
- [ ] Swagger/OpenAPI 설정
- [ ] API 명세서 작성

### Task 10.2: 프로젝트 문서
- [ ] README.md
- [ ] 설치 가이드
- [ ] 개발 가이드

## 개발 우선순위

### 1단계 (핵심 기능)
1. User CRUD
2. FarmingDiary CRUD
3. Comment CRUD

### 2단계 (확장 기능)
1. Crop CRUD
2. Review CRUD
3. Report CRUD

### 3단계 (부가 기능)
1. PaymentMethod CRUD
2. 검색 기능
3. 페이징/정렬

## 주의사항

### 필수 확인 사항
1. **Entity와 DB 스키마 일치**: 컬럼명 대소문자까지 정확히 일치
2. **MyBatis 매핑**: resultMap 명시적 정의
3. **Spring Boot 3.x**: jakarta 패키지 사용
4. **타입 일치**: LocalDateTime, LocalDate 사용
5. **NOT NULL 제약**: 필수 필드 검증

### 개발 원칙
1. **단순함 유지**: 복잡한 기능보다 안정성 우선
2. **테스트 우선**: 기능 개발 후 즉시 테스트
3. **일관성**: 명명 규칙, 코드 스타일 통일
4. **문서화**: 주요 결정사항 기록

## 예상 일정
- Phase 1-2: 1일
- Phase 3-4: 2일
- Phase 5-6: 2일
- Phase 7-8: 3일
- Phase 9-10: 2일
- **총 예상 기간**: 10일