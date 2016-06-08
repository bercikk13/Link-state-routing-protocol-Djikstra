package iit.djikstras.cn1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DijkstrasServlet
 */
@WebServlet("/dijkstrasServlet")
public class DijkstrasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DijkstrasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			doGet(request, response);
		
				List<Object> routers = new ArrayList<Object>();
				//Get the user input
				int src= Integer.parseInt(request.getParameter("Source_Node"));
				int dest= Integer.parseInt(request.getParameter("Destinaton_Node"));
				
				//Save the source and the destination in the Servlet context
				request.getServletContext().setAttribute("source",src-1);
				request.getServletContext().setAttribute("destination",dest-1);
				
				//Get the input matrix
				int[][] input_matrix = (int[][])request.getServletContext().getAttribute("matrix");
				
				Dijkstras d = new Dijkstras();
				if(src>input_matrix.length || dest>input_matrix.length){
					RequestDispatcher rd=request.getRequestDispatcher("InvalidNode.jsp");  
					rd.forward(request,response);
				}
				else{
				//Compute Short path
				routers = (List<Object>) d.dijkstras(input_matrix, src-1, dest-1);
				
				//Save the routers in a servlet context that has to be passed to the JSP
				request.getServletContext().setAttribute("routerList",routers.get(2));
				request.getServletContext().setAttribute("matrix_destination",routers.get(1));
				request.getServletContext().setAttribute("matrix_source",routers.get(0));
				RequestDispatcher rd=request.getRequestDispatcher("ShortestPathDisplay.jsp"); 
				rd.forward(request,response);
				}
		}
		catch(Exception e){
			RequestDispatcher rd=request.getRequestDispatcher("InvalidNode.jsp");  
			rd.forward(request,response);
		}
		
	}

}
