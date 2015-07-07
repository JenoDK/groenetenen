<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Groene tenen' />
</head>
<body>
	<v:menu />
	<h1>Groene tenen</h1>
	<blockquote>De aanleg van een gezellig gazon</blockquote>
	<c:set var='foto' value='${empty foto ? "ochtend" : foto}' />
	<img alt='gazon in de ${foto}'
		src='<c:url value="/images/${foto}.jpg"/>' class='fullwidth'>
	<c:url var='ochtendURL' value=''>
		<c:param name='foto' value='ochtend' />
	</c:url>
	<c:url var='avondURL' value=''>
		<c:param name='foto' value='avond' />
	</c:url>
	<a href='${ochtendURL}'>Ochtend</a>
	<a href='${avondURL}'>Avond</a>
</body>
</html>