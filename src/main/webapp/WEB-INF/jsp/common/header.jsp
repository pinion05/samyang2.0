<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>삼양 농장 관리 시스템</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Malgun Gothic', sans-serif;
            background-color: #f5f5f5;
        }
        
        .header {
            background-color: #2e7d32;
            color: white;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        
        .header h1 {
            font-size: 24px;
            margin-bottom: 10px;
        }
        
        .nav {
            background-color: #388e3c;
            padding: 0;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        
        .nav ul {
            list-style: none;
            display: flex;
        }
        
        .nav li {
            position: relative;
        }
        
        .nav a {
            display: block;
            color: white;
            text-decoration: none;
            padding: 15px 20px;
            transition: background-color 0.3s;
        }
        
        .nav a:hover {
            background-color: #4caf50;
        }
        
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: white;
            min-height: calc(100vh - 200px);
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        
        .btn {
            display: inline-block;
            padding: 8px 16px;
            background-color: #4caf50;
            color: white;
            text-decoration: none;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            transition: background-color 0.3s;
        }
        
        .btn:hover {
            background-color: #45a049;
        }
        
        .btn-danger {
            background-color: #f44336;
        }
        
        .btn-danger:hover {
            background-color: #da190b;
        }
        
        .btn-secondary {
            background-color: #757575;
        }
        
        .btn-secondary:hover {
            background-color: #616161;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        
        tr:hover {
            background-color: #f5f5f5;
        }
        
        .form-group {
            margin-bottom: 15px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        
        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }
        
        .form-group textarea {
            resize: vertical;
            min-height: 100px;
        }
        
        .alert {
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 4px;
        }
        
        .alert-success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        
        .alert-error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>삼양 농장 관리 시스템</h1>
        <p>스마트 농업 플랫폼</p>
    </div>
    
    <nav class="nav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/">홈</a></li>
            <li><a href="${pageContext.request.contextPath}/users">사용자 관리</a></li>
            <li><a href="${pageContext.request.contextPath}/crops">작물 관리</a></li>
            <li><a href="${pageContext.request.contextPath}/diaries">영농일지</a></li>
            <li><a href="${pageContext.request.contextPath}/reviews">리뷰 관리</a></li>
            <li><a href="${pageContext.request.contextPath}/reports">신고 관리</a></li>
            <li><a href="${pageContext.request.contextPath}/payment-methods">결제수단 관리</a></li>
        </ul>
    </nav>
    
    <div class="container">