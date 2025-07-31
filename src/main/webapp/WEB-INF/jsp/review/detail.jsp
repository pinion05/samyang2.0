<%@ include file="../common/header.jsp" %>

<h2>리뷰 상세</h2>

<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>

<div style="background-color: #f9f9f9; padding: 20px; border-radius: 8px; margin-top: 20px;">
    <table style="width: 100%; border: none;">
        <tr>
            <td style="width: 150px; font-weight: bold; padding: 10px; border: none;">리뷰 ID:</td>
            <td style="padding: 10px; border: none;">${review.reviewID}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">작성자:</td>
            <td style="padding: 10px; border: none;">${review.userName}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">작물:</td>
            <td style="padding: 10px; border: none;">${review.cropName}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">평점:</td>
            <td style="padding: 10px; border: none;">
                <c:forEach begin="1" end="5">
                    <c:choose>
                        <c:when test="${review.rating >= pageScope.current}">
                            ⭐
                        </c:when>
                        <c:otherwise>
                            ☆
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                (${review.rating}/5)
            </td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">제목:</td>
            <td style="padding: 10px; border: none;">${review.title}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">내용:</td>
            <td style="padding: 10px; border: none;">
                <div style="white-space: pre-wrap;">${review.content}</div>
            </td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">작성일:</td>
            <td style="padding: 10px; border: none;">${review.createdAt}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">수정일:</td>
            <td style="padding: 10px; border: none;">${review.updatedAt}</td>
        </tr>
    </table>
</div>

<div style="margin-top: 20px;">
    <a href="${pageContext.request.contextPath}/reviews/${review.reviewID}/edit" class="btn">수정</a>
    <a href="${pageContext.request.contextPath}/reviews" class="btn btn-secondary">목록으로</a>
    
    <form action="${pageContext.request.contextPath}/reviews/${review.reviewID}/delete" method="post" style="display: inline-block; margin-left: 10px;">
        <button type="submit" class="btn btn-danger" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</button>
    </form>
</div>

<%@ include file="../common/footer.jsp" %>