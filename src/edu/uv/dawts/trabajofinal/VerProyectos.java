package edu.uv.dawts.trabajofinal;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerProyectos
 */
@WebServlet("/jefeproyecto/VerProyectos")
public class VerProyectos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerProyectos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AccesoDatos ad = (AccesoDatos) getServletContext().getAttribute("bd");

		try {
			ArrayList<Proyecto> proyectos = ad.getAllProyectos();

			request.setAttribute("proyectos", proyectos);

			if (request.getHeader("Accept").equals("application/json")) {
				OutputStream out = response.getOutputStream();
				PrintWriter pw = new PrintWriter(out);
				response.setContentType("application/json; charset=utf-8");
				response.flushBuffer();
				Util util = new Util<Tarea>();
				pw.println(util.dataToJson(proyectos));
				pw.flush();
				pw.close();
				out.close();
			} else {
				getServletContext().getRequestDispatcher(
					"/jefeproyecto/muestraProyectos.jsp").forward(request,
					response);
			}
		} catch (Exception ex) {
			request.setAttribute("msg",
					"Se ha producido un error interno al crear el proyecto");
			getServletContext().getRequestDispatcher("/errorPage.jsp").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
