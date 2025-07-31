package com.farm404.samyang.exception;

/**
 * 리소스를 찾을 수 없을 때 발생하는 예외
 */
public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public ResourceNotFoundException(String resourceName, Integer id) {
        super(String.format("%s(ID: %d)를 찾을 수 없습니다.", resourceName, id));
    }
    
    public ResourceNotFoundException(String resourceName, String identifier) {
        super(String.format("%s(%s)를 찾을 수 없습니다.", resourceName, identifier));
    }
}