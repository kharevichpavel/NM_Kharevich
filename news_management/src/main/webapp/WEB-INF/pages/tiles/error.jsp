<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.error" var="return_base_page" />


<p class="error">
	<c:out value="${sessionScope.errorNumber}" />
</p>

<form action="controller" method="post">
	<div>
		<input type="hidden" name="command" value="GO_TO_BASE_PAGE" />
		<input type="submit" value="${return_base_page}" />
	</div>
</form>


