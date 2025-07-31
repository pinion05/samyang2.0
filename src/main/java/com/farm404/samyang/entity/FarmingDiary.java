package com.farm404.samyang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FarmingDiary extends BaseEntity {
    private Integer DiaryID;
    private Integer UserID;
    private Integer CropID;
    private String Title;
    private String Content;
    private String WeatherCondition;
    private BigDecimal Temperature;
    private BigDecimal Humidity;
    private LocalDate WorkDate;
}