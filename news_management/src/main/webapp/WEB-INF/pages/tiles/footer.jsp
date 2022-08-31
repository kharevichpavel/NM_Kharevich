<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.footer.left" var="footer_left" />
<fmt:message bundle="${loc}" key="local.footer.right" var="footer_right" />

<center>
	<c:out value="${footer_left}"/>&#169;<c:out value="${footer_right}"/>
</center>
