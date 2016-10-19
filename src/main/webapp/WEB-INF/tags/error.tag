<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${error != null}">
    <div class="alert alert-error">
        <strong>${error}</strong>
    </div>
</c:if>