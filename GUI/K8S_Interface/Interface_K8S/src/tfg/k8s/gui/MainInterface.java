package tfg.k8s.gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class MainInterface
 */
@WebServlet("/MainInterface")
public class MainInterface extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static ArrayList<NamespaceK8S> namespacesArray = new ArrayList<NamespaceK8S>();
	public static ArrayList<DeploymentK8S> deploymentsArray = new ArrayList<DeploymentK8S>();
	public static ArrayList<HpaK8S> hpaArray = new ArrayList<HpaK8S>();
	public static ArrayList<ServiceK8S> servicesArray = new ArrayList<ServiceK8S>();
	
	public static ReadJSON rj = new ReadJSON();
	
    /**
     * @throws ParseException 
     * @throws IOException 
     * @throws FileNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public MainInterface() throws FileNotFoundException, IOException, ParseException {
        super();
        if(checkCluster()) {
        	initializeData();
        }
        
    }
    
    public MainInterface(int flag) {
    	//Constructor vacío para acceder a los métodos
    }
    
    private void initializeData() throws FileNotFoundException, IOException, ParseException {
    	
    	namespacesArray = rj.readNamespace();
    	if(!namespacesArray.isEmpty()) {
    		deploymentsArray = rj.readDeployments(namespacesArray.get(0).getName());
        	hpaArray = rj.readHPA(namespacesArray.get(0).getName());
        	servicesArray = rj.readServices(namespacesArray.get(0).getName());
    	}else {
    		deploymentsArray = rj.readDeployments("");
        	hpaArray = rj.readHPA("");
        	servicesArray = rj.readServices("");
    	}
    	
    }
    
    public void refreshData(HttpServletRequest request) throws FileNotFoundException, IOException, ParseException {
    	String namespace = request.getParameter("namespace");
    	deploymentsArray = rj.readDeployments(namespace);
    	hpaArray = rj.readHPA(namespace);
    	servicesArray = rj.readServices(namespace);
    }
    
    public void refreshData(String namespace) throws FileNotFoundException, IOException, ParseException {
    	deploymentsArray = rj.readDeployments(namespace);
    	hpaArray = rj.readHPA(namespace);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			namespacesArray = rj.readNamespace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		for(NamespaceK8S n : namespacesArray) {
			System.out.println("nombre = "+n.getName());
		}
        request.setAttribute("namespaces", namespacesArray);
        request.getRequestDispatcher("/WEB-INF/MainInterface.jsp").forward(request, response);
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public boolean checkCluster() {
		return true;
		//return rj.checkCluster();
		
	}

}
