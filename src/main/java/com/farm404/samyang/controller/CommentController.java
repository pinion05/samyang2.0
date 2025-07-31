package com.farm404.samyang.controller;

import com.farm404.samyang.dto.request.CommentCreateRequest;
import com.farm404.samyang.dto.response.CommentResponse;
import com.farm404.samyang.entity.Comment;
import com.farm404.samyang.entity.User;
import com.farm404.samyang.service.CommentService;
import com.farm404.samyang.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    
    private final CommentService commentService;
    private final UserService userService;
    
    // 생성자 주입
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }
    
    // 모든 댓글 조회
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getAllComments(
            @RequestParam(required = false) Integer postID,
            @RequestParam(required = false) Integer userID) {
        
        List<Comment> comments;
        if (postID != null) {
            comments = commentService.findByPostID(postID);
        } else if (userID != null) {
            comments = commentService.findByUserID(userID);
        } else {
            comments = commentService.findAll();
        }
        
        List<CommentResponse> responses = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponse response = convertToResponse(comment);
            responses.add(response);
        }
        
        return ResponseEntity.ok(responses);
    }
    
    // ID로 댓글 조회
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable Integer id) {
        Comment comment = commentService.findById(id);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        
        CommentResponse response = convertToResponse(comment);
        return ResponseEntity.ok(response);
    }
    
    // 댓글 생성
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@Valid @RequestBody CommentCreateRequest request) {
        Comment comment = new Comment();
        comment.setPostID(request.getPostID());
        comment.setUserID(request.getUserID());
        comment.setContent(request.getContent());
        
        Comment createdComment = commentService.create(comment);
        CommentResponse response = convertToResponse(createdComment);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Integer id,
            @Valid @RequestBody CommentCreateRequest request) {
        
        Comment comment = new Comment();
        comment.setPostID(request.getPostID());
        comment.setUserID(request.getUserID());
        comment.setContent(request.getContent());
        
        Comment updatedComment = commentService.update(id, comment);
        CommentResponse response = convertToResponse(updatedComment);
        
        return ResponseEntity.ok(response);
    }
    
    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    // Entity를 Response로 변환하는 헬퍼 메소드
    private CommentResponse convertToResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setCommentID(comment.getCommentID());
        response.setPostID(comment.getPostID());
        response.setUserID(comment.getUserID());
        response.setContent(comment.getContent());
        response.setCreatedAt(comment.getCreatedAt());
        response.setUpdatedAt(comment.getUpdatedAt());
        
        // 사용자 이름 추가
        User user = userService.findById(comment.getUserID());
        if (user != null) {
            response.setUserName(user.getFullName());
        }
        
        return response;
    }
}