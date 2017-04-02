import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		int rand  = randGen.nextInt(w);
		int rand2  = randGen.nextInt(h);
		
		selectNextPath(maze.getCell(rand, rand2));
		return maze;
	}

	private static void selectNextPath(Cell currentCell) {
		currentCell.setBeenVisited(true);
		int count = 0;
		boolean hasOpenNeighbors = false;
		if ((maze.getCell(currentCell.getX()+1, currentCell.getY())).hasBeenVisited()== true) {
			hasOpenNeighbors = true;
			count++;
		}
		if ((maze.getCell(currentCell.getX(), currentCell.getY()+1)).hasBeenVisited()== true) {
			hasOpenNeighbors = true;
			count++;
		}
		if ((maze.getCell(currentCell.getX()-1, currentCell.getY())).hasBeenVisited()== true) {
			hasOpenNeighbors = true;
			count++;
		}
		if ((maze.getCell(currentCell.getX(), currentCell.getY()-1)).hasBeenVisited()== true) {
			hasOpenNeighbors = true;
			count++;
		}
		Cell [] c = new Cell[count];
		int counter = 0;
		if ((maze.getCell(currentCell.getX()+1, currentCell.getY())).hasBeenVisited()== true) {
			c[counter] =maze.getCell((currentCell.getX()+1), (currentCell.getY()));
			counter++;
		}
		if ((maze.getCell(currentCell.getX(), currentCell.getY()+1)).hasBeenVisited()== true) {
			c[counter] =maze.getCell((currentCell.getX()), (currentCell.getY()+1));
			counter++;
		}
		if ((maze.getCell(currentCell.getX()-1, currentCell.getY())).hasBeenVisited()== true) {
			c[counter] =maze.getCell((currentCell.getX()-1), (currentCell.getY()));
			counter++;
		}
		if ((maze.getCell(currentCell.getX(), currentCell.getY()-1)).hasBeenVisited()== true) {
			c[counter] =maze.getCell((currentCell.getX()), (currentCell.getY()-1));
			counter++;
		}
		
		// if has unvisited neighbors,
			// select one at random.
			// push it to the stack
			// remove the wall between the two cells
			// make the new cell the current cell and mark it as visited
		
			//call the selectNextPath method with the current cell
		if (hasOpenNeighbors) {
			int r = randGen.nextInt(count);
			for (Cell cell : c) {
				if (c[r].equals(cell)) {
					System.out.println("hello");
					cell.setBeenVisited(true);
					removeWalls(c[r],currentCell);
					selectNextPath(c[r]);
				}
				else{
					uncheckedCells.push(cell);
				}
			}
		}
		// if all neighbors are visited
			//if the stack is not empty
				// pop a cell from the stack
				// make that the current cell
		
				//call the selectNextPath method with the current cell
		
		if (!hasOpenNeighbors) {
			if (!uncheckedCells.isEmpty()) {
				Cell x = uncheckedCells.pop();
				selectNextPath(x);
			}
		}
	}

	private static void removeWalls(Cell c1, Cell c2) {
		if (c2.getX()>c1.getX()) {
			c2.setEastWall(false);
			c1.setWestWall(false);
		}
		if (c2.getX()<c1.getX()) {
			c2.setWestWall(false);
			c1.setEastWall(false);
		}
		if (c2.getY()<c1.getY()) {
			c2.setNorthWall(false);
			c1.setSouthWall(false);
		}
		if (c2.getY()>c1.getY()) {
			c2.setSouthWall(false);
			c1.setNorthWall(false);
		}
	}

	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		return null;
	}
}