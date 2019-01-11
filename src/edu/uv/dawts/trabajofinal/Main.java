package edu.uv.dawts.trabajofinal;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main
 */
@WebServlet("/index")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccesoDatos ad = (AccesoDatos) getServletContext().getAttribute("bd");
		
		try {
			if (request.getRemoteUser() != null) {
				
				Role user = new Role();
				String test = request.getRemoteUser();
				user.setUsername(test);
				System.out.println("User: print " + test);
				String rol = ad.getRol(test);
				user.setRol(rol);
				
				request.setAttribute("user",user);
				
				System.out.println("ROL iMPRIMIENDO"  );
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
				
//				if (rol.equals("jefe_proyecto")) {				
//					getServletContext().getRequestDispatcher("/jefeproyecto/muestraProyectos.jsp").forward(request, response);
//				} else {
//					getServletContext().getRequestDispatcher("/programador/verTareas.jsp").forward(request, response);				
//				}
			
			}else {
				getServletContext().getRequestDispatcher("/login.html").forward(request, response);	
			}
			


		} catch (Exception e) {
			request.setAttribute("msg",
					"Se ha producido un error interno al crear el proyecto");
			getServletContext().getRequestDispatcher("/errorPage.jsp").forward(
					request, response);
			e.printStackTrace();
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
