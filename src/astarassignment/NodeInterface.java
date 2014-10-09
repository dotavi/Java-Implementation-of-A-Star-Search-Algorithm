package astarassignment;

import java.util.Set;

//We have now modelled the node and all actions that can be 
//effected on each node
public interface NodeInterface<N> {
	
	//Calc the Manhattan Distance from the current 
	double calcHeuristic(N goal);

	double traversalCost(N neighbour);
	
	//This method creates a list of the neighbours of the current node being examined. Tests are performed to remove the diagonal neighbours, 
	//as these are not relevant for this implementation 
	Set<N> getNeighbours();


}
