<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Filialen per id' />
</head>
<body>
	<v:menu />
	<h1>Filialen per id</h1>
	<form id='zoekForm'>
		<label>Id: <input id='filiaalId' required type='number'
			min='1' autofocus /></label> <input type='submit' value='Zoeken'>
	</form>
	<dl>
		<dt>Naam</dt>
		<dd id='naam'></dd>
		<dt>Adres</dt>
		<dd id='adres'></dd>
	</dl>
	<c:url value='/filialen' var='url' />
	<script>
		document.getElementById('zoekForm').onsubmit = zoekFiliaal;
		function zoekFiliaal() {
			var request = new XMLHttpRequest();
			request.open("GET", '${url}' + '/'
					+ document.getElementById('filiaalId').value, true);
			request.setRequestHeader('accept', 'application/json');
			request.onload = responseIsBinnengekomen;
			request.send();
			return false;
		}
		function responseIsBinnengekomen() {
			switch (this.status) {
			case 200:
				var filiaalResource = JSON.parse(this.responseText);
				var filiaal = filiaalResource.filiaal;
				document.getElementById('naam').innerHTML = filiaal.naam;
				var adres = filiaal.adres;
				document.getElementById('adres').innerHTML = adres.straat + ' '
						+ adres.huisNr + ' ' + adres.postcode + ' '
						+ adres.gemeente;
				break;
			case 404:
				alert('Filiaal bestaat niet');
				break;
			default:
				alert("Technisch probleem");
			}
		}
	</script>
</body>
</html>