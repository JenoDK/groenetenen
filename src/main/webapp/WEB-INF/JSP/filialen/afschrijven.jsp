<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Afschrijven' />
</head>
<body>
	<v:menu />
	<h1>Filiaal afschrijven</h1>
	<form:form action="" commandName="afschrijvenForm">
		<form:label path='filialen'>Filiaal:
<form:errors path='filialen' />
		</form:label>
		<form:checkboxes items='${filialen}' itemLabel='naam' itemValue='id'
			path='filialen' element='div' />
		<input type='submit' value='Afschrijven' />
	</form:form>
</body>
</html>