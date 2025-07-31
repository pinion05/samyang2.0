package com.farm404.samyang.service;

import com.farm404.samyang.entity.PaymentMethod;
import com.farm404.samyang.mapper.PaymentMethodMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentMethodService {
    
    private final PaymentMethodMapper paymentMethodMapper;
    
    // 생성자 주입
    public PaymentMethodService(PaymentMethodMapper paymentMethodMapper) {
        this.paymentMethodMapper = paymentMethodMapper;
    }
    
    // ID로 결제수단 찾기
    public PaymentMethod findById(Integer paymentMethodID) {
        if (paymentMethodID == null) {
            return null;
        }
        return paymentMethodMapper.selectById(paymentMethodID);
    }
    
    // 모든 결제수단 조회
    public List<PaymentMethod> findAll() {
        return paymentMethodMapper.selectAll();
    }
    
    // 특정 사용자의 결제수단 찾기
    public List<PaymentMethod> findByUserID(Integer userID) {
        if (userID == null) {
            return paymentMethodMapper.selectAll();
        }
        return paymentMethodMapper.selectByUserID(userID);
    }
    
    // 특정 사용자의 기본 결제수단 찾기
    public PaymentMethod findDefaultByUserID(Integer userID) {
        if (userID == null) {
            return null;
        }
        return paymentMethodMapper.selectDefaultByUserID(userID);
    }
    
    // 결제수단 생성
    public PaymentMethod create(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("결제수단 정보가 없습니다.");
        }
        
        // 필수 정보 확인
        if (paymentMethod.getUserID() == null) {
            throw new IllegalArgumentException("사용자 ID가 없습니다.");
        }
        if (paymentMethod.getMethodType() == null || paymentMethod.getMethodType().trim().isEmpty()) {
            throw new IllegalArgumentException("결제수단 타입이 없습니다.");
        }
        if (paymentMethod.getMethodName() == null || paymentMethod.getMethodName().trim().isEmpty()) {
            throw new IllegalArgumentException("결제수단 이름이 없습니다.");
        }
        
        // 기본값 설정
        if (paymentMethod.getIsDefault() == null) {
            paymentMethod.setIsDefault(false);
        }
        
        // 저장
        paymentMethodMapper.insert(paymentMethod);
        return paymentMethod;
    }
    
    // 결제수단 수정
    public PaymentMethod update(Integer paymentMethodID, PaymentMethod paymentMethod) {
        if (paymentMethodID == null || paymentMethod == null) {
            throw new IllegalArgumentException("수정할 정보가 없습니다.");
        }
        
        // 존재 확인
        PaymentMethod existingMethod = paymentMethodMapper.selectById(paymentMethodID);
        if (existingMethod == null) {
            throw new IllegalArgumentException("결제수단을 찾을 수 없습니다.");
        }
        
        // ID 설정
        paymentMethod.setPaymentMethodID(paymentMethodID);
        
        // 수정
        paymentMethodMapper.update(paymentMethod);
        return paymentMethod;
    }
    
    // 결제수단 삭제
    public void delete(Integer paymentMethodID) {
        if (paymentMethodID == null) {
            throw new IllegalArgumentException("삭제할 결제수단 ID가 없습니다.");
        }
        
        // 존재 확인
        PaymentMethod existingMethod = paymentMethodMapper.selectById(paymentMethodID);
        if (existingMethod == null) {
            throw new IllegalArgumentException("결제수단을 찾을 수 없습니다.");
        }
        
        // 삭제
        paymentMethodMapper.delete(paymentMethodID);
    }
}