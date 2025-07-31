package com.farm404.samyang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaymentMethod extends BaseEntity {
    private Integer PaymentMethodID;
    private Integer UserID;
    private String MethodType;
    private String MethodName;
    private String AccountInfo;
    private Boolean IsDefault;
    
    // 화면 표시용 필드 (DB에는 저장되지 않음)
    private transient String userName;
    
    // MVC 컨트롤러에서 사용하는 필드명과 매핑하기 위한 getter/setter
    public Integer getPaymentMethodID() {
        return PaymentMethodID;
    }
    
    public void setPaymentMethodID(Integer paymentMethodID) {
        PaymentMethodID = paymentMethodID;
    }
}