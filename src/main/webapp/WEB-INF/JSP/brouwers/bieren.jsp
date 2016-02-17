<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='${brouwer.naam}' />
</head>
<body>
	<v:menu />
	<c:choose>
		<c:when test="${not empty brouwer}">
			<h1>${brouwer.naam} (${brouwer.adres.gemeente})</h1>
			<ul class="zebra">
				<c:forEach items='${brouwer.bieren}' var='bier'>
					<spring:url var='url' value='/bieren/{id}'>
						<spring:param name='id' value='${bier.id}' />
					</spring:url>
					<li><a href="${url}"> ${bier.naam}</a></li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<div class='fout'>Brouwer niet gevonden</div>
		</c:otherwise>
	</c:choose>
	<c:if test='${not empty param.fout}'>
		<div class='fout'>${param.fout}</div>
	</c:if>
</body>
</html>