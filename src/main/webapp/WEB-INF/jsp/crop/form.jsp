<%@ include file="../common/header.jsp" %>

<h2>${isEdit ? '작물 수정' : '새 작물 추가'}</h2>

<c:if test="${not empty error}">
    <div class="alert alert-error">${error}</div>
</c:if>

<form action="${pageContext.request.contextPath}/crops${isEdit ? '/' : ''}${isEdit ? crop.cropID : ''}" method="post">
    <div class="form-group">
        <label for="cropName">작물명 *</label>
        <input type="text" id="cropName" name="cropName" value="${crop.cropName}" required>
    </div>
    
    <div class="form-group">
        <label for="category">카테고리 *</label>
        <select id="category" name="category" required>
            <option value="">선택하세요</option>
            <option value="곡물" ${crop.category == '곡물' ? 'selected' : ''}>곡물</option>
            <option value="채소" ${crop.category == '채소' ? 'selected' : ''}>채소</option>
            <option value="과일" ${crop.category == '과일' ? 'selected' : ''}>과일</option>
            <option value="특용작물" ${crop.category == '특용작물' ? 'selected' : ''}>특용작물</option>
            <option value="기타" ${crop.category == '기타' ? 'selected' : ''}>기타</option>
        </select>
    </div>
    
    <div class="form-group">
        <label for="price">가격 (원) *</label>
        <input type="number" id="price" name="price" value="${crop.price}" required min="0" step="100">
    </div>
    
    <div class="form-group">
        <label for="unit">단위 *</label>
        <input type="text" id="unit" name="unit" value="${crop.unit}" required placeholder="예: kg, 개, 포기">
    </div>
    
    <div class="form-group">
        <label for="stockQuantity">재고 수량</label>
        <input type="number" id="stockQuantity" name="stockQuantity" value="${crop.stockQuantity}" min="0">
    </div>
    
    <div class="form-group">
        <label for="growthDuration">재배 일수</label>
        <input type="number" id="growthDuration" name="growthDuration" value="${crop.growthDuration}" min="0">
    </div>
    
    <div class="form-group">
        <label for="description">설명</label>
        <textarea id="description" name="description">${crop.description}</textarea>
    </div>
    
    <c:if test="${isEdit}">
        <div class="form-group">
            <label for="rating">평점</label>
            <input type="number" id="rating" name="rating" value="${crop.rating}" min="0" max="5" step="0.1">
        </div>
    </c:if>
    
    <div style="margin-top: 20px;">
        <button type="submit" class="btn">${isEdit ? '수정' : '등록'}</button>
        <a href="${pageContext.request.contextPath}/crops" class="btn btn-secondary">취소</a>
    </div>
</form>

<%@ include file="../common/footer.jsp" %>