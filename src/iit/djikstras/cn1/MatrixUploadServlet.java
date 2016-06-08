package iit.djikstras.cn1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class MatrixUploadServlet
 */
@WebServlet("/matrixUploadServlet")
public class MatrixUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private File delete_directory = null; //Variable to check if file exists in the folder
	private String UPLOAD_DIRECTORY=null;//Variable to store value from config.properties
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatrixUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /** 
     * Function To read value from the config file
     */
    public void init() throws ServletException {
        // TODO Auto-generated method stub
    		
    		/*Read config.properties*/
    	
        	InputStream config =  getClass().getResourceAsStream("/config.properties");
        	Properties prop = new Properties();
        	 
        	try{
        	 	
        		prop.load(config);			//Load the values from config.properties
        	 } 
        	catch(IOException e){
        		
        		e.printStackTrace(); 		//print exception
        	 }
        	
        	//Load value into the declared String variable
        	 UPLOAD_DIRECTORY = prop.getProperty("UPLOAD_DIRECTORY");
        	 delete_directory = new File(UPLOAD_DIRECTORY);
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
		
		// Variable to Check if content is multipart
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		
        if (isMultipart) {
        	
                // Creation of factory for file items
                FileItemFactory factory = new DiskFileItemFactory();

                // Creation of file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                
                try {
                    	// Parsing the request
                	   List<FileItem> multiparts = upload.parseRequest(request);

	                   for (FileItem item : multiparts) {
	                  
	                	   if (!item.isFormField()) {
	                		   
	                		   String name = new File(item.getName()).getName();//name of the file to be uploaded
	                		   FileUtils.cleanDirectory(delete_directory); //Clean the directory before uploading the file
	                		   item.write(new File(UPLOAD_DIRECTORY + File.separator + name));//Write into the directory
	                   
	                		   //Store the name of the directory and file for future access
	                		   request.getServletContext().setAttribute("upload_directory",UPLOAD_DIRECTORY + File.separator + name);
	                   		}
	                   	}
                	} 
                catch (FileNotFoundException e1) 
                {
                	//If file is not found, handle the exception
                	delete_file((String) request.getServletContext().getAttribute("upload_directory"));
        			response.sendRedirect("FileError.jsp");
        			return;
     				
                } 
                catch (Exception e) {
                		
                	//Handle any other exceptions
						e.printStackTrace();
				}
             } 
        	//Check if the entered matrix is of the expected format
        	checkMatrixFormat((String) request.getServletContext().getAttribute("upload_directory"),request,response);
        
	}
	/**
	 * Function to Check if the input is in the expected format
	 * @param file_Path
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void checkMatrixFormat(String file_Path,HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
		String fileLine = null; //Line in the file
		String matrix_elements[] = null; //Elements that are entered in the matrix from the file
        
		int rowCount = 0; //Number of rows
        int colCount = 0; //Number of columns
        int col_temp = 0; //To store the coloumns temporarily
        int diagonal_pos = 0;//To Check if there are 0's in the diagonal
        
        //Read the File
        BufferedReader br= new BufferedReader(new FileReader(file_Path));
        
        while((fileLine= br.readLine())!= null){
        	
        	rowCount++; //Incremented with each line
			matrix_elements = fileLine.split(" "); //Split the lines using white space as the delimiter
			colCount = matrix_elements.length;  
			diagonal_pos = rowCount-1;
			
			/* If the diagonals have an entry other than 0, 
			 * then delete the file in the directory and throw an error 
			 */
			
			if((col_temp!=0 && col_temp!=colCount) || (!matrix_elements[diagonal_pos].equals("0"))){
				
				delete_file(file_Path);
				response.sendRedirect("FileError.jsp");
				br.close();
				return;
			}
        	
			col_temp = colCount;
        }
        
        
        /* If the matrix is not a square matrix
         *  then delete the file in the directory and throw an error 
         */
		if(colCount != rowCount){
			try{
			
				delete_file(file_Path);
				response.sendRedirect("FileError.jsp");
				br.close();
				return;
			}
		 /* In case of any exception, takes the user to the FileError page
	      */
			catch (FileNotFoundException e){
				
				request.getRequestDispatcher("FileError.jsp").forward(request, response);
            }
		}
        
        else {
        	/* If the matrix has been uploaded successfully, 
        	 * then the user is taken to the Matrix Successful page
	         */
			request.getRequestDispatcher("MatrixSuccessfulUpload.jsp").forward(request, response);
		}
		//Close the opened reader
		br.close();
        
	}
	/** 
	 * Function to delete previously existing Files 
	 * @param file_Path
	 */
	public void delete_file(String file_Path){
		File file = new File(file_Path);
			if (file.exists()){
					file.delete();
			}
		}
}
