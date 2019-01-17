package edu.uv.dawts.trabajofinal;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class AddTarea
 */
@WebServlet("/jefeproyecto/AddTarea")
public class AddTarea extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddTarea() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		int proyecto = Integer.parseInt(request.getParameter("proyecto"));
		String usuario = request.getParameter("usuario");
		int year = Integer.parseInt(request.getParameter("year"));
		int mes = Integer.parseInt(request.getParameter("mes"));
		int dia = Integer.parseInt(request.getParameter("dia"));
		AccesoDatos ad = (AccesoDatos) getServletContext().getAttribute("bd");

		try {
			ad.creaTarea(nombre, proyecto, usuario, year, mes, dia);
			ArrayList<Tarea> tareas = ad.getAllTareas(proyecto);

			request.setAttribute("tareas", tareas);

			if (request.getHeader("Accept").equals("application/json")) {
				OutputStream out = response.getOutputStream();
				 PrintWriter pw = new PrintWriter(out);
				 
				response.setContentType("application/json; charset=utf-8");
				response.flushBuffer();
				Util util = new Util<Tarea>();
				 pw.println(util.dataToJson(tareas));
				 pw.flush();
				 pw.close();
				 out.close();

			} else {
				response.setContentType("text/html; charset=utf-8");
				getServletContext().getRequestDispatcher("/jefeproyecto/verTareas.jsp").forward(request, response);
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
			request.setAttribute("msg",
					"Se ha producido un error interno al crear el proyecto");
			getServletContext().getRequestDispatcher("/errorPage.jsp").forward(
					request, response);
		}

	}

}
