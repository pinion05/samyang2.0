<%@ include file="../common/header.jsp" %>

<h2>리뷰 관리</h2>

<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>

<c:if test="${not empty error}">
    <div class="alert alert-error">${error}</div>
</c:if>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/reviews/new" class="btn">새 리뷰 작성</a>
</div>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>작성자</th>
            <th>작물</th>
            <th>평점</th>
            <th>제목</th>
            <th>작성일</th>
            <th>관리</th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${empty reviews}">
                <tr>
                    <td colspan="7" style="text-align: center;">등록된 리뷰가 없습니다.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="review" items="${reviews}">
                    <tr>
                        <td>${review.reviewID}</td>
                        <td>${review.userName}</td>
                        <td>${review.cropName}</td>
                        <td>
                            <c:forEach begin="1" end="${review.rating}">
                                ⭐
                            </c:forEach>
                        </td>
                        <td>${review.title}</td>
                        <td>${review.createdAt}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/reviews/${review.reviewID}" class="btn" style="padding: 4px 8px; font-size: 12px;">상세</a>
                            <a href="${pageContext.request.contextPath}/reviews/${review.reviewID}/edit" class="btn btn-secondary" style="padding: 4px 8px; font-size: 12px;">수정</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %>