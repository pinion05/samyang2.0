<%@ include file="../common/header.jsp" %>

<h2>${isEdit ? '결제수단 수정' : '새 결제수단 추가'}</h2>

<c:if test="${not empty error}">
    <div class="alert alert-error">${error}</div>
</c:if>

<form action="${pageContext.request.contextPath}/payment-methods${isEdit ? '/' : ''}${isEdit ? paymentMethod.paymentMethodID : ''}" method="post">
    <div class="form-group">
        <label for="userID">사용자 *</label>
        <select id="userID" name="userID" required ${isEdit ? 'disabled' : ''}>
            <option value="">선택하세요</option>
            <c:forEach var="user" items="${users}">
                <option value="${user.userID}" ${paymentMethod.userID == user.userID ? 'selected' : ''}>
                    ${user.fullName} (${user.username})
                </option>
            </c:forEach>
        </select>
        <c:if test="${isEdit}">
            <input type="hidden" name="userID" value="${paymentMethod.userID}">
        </c:if>
    </div>
    
    <div class="form-group">
        <label for="methodType">결제 방법 *</label>
        <select id="methodType" name="methodType" required>
            <option value="">선택하세요</option>
            <option value="신용카드" ${paymentMethod.methodType == '신용카드' ? 'selected' : ''}>신용카드</option>
            <option value="체크카드" ${paymentMethod.methodType == '체크카드' ? 'selected' : ''}>체크카드</option>
            <option value="계좌이체" ${paymentMethod.methodType == '계좌이체' ? 'selected' : ''}>계좌이체</option>
            <option value="무통장입금" ${paymentMethod.methodType == '무통장입금' ? 'selected' : ''}>무통장입금</option>
            <option value="간편결제" ${paymentMethod.methodType == '간편결제' ? 'selected' : ''}>간편결제</option>
        </select>
    </div>
    
    <div class="form-group">
        <label for="methodName">수단명 *</label>
        <input type="text" id="methodName" name="methodName" value="${paymentMethod.methodName}" required 
               placeholder="예: 국민카드, 농협계좌 등">
    </div>
    
    <div class="form-group">
        <label for="accountInfo">계좌 정보 *</label>
        <input type="text" id="accountInfo" name="accountInfo" value="${paymentMethod.accountInfo}" required 
               placeholder="예: **** **** **** 1234">
        <small style="color: #666;">카드번호나 계좌번호의 일부만 입력하세요 (보안상 전체 번호는 입력하지 마세요)</small>
    </div>
    
    <div class="form-group">
        <label for="isDefault">
            <input type="checkbox" id="isDefault" name="isDefault" value="true" 
                   ${paymentMethod.isDefault ? 'checked' : ''}>
            기본 결제수단으로 설정
        </label>
    </div>
    
    <div style="margin-top: 20px;">
        <button type="submit" class="btn">${isEdit ? '수정' : '등록'}</button>
        <a href="${pageContext.request.contextPath}/payment-methods" class="btn btn-secondary">취소</a>
    </div>
</form>

<%@ include file="../common/footer.jsp" %>