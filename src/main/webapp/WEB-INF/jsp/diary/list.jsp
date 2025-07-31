<%@ include file="../common/header.jsp" %>

<h2>영농일지 관리</h2>

<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>

<c:if test="${not empty error}">
    <div class="alert alert-error">${error}</div>
</c:if>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/diaries/new" class="btn">새 일지 작성</a>
</div>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>작성자</th>
            <th>작물</th>
            <th>날씨</th>
            <th>온도</th>
            <th>습도</th>
            <th>작업일</th>
            <th>작성일</th>
            <th>관리</th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${empty diaries}">
                <tr>
                    <td colspan="9" style="text-align: center;">등록된 영농일지가 없습니다.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="diary" items="${diaries}">
                    <tr>
                        <td>${diary.diaryID}</td>
                        <td>${diary.userName}</td>
                        <td>${diary.cropName}</td>
                        <td>${diary.weather}</td>
                        <td>${diary.temperature}°C</td>
                        <td>${diary.humidity}%</td>
                        <td>${diary.workDate}</td>
                        <td>${diary.createdAt}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/diaries/${diary.diaryID}" class="btn" style="padding: 4px 8px; font-size: 12px;">상세</a>
                            <a href="${pageContext.request.contextPath}/diaries/${diary.diaryID}/edit" class="btn btn-secondary" style="padding: 4px 8px; font-size: 12px;">수정</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %>