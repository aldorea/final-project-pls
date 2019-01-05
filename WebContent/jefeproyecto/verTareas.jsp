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
 <th> ID </th>
 <th> Nombre </th>
</tr>
<c:forEach items="${tareas}" var="tarea">
   <tr>
      <td>${tarea.nombre}</td>
      <td>${tarea.id}</td>
   </tr>
</c:forEach>
</table>

<h2> Crear una tarea nueva </h2>

<form action="/TrabajoFinal/jefeproyecto/AddTarea" method="POST">
  Nombre de la tarea: <input type="text" name="nombre" />
  Asignar tarea a: <input type="text" name="usuario" />
  Fecha prevista finalización:
  Año (yyyy): <input type="text" name="year" />
  Mes (mm): <input type="text" name="mes" />
  Día (dd): <input type="text" name="dia" />
  <input type="hidden" name="proyecto" value="<%= request.getAttribute("proyecto_id") %>" />
  <input type="submit" value="Crear tarea" />
</form>

</body>
</html>