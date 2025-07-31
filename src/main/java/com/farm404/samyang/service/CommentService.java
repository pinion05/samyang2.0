package com.farm404.samyang.service;

import com.farm404.samyang.entity.Comment;
import com.farm404.samyang.mapper.CommentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentService {
    
    private final CommentMapper commentMapper;
    
    // 생성자 주입
    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }
    
    // ID로 댓글 찾기
    public Comment findById(Integer commentID) {
        if (commentID == null) {
            return null;
        }
        return commentMapper.selectById(commentID);
    }
    
    // 모든 댓글 조회
    public List<Comment> findAll() {
        return commentMapper.selectAll();
    }
    
    // 특정 게시물의 댓글 찾기
    public List<Comment> findByPostID(Integer postID) {
        if (postID == null) {
            return commentMapper.selectAll();
        }
        return commentMapper.selectByPostID(postID);
    }
    
    // 특정 사용자의 댓글 찾기
    public List<Comment> findByUserID(Integer userID) {
        if (userID == null) {
            return commentMapper.selectAll();
        }
        return commentMapper.selectByUserID(userID);
    }
    
    // 댓글 생성
    public Comment create(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("댓글 정보가 없습니다.");
        }
        
        // 필수 정보 확인
        if (comment.getPostID() == null) {
            throw new IllegalArgumentException("게시물 ID가 없습니다.");
        }
        if (comment.getUserID() == null) {
            throw new IllegalArgumentException("사용자 ID가 없습니다.");
        }
        if (comment.getContent() == null || comment.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("댓글 내용이 없습니다.");
        }
        
        // 저장
        commentMapper.insert(comment);
        return comment;
    }
    
    // 댓글 수정
    public Comment update(Integer commentID, Comment comment) {
        if (commentID == null || comment == null) {
            throw new IllegalArgumentException("수정할 정보가 없습니다.");
        }
        
        // 존재 확인
        Comment existingComment = commentMapper.selectById(commentID);
        if (existingComment == null) {
            throw new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        }
        
        // ID 설정
        comment.setCommentID(commentID);
        
        // 수정
        commentMapper.update(comment);
        return comment;
    }
    
    // 댓글 삭제
    public void delete(Integer commentID) {
        if (commentID == null) {
            throw new IllegalArgumentException("삭제할 댓글 ID가 없습니다.");
        }
        
        // 존재 확인
        Comment existingComment = commentMapper.selectById(commentID);
        if (existingComment == null) {
            throw new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        }
        
        // 삭제
        commentMapper.delete(commentID);
    }
}