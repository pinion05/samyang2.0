package com.farm404.samyang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
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
}