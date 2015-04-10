<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
	</head>
	<body>
		<h1>Publicar aviso de mascota perdida</h1>
		
			<g:form action="publicarAviso">
				nombre: <g:textField name="nombre" /><br/>
				raza: <g:textField name="raza" /> <br/>
				direccion: <g:textField name="direccion" />
				
				<input type="submit" value="dale que lo perdí" />
			</g:form>
	</body>
</html>
