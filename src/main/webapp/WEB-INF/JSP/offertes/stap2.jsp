<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Aanvraag offerte (stap 2)' />
</head>
<body>
	<v:menu />
	<h1>Aanvraag offerte</h1>
	<h2>Stap 2</h2>
	<form method='post' action="<c:url value='/offertes'/>">
		<c:url value='/offertes' var='url' />
		<form:form action='${url}' commandName='offerte'>
			<form:label path='oppervlakte'>Oppervlakte:
				<form:errors path='oppervlakte' />
			</form:label>
			<form:input path='oppervlakte' autofocus='true' required='required'
				type='number' min='1' />
			<c:forEach items='${offerte.gazontypes}' var='entry'>
				<div class='rij'>
					<form:checkbox path='gazontypes[${entry.key}]'
						label="${entry.key.toString().toLowerCase()}" />
				</div>
			</c:forEach>
			<form:errors cssClass="fout"/>
			<input type='submit' value='Vorige stap' name='van2naar1'
				formnovalidate>
			<input type='submit' value='Bevestigen' name='bevestigen'>
		</form:form>
	</form>
</body>
</html>