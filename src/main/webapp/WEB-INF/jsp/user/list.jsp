<%@ include file="../common/header.jsp" %>

<h2>사용자 관리</h2>

<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>

<c:if test="${not empty error}">
    <div class="alert alert-error">${error}</div>
</c:if>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/users/new" class="btn">새 사용자 추가</a>
</div>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>사용자명</th>
            <th>이름</th>
            <th>이메일</th>
            <th>전화번호</th>
            <th>역할</th>
            <th>가입일</th>
            <th>관리</th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${empty users}">
                <tr>
                    <td colspan="8" style="text-align: center;">등록된 사용자가 없습니다.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.userID}</td>
                        <td>${user.username}</td>
                        <td>${user.fullName}</td>
                        <td>${user.email}</td>
                        <td>${user.phoneNumber}</td>
                        <td>${user.role}</td>
                        <td>${user.createdAt}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/users/${user.userID}" class="btn" style="padding: 4px 8px; font-size: 12px;">상세</a>
                            <a href="${pageContext.request.contextPath}/users/${user.userID}/edit" class="btn btn-secondary" style="padding: 4px 8px; font-size: 12px;">수정</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %>