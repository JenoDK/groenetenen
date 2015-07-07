<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Filiaal toevoegen' />
</head>
<body>
	<v:menu />
	<h1>Filiaal toevoegen</h1>
	<c:url value='/filialen' var='url' />
	<form:form action='${url}' commandName='filiaal' id='toevoegform'>
		<jsp:include page='filiaalformfields.jsp' />
		<input type='submit' value='Toevoegen' id='toevoegknop'>
	</form:form>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>