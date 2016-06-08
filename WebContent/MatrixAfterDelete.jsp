<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="iit.djikstras.cn1.DeleteNode"%>"
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> <%-- Head of the JSP --%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>Updated Matrix</title> <%-- Title of the Web Page --%>
			
			<style type="text/css">				<%-- Style Section describes how the contents on the webpage are displayed --%>
				
		<%-- Body style of the webpage --%>
				body {
					margin: 0; 					/* No Margin Space Set */
					padding: 0;				    /* No Padding Set */
					overflow: hidden; 			/* Hide The overflow */
					height: 100%; 				/* Sets the height of the body container */
					max-height: 100%;		    /* Sets the max height of the body container */
					font-family:Sans-serif; 	/* Sets the font style of the text */
					line-height: 1.5em; 		/* Sets the line height */
				}
				
		<%-- Style of the Main portion of the webpage --%>
				main {
					position: absolute; 		/*Sets the type of positioning method used for an element*/
					top: 50px; 					/* Sets the height of the header*/
					bottom: 50px; 				/* Sets the height of the footer */
					left: 100px; 				/* Sets the width of the left column */
					right: 230px;				/* Sets the width of the right column */
					background: #fff;		 	/* Sets the background colour */
				}
			
		<%-- header style of the webpage --%>			
				#header {
					position: absolute;			/*Sets the type of positioning method used for an element*/
					top: 0;						/* Sets the height of the header*/
					left: 0;					/* Sets the width of the left column */
					width: 100%;				/* Sets the width of the header */
					height: 50px; 				/* Sets the height of the header */
					-webkit-border-radius: 5px; /* Creation of a rounded border */
					border-radius: 150px; 		/* Sets the border radius of area */
					background: #BCCE98; 		/* Sets the background colour */
				}
		
		<%-- footer style of the webpage --%>
				#footer {
					position: absolute;			/*Sets the type of positioning method used for an element*/
					left: 0;					/* Sets the width of the left column */
					bottom: 0;					/* Sets the height of the footer */
					width: 100%;					/* Sets the width of the header */
					height: 50px; 				/* Sets the height of the header */
					border-radius: 150px; 		/* Sets the border radius of area */
					background: #BCCE98; 		/* Sets the background colour */
				}
			
		<%-- navigator style of the webpage --%>			
				#nav {
					position: absolute; 		/*Sets the type of positioning method used for an element*/
					top: 50px; 					/* Set this to the height of the header */
					bottom: 50px; 				/* Set this to the height of the footer */
					right: 0; 					/* Sets the width of the right column */
					width: 300px;				/* Sets the width of the header */
					border-radius: 150px; 		/* Sets the border radius of area */
					background: #DAE9BC; 		/* Sets the background colour */		
				}
				
		<%-- navigator style of the webpage --%>
				.innertube {
					margin: 15px; 				/* Provides padding for the content */
					font-family:monospace;		/* Sets the font style of the text */
					text-align: left;			/* Sets the alignment of the text */
					display: block;				/* Sets the type of display */
					
				}
				
		<%-- table style of the webpage --%>
				#table{		
					position: absolute; 		/*Sets the type of positioning method used for an element*/
					font-family:monospace;		/* Sets the font style of the text */
					font-size:15px;				/* Sets the size of the text */
					top: 50px; 				/* Sets the height of the header */
					bottom: 50px;				/* Sets the height of the footer */
					width: 800px;				/* Sets the width of the header */
					-webkit-border-radius: 5px; /* Creation of a rounded border */
					border-radius: 150px; 		/* Sets the border radius of area */
				}
				
		<%-- submit button style of the webpage --%>
				input[type=submit] {
					padding:5px 15px;		 	/* Sets the padding */
					background:#ccc; 		 	/* Sets the background colour */
					font-size:15px;		     	/* Sets the font size of the text in the button area */
					border:0 none; 			 	/* Sets the border of the button */
					cursor:pointer;			 	/* Sets the cursor type */
					-webkit-border-radius: 5px; /* Creation of a rounded border */
					font-family:monospace;		/* Sets the font style of the text */
					border-radius: 15px; 		/* Sets the border radius of the button */
				}
				
		<%-- reset button style of the webpage --%>
				input[type=reset] {
					padding:5px 15px; 
					background:#ccc; 			/* Sets the background colour */
					font-size:15px; 			/* Sets the font size of the text in the button area */
					border:0 none; 				/* Sets the border of the button */
					cursor:pointer; 			/* Sets the cursor type */
					-webkit-border-radius: 5px; /* Creation of a rounded border */
					font-family:monospace;		/* Sets the font style of the text */border-radius: 15px; 		/* Sets the border radius of the button */
				}
		</style>	
	</head>
	
	<%-- Body Section of the webpage--%>
	<body>
	
		<%-- Creation of the header and associating it with the style above--%>
		<header id="header">
			<div class="innertube">
				<center><h1><i>Link State Routing Algorithm Simulation</i></h1></center>
			</div>
		</header>
		
		<%-- Creation of the main area of the webpage and associating it with the style above--%>
		<main>
			<div class="innertube">
			<table id="table" >
  				<tr>
    				<th>
    					<h1><i>The Matrix is as below:</i></h1>
    				</th>
  				</tr>
  				<tr>
  					<th>
  						<%-- Display The Topology--%>
    					<%
    						//load the Matrix
    						int[][] manage_matrix = (int[][])request.getServletContext().getAttribute("matrix");
    						int[][] updated_matrix=null;
    						//Get the node that has to be deleted
    						int row_num=(int)request.getAttribute("delete_node");
    							
    						DeleteNode del_node = new DeleteNode();
    						updated_matrix = del_node.delete_node(manage_matrix, row_num);
    						request.getServletContext().setAttribute("matrix",updated_matrix);
    						int[][] matrix =(int[][]) request.getServletContext().getAttribute("matrix");
							if(matrix!=null){
    							
    							for(int i=0;i<matrix.length;i++){
    								for(int j=0;j<matrix.length;j++){
    									out.write(matrix[i][j] + "&nbsp&nbsp");
    										}
    									out.print("<br><br>");
    								}
    							request.getServletContext().setAttribute("matrix",updated_matrix);
    						}
    						else
    						{
    							response.sendRedirect("NoFileError.jsp");
      	  						return;
    						}
    						
    						%>
					</th>
 				</tr>
 				
 				<tr>
  					<th><%-- Creation of a form, the actions executed after an event--%>
    					<form action="ConnectionTable_afterDel.jsp" method="post">
    						<h2><i>
    							<input type="submit" value="Connection Tables" >
							</i></h2>
						</form>
					</th>
 				</tr>
  
		</table>
			</div>
		</main>
		
		<%-- Creation of navigator--%>
		<nav id="nav">
		
			<div class="innertube">
			</div>
		</nav>	
		
		<%-- Creation of footer--%>
		
		<footer id="footer">
			<div class="innertube" >
				<center><h1><i>
					<iframe src="http://free.timeanddate.com/clock/i4xapfgx/n64/fn7/fs16/tcbcce98/pcbcce98/ftb/th2" frameborder="0" width="108" height="25"></iframe>
				</i></h1></center>
			</div>
		</footer>
		
</body>
</html>