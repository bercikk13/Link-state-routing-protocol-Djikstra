package iit.djikstras.cn1;

import java.io.BufferedReader;
import java.io.FileReader;

public class ViewMatrix {
	/**
	 * Function to display the matrix on the web page
	 * @param file_Path
	 * @return
	 */
	public int[][] matrix(String file_Path){
		try{
				int rowCount = 0; // To get the total number of rows
				int final_matrix[][] = null; //Returns the final matrix
				int temp = 0;
				String rows = null; //Number of rows in the matrix
				@SuppressWarnings("unused")
				String fileLine = null; // Entry in the file

					//Read the File
					BufferedReader br= new BufferedReader(new FileReader(file_Path));
					
					//The total row count is same as the number of lines
					while((fileLine= br.readLine())!= null)
						{
							rowCount++;
						}
					
					final_matrix = new int[rowCount][rowCount];
					
					
					//Close the reader
					br.close();
					
					//Read the File
					BufferedReader br1= new BufferedReader(new FileReader(file_Path));
					
					//Move the elements of file to the new matrix to display
					while((rows = br1.readLine()) != null)
						{
							String matrix_diplay_entry[] = rows.split(" ");
							for(int i = 0; i< rowCount; i++)
								{
									final_matrix[temp][i] = Integer.parseInt(matrix_diplay_entry[i]);
								}
							
							temp++;
						}
					
					
				//Close the reader
					br1.close();
				return final_matrix;
				}
				catch (Exception e){
					return null;
				}
		
	}
}
