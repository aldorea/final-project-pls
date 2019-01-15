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
 * Servlet implementation class AddProyecto
 */
@WebServlet("/jefeproyecto/AddProyecto")
public class AddProyecto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProyecto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nombreProyecto = request.getParameter("nombre");
		AccesoDatos ad = (AccesoDatos) getServletContext().getAttribute("bd");
		OutputStream out = response.getOutputStream();
		PrintWriter pw = new PrintWriter(out);

		try {
			ad.creaProyecto(nombreProyecto);
			ArrayList<Proyecto> proyectos = ad.getAllProyectos();

			request.setAttribute("proyectos", proyectos);

			if (request.getHeader("Accept").equals("application/json")) {
				response.setContentType("application/json; charset=utf-8");
				response.flushBuffer();
				Util util = new Util<Tarea>();
				pw.println(util.dataToJson(proyectos));
			} else {
				response.setContentType("text/html; charset=utf-8");
				getServletContext().getRequestDispatcher("/jefeproyecto/muestraProyectos.jsp").forward(request, response);
			}
		} catch (Exception ex) {
			request.setAttribute("msg",
					"Se ha producido un error interno al crear el proyecto");
			getServletContext().getRequestDispatcher("/errorPage.jsp").forward(
					request, response);
		} finally {
			pw.flush();
			pw.close();
		}

	}

}
