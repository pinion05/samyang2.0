<%@ include file="../common/header.jsp" %>

<h2>신고 상세</h2>

<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>

<div style="background-color: #f9f9f9; padding: 20px; border-radius: 8px; margin-top: 20px;">
    <table style="width: 100%; border: none;">
        <tr>
            <td style="width: 150px; font-weight: bold; padding: 10px; border: none;">신고 ID:</td>
            <td style="padding: 10px; border: none;">${report.reportID}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">신고자:</td>
            <td style="padding: 10px; border: none;">${report.reporterName}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">대상 유형:</td>
            <td style="padding: 10px; border: none;">${report.targetType}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">대상 ID:</td>
            <td style="padding: 10px; border: none;">${report.targetID}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">신고 사유:</td>
            <td style="padding: 10px; border: none;">${report.reason}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">상세 설명:</td>
            <td style="padding: 10px; border: none;">
                <div style="white-space: pre-wrap;">${report.description}</div>
            </td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">처리 상태:</td>
            <td style="padding: 10px; border: none;">
                <c:choose>
                    <c:when test="${report.status == 'pending'}">
                        <span style="color: orange; font-weight: bold;">대기중</span>
                    </c:when>
                    <c:when test="${report.status == 'processing'}">
                        <span style="color: blue; font-weight: bold;">처리중</span>
                    </c:when>
                    <c:when test="${report.status == 'resolved'}">
                        <span style="color: green; font-weight: bold;">해결됨</span>
                    </c:when>
                    <c:when test="${report.status == 'rejected'}">
                        <span style="color: red; font-weight: bold;">반려됨</span>
                    </c:when>
                    <c:otherwise>
                        ${report.status}
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">신고일:</td>
            <td style="padding: 10px; border: none;">${report.createdAt}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">수정일:</td>
            <td style="padding: 10px; border: none;">${report.updatedAt}</td>
        </tr>
    </table>
</div>

<div style="margin-top: 20px;">
    <a href="${pageContext.request.contextPath}/reports/${report.reportID}/edit" class="btn">수정</a>
    <a href="${pageContext.request.contextPath}/reports" class="btn btn-secondary">목록으로</a>
    
    <form action="${pageContext.request.contextPath}/reports/${report.reportID}/delete" method="post" style="display: inline-block; margin-left: 10px;">
        <button type="submit" class="btn btn-danger" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</button>
    </form>
</div>

<%@ include file="../common/footer.jsp" %>