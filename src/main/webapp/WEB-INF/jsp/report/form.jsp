<%@ include file="../common/header.jsp" %>

<h2>${isEdit ? '신고 수정' : '새 신고 접수'}</h2>

<c:if test="${not empty error}">
    <div class="alert alert-error">${error}</div>
</c:if>

<form action="${pageContext.request.contextPath}/reports${isEdit ? '/' : ''}${isEdit ? report.reportID : ''}" method="post">
    <div class="form-group">
        <label for="reporterID">신고자 *</label>
        <select id="reporterID" name="reporterID" required ${isEdit ? 'disabled' : ''}>
            <option value="">선택하세요</option>
            <c:forEach var="user" items="${users}">
                <option value="${user.userID}" ${report.reporterID == user.userID ? 'selected' : ''}>
                    ${user.fullName} (${user.username})
                </option>
            </c:forEach>
        </select>
        <c:if test="${isEdit}">
            <input type="hidden" name="reporterID" value="${report.reporterID}">
        </c:if>
    </div>
    
    <div class="form-group">
        <label for="targetType">대상 유형 *</label>
        <select id="targetType" name="targetType" required>
            <option value="">선택하세요</option>
            <option value="user" ${report.targetType == 'user' ? 'selected' : ''}>사용자</option>
            <option value="crop" ${report.targetType == 'crop' ? 'selected' : ''}>작물</option>
            <option value="diary" ${report.targetType == 'diary' ? 'selected' : ''}>영농일지</option>
            <option value="comment" ${report.targetType == 'comment' ? 'selected' : ''}>댓글</option>
            <option value="review" ${report.targetType == 'review' ? 'selected' : ''}>리뷰</option>
        </select>
    </div>
    
    <div class="form-group">
        <label for="targetID">대상 ID *</label>
        <input type="number" id="targetID" name="targetID" value="${report.targetID}" required min="1">
        <small style="color: #666;">신고 대상의 ID를 입력하세요</small>
    </div>
    
    <div class="form-group">
        <label for="reason">신고 사유 *</label>
        <select id="reason" name="reason" required>
            <option value="">선택하세요</option>
            <option value="스팸" ${report.reason == '스팸' ? 'selected' : ''}>스팸</option>
            <option value="욕설/비방" ${report.reason == '욕설/비방' ? 'selected' : ''}>욕설/비방</option>
            <option value="허위정보" ${report.reason == '허위정보' ? 'selected' : ''}>허위정보</option>
            <option value="광고" ${report.reason == '광고' ? 'selected' : ''}>광고</option>
            <option value="개인정보노출" ${report.reason == '개인정보노출' ? 'selected' : ''}>개인정보노출</option>
            <option value="기타" ${report.reason == '기타' ? 'selected' : ''}>기타</option>
        </select>
    </div>
    
    <div class="form-group">
        <label for="description">상세 설명 *</label>
        <textarea id="description" name="description" required rows="5">${report.description}</textarea>
    </div>
    
    <c:if test="${isEdit}">
        <div class="form-group">
            <label for="status">처리 상태 *</label>
            <select id="status" name="status" required>
                <option value="pending" ${report.status == 'pending' ? 'selected' : ''}>대기중</option>
                <option value="processing" ${report.status == 'processing' ? 'selected' : ''}>처리중</option>
                <option value="resolved" ${report.status == 'resolved' ? 'selected' : ''}>해결됨</option>
                <option value="rejected" ${report.status == 'rejected' ? 'selected' : ''}>반려됨</option>
            </select>
        </div>
    </c:if>
    
    <div style="margin-top: 20px;">
        <button type="submit" class="btn">${isEdit ? '수정' : '접수'}</button>
        <a href="${pageContext.request.contextPath}/reports" class="btn btn-secondary">취소</a>
    </div>
</form>

<%@ include file="../common/footer.jsp" %>