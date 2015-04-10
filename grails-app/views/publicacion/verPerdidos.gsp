<%!
import petrescue.*
%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
	</head>
	<body>
		<h1>Viendo las mascotas perdidas</h1>
		
		
		<g:if test="${perdidos}">
			<table>
				<tr>
					<th>nombre mascota</th>
					<th>raza</th>
					<th>prov</th>
					<th>fecha</th>
				</tr>
				<g:each in="${perdidos}" var="aviso">
					<g:if test="${aviso.tipoAviso == TipoAviso.PERDIDO}">
						<tr>
							<td>${aviso.mascota.nombre}</td>
							<td>${aviso.mascota.raza}</td>
							<td>${aviso.ubicacion.provincia}</td>
							<td>${aviso.fecha}</td>
						</tr>
					</g:if>
				</g:each>
			</table>
		</g:if>
		<g:else>
			dale flaco la lista esta vacia
		</g:else>
	</body>
</html>
