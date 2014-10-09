package astarassignment;
 
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
 
//astar Pathfinding Algorithm
public class Astar {
 
//start is the starting node, goal is the end node
	
public static <N extends NodeInterface<N>> List<N> aStarSearch(N start, N goal) {
	Set<N> closed = new HashSet<N>();		//create the closed list of nodes, initially empty 
	Map<N, N> fromMap = new HashMap<N, N>();
	List<N> route = new LinkedList<N>();
	
	Map<N, Double> gValue = new HashMap<N, Double>();
	final Map<N, Double> fValue = new HashMap<N, Double>();
	
	PriorityQueue<N> open = new PriorityQueue<N>(11, new Comparator<N>() {
 		public int compare(N nodeA, N nodeB) {
			return Double.compare(fValue.get(nodeA), fValue.get(nodeB));
		}
	});
 
	gValue.put(start, 0.0);
	fValue.put(start, start.calcHeuristic(goal));
	open.offer(start);
 
	while (!open.isEmpty()) {			
		N current = open.poll();
		if (current.equals(goal)) {
			while (current != null) {
				route.add(0, current);
				current = fromMap.get(current);
				System.out.println("Route" + current);
			}
			return route;			//Return if goal state is reached
		}
 
		closed.add(current);		//Remove node with smallest evaluation function and mark closed
		
		System.out.println("Route" + current);
		
		for (N neighbour : current.getNeighbours()) {		
			if (closed.contains(neighbour)) {			//If closed contains already visited node, ignore
				continue;
			}
 
			double gDash = gValue.get(current) + current.traversalCost(neighbour);
 
			boolean contains = open.contains(neighbour);
			if (!contains || gDash < gValue.get(neighbour)) {
				gValue.put(neighbour, gDash);
				fValue.put(neighbour, gDash + neighbour.calcHeuristic(goal));
 
				if (contains) {
					open.remove(neighbour);
				}
 
				open.offer(neighbour);
				fromMap.put(neighbour, current);
				
				}
			}
		}
 
		return null;
}
 
}