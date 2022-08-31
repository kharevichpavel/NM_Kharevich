<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.guestinfo" var="guest_info" />
<fmt:message bundle="${loc}" key="local.guestinfo.news" var="news" />
<fmt:message bundle="${loc}" key="local.guestinfo.latestnews" var="latestnews" />
<fmt:message bundle="${loc}" key="local.guestinfo.nonews" var="no_news" />

<center>
	<h4><c:out value="${guest_info}"/></h4>
</center>

<div class="body-title">
	<a href=""><c:out value="${news}"/></a> <c:out value="${latestnews}"/>
</div>


<form action="command.do?method=delete" method="post">
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
			</div>
		</div>

	</c:forEach>

	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
        <c:out value="${no_news}"/>
	</c:if>
	</div>

</form>

