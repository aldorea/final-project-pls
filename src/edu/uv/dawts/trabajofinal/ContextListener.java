package edu.uv.dawts.trabajofinal;

import java.sql.Connection;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {
	@Resource(lookup="java:jboss/datasource/Laboratorio2")
	private DataSource ds;
	private AccesoDatos ad;

	/**
	 * Default constructor.
	 */
	public ContextListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Coloca una instancia de AccesoDatos en el ámbito aplicación
	 * (ServletContext) De este modo todos los componentes pueden acceder a
	 * ella.
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		try {
			Connection con = ds.getConnection();
			ad = new AccesoDatos(con);
			arg0.getServletContext().setAttribute("bd", ad);
		} catch (Exception ex) {
			System.out.println("Error creating instance AccesoDatos");
		}
	}

	/**
	 * Al destruir el contexto se liberan los recursos asociados a AccesoDatos
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		AccesoDatos ad = (AccesoDatos) arg0.getServletContext().getAttribute("bd");
		if (ad != null) {
			try {
				ad.closeAll();
			} catch (Exception ex) {
				System.out
						.println("Error while closing Statements and PreparedStatements");
			}

		}
	}

}
