package tfg.k8s.gui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Results
 */
@WebServlet("/Results")
public class ResultsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Results r = new Results();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
			try {
				String peticionesTotales = request.getParameter("peticionesTotales").toString();
				String peticionesConcurrentes = request.getParameter("peticionesConcurrentes").toString();
				String peticionesPorSegundo = request.getParameter("peticionesPorSegundo").toString();
				String servicio = request.getParameter("servicio");
				String namespace = request.getParameter("namespace");
				String deployment = request.getParameter("deployment");
				String hpa = request.getParameter("hpa");
				
				Peticion p = new Peticion(peticionesTotales, peticionesConcurrentes, 
						peticionesPorSegundo, servicio, namespace, deployment, hpa);
				//Results r = new Results(p);
				//System.out.println("peticion = "+p.toString());
				
				//response.sendRedirect("Results.jsp");
				request.setAttribute("peticion", p);
				request.getSession().setAttribute("arrayHPA", Results.arrayHPA);
				request.getRequestDispatcher("Results.jsp").forward(request, response);
	        } catch (NullPointerException e) {
	            System.out.print("Caught the NullPointerException");
	            //return;
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
