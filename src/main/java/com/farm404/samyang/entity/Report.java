package com.farm404.samyang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Report extends BaseEntity {
    private Integer ReportID;
    private Integer ReporterID;
    private String TargetType;
    private Integer TargetID;
    private String Reason;
    private String Description;
    private String Status;
}