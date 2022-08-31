<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.guestinfo.news" var="news_news" />
<fmt:message bundle="${loc}" key="local.viewnews" var="view_news" />
<fmt:message bundle="${loc}" key="local.news.date" var="news_date" />
<fmt:message bundle="${loc}" key="local.news.title" var="news_title" />
<fmt:message bundle="${loc}" key="local.news.brief" var="news_brief" />
<fmt:message bundle="${loc}" key="local.news.content" var="news_content" />
<fmt:message bundle="${loc}" key="local.locbutton.name.edit.news" var="edit_news" />
<fmt:message bundle="${loc}" key="local.locbutton.name.delete.news" var="delete_news" />

<div class="body-title">
	<a href=""><c:out value="${news_news}"/></a> <c:out value="${view_news}"/>
</div>

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text"><c:out value="${news_title}"/></td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.title }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><c:out value="${news_date}"/></td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.newsDate }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><c:out value="${news_brief}"/></td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.brief }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><c:out value="${news_content}"/></td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.content }" />
				</div></td>
		</tr>
	</table>
</div>


<c:if test="${sessionScope.role eq 'admin'}">
<div class="first-view-button">
	<form action="controller" method="post">
		<input type="hidden" name="command" value="go_to_edit_news" /> <input
			type="hidden" name="id" value="${news.idNews}" /> <input
			type="submit" value="${edit_news}" />
	</form>
</div>
<br><br>
<div class="second-view-button">
	<form action="controller" method="post">
		<input type="hidden" name="command" value="delete_news" /> 
		<input type="hidden" name="id" value="${news.idNews}" /> 
		<input	type="submit" value="${delete_news}" />
	</form>
</div>
</c:if>

