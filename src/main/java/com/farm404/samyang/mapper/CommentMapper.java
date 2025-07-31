package com.farm404.samyang.mapper;

import com.farm404.samyang.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    
    // 댓글 하나 찾기
    Comment selectById(@Param("CommentID") Integer commentID);
    
    // 모든 댓글 찾기
    List<Comment> selectAll();
    
    // 특정 게시물의 댓글 찾기
    List<Comment> selectByPostID(@Param("PostID") Integer postID);
    
    // 특정 사용자의 댓글 찾기
    List<Comment> selectByUserID(@Param("UserID") Integer userID);
    
    // 댓글 추가
    int insert(Comment comment);
    
    // 댓글 수정
    int update(Comment comment);
    
    // 댓글 삭제
    int delete(@Param("CommentID") Integer commentID);
}