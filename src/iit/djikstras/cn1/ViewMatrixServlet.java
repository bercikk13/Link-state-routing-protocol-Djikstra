package iit.djikstras.cn1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewMatrixServlet
 */
@WebServlet("/ViewMatrixServlet")
public class ViewMatrixServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMatrixServlet() {
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
		
		ViewMatrix mat = new ViewMatrix();
		String filePath = (String) request.getServletContext().getAttribute("upload_directory");
		int[][] matrix = mat.matrix(filePath);
		request.getServletContext().setAttribute("matrix",matrix);
		
		if(matrix.length!=0){
			RequestDispatcher rd=request.getRequestDispatcher("ViewMatrix.jsp");  
			rd.forward(request,response);
		}
		
	}

}
