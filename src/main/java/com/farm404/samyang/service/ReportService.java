package com.farm404.samyang.service;

import com.farm404.samyang.entity.Report;
import com.farm404.samyang.mapper.ReportMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReportService {
    
    private final ReportMapper reportMapper;
    
    // 생성자 주입
    public ReportService(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }
    
    // ID로 신고 찾기
    public Report findById(Integer reportID) {
        if (reportID == null) {
            return null;
        }
        return reportMapper.selectById(reportID);
    }
    
    // 모든 신고 조회
    public List<Report> findAll() {
        return reportMapper.selectAll();
    }
    
    // 특정 신고자의 신고 찾기
    public List<Report> findByReporterID(Integer reporterID) {
        if (reporterID == null) {
            return reportMapper.selectAll();
        }
        return reportMapper.selectByReporterID(reporterID);
    }
    
    // 특정 상태의 신고 찾기
    public List<Report> findByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            return reportMapper.selectAll();
        }
        return reportMapper.selectByStatus(status);
    }
    
    // 신고 생성
    public Report create(Report report) {
        if (report == null) {
            throw new IllegalArgumentException("신고 정보가 없습니다.");
        }
        
        // 필수 정보 확인
        if (report.getReporterID() == null) {
            throw new IllegalArgumentException("신고자 ID가 없습니다.");
        }
        if (report.getTargetType() == null || report.getTargetType().trim().isEmpty()) {
            throw new IllegalArgumentException("신고 대상 타입이 없습니다.");
        }
        if (report.getTargetID() == null) {
            throw new IllegalArgumentException("신고 대상 ID가 없습니다.");
        }
        if (report.getReason() == null || report.getReason().trim().isEmpty()) {
            throw new IllegalArgumentException("신고 사유가 없습니다.");
        }
        
        // 상태 기본값 설정
        if (report.getStatus() == null || report.getStatus().trim().isEmpty()) {
            report.setStatus("pending");
        }
        
        // 저장
        reportMapper.insert(report);
        return report;
    }
    
    // 신고 수정
    public Report update(Integer reportID, Report report) {
        if (reportID == null || report == null) {
            throw new IllegalArgumentException("수정할 정보가 없습니다.");
        }
        
        // 존재 확인
        Report existingReport = reportMapper.selectById(reportID);
        if (existingReport == null) {
            throw new IllegalArgumentException("신고를 찾을 수 없습니다.");
        }
        
        // ID 설정
        report.setReportID(reportID);
        
        // 수정
        reportMapper.update(report);
        return report;
    }
    
    // 신고 삭제
    public void delete(Integer reportID) {
        if (reportID == null) {
            throw new IllegalArgumentException("삭제할 신고 ID가 없습니다.");
        }
        
        // 존재 확인
        Report existingReport = reportMapper.selectById(reportID);
        if (existingReport == null) {
            throw new IllegalArgumentException("신고를 찾을 수 없습니다.");
        }
        
        // 삭제
        reportMapper.delete(reportID);
    }
}