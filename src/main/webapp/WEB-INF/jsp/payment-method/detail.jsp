<%@ include file="../common/header.jsp" %>

<h2>결제수단 상세</h2>

<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>

<div style="background-color: #f9f9f9; padding: 20px; border-radius: 8px; margin-top: 20px;">
    <table style="width: 100%; border: none;">
        <tr>
            <td style="width: 150px; font-weight: bold; padding: 10px; border: none;">결제수단 ID:</td>
            <td style="padding: 10px; border: none;">${paymentMethod.paymentMethodID}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">사용자:</td>
            <td style="padding: 10px; border: none;">${paymentMethod.userName}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">결제 방법:</td>
            <td style="padding: 10px; border: none;">${paymentMethod.methodType}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">수단명:</td>
            <td style="padding: 10px; border: none;">${paymentMethod.methodName}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">계좌 정보:</td>
            <td style="padding: 10px; border: none;">${paymentMethod.accountInfo}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">기본 설정:</td>
            <td style="padding: 10px; border: none;">
                <c:choose>
                    <c:when test="${paymentMethod.isDefault}">
                        <span style="color: green; font-weight: bold;">✓ 기본 결제수단</span>
                    </c:when>
                    <c:otherwise>
                        <span style="color: gray;">일반 결제수단</span>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">등록일:</td>
            <td style="padding: 10px; border: none;">${paymentMethod.createdAt}</td>
        </tr>
        <tr>
            <td style="font-weight: bold; padding: 10px; border: none;">수정일:</td>
            <td style="padding: 10px; border: none;">${paymentMethod.updatedAt}</td>
        </tr>
    </table>
</div>

<div style="margin-top: 20px;">
    <a href="${pageContext.request.contextPath}/payment-methods/${paymentMethod.paymentMethodID}/edit" class="btn">수정</a>
    <a href="${pageContext.request.contextPath}/payment-methods" class="btn btn-secondary">목록으로</a>
    
    <form action="${pageContext.request.contextPath}/payment-methods/${paymentMethod.paymentMethodID}/delete" method="post" style="display: inline-block; margin-left: 10px;">
        <button type="submit" class="btn btn-danger" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</button>
    </form>
</div>

<%@ include file="../common/footer.jsp" %>