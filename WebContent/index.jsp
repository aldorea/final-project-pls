<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manejador de Proyectos</title>
</head>
<body>
	<h1>Manejador de Proyectos</h1>
	<p>Bienvenido <% String rol = (String) request.getAttribute("user"); %></p>
	<c:out value="${rol}"></c:out>
	<c:if test="${rol eq 'jefe_proyecto'}">
		<a href="/jefeproyecto/muestraProyectos">Ver proyectos</a>
	</c:if>
	<c:if test="${rol eq 'programador'}">
		<a href="/programador/verTareas">Ver tus tareas</a>
	</c:if>
</body>
</html>