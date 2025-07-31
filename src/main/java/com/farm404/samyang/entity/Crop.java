package com.farm404.samyang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Crop extends BaseEntity {
    private Integer CropID;
    private String CropName;
    private String ScientificName;
    private String Category;
    private String Description;
}