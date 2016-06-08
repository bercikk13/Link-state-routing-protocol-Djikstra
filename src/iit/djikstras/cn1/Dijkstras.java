package iit.djikstras.cn1;

import java.util.ArrayList;
import java.util.List;

public class Dijkstras {
	/**
	 * Method to display the shortest path between the source and the destination routers
	 * @param input_matrix
	 * @param source
	 * @param destination
	 * @return
	 */
	public Object dijkstras(int[][] input_matrix, int source, int destination){
			
		try{
			int index = source;
			int counter = 0 ;
			int k = 1; 
			List<Object> ls = new ArrayList<Object>();
			//Array to hold all the distances from source to destination
			int[] distance_array = input_matrix[source].clone();
			
			//Array to validate if a node has been visited
			boolean[] visit_array = new boolean[input_matrix[0].length];
			
			//Array to hold the path from the source to the destination
			int[] path_array = new int[input_matrix[0].length];

			//Initialize all the elements of path_array to source(or any value)
			for (int i = 0; i < path_array.length; i++) 
			{
				path_array[i] = source;
			}
			
			//Initialize true for source in the visit-array
			visit_array[index] = true;
			
			//Repeat till the maximum number of elements in a row or column of the input matrix
			while (counter < input_matrix[0].length) 
			{
				
				//Initialize a minimum variable to Integer.Max_Value or 9999 or any big number
				int min = Integer.MAX_VALUE;
				
				//Find the minimum element of the distance array 
				for (int m = 0; m < distance_array.length; m++) {
					
					if (!visit_array[m] && distance_array[m] != -1 && m != index) {
				
						if (distance_array[m] < min) {
							min = distance_array[m];
							index = m;
						}
					}
				}
				
				//If a direct path exists, break from the loop
				if (index == destination) 
				{
					break;
				}
				
				//Initialize the index of the minimum element in the visit array as true
				visit_array[index] = true;
				counter++;
				
				//Find the path and cost from the node containing the minimum element
				for (int i = 0; i < distance_array.length; i++) {
					
					if ((distance_array[i] == -1 && input_matrix[index][i] != -1 && ! visit_array[i]))
					{
						distance_array[i] = distance_array[index] + input_matrix[index][i];
						path_array[i] = index;
					}
					
					else if((input_matrix[index][i] != -1 && distance_array[index] + input_matrix[index][i] < distance_array[i])){
						distance_array[i] = distance_array[index] + input_matrix[index][i];
						path_array[i] = index;
					
					}
				}
			
			}
			//Holds the final route
			int[] route = new int[input_matrix.length]; 
			int d = destination;
			route[0]=d;
		
			List<Object> routerList = new ArrayList<Object>();
			while (path_array[d] != source) 
			{
				d = path_array[d];
				route[k] = d;
				k++;
			}
			route[k] = source;
			for (int j = k; j > 0; j--)
			{	
				routerList.add(route[j]+1);
			}
				ls.add(distance_array[source]);
				ls.add(distance_array[destination]);
				ls.add(routerList);
			 return ls;
			 
			
		}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
			
	}

}

