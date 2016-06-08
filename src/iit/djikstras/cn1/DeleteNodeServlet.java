package iit.djikstras.cn1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteNodeServlet
 */
@WebServlet("/deleteNodeServlet")
public class DeleteNodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNodeServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		try{
			/**
			 *  Get the Parameter chosen by the user from Welcome.jsp
			 */
			int option = Integer.parseInt(request.getParameter("Option"));
			request.getServletContext().setAttribute("del_node",option);
			
			//To store the de-activated variables
			//List deact_node = new ArrayList();
			/**
			 *  Parameter to verify if the node entered is a valid node
			 */
			
			int[][] matrix_topology=(int[][]) request.getServletContext().getAttribute("matrix");
			if((matrix_topology.length<option)){
				RequestDispatcher rd=request.getRequestDispatcher("InvalidDeleteNode.jsp");  
				rd.forward(request,response);
			}
			else{
				request.setAttribute("delete_node", option);
				RequestDispatcher rd=request.getRequestDispatcher("MatrixAfterDelete.jsp");  
				rd.forward(request,response);
			}
		}
		
		catch(NullPointerException e1){
			RequestDispatcher rd=request.getRequestDispatcher("NoFileError.jsp");  
			rd.forward(request,response);
		}
		catch(Exception e){
			RequestDispatcher rd=request.getRequestDispatcher("InvalidDeleteNode.jsp");  
			rd.forward(request,response);
		}
	}

}
