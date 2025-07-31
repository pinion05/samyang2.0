<%@ include file="../common/header.jsp" %>

<h2>작물 상세 정보</h2>

<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>

<div style="background-color: #f9f9f9; padding: 20px; border-radius: 8px; margin-top: 20px;">
    <table style="width: 100%; border: none;">
        <tr>
            <td style="width: 150px; font-weight: bold; padding: 10px; border: none;">작물 ID:</td>
            <td style="padding: 10px; border: none;">${crop.cropID}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">작물명:</td>
            <td style="padding: 10px; border: none;">${crop.cropName}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">카테고리:</td>
            <td style="padding: 10px; border: none;">${crop.category}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">가격:</td>
            <td style="padding: 10px; border: none;">${crop.price}원</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">단위:</td>
            <td style="padding: 10px; border: none;">${crop.unit}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">재고:</td>
            <td style="padding: 10px; border: none;">${crop.stockQuantity}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">평점:</td>
            <td style="padding: 10px; border: none;">${crop.rating}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">재배일수:</td>
            <td style="padding: 10px; border: none;">${crop.growthDuration}일</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">설명:</td>
            <td style="padding: 10px; border: none;">${crop.description}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">등록일:</td>
            <td style="padding: 10px; border: none;">${crop.createdAt}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">수정일:</td>
            <td style="padding: 10px; border: none;">${crop.updatedAt}</td>
        </tr>
    </table>
</div>

<div style="margin-top: 20px;">
    <a href="${pageContext.request.contextPath}/crops/${crop.cropID}/edit" class="btn">수정</a>
    <a href="${pageContext.request.contextPath}/crops" class="btn btn-secondary">목록으로</a>
    
    <form action="${pageContext.request.contextPath}/crops/${crop.cropID}/delete" method="post" style="display: inline-block; margin-left: 10px;">
        <button type="submit" class="btn btn-danger" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</button>
    </form>
</div>

<%@ include file="../common/footer.jsp" %>