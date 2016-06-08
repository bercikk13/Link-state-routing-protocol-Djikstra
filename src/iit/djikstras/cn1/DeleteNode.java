package iit.djikstras.cn1;

public class DeleteNode {
/* Function to 
 * delete the node from the matrix topology
 */
	public int[][] delete_node(int[][] matrix,int row_num){
			//the final matrix
		   int final_matrixArr[][] = new int[matrix.length][matrix.length];
		   
		   //Copy All the original matrix elements to this matrix ,except for the deleted node elements 
	        int p = 0;
	        for( int i = 0; i < matrix.length; ++i)
	        {
	            int q = 0;
	            for( int j = 0; j < matrix.length; ++j)
	            {
	                final_matrixArr[p][q] = matrix[i][j];
	                if((i==row_num-1) || (j==row_num-1)) {
	                	  final_matrixArr[p][q]=-1;
	                  }

	                ++q;
	            }

	            ++p;
	        }
	        return final_matrixArr;
	}
}
