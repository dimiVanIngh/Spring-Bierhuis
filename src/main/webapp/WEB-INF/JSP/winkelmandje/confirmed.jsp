<%@page contentType='text/html' pageEncoding='UTF-8' trimDirectiveWhitespaces='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='confirmed' />
</head>
<body>
<v:menu />
<body>
	<c:choose>
		<c:when test="${orderNr != 0}">
			<h2>Je mandje is bevestigd als bestelbon ${orderNr}</h2>
		</c:when>
	</c:choose>
</body>
</html>