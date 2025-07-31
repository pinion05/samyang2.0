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
    
    // 화면 표시용 필드 (DB에는 저장되지 않음)
    private transient String reporterName;
    
    // MVC 컨트롤러에서 사용하는 필드명과 매핑하기 위한 getter/setter
    public Integer getReportID() {
        return ReportID;
    }
    
    public void setReportID(Integer reportID) {
        ReportID = reportID;
    }
}