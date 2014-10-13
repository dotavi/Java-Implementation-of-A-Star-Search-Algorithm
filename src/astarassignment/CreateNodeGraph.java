package astarassignment;
 
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 

public class CreateNodeGraph {
private final double[][] map;

public CreateNodeGraph(double[][] map) {
	this.map = map;
}

public List<Node> findPath(int originX, int originY, int destX, int destY) {
	return Astar.aStarSearch(new Node(originX, originY), new Node(destX, destY));
	}
 
//Node, represented by x,y coordinates
public class Node implements NodeInterface<Node> {
		private int x;
		private int y;
		
		public int getX() {
		    return x;
		}
		public void setX(int x) {
		    this.x = x;
		}
		public int getY() {
		    return y;
		}
		public void setY(int y) {
		    this.y = y;}
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	 
		//Calculates the Manhattan Distance
		public double calcHeuristic(Node dest) {
			return Math.abs(x - dest.x) + Math.abs(y - dest.y);
		}
 
		public double traversCost(Node neighbor) {
			return 1 + map[neighbor.x][neighbor.y];
		}
 
		public Set<Node> getNeighbors() {
			Set<Node> neighbors = new HashSet<Node>();
 
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
				
					
					neighbors.add(new Node(i, j));
					
					
				}
			}
		
			return neighbors; 
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
	final int primenum = 31;
	int res = 1;
	res = primenum * res + getOuterType().hashCode();
	res = primenum * res + x;
	res = primenum * res + y;
	return res;
	}
 
@Override
public boolean equals(Object objt) {
	if (this == objt)
		return true;
	if (objt == null)
		return false;
	if (getClass() != objt.getClass())
		return false;
	Node other = (Node) objt;
	if (!getOuterType().equals(other.getOuterType()))
		return false;
	if (x != other.x)
		return false;
	if (y != other.y)
		return false;
	return true;
}
 
private CreateNodeGraph getOuterType() {
	return CreateNodeGraph.this;
}
 
}
 

 
 
}