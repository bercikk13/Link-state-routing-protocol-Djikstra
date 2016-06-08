package iit.djikstras.cn1;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OptionSelectServlet
 */
@WebServlet("/optionSelectServlet")
public class OptionSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OptionSelectServlet() {
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
		
		/**
		 *  Get the Parameter chosen by the user from Welcome.jsp
		 */
		String option = request.getParameter("Option"); 
		
		
		/**
		 * Navigate to one of the below JSP's depending on the value of the "option"
		 */
		
		//Go to Matrix Upload Page if user selects option 1
		if(option.equals("Option_1")){
			
			RequestDispatcher rd=request.getRequestDispatcher("MatrixTopology.jsp");  
	        rd.forward(request,response); 
		}
		
		
		//Go to Manage Matrix Page if user selects option 3
		else if(option.equals("Option_3")){
			
			RequestDispatcher rd=request.getRequestDispatcher("DeleteNode.jsp");  
	        rd.forward(request,response); 
		}
		
		//Go to View Connection table Page if user selects option 4
		else if(option.equals("Option_4")){
			
			try{
				
			File file = new File((String)request.getServletContext().getAttribute("upload_directory"));
				if (file.exists()){
					RequestDispatcher rd=request.getRequestDispatcher("ConnectionTable.jsp"); 
					rd.forward(request,response); 
				}
				else {
					RequestDispatcher rd=request.getRequestDispatcher("NoFileError.jsp"); 
					rd.forward(request,response); 
				}
			}
			
			catch(NullPointerException e){
				
				RequestDispatcher rd=request.getRequestDispatcher("NoFileError.jsp"); 
				rd.forward(request,response); 
			}
		}
		//Go to Dijkstras page if user selects option 5
		else if(option.equals("Option_5")){
			
			try{
				
			File file = new File((String)request.getServletContext().getAttribute("upload_directory"));
				if (file.exists()){
					RequestDispatcher rd=request.getRequestDispatcher("Dijkstras.jsp"); 
					rd.forward(request,response); 
				}
				else {
					RequestDispatcher rd=request.getRequestDispatcher("NoFileError.jsp"); 
					rd.forward(request,response); 
				}
			}
			
			catch(NullPointerException e){
				
				RequestDispatcher rd=request.getRequestDispatcher("NoFileError.jsp"); 
				rd.forward(request,response); 
			}
		}
		//Go to View Exit Page if user selects option 6
		else if(option.equals("Option_6")){
			
			RequestDispatcher rd=request.getRequestDispatcher("ExitPage.jsp"); 
			rd.forward(request,response);
		}
	}

}
