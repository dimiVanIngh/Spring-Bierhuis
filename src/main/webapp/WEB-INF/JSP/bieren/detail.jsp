<%@page contentType='text/html' pageEncoding='UTF-8' trimDirectiveWhitespaces='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='${bier.naam}' />
</head>
<body>
	<v:menu />
	<h1>${bier.naam}</h1>
	<dl>
		<dt>Alcohol</dt>
		<dd>${bier.alcohol} &#37;</dd>
		<dt>Prijs</dt>
		<dd><fmt:formatNumber type="currency" value="${bier.prijs}" /></dd>
		<dt>Soort</dt>
		<dd>${bier.soort.naam}</dd>
		<dt>Brouwer</dt>
		<dd>${bier.brouwer.naam}</dd>
	</dl>
	<form method>
		
	</form>
</html>