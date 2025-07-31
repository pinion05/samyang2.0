<%@ include file="../common/header.jsp" %>

<h2>신고 관리</h2>

<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>

<c:if test="${not empty error}">
    <div class="alert alert-error">${error}</div>
</c:if>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/reports/new" class="btn">새 신고 접수</a>
</div>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>신고자</th>
            <th>대상 유형</th>
            <th>대상 ID</th>
            <th>사유</th>
            <th>상태</th>
            <th>신고일</th>
            <th>관리</th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${empty reports}">
                <tr>
                    <td colspan="8" style="text-align: center;">접수된 신고가 없습니다.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="report" items="${reports}">
                    <tr>
                        <td>${report.reportID}</td>
                        <td>${report.reporterName}</td>
                        <td>${report.targetType}</td>
                        <td>${report.targetID}</td>
                        <td>${report.reason}</td>
                        <td>
                            <c:choose>
                                <c:when test="${report.status == 'pending'}">
                                    <span style="color: orange;">대기중</span>
                                </c:when>
                                <c:when test="${report.status == 'processing'}">
                                    <span style="color: blue;">처리중</span>
                                </c:when>
                                <c:when test="${report.status == 'resolved'}">
                                    <span style="color: green;">해결됨</span>
                                </c:when>
                                <c:when test="${report.status == 'rejected'}">
                                    <span style="color: red;">반려됨</span>
                                </c:when>
                                <c:otherwise>
                                    ${report.status}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${report.createdAt}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/reports/${report.reportID}" class="btn" style="padding: 4px 8px; font-size: 12px;">상세</a>
                            <a href="${pageContext.request.contextPath}/reports/${report.reportID}/edit" class="btn btn-secondary" style="padding: 4px 8px; font-size: 12px;">수정</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %>