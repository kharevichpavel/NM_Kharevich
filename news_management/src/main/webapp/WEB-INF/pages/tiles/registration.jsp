<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.registration.title" var="registration_title" />

<fmt:message bundle="${loc}" key="local.registration.email" var="registration_email" />
<fmt:message bundle="${loc}" key="local.registration.tel" var="registration_tel" />
<fmt:message bundle="${loc}" key="local.registration.login" var="registration_login" />
<fmt:message bundle="${loc}" key="local.registration.password" var="registration_password" />
<fmt:message bundle="${loc}" key="local.registration.register" var="registration_register" />

<fmt:message bundle="${loc}" key="local.locbutton.name.clear" var="clear" />

<div class="registration">
	<form action="controller" method="post">
		<p align="center"><c:out value="${registration_title}"/></p>
		<br>
		<div>

			<c:out value="${registration_email}"/><br>
			<input type="email" id="email" name="email" size="30px" required
				placeholder="Example: one@gmail.com"><br> 
			<c:out value="${registration_tel}"/><br>
			<input type="tel" id="tel" name="tel" size="30px" required
				placeholder="Example: +375291111111" pattern="(\+)([0-9]){12}"><br>
			<c:out value="${registration_login}"/><br>
			<input type="text" id="login" name="login" required><br>
			<c:out value="${registration_password}"/><br>
			<input type="password" id="password" name="password" required>

		</div>
		<br>

		<div>
			<input type="hidden" name="command" value="do_registration" /> <input
				type="submit" value="${registration_register}" />
			<button type="reset"><c:out value="${clear}"/></button>
		</div>

	</form>

</div>
