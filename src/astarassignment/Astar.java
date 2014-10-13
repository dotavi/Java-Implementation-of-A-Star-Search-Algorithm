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
	Set<N> closedlist = new HashSet<N>();		//create the closed list of nodes, initially empty 
	Map<N, N> pathmap = new HashMap<N, N>();
	List<N> route = new LinkedList<N>();
	
	Map<N, Double> gVal = new HashMap<N, Double>();
	final Map<N, Double> fVal = new HashMap<N, Double>();
	
	PriorityQueue<N> openlist = new PriorityQueue<N>(11, new Comparator<N>() {
 		public int compare(N nodeA, N nodeB) {
			return Double.compare(fVal.get(nodeA), fVal.get(nodeB));
		}
	});
 
	gVal.put(start, 0.0);
	fVal.put(start, start.calcHeuristic(goal));
	openlist.offer(start);
 
	while (!openlist.isEmpty()) {			
		N current = openlist.poll();
		if (current.equals(goal)) {
			while (current != null) {
				route.add(0, current);
				current = pathmap.get(current);
			}
			return route;			//Return if goal state is reached
		}
 
		closedlist.add(current);		//Remove node with smallest evaluation function and mark closed
		
		for (N neighbor : current.getNeighbors()) {		
			if (closedlist.contains(neighbor)) {			//If closed contains already visited node, ignore
				continue;
			}
 
			double gDash = gVal.get(current) + current.traversCost(neighbor);
 
			boolean contains = openlist.contains(neighbor);
			if (!contains || gDash < gVal.get(neighbor)) {
				gVal.put(neighbor, gDash);
				fVal.put(neighbor, gDash + neighbor.calcHeuristic(goal));
 
				if (contains) {
					openlist.remove(neighbor);
				}
 
				openlist.offer(neighbor);
				pathmap.put(neighbor, current);
				
				}
			}
		}
 
		return null;
}
 
}