package edu.uv.dawts.trabajofinal;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerTareasProgramador
 */
@WebServlet("/programador/VerTareas")
public class VerTareasProgramador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerTareasProgramador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccesoDatos ad = (AccesoDatos) getServletContext().getAttribute("bd");
		
		String user = request.getParameter("username");
		
		try {
			ArrayList<Tarea> tareas = ad.getTareasUsuario(user);
			request.setAttribute("tareas", tareas);

			getServletContext().getRequestDispatcher("/programador/verTareas.jsp").forward(request,
					response);

		} catch (Exception e) {
			request.setAttribute("msg",
					"Se ha producido un error interno al crear el proyecto");
			getServletContext().getRequestDispatcher("/errorPage.jsp").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
