<%@page contentType='text/html' pageEncoding='UTF-8'%>
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
	<h1>Welkom in het huis van de Belgische bieren</h1>
	<img alt='belgische bieren' src='<c:url value="/images/bierhuis.jpg"/>'>
	<p>We hebben momenteel <c:out value="${aantalBieren}"></c:out> bieren</p>
</body>
</html>