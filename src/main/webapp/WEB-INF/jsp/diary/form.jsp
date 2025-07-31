<%@ include file="../common/header.jsp" %>

<h2>${isEdit ? '영농일지 수정' : '새 영농일지 작성'}</h2>

<c:if test="${not empty error}">
    <div class="alert alert-error">${error}</div>
</c:if>

<form action="${pageContext.request.contextPath}/diaries${isEdit ? '/' : ''}${isEdit ? diary.diaryID : ''}" method="post">
    <div class="form-group">
        <label for="userID">작성자 *</label>
        <select id="userID" name="userID" required>
            <option value="">선택하세요</option>
            <c:forEach var="user" items="${users}">
                <option value="${user.userID}" ${diary.userID == user.userID ? 'selected' : ''}>
                    ${user.fullName} (${user.username})
                </option>
            </c:forEach>
        </select>
    </div>
    
    <div class="form-group">
        <label for="cropID">작물 *</label>
        <select id="cropID" name="cropID" required>
            <option value="">선택하세요</option>
            <c:forEach var="crop" items="${crops}">
                <option value="${crop.cropID}" ${diary.cropID == crop.cropID ? 'selected' : ''}>
                    ${crop.cropName} (${crop.category})
                </option>
            </c:forEach>
        </select>
    </div>
    
    <div class="form-group">
        <label for="workDate">작업일 *</label>
        <input type="date" id="workDate" name="workDate" value="${diary.workDate}" required>
    </div>
    
    <div class="form-group">
        <label for="weather">날씨 *</label>
        <select id="weather" name="weather" required>
            <option value="">선택하세요</option>
            <option value="맑음" ${diary.weather == '맑음' ? 'selected' : ''}>맑음</option>
            <option value="흐림" ${diary.weather == '흐림' ? 'selected' : ''}>흐림</option>
            <option value="비" ${diary.weather == '비' ? 'selected' : ''}>비</option>
            <option value="눈" ${diary.weather == '눈' ? 'selected' : ''}>눈</option>
            <option value="구름많음" ${diary.weather == '구름많음' ? 'selected' : ''}>구름많음</option>
        </select>
    </div>
    
    <div class="form-group">
        <label for="temperature">온도 (°C) *</label>
        <input type="number" id="temperature" name="temperature" value="${diary.temperature}" required min="-50" max="50" step="0.1">
    </div>
    
    <div class="form-group">
        <label for="humidity">습도 (%) *</label>
        <input type="number" id="humidity" name="humidity" value="${diary.humidity}" required min="0" max="100">
    </div>
    
    <div class="form-group">
        <label for="workDescription">작업 내용 *</label>
        <textarea id="workDescription" name="workDescription" required rows="5">${diary.workDescription}</textarea>
    </div>
    
    <div class="form-group">
        <label for="notes">메모</label>
        <textarea id="notes" name="notes" rows="3">${diary.notes}</textarea>
    </div>
    
    <div class="form-group">
        <label for="imageURL">이미지 URL</label>
        <input type="text" id="imageURL" name="imageURL" value="${diary.imageURL}" placeholder="http://example.com/image.jpg">
    </div>
    
    <div style="margin-top: 20px;">
        <button type="submit" class="btn">${isEdit ? '수정' : '등록'}</button>
        <a href="${pageContext.request.contextPath}/diaries" class="btn btn-secondary">취소</a>
    </div>
</form>

<%@ include file="../common/footer.jsp" %>