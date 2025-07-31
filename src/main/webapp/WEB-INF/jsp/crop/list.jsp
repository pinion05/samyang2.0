<%@ include file="../common/header.jsp" %>

<h2>작물 관리</h2>

<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>

<c:if test="${not empty error}">
    <div class="alert alert-error">${error}</div>
</c:if>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/crops/new" class="btn">새 작물 추가</a>
</div>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>작물명</th>
            <th>카테고리</th>
            <th>가격</th>
            <th>단위</th>
            <th>재고</th>
            <th>평점</th>
            <th>재배일수</th>
            <th>관리</th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${empty crops}">
                <tr>
                    <td colspan="9" style="text-align: center;">등록된 작물이 없습니다.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="crop" items="${crops}">
                    <tr>
                        <td>${crop.cropID}</td>
                        <td>${crop.cropName}</td>
                        <td>${crop.category}</td>
                        <td>${crop.price}원</td>
                        <td>${crop.unit}</td>
                        <td>${crop.stockQuantity}</td>
                        <td>${crop.rating}</td>
                        <td>${crop.growthDuration}일</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/crops/${crop.cropID}" class="btn" style="padding: 4px 8px; font-size: 12px;">상세</a>
                            <a href="${pageContext.request.contextPath}/crops/${crop.cropID}/edit" class="btn btn-secondary" style="padding: 4px 8px; font-size: 12px;">수정</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %>