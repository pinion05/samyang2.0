package com.farm404.samyang.mapper;

import com.farm404.samyang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    
    // 사용자 하나 찾기
    User selectById(@Param("UserID") Integer userID);
    
    // 사용자 이름으로 찾기
    User selectByUsername(@Param("Username") String username);
    
    // 모든 사용자 찾기
    List<User> selectAll();
    
    // 사용자 추가
    int insert(User user);
    
    // 사용자 수정
    int update(User user);
    
    // 사용자 삭제
    int delete(@Param("UserID") Integer userID);
}