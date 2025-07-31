package com.farm404.samyang.mapper;

import com.farm404.samyang.entity.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportMapper {
    
    // 신고 하나 찾기
    Report selectById(@Param("ReportID") Integer reportID);
    
    // 모든 신고 찾기
    List<Report> selectAll();
    
    // 특정 신고자의 신고 찾기
    List<Report> selectByReporterID(@Param("ReporterID") Integer reporterID);
    
    // 특정 상태의 신고 찾기
    List<Report> selectByStatus(@Param("Status") String status);
    
    // 신고 추가
    int insert(Report report);
    
    // 신고 수정
    int update(Report report);
    
    // 신고 삭제
    int delete(@Param("ReportID") Integer reportID);
}