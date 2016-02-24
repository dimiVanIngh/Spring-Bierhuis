<%@page contentType='text/html' pageEncoding='UTF-8' trimDirectiveWhitespaces='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Reservatie mandje' />
</head>
<body>
<v:menu />
	<h2>Winkelwagen</h2>

	<c:if test="${not empty bestelbon}">
		<table>
			<thead>
				<tr>
					<th>Bier</th>
					<th>Prijs</th>
					<th>Aantal</th>
					<th>Te betalen</th>
				</tr>
			</thead>
			<c:forEach var="lijn" items="${bestelbon.bestelbonlijnen}">
				<tr>
					<td>${lijn.bier.naam}</td>
					<td><fmt:formatNumber type="currency"
							value="${lijn.bier.prijs}" /></td>
					<td><c:out value='${lijn.aantal}' /></td>
					<td><fmt:formatNumber type="currency"
							value="${lijn.getTotaleprijs()}" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="3">Te betalen:</td>
				<td><fmt:formatNumber type="currency"
						value="${bestelbon.getTotalePrijs()}" /></td>
			</tr>
		</table>

		<br />
		<form:form method='post' id='bevestigform' commandName="bestelbon">
			<form:label path='naam'>Naam:<form:errors path='naam' delimiter=", "/></form:label>
			<form:input path='naam' autofocus="autofocus" required="required" maxlength="50"/>
			<form:label path='adres.straat'>Straat:<form:errors path='adres.straat' delimiter=', '/></form:label>
			<form:input path='adres.straat' required='required'/>
			<form:label path='adres.huisNr'>Huisnummer:<form:errors path='adres.huisNr' delimiter=', '/></form:label>
			<form:input path='adres.huisNr' required='required' maxlength='7'/>
			<form:label path='adres.postcode'>Postcode:<form:errors path='adres.postcode' delimiter=', '/></form:label> 
			<form:input path='adres.postcode' required='required' type='number' min='1000' max='9999'/>
			<form:label path='adres.gemeente'>Gemeente:<form:errors path='adres.gemeente' delimiter=', '/></form:label>
			<form:input path='adres.gemeente' required='required' maxlength='50'/>
		<input type='submit' value='Als bestelbon bevestigen' id='bevestigknop'>
		</form:form>
		<script>
			document.getElementById('bevestigform').onsubmit = function() {
				document.getElementById('bevestigknop').disabled = true;
			};
		</script>
	</c:if>
</body>
</html>