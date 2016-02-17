<%@page contentType='text/html' pageEncoding='UTF-8'
	trimDirectiveWhitespaces='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Reservatie mandje' />
</head>
<body>
<v:menu />
	<h2>Winkelwagen</h2>

	<c:if test="${not empty mandje}">
		<table>
			<thead>
				<tr>
					<th>Wijn</th>
					<th>Prijs</th>
					<th>Aantal</th>
					<th>Te betalen</th>
				</tr>
			</thead>
			<c:forEach var="lijn" items="${reservatie.bestelbonlijnen}">
				<tr>
					<td>${lijn.getVolledigeNaam()}</td>
					<td><fmt:formatNumber type="currency"
							value="${lijn.wijn.prijs}" /></td>
					<td><c:out value='${lijn.aantal}' /></td>
					<td><fmt:formatNumber type="currency"
							value="${lijn.getTotaleprijs()}" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="3">Te betalen:</td>
				<td><fmt:formatNumber type="currency"
						value="${reservatie.getTotalePrijs()}" /></td>
			</tr>
		</table>

		<br />
		<form method='post' id='bevestigform'>
			<label>Naam<span>${fouten.naam}</span>
				 <input name='naam'value='${param.naam}' autofocus required></label> 
			<label>Straat <span>${fouten.straat}</span> 
				<input name='straat' value='${param.straat}' required></label>
			<label>Huisnummer <span>${fouten.huisnummer}</span> 
			 	<input name='huisnummer' value='${param.huisnummer}' required></label> 
			<label>Postcode <span>${fouten.postcode}</span> 
				<input name='postcode' value='${param.postcode}' required></label>
			<label>Gemeente <span>${fouten.gemeente}</span> 
				<input name='gemeente' value='${param.gemeente}' required></label>

			<div>
			<p>Bestelwijze
			<c:if test="${not empty fouten.bestelwijze}">
				<span class="error">
					 ${fouten.bestelwijze}
				</span>
			</c:if>
			</p>
				<label> <input type='radio' name='bestelwijze'
					value='AFHALEN' ${param.bestelwijze=='AFHALEN' ? 'checked' : ''}>Afhalen
				</label>
			</div>
			<div>
				<label><input type='radio' name='bestelwijze'
					value='OPSTUREN' ${param.bestelwijze=='OPSTUREN' ? 'checked' : ''}>Opsturen</label>
			</div>
			<input type='submit' value='Als bestelbon bevestigen'
				id='bevestigknop'>
		</form>
		<script>
			document.getElementById('bevestigform').onsubmit = function() {
				document.getElementById('bevestigknop').disabled = true;
			};
		</script>

	</c:if>
</body>
</html>