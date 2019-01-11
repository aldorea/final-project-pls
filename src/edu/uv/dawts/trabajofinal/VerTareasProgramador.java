package edu.uv.dawts.trabajofinal;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

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
		
		try {
			String user = request.getRemoteUser();
			String rol = ad.getRol(user);
			ArrayList<Tarea> tareas = ad.getTareasUsuario(user);
			
			Date date = new Date();
			LocalDate today = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			request.setAttribute("tareas", tareas);
			request.setAttribute("today", today);

			getServletContext().getRequestDispatcher("/programador/verTareas.jsp").forward(request,
					response);
		} catch (Exception e1) {
			request.setAttribute("msg",
					"Se ha producido un error interno al crear el proyecto");
			getServletContext().getRequestDispatcher("/errorPage.jsp").forward(
					request, response);
			e1.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccesoDatos ad = (AccesoDatos) getServletContext().getAttribute("bd");
		Date today = new Date();
		LocalDate localDate = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	
		
		int year = localDate.getYear();
		int mes = localDate.getMonthValue();
		int dia = localDate.getDayOfMonth();
		int tr_id = Integer.parseInt(request.getParameter("tr_id"));
		
		
		try {
			ad.setFechaFinalizacion(year, mes, dia, tr_id);
			doGet(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
