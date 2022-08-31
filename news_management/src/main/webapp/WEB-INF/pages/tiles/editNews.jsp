<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.editnews.editnews" var="edit_news" />
<fmt:message bundle="${loc}" key="local.news.title" var="news_title" />
<fmt:message bundle="${loc}" key="local.news.brief" var="news_brief" />
<fmt:message bundle="${loc}" key="local.news.content" var="news_content" />
<fmt:message bundle="${loc}" key="local.locbutton.name.edit.news" var="edit_news_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.clear" var="clear" />

<div style="color:  #353596;">
	<form action="controller" method="post">
		<p align="center"><c:out value="${edit_news}"/></p>
		<br>
		<div>			
			<p class="addNews"><c:out value="${news_title}"/></p>
			<textarea name="title" cols="60" rows="3" >${news.title }</textarea><br> 
			<p><c:out value="${news_brief}"/></p>
			<textarea name="brief" cols="60" rows="3">${news.brief}</textarea><br> 
			<p><c:out value="${news_content}"/></p>
			<textarea name="content" cols="60" rows="8">${news.content}</textarea>
		</div>
		<br>

		<div>
			<input type="hidden" name="command" value="do_edit_news" /> 			
			
			<input type="hidden" name="id" value="${news.idNews}" />
			<input	type="submit" value="${edit_news_button}" />
			<button type="reset"><c:out value="${clear}"/></button>
		</div>
	</form>
</div>