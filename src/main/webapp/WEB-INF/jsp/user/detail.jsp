<%@ include file="../common/header.jsp" %>

<h2>사용자 상세 정보</h2>

<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>

<div style="background-color: #f9f9f9; padding: 20px; border-radius: 8px; margin-top: 20px;">
    <table style="width: 100%; border: none;">
        <tr>
            <td style="width: 150px; font-weight: bold; padding: 10px; border: none;">사용자 ID:</td>
            <td style="padding: 10px; border: none;">${user.userID}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">사용자명:</td>
            <td style="padding: 10px; border: none;">${user.username}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">이름:</td>
            <td style="padding: 10px; border: none;">${user.fullName}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">이메일:</td>
            <td style="padding: 10px; border: none;">${user.email}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">전화번호:</td>
            <td style="padding: 10px; border: none;">${user.phoneNumber}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">주소:</td>
            <td style="padding: 10px; border: none;">${user.address}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">역할:</td>
            <td style="padding: 10px; border: none;">${user.role}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">포인트:</td>
            <td style="padding: 10px; border: none;">${user.points}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">가입일:</td>
            <td style="padding: 10px; border: none;">${user.createdAt}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">수정일:</td>
            <td style="padding: 10px; border: none;">${user.updatedAt}</td>
        </tr>
    </table>
</div>

<div style="margin-top: 20px;">
    <a href="${pageContext.request.contextPath}/users/${user.userID}/edit" class="btn">수정</a>
    <a href="${pageContext.request.contextPath}/users" class="btn btn-secondary">목록으로</a>
    
    <form action="${pageContext.request.contextPath}/users/${user.userID}/delete" method="post" style="display: inline-block; margin-left: 10px;">
        <button type="submit" class="btn btn-danger" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</button>
    </form>
</div>

<%@ include file="../common/footer.jsp" %>