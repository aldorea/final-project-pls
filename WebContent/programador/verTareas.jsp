<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Proyectos</title>
<style type="text/css">
   table {border-collapse: collapse;}
   table, tr, td , th {border: 1px solid blue;}
   th {font-weight: bold;}
   td {padding-left: 2em; padding-right:2em;}
</style>
</head>
<body>

<h1> Tareas </h1>

<table>
<tr>
 <th> Nombre tarea </th>
 <th> Fecha prevista finalización </th>
 <th> Fecha finalización </th>
 <th> </th>
</tr>
<c:forEach items="${tareas}" var="tarea">
   <tr>
      <td>${tarea.nombre}</td>
      <td>${tarea.fechaTope}</td>
      <td>${tarea.fechaFinalizacion}</td>
      <td>
      	<form action="/TrabajoFinal/programador/VerTareas" method="POST">
      		<input type="hidden" name="tr_id" value="${tarea.id}" />
      		<button>Finalizar tarea</button>
      	</form>
      </td>
   </tr>
</c:forEach>
</table>

</body>
</html>