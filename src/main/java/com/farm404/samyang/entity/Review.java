package com.farm404.samyang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Review extends BaseEntity {
    private Integer ReviewID;
    private Integer UserID;
    private Integer CropID;
    private Integer Rating;
    private String Title;
    private String Content;
}