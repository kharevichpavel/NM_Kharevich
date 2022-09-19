<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.error"
	var="return_base_page" />
<fmt:message bundle="${loc}" key="local.error.1" var="error_1" />
<fmt:message bundle="${loc}" key="local.error.2" var="error_2" />
<fmt:message bundle="${loc}" key="local.error.3" var="error_3" />
<fmt:message bundle="${loc}" key="local.error.4" var="error_4" />
<fmt:message bundle="${loc}" key="local.error.5" var="error_5" />
<fmt:message bundle="${loc}" key="local.error.6" var="error_6" />
<fmt:message bundle="${loc}" key="local.error.7" var="error_7" />
<fmt:message bundle="${loc}" key="local.error.8" var="error_8" />
<fmt:message bundle="${loc}" key="local.error.9" var="error_9" />


<p class="error">
	<c:if
		test="${sessionScope.errorNumber eq 'You do not have administrator rights!'}">
		<c:out value="${error_1}" />
	</c:if>
	<c:if
		test="${sessionScope.errorNumber eq 'An error occurred while deleting the news!'}">
		<c:out value="${error_2}" />
	</c:if>
	<c:if
		test="${sessionScope.errorNumber eq 'User exists, change login!'}">
		<c:out value="${error_3}" />
	</c:if>
	<c:if
		test="${sessionScope.errorNumber eq 'Something went wrong, please try again later!'}">
		<c:out value="${error_4}" />
	</c:if>
	<c:if
		test="${sessionScope.errorNumber eq 'An error occurred while creating the news!'}">
		<c:out value="${error_5}" />
	</c:if>
	<c:if
		test="${sessionScope.errorNumber eq 'An error occurred while changing the news!'}">
		<c:out value="${error_6}" />
	</c:if>
	<c:if
		test="${sessionScope.errorNumber eq 'An error occurred while changing the locale!'}">
		<c:out value="${error_7}" />
	</c:if>
	<c:if
		test="${sessionScope.errorNumber eq 'An error occurred while registering!'}">
		<c:out value="${error_8}" />
	</c:if>
	<c:if
		test="${sessionScope.errorNumber eq 'An error occurred while logging in!'}">
		<c:out value="${error_9}" />
	</c:if>	
</p>



