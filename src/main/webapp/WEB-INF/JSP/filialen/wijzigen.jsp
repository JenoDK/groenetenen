<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='${filiaal.naam}' />
</head>
<body>
	<v:menu />
	<h1>${filiaal.naam}</h1>
	<spring:url value='/filialen/{id}/wijzigen' var='url'>
		<spring:param name='id' value='${filiaal.id}' />
	</spring:url>
	<form:form action='${url}' commandName='filiaal'>
		<jsp:include page='filiaalformfields.jsp' />
		<input type='submit' value='Wijzigen'>
	</form:form>
</body>
</html>