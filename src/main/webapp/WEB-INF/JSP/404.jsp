<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Fout' />
</head>
<body>
	<v:menu />
	<h1>Fout</h1>
	<p>De gevraagde resource werd niet gevonden</p>
	<img alt='fout' src='<c:url value="/images/404.png"/>'>
</body>
</html>
