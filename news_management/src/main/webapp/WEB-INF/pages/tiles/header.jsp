<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.header.newstitle" var="header_newstitle" />
<fmt:message bundle="${loc}" key="local.header.auth.login" var="auth_login" />
<fmt:message bundle="${loc}" key="local.header.auth.password" var="auth_password" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.by" var="by_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.registration" var="registration_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.sign.in" var="sign_in_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.sign.out" var="sign_out_button" />

<div class="wrapper">
	<div class="newstitle"><c:out value="${header_newstitle}"/></div>


	<div class="local-link">

		<div align="right">
			<a href="controller?command=DO_LOCAL&local=en"><c:out value="${en_button}"/></a> &nbsp;&nbsp;
			<a href="controller?command=DO_LOCAL&local=by"><c:out value="${by_button}"/></a> &nbsp;&nbsp; 
			<a	href="controller?command=DO_LOCAL&local=ru"><c:out value="${ru_button}"/></a> <br />
			<p style="height:6px"></p>
		</div>

		<c:if test="${not (sessionScope.user eq 'active')}">

			<div align="right">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_sign_in" /> 
					<c:out value="${auth_login}"/> <input type="text" name="login" value=""/><br />
					<p style="height:3px"></p> 
					<c:out value="${auth_password}"/> <input type="password" name="password" value=""/><br />
					<p style="height:3px"></p>

					<c:if test="${not (sessionScope.AuthenticationError eq null)}">
						<font color="red" style="text-transform:uppercase"> 
						   <c:out value="${sessionScope.AuthenticationError}" />&nbsp;&nbsp;						   
						</font> 
					</c:if>
					<c:set var="AuthenticationError" value="" scope="session" />					
					
					<a href="controller?command=go_to_registration_page"><c:out value="${registration_button}"/></a> &nbsp;&nbsp; 
					<input type="submit" value="${sign_in_button}" /><br />
				</form>
			</div>

		</c:if>
		
		<c:if test="${sessionScope.user eq 'active'}">

			<div align="right">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_sign_out" /> 
					<input type="submit" value="${sign_out_button}" /><br />
				</form>
			</div>

		</c:if>
	</div>

</div>
