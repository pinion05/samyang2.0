<%@ include file="../common/header.jsp" %>

<h2>결제수단 관리</h2>

<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>

<c:if test="${not empty error}">
    <div class="alert alert-error">${error}</div>
</c:if>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/payment-methods/new" class="btn">새 결제수단 추가</a>
</div>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>사용자</th>
            <th>결제 방법</th>
            <th>수단명</th>
            <th>계좌 정보</th>
            <th>기본 설정</th>
            <th>등록일</th>
            <th>관리</th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${empty paymentMethods}">
                <tr>
                    <td colspan="8" style="text-align: center;">등록된 결제수단이 없습니다.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="paymentMethod" items="${paymentMethods}">
                    <tr>
                        <td>${paymentMethod.paymentMethodID}</td>
                        <td>${paymentMethod.userName}</td>
                        <td>${paymentMethod.methodType}</td>
                        <td>${paymentMethod.methodName}</td>
                        <td>${paymentMethod.accountInfo}</td>
                        <td>
                            <c:choose>
                                <c:when test="${paymentMethod.isDefault}">
                                    <span style="color: green;">✓ 기본</span>
                                </c:when>
                                <c:otherwise>
                                    -
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${paymentMethod.createdAt}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/payment-methods/${paymentMethod.paymentMethodID}" class="btn" style="padding: 4px 8px; font-size: 12px;">상세</a>
                            <a href="${pageContext.request.contextPath}/payment-methods/${paymentMethod.paymentMethodID}/edit" class="btn btn-secondary" style="padding: 4px 8px; font-size: 12px;">수정</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %>