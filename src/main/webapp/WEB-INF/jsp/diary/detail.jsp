<%@ include file="../common/header.jsp" %>

<h2>영농일지 상세</h2>

<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>

<div style="background-color: #f9f9f9; padding: 20px; border-radius: 8px; margin-top: 20px;">
    <table style="width: 100%; border: none;">
        <tr>
            <td style="width: 150px; font-weight: bold; padding: 10px; border: none;">일지 ID:</td>
            <td style="padding: 10px; border: none;">${diary.diaryID}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">작성자:</td>
            <td style="padding: 10px; border: none;">${diary.userName}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">작물:</td>
            <td style="padding: 10px; border: none;">${diary.cropName}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">작업일:</td>
            <td style="padding: 10px; border: none;">${diary.workDate}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">날씨:</td>
            <td style="padding: 10px; border: none;">${diary.weather}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">온도:</td>
            <td style="padding: 10px; border: none;">${diary.temperature}°C</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">습도:</td>
            <td style="padding: 10px; border: none;">${diary.humidity}%</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">작업 내용:</td>
            <td style="padding: 10px; border: none;">
                <div style="white-space: pre-wrap;">${diary.workDescription}</div>
            </td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">메모:</td>
            <td style="padding: 10px; border: none;">
                <div style="white-space: pre-wrap;">${diary.notes}</div>
            </td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">이미지:</td>
            <td style="padding: 10px; border: none;">
                <c:if test="${not empty diary.imageURL}">
                    <img src="${diary.imageURL}" alt="작업 이미지" style="max-width: 400px; max-height: 300px;">
                </c:if>
                <c:if test="${empty diary.imageURL}">
                    이미지 없음
                </c:if>
            </td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">작성일:</td>
            <td style="padding: 10px; border: none;">${diary.createdAt}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">수정일:</td>
            <td style="padding: 10px; border: none;">${diary.updatedAt}</td>
        </tr>
    </table>
</div>

<div style="margin-top: 20px;">
    <a href="${pageContext.request.contextPath}/diaries/${diary.diaryID}/edit" class="btn">수정</a>
    <a href="${pageContext.request.contextPath}/diaries" class="btn btn-secondary">목록으로</a>
    
    <form action="${pageContext.request.contextPath}/diaries/${diary.diaryID}/delete" method="post" style="display: inline-block; margin-left: 10px;">
        <button type="submit" class="btn btn-danger" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</button>
    </form>
</div>

<%@ include file="../common/footer.jsp" %>