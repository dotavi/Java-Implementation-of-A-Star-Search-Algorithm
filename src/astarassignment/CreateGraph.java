package astarassignment;
 
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 

public class CreateGraph {
private final double[][] map;

public CreateGraph(double[][] map) {
	this.map = map;
}

public List<MapNode> findPath(int xStart, int yStart, int xGoal, int yGoal) {
	return Astar.aStarSearch(new MapNode(xStart, yStart), new MapNode(xGoal, yGoal));
	}
 
//Node, represented by x,y coordinates
public class MapNode implements NodeInterface<MapNode> {
		private final int x, y;
	 
		public MapNode(int x, int y) {
			this.x = x;
			this.y = y;
		}
	 
		//Calculates the Manhattan Distance
		public double calcHeuristic(MapNode goal) {
			return Math.abs(x - goal.x) + Math.abs(y - goal.y);
		}
 
		public double traversalCost(MapNode neighbour) {
			return 1 + map[neighbour.x][neighbour.y];
		}
 
		public Set<MapNode> getNeighbours() {
			Set<MapNode> neighbours = new HashSet<MapNode>();
 
			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					if ((i == x && j == y) || i < 0 || j < 0 || j >= map.length || i >= map[j].length) {
						continue; 	//End this loop iteration, and increment j
					}
					if ( ((i < x && j < y) || (i > x && j > y))) {		//Here we ignore the NW and SE neighbours
						continue;
						}
					if ( ((i > x && j < y) || (i < x && j > y))) {		//Here we ignore the NE and SW neighbours
						continue;
						}
					if (map[i][j] == 9) {								//WATER!! IGNORE
						continue;	//End this loop iteration, and increment j
					}
				
					
					neighbours.add(new MapNode(i, j));
					
					
				}
			}
		
			return neighbours; 
		}
 
@Override
public String toString() {
return "(" + x + ", " + y + ")";
}

//
//The hashcode function is used for the hashmap where the entries are stored. 
//The prime 31 is a standard prime to generate a good hash code.

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + getOuterType().hashCode();
	result = prime * result + x;
	result = prime * result + y;
	return result;
	}
 
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	MapNode other = (MapNode) obj;
	if (!getOuterType().equals(other.getOuterType()))
		return false;
	if (x != other.x)
		return false;
	if (y != other.y)
		return false;
	return true;
}
 
private CreateGraph getOuterType() {
	return CreateGraph.this;
}
 
}
 

 
 
}