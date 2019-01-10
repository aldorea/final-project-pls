

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uv.dawts.trabajofinal.AccesoDatos;

/**
 * Servlet implementation class Main
 */
@WebServlet("/index")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private String rol;
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
			String user = request.getRemoteUser();
			this.setRol(ad.getRol(user));
			request.setAttribute("user", user);
			request.setAttribute("rol", this.getRol());
			
			System.out.println(user);
			System.out.println(rol);
			
//			if (rol.equals("jefe_proyecto")) {				
//				getServletContext().getRequestDispatcher("/jefeproyecto/muestraProyectos.jsp").forward(request, response);
//			} else {
//				getServletContext().getRequestDispatcher("/programador/verTareas.jsp").forward(request, response);				
//			}
			
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

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

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	

}
