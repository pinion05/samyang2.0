package com.farm404.samyang.service;

import com.farm404.samyang.entity.User;
import com.farm404.samyang.mapper.UserMapper;
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
        if (user == null) {
            throw new IllegalArgumentException("사용자 정보가 없습니다.");
        }
        
        // 필수 정보 확인
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("사용자명이 없습니다.");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("비밀번호가 없습니다.");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("이메일이 없습니다.");
        }
        if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("이름이 없습니다.");
        }
        
        // 중복 확인
        User existingUser = userMapper.selectByUsername(user.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("이미 존재하는 사용자명입니다.");
        }
        
        // 저장
        userMapper.insert(user);
        return user;
    }
    
    // 사용자 수정
    public User update(Integer userID, User user) {
        if (userID == null || user == null) {
            throw new IllegalArgumentException("수정할 정보가 없습니다.");
        }
        
        // 존재 확인
        User existingUser = userMapper.selectById(userID);
        if (existingUser == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
        
        // ID 설정
        user.setUserID(userID);
        
        // 수정
        userMapper.update(user);
        return user;
    }
    
    // 사용자 삭제
    public void delete(Integer userID) {
        if (userID == null) {
            throw new IllegalArgumentException("삭제할 사용자 ID가 없습니다.");
        }
        
        // 존재 확인
        User existingUser = userMapper.selectById(userID);
        if (existingUser == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
        
        // 삭제
        userMapper.delete(userID);
    }
}