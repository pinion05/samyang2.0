package com.farm404.samyang.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodCreateRequest {
    
    @NotNull(message = "사용자 ID는 필수입니다.")
    private Integer userID;
    
    @NotBlank(message = "결제수단 타입은 필수입니다.")
    @Pattern(regexp = "card|bank_transfer|mobile", message = "결제수단 타입은 card, bank_transfer, mobile 중 하나여야 합니다.")
    private String methodType;
    
    @NotBlank(message = "결제수단 이름은 필수입니다.")
    @Size(max = 100, message = "결제수단 이름은 100자 이하여야 합니다.")
    private String methodName;
    
    @Size(max = 255, message = "계좌 정보는 255자 이하여야 합니다.")
    private String accountInfo;
    
    private Boolean isDefault;
}