package com.farm404.samyang.controller;

import com.farm404.samyang.dto.request.PaymentMethodCreateRequest;
import com.farm404.samyang.dto.response.PaymentMethodResponse;
import com.farm404.samyang.entity.PaymentMethod;
import com.farm404.samyang.entity.User;
import com.farm404.samyang.service.PaymentMethodService;
import com.farm404.samyang.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {
    
    private final PaymentMethodService paymentMethodService;
    private final UserService userService;
    
    // 생성자 주입
    public PaymentMethodController(PaymentMethodService paymentMethodService, UserService userService) {
        this.paymentMethodService = paymentMethodService;
        this.userService = userService;
    }
    
    // 모든 결제수단 조회
    @GetMapping
    public ResponseEntity<List<PaymentMethodResponse>> getAllPaymentMethods(
            @RequestParam(required = false) Integer userID) {
        
        List<PaymentMethod> paymentMethods;
        if (userID != null) {
            paymentMethods = paymentMethodService.findByUserID(userID);
        } else {
            paymentMethods = paymentMethodService.findAll();
        }
        
        List<PaymentMethodResponse> responses = new ArrayList<>();
        for (PaymentMethod paymentMethod : paymentMethods) {
            PaymentMethodResponse response = convertToResponse(paymentMethod);
            responses.add(response);
        }
        
        return ResponseEntity.ok(responses);
    }
    
    // ID로 결제수단 조회
    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodResponse> getPaymentMethodById(@PathVariable Integer id) {
        PaymentMethod paymentMethod = paymentMethodService.findById(id);
        if (paymentMethod == null) {
            return ResponseEntity.notFound().build();
        }
        
        PaymentMethodResponse response = convertToResponse(paymentMethod);
        return ResponseEntity.ok(response);
    }
    
    // 사용자의 기본 결제수단 조회
    @GetMapping("/users/{userID}/default")
    public ResponseEntity<PaymentMethodResponse> getDefaultPaymentMethod(@PathVariable Integer userID) {
        PaymentMethod paymentMethod = paymentMethodService.findDefaultByUserID(userID);
        if (paymentMethod == null) {
            return ResponseEntity.notFound().build();
        }
        
        PaymentMethodResponse response = convertToResponse(paymentMethod);
        return ResponseEntity.ok(response);
    }
    
    // 결제수단 생성
    @PostMapping
    public ResponseEntity<PaymentMethodResponse> createPaymentMethod(@Valid @RequestBody PaymentMethodCreateRequest request) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setUserID(request.getUserID());
        paymentMethod.setMethodType(request.getMethodType());
        paymentMethod.setMethodName(request.getMethodName());
        paymentMethod.setAccountInfo(request.getAccountInfo());
        paymentMethod.setIsDefault(request.getIsDefault());
        
        PaymentMethod createdPaymentMethod = paymentMethodService.create(paymentMethod);
        PaymentMethodResponse response = convertToResponse(createdPaymentMethod);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    // 결제수단 수정
    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodResponse> updatePaymentMethod(
            @PathVariable Integer id,
            @Valid @RequestBody PaymentMethodCreateRequest request) {
        
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setUserID(request.getUserID());
        paymentMethod.setMethodType(request.getMethodType());
        paymentMethod.setMethodName(request.getMethodName());
        paymentMethod.setAccountInfo(request.getAccountInfo());
        paymentMethod.setIsDefault(request.getIsDefault());
        
        PaymentMethod updatedPaymentMethod = paymentMethodService.update(id, paymentMethod);
        PaymentMethodResponse response = convertToResponse(updatedPaymentMethod);
        
        return ResponseEntity.ok(response);
    }
    
    // 결제수단 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable Integer id) {
        paymentMethodService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    // Entity를 Response로 변환하는 헬퍼 메소드
    private PaymentMethodResponse convertToResponse(PaymentMethod paymentMethod) {
        PaymentMethodResponse response = new PaymentMethodResponse();
        response.setPaymentMethodID(paymentMethod.getPaymentMethodID());
        response.setUserID(paymentMethod.getUserID());
        response.setMethodType(paymentMethod.getMethodType());
        response.setMethodName(paymentMethod.getMethodName());
        response.setAccountInfo(paymentMethod.getAccountInfo());
        response.setIsDefault(paymentMethod.getIsDefault());
        response.setCreatedAt(paymentMethod.getCreatedAt());
        response.setUpdatedAt(paymentMethod.getUpdatedAt());
        
        // 사용자 이름 추가
        User user = userService.findById(paymentMethod.getUserID());
        if (user != null) {
            response.setUserName(user.getFullName());
        }
        
        return response;
    }
}