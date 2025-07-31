<%@ include file="common/header.jsp" %>

<h2>삼양 농장 관리 시스템에 오신 것을 환영합니다!</h2>

<div style="margin-top: 30px;">
    <h3>주요 기능</h3>
    <div style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 20px; margin-top: 20px;">
        <div style="border: 1px solid #ddd; padding: 20px; border-radius: 8px;">
            <h4>사용자 관리</h4>
            <p>농장 사용자들의 정보를 관리합니다.</p>
            <a href="${pageContext.request.contextPath}/users" class="btn">바로가기</a>
        </div>
        
        <div style="border: 1px solid #ddd; padding: 20px; border-radius: 8px;">
            <h4>작물 관리</h4>
            <p>재배 중인 작물 정보를 관리합니다.</p>
            <a href="${pageContext.request.contextPath}/crops" class="btn">바로가기</a>
        </div>
        
        <div style="border: 1px solid #ddd; padding: 20px; border-radius: 8px;">
            <h4>영농일지</h4>
            <p>일일 농사 기록을 관리합니다.</p>
            <a href="${pageContext.request.contextPath}/diaries" class="btn">바로가기</a>
        </div>
        
        <div style="border: 1px solid #ddd; padding: 20px; border-radius: 8px;">
            <h4>리뷰 관리</h4>
            <p>작물에 대한 리뷰를 관리합니다.</p>
            <a href="${pageContext.request.contextPath}/reviews" class="btn">바로가기</a>
        </div>
        
        <div style="border: 1px solid #ddd; padding: 20px; border-radius: 8px;">
            <h4>신고 관리</h4>
            <p>사용자 신고를 관리합니다.</p>
            <a href="${pageContext.request.contextPath}/reports" class="btn">바로가기</a>
        </div>
        
        <div style="border: 1px solid #ddd; padding: 20px; border-radius: 8px;">
            <h4>결제수단 관리</h4>
            <p>사용자 결제수단을 관리합니다.</p>
            <a href="${pageContext.request.contextPath}/payment-methods" class="btn">바로가기</a>
        </div>
    </div>
</div>

<%@ include file="common/footer.jsp" %>