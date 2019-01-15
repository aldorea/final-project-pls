<%@page import="edu.uv.dawts.trabajofinal.AccesoDatos"%>
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
	<p>Bienvenido</p>
	<% AccesoDatos ad = (AccesoDatos) getServletContext().getAttribute("bd");
	String rol = ad.getRol(request.getRemoteUser());
	if(rol.equals("jefe_proyecto")){
		out.println("<a href='/TrabajoFinal/jefeproyecto/VerProyectos'>Ver proyectos</a>");
	}else{
		out.println("<a href='/TrabajoFinal/programador/verTareas'>Ver tus tareas</a>");
	}
	%>
	
	
<%-- 	<c:if test="${user.rol eq 'jefe_proyecto'}">
		out.println("<a href='/jefeproyecto/muestraProyectos'>Ver proyectos</a>");	
	</c:if>
	<c:if test="${user.rol eq 'programador'}">
		<a href="/programador/verTareas.jsp">Ver tus tareas</a>
	</c:if> --%> 
</body>
</html>