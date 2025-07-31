package com.farm404.samyang.exception;

/**
 * 중복된 데이터가 있을 때 발생하는 예외
 */
public class DuplicateException extends RuntimeException {
    
    public DuplicateException(String message) {
        super(message);
    }
    
    public DuplicateException(String fieldName, String value) {
        super(String.format("이미 존재하는 %s입니다: %s", fieldName, value));
    }
}