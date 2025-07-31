package com.farm404.samyang.mapper;

import com.farm404.samyang.entity.PaymentMethod;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMethodMapper {
    
    // 결제수단 하나 찾기
    PaymentMethod selectById(@Param("PaymentMethodID") Integer paymentMethodID);
    
    // 모든 결제수단 찾기
    List<PaymentMethod> selectAll();
    
    // 특정 사용자의 결제수단 찾기
    List<PaymentMethod> selectByUserID(@Param("UserID") Integer userID);
    
    // 특정 사용자의 기본 결제수단 찾기
    PaymentMethod selectDefaultByUserID(@Param("UserID") Integer userID);
    
    // 결제수단 추가
    int insert(PaymentMethod paymentMethod);
    
    // 결제수단 수정
    int update(PaymentMethod paymentMethod);
    
    // 결제수단 삭제
    int delete(@Param("PaymentMethodID") Integer paymentMethodID);
}