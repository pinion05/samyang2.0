<%@ include file="../common/header.jsp" %>

<h2>${isEdit ? '사용자 수정' : '새 사용자 추가'}</h2>

<c:if test="${not empty error}">
    <div class="alert alert-error">${error}</div>
</c:if>

<form action="${pageContext.request.contextPath}/users${isEdit ? '/' : ''}${isEdit ? user.userID : ''}" method="post">
    <div class="form-group">
        <label for="username">사용자명 *</label>
        <input type="text" id="username" name="username" value="${user.username}" required 
               ${isEdit ? 'readonly' : ''} style="${isEdit ? 'background-color: #f0f0f0;' : ''}">
    </div>
    
    <div class="form-group">
        <label for="password">비밀번호 ${isEdit ? '' : '*'}</label>
        <input type="password" id="password" name="password" ${isEdit ? '' : 'required'}>
        <c:if test="${isEdit}">
            <small style="color: #666;">비밀번호를 변경하려면 입력하세요. 비워두면 기존 비밀번호가 유지됩니다.</small>
        </c:if>
    </div>
    
    <div class="form-group">
        <label for="fullName">이름 *</label>
        <input type="text" id="fullName" name="fullName" value="${user.fullName}" required>
    </div>
    
    <div class="form-group">
        <label for="email">이메일 *</label>
        <input type="email" id="email" name="email" value="${user.email}" required>
    </div>
    
    <div class="form-group">
        <label for="phoneNumber">전화번호</label>
        <input type="tel" id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}">
    </div>
    
    <div class="form-group">
        <label for="address">주소</label>
        <input type="text" id="address" name="address" value="${user.address}">
    </div>
    
    <div class="form-group">
        <label for="role">역할 *</label>
        <select id="role" name="role" required>
            <option value="">선택하세요</option>
            <option value="farmer" ${user.role == 'farmer' ? 'selected' : ''}>농부</option>
            <option value="buyer" ${user.role == 'buyer' ? 'selected' : ''}>구매자</option>
            <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>관리자</option>
        </select>
    </div>
    
    <c:if test="${isEdit}">
        <div class="form-group">
            <label for="points">포인트</label>
            <input type="number" id="points" name="points" value="${user.points}" min="0">
        </div>
    </c:if>
    
    <div style="margin-top: 20px;">
        <button type="submit" class="btn">${isEdit ? '수정' : '등록'}</button>
        <a href="${pageContext.request.contextPath}/users" class="btn btn-secondary">취소</a>
    </div>
</form>

<%@ include file="../common/footer.jsp" %>