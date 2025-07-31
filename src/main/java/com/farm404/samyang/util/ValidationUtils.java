package com.farm404.samyang.util;

import com.farm404.samyang.exception.ValidationException;

public class ValidationUtils {
    
    /**
     * 문자열이 null이거나 비어있는지 확인하고, 비어있으면 예외를 던집니다.
     * 
     * @param value 검증할 문자열
     * @param fieldName 필드명 (에러 메시지에 사용)
     * @return trim된 문자열
     * @throws ValidationException 값이 null이거나 비어있을 때
     */
    public static String requireNonEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(fieldName + "은(는) 필수입니다.");
        }
        return value.trim();
    }
    
    /**
     * 객체가 null인지 확인하고, null이면 예외를 던집니다.
     * 
     * @param value 검증할 객체
     * @param fieldName 필드명 (에러 메시지에 사용)
     * @return 입력받은 객체
     * @throws ValidationException 값이 null일 때
     */
    public static <T> T requireNonNull(T value, String fieldName) {
        if (value == null) {
            throw new ValidationException(fieldName + "은(는) 필수입니다.");
        }
        return value;
    }
    
    /**
     * ID가 유효한지 확인합니다. (null이 아니고 양수)
     * 
     * @param id 검증할 ID
     * @param entityName 엔티티명 (에러 메시지에 사용)
     * @return 입력받은 ID
     * @throws ValidationException ID가 null이거나 0 이하일 때
     */
    public static Integer requireValidId(Integer id, String entityName) {
        if (id == null || id <= 0) {
            throw new ValidationException(entityName + " ID가 유효하지 않습니다.");
        }
        return id;
    }
    
    /**
     * 이메일 형식이 유효한지 확인합니다.
     * 
     * @param email 검증할 이메일
     * @return 입력받은 이메일
     * @throws ValidationException 이메일 형식이 올바르지 않을 때
     */
    public static String requireValidEmail(String email) {
        requireNonEmpty(email, "이메일");
        
        // 간단한 이메일 정규식
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.matches(emailRegex)) {
            throw new ValidationException("올바른 이메일 형식이 아닙니다.");
        }
        
        return email;
    }
}