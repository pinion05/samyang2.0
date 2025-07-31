package com.farm404.samyang.controller;

import com.farm404.samyang.dto.request.ReportCreateRequest;
import com.farm404.samyang.dto.response.ReportResponse;
import com.farm404.samyang.entity.Report;
import com.farm404.samyang.entity.User;
import com.farm404.samyang.service.ReportService;
import com.farm404.samyang.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    
    private final ReportService reportService;
    private final UserService userService;
    
    // 생성자 주입
    public ReportController(ReportService reportService, UserService userService) {
        this.reportService = reportService;
        this.userService = userService;
    }
    
    // 모든 신고 조회
    @GetMapping
    public ResponseEntity<List<ReportResponse>> getAllReports(
            @RequestParam(required = false) Integer reporterID,
            @RequestParam(required = false) String status) {
        
        List<Report> reports;
        if (reporterID != null) {
            reports = reportService.findByReporterID(reporterID);
        } else if (status != null && !status.isEmpty()) {
            reports = reportService.findByStatus(status);
        } else {
            reports = reportService.findAll();
        }
        
        List<ReportResponse> responses = new ArrayList<>();
        for (Report report : reports) {
            ReportResponse response = convertToResponse(report);
            responses.add(response);
        }
        
        return ResponseEntity.ok(responses);
    }
    
    // ID로 신고 조회
    @GetMapping("/{id}")
    public ResponseEntity<ReportResponse> getReportById(@PathVariable Integer id) {
        Report report = reportService.findById(id);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        
        ReportResponse response = convertToResponse(report);
        return ResponseEntity.ok(response);
    }
    
    // 신고 생성
    @PostMapping
    public ResponseEntity<ReportResponse> createReport(@Valid @RequestBody ReportCreateRequest request) {
        Report report = new Report();
        report.setReporterID(request.getReporterID());
        report.setTargetType(request.getTargetType());
        report.setTargetID(request.getTargetID());
        report.setReason(request.getReason());
        report.setDescription(request.getDescription());
        report.setStatus(request.getStatus());
        
        Report createdReport = reportService.create(report);
        ReportResponse response = convertToResponse(createdReport);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    // 신고 수정
    @PutMapping("/{id}")
    public ResponseEntity<ReportResponse> updateReport(
            @PathVariable Integer id,
            @Valid @RequestBody ReportCreateRequest request) {
        
        Report report = new Report();
        report.setReporterID(request.getReporterID());
        report.setTargetType(request.getTargetType());
        report.setTargetID(request.getTargetID());
        report.setReason(request.getReason());
        report.setDescription(request.getDescription());
        report.setStatus(request.getStatus());
        
        Report updatedReport = reportService.update(id, report);
        ReportResponse response = convertToResponse(updatedReport);
        
        return ResponseEntity.ok(response);
    }
    
    // 신고 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Integer id) {
        reportService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    // Entity를 Response로 변환하는 헬퍼 메소드
    private ReportResponse convertToResponse(Report report) {
        ReportResponse response = new ReportResponse();
        response.setReportID(report.getReportID());
        response.setReporterID(report.getReporterID());
        response.setTargetType(report.getTargetType());
        response.setTargetID(report.getTargetID());
        response.setReason(report.getReason());
        response.setDescription(report.getDescription());
        response.setStatus(report.getStatus());
        response.setCreatedAt(report.getCreatedAt());
        response.setUpdatedAt(report.getUpdatedAt());
        
        // 신고자 이름 추가
        User reporter = userService.findById(report.getReporterID());
        if (reporter != null) {
            response.setReporterName(reporter.getFullName());
        }
        
        return response;
    }
}