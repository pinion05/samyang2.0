<%@ include file="../common/header.jsp" %>

<h2>${isEdit ? '리뷰 수정' : '새 리뷰 작성'}</h2>

<c:if test="${not empty error}">
    <div class="alert alert-error">${error}</div>
</c:if>

<form action="${pageContext.request.contextPath}/reviews${isEdit ? '/' : ''}${isEdit ? review.reviewID : ''}" method="post">
    <div class="form-group">
        <label for="userID">작성자 *</label>
        <select id="userID" name="userID" required ${isEdit ? 'disabled' : ''}>
            <option value="">선택하세요</option>
            <c:forEach var="user" items="${users}">
                <option value="${user.userID}" ${review.userID == user.userID ? 'selected' : ''}>
                    ${user.fullName} (${user.username})
                </option>
            </c:forEach>
        </select>
        <c:if test="${isEdit}">
            <input type="hidden" name="userID" value="${review.userID}">
        </c:if>
    </div>
    
    <div class="form-group">
        <label for="cropID">작물 *</label>
        <select id="cropID" name="cropID" required ${isEdit ? 'disabled' : ''}>
            <option value="">선택하세요</option>
            <c:forEach var="crop" items="${crops}">
                <option value="${crop.cropID}" ${review.cropID == crop.cropID ? 'selected' : ''}>
                    ${crop.cropName} (${crop.category})
                </option>
            </c:forEach>
        </select>
        <c:if test="${isEdit}">
            <input type="hidden" name="cropID" value="${review.cropID}">
        </c:if>
    </div>
    
    <div class="form-group">
        <label for="rating">평점 *</label>
        <select id="rating" name="rating" required>
            <option value="">선택하세요</option>
            <option value="5" ${review.rating == 5 ? 'selected' : ''}>⭐⭐⭐⭐⭐ (5점)</option>
            <option value="4" ${review.rating == 4 ? 'selected' : ''}>⭐⭐⭐⭐ (4점)</option>
            <option value="3" ${review.rating == 3 ? 'selected' : ''}>⭐⭐⭐ (3점)</option>
            <option value="2" ${review.rating == 2 ? 'selected' : ''}>⭐⭐ (2점)</option>
            <option value="1" ${review.rating == 1 ? 'selected' : ''}>⭐ (1점)</option>
        </select>
    </div>
    
    <div class="form-group">
        <label for="title">제목 *</label>
        <input type="text" id="title" name="title" value="${review.title}" required>
    </div>
    
    <div class="form-group">
        <label for="content">내용 *</label>
        <textarea id="content" name="content" required rows="5">${review.content}</textarea>
    </div>
    
    <div style="margin-top: 20px;">
        <button type="submit" class="btn">${isEdit ? '수정' : '등록'}</button>
        <a href="${pageContext.request.contextPath}/reviews" class="btn btn-secondary">취소</a>
    </div>
</form>

<%@ include file="../common/footer.jsp" %>