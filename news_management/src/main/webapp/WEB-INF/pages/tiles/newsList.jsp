<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.guestinfo.news" var="news_news" />
<fmt:message bundle="${loc}" key="local.newslist" var="newslist" />
<fmt:message bundle="${loc}" key="local.newslist.edit" var="newslist_edit" />
<fmt:message bundle="${loc}" key="local.newslist.view" var="newslist_view" />
<fmt:message bundle="${loc}" key="local.guestinfo.nonews" var="no_news" />
<fmt:message bundle="${loc}" key="local.newslist.delete" var="delete" />

<div class="body-title">
	<a href=""><c:out value="${news_news}"/></a> <c:out value="${newslist}"/>
</div>

<form action="controller" method="post">
	<c:forEach var="news" items="${requestScope.news}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<c:out value="${news.title}" />
				</div>
				<div class="news-date">
					<font size="1"><c:out value="${news.newsDate}" /></font>
				</div>

				<div class="news-content">
					<c:out value="${news.brief}" />
				</div>
				<div class="news-link-to-wrapper">
					<div class="link-position">
						<c:if test="${sessionScope.role eq 'admin'}">
							<a style="text-decoration: none;"
								href="controller?command=go_to_edit_news&id=${news.idNews}"><c:out value="${newslist_edit}"/>&nbsp;</a>
						</c:if>

						<a style="text-decoration: none;"
							href="controller?command=go_to_view_news&id=${news.idNews}"><c:out value="${newslist_view}"/>&nbsp;</a>

						<c:if test="${sessionScope.role eq 'admin'}">
							<input type="hidden" name="command" value="delete_news" />
							<input type="checkbox" name="id" value="${news.idNews }" />							
						</c:if>
					</div>
				</div>
			</div>
		</div>		
		
	</c:forEach>		

	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
        <p><c:out value="${no_news}"/></p>
	</c:if>
	</div>
	<div class="second-button">

			<c:if test="${not(requestScope.news eq null)}">
				<c:if test="${sessionScope.role eq 'admin'}">
					<p align="right"><input type="submit" value="${delete}"/></p>
					<br />
				</c:if>
			</c:if>

		</div>
</form>

