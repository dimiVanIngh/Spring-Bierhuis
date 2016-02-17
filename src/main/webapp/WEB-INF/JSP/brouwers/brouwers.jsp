<%@page contentType='text/html' pageEncoding='UTF-8' trimDirectiveWhitespaces='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Brouwers' />
</head>
<body>
	<v:menu />
	<h1>Brouwers</h1>
	<ul class="zebra">
		<c:forEach items='${brouwers}' var='brouwer'>
			<spring:url var='url' value='/brouwers/{id}'>
				<spring:param name='id' value='${brouwer.id}' />
			</spring:url>
			<li><a href="${url}"> ${brouwer.naam}
				(${brouwer.adres.gemeente})</a></li>
		</c:forEach>
	</ul>
</body>
</html>