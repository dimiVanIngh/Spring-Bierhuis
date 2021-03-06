<%@page contentType='text/html' pageEncoding='UTF-8'
	trimDirectiveWhitespaces='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='${bier.naam}' />
</head>
<body>
	<v:menu />
	<c:choose>
		<c:when test="${not empty bier}">
			<h1>${bier.naam}</h1>
			<dl>
				<dt>Alcohol</dt>
				<dd>${bier.alcohol}&#37;</dd>
				<dt>Prijs</dt>
				<dd>
					<fmt:formatNumber type="currency" value="${bier.prijs}" />
				</dd>
				<dt>Soort</dt>
				<dd>${bier.soort.naam}</dd>
				<dt>Brouwer</dt>
				<dd>${bier.brouwer.naam}</dd>
			</dl>
			<form:form method="post" commandName="bestelbonlijn">
				<form:label path='aantal'>Aantal (bakken):<form:errors
						path='aantal' delimiter=", "/>
				</form:label>
				<form:input path='aantal' type="number" min="1" step="1"
					autofocus="autofocus" required="required" />
				<input type="submit" value="Toevoegen" id="reserveerKnop">
			</form:form>
		</c:when>
		<c:otherwise>
			// Mooie rode error
		</c:otherwise>
	</c:choose>
</html>

