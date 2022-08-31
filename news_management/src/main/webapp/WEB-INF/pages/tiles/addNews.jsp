<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.addnews.addnews" var="add_news" />
<fmt:message bundle="${loc}" key="local.news.title" var="news_title" />
<fmt:message bundle="${loc}" key="local.news.brief" var="news_brief" />
<fmt:message bundle="${loc}" key="local.news.content" var="news_content" />
<fmt:message bundle="${loc}" key="local.locbutton.name.add.news" var="add_news_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.clear" var="clear" />


<div style="color:  #353596;">
	<form action="controller" method="post">
		<p align="center"><c:out value="${add_news}"/></p>
		<br>
		<div>			
			<p class="addNews"><c:out value="${news_title}"/></p>
			<textarea name="title" cols="60" rows="3"></textarea><br> 
			<p><c:out value="${news_brief}"/></p>
			<textarea name="brief" cols="60" rows="3"></textarea><br> 
			<p><c:out value="${news_content}"/></p>
			<textarea name="content" cols="60" rows="8"></textarea>
		</div>
		<br>

		<div>
			<input type="hidden" name="command" value="add_news"/> <input
				type="submit" value="${add_news_button}" />
			<button type="reset"><c:out value="${clear}"/></button>
		</div>
	</form>
</div>

