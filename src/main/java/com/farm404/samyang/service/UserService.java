package com.farm404.samyang.service;

import com.farm404.samyang.entity.User;
import com.farm404.samyang.exception.DuplicateException;
import com.farm404.samyang.exception.ResourceNotFoundException;
import com.farm404.samyang.mapper.UserMapper;
import com.farm404.samyang.util.ValidationUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    
    private final UserMapper userMapper;
    
    // 생성자 주입
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    // ID로 사용자 찾기
    public User findById(Integer userID) {
        if (userID == null) {
            return null;
        }
        return userMapper.selectById(userID);
    }
    
    // 사용자명으로 찾기
    public User findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        return userMapper.selectByUsername(username);
    }
    
    // 모든 사용자 조회
    public List<User> findAll() {
        return userMapper.selectAll();
    }
    
    // 사용자 생성
    public User create(User user) {
        ValidationUtils.requireNonNull(user, "사용자 정보");
        
        // 필수 정보 확인
        ValidationUtils.requireNonEmpty(user.getUsername(), "사용자명");
        ValidationUtils.requireNonEmpty(user.getPassword(), "비밀번호");
        ValidationUtils.requireValidEmail(user.getEmail());
        ValidationUtils.requireNonEmpty(user.getFullName(), "이름");
        
        // 중복 확인
        User existingUser = userMapper.selectByUsername(user.getUsername());
        if (existingUser != null) {
            throw new DuplicateException("사용자명", user.getUsername());
        }
        
        // 저장
        userMapper.insert(user);
        return user;
    }
    
    // 사용자 수정
    public User update(Integer userID, User user) {
        ValidationUtils.requireValidId(userID, "사용자");
        ValidationUtils.requireNonNull(user, "수정할 정보");
        
        // 존재 확인
        User existingUser = userMapper.selectById(userID);
        if (existingUser == null) {
            throw new ResourceNotFoundException("사용자", userID);
        }
        
        // ID 설정
        user.setUserID(userID);
        
        // 수정
        userMapper.update(user);
        return user;
    }
    
    // 사용자 삭제
    public void delete(Integer userID) {
        ValidationUtils.requireValidId(userID, "사용자");
        
        // 존재 확인
        User existingUser = userMapper.selectById(userID);
        if (existingUser == null) {
            throw new ResourceNotFoundException("사용자", userID);
        }
        
        // 삭제
        userMapper.delete(userID);
    }
}