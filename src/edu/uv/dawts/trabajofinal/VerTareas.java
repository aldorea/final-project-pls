package edu.uv.dawts.trabajofinal;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class VerTareas
 */
@WebServlet("/jefeproyecto/VerTareas")
public class VerTareas extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerTareas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccesoDatos ad = (AccesoDatos) getServletContext().getAttribute("bd");
		HttpSession session = request.getSession();

		int id = Integer.parseInt(request.getParameter("id_pr"));
		String nombre = request.getParameter("nombre_pr");

		try {
			ArrayList<Tarea> tareas = ad.getAllTareas(id);
			request.setAttribute("tareas", tareas);
			session.setAttribute("proyecto_id", id);

			getServletContext().getRequestDispatcher("/jefeproyecto/verTareas.jsp").forward(request,
					response);

		} catch (Exception e) {
			request.setAttribute("msg",
					"Se ha producido un error interno al crear el proyecto");
			getServletContext().getRequestDispatcher("/errorPage.jsp").forward(
					request, response);
		}

	}

}