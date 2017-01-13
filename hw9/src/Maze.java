/* Maze.java */

import java.util.*;
import set.*;

/**
 *  The Maze class represents a maze in a rectangular grid.  There is exactly
 *  one path between any two points.
 **/

public class Maze {

  // Horizontal and vertical dimensions of the maze.
  protected int horiz;
  protected int vert;
  // Horizontal and vertical interior walls; each is true if the wall exists.
  protected boolean[][] hWalls;
  protected boolean[][] vWalls;

  // Object for generting random numbers.
  private static Random random;

  // Constants used in depth-first search (which checks for cycles in the
  // maze).
  private static final int STARTHERE = 0;
  private static final int FROMLEFT = 1;
  private static final int FROMRIGHT = 2;
  private static final int FROMABOVE = 3;
  private static final int FROMBELOW = 4;
  


  /**
   *  Maze() creates a rectangular maze having "horizontalSize" cells in the
   *  horizontal direction, and "verticalSize" cells in the vertical direction.
   *  There is a path between any two cells of the maze.  A disjoint set data
   *  structure is used to ensure that there is only one path between any two
   *  cells.
   **/
  public Maze(int horizontalSize, int verticalSize) {
    int i, j;

    horiz = horizontalSize;
    vert = verticalSize;
    if ((horiz < 1) || (vert < 1) || ((horiz == 1) && (vert == 1))) {
      return;                                    // There are no interior walls
    }

    // Create all of the horizontal interior walls.  Initially, every
    // horizontal wall exists; they will be removed later by the maze
    // generation algorithm.
    if (vert > 1) {
      hWalls = new boolean[horiz][vert - 1];
      for (j = 0; j < vert - 1; j++) {
        for (i = 0; i < horiz; i++) {
          hWalls[i][j] = true;
        }
      }
    }
    // Create all of the vertical interior walls.
    if (horiz > 1) {
      vWalls = new boolean[horiz - 1][vert];
      for (i = 0; i < horiz - 1; i++) {
        for (j = 0; j < vert; j++) {
          vWalls[i][j] = true;
        }
      }
    }



    /**
     * Fill in the rest of this method.  You should go through all the walls of
     * the maze in random order, and remove any wall whose removal will not
     * create a cycle.  Use the implementation of disjoint sets provided in the
     * set package to avoid creating any cycles.
     *
     * Note the method randInt() further below, which generates a random
     * integer.  randInt() generates different numbers every time the program
     * is run, so that you can make lots of different mazes.
     **/
    //DisjointSets class for each cell of maze
    DisjointSets dSet = new DisjointSets(horiz * vert);
    //int[][] vHWalls = new int[horiz][vert-1];
    //int[][] vVWalls = new int[horiz-1][vert];
    
    int[] randHWalls = new int[horiz * (vert - 1)];
    int[] randVWalls = new int[(horiz - 1) * vert];
    
    //initialize the random numbers
    for(i = 0; i < horiz * (vert - 1); i++){
    	randHWalls[i] = randInt(horiz * (vert - 1));
    	for(int i2 =0; i2 <i; i2++){
    		if(randHWalls[i2] == randHWalls[i]){
    			i--;
    			break;
    		}
    	}
    	// for debug
    	System.out.print(randHWalls[i] + " ");
    }
    
    System.out.println("up is the randHWalls , below is randVWalls:");
    
    for(i = 0; i < (horiz - 1) * vert; i++){
    	randVWalls[i] = randInt((horiz - 1) * vert);
    	for(int i2 =0; i2 <i; i2++){
    		if(randVWalls[i2] == randVWalls[i]){
    			i--;
    			break;
    		}
    	}
    	//for debug
    	System.out.print(randVWalls[i] + " ");
    }
    
    //then begin check the joints
    int numHWalls = horiz * (vert - 1);
    int numVWalls = (horiz - 1) * vert;
    while(numHWalls != 0 || numVWalls != 0)
    {
    	if(numHWalls > 0){
    		int index = randHWalls[numHWalls-1];
    		
    		System.out.println("the index is:" + index);
    		
    		
    		int indexH = index % horiz;
    		int indexV = index / horiz;
    		numHWalls--;
    		//break or not
    		if(dSet.find(index) == index && dSet.find(index + horiz) == index + horiz) {
    			hWalls[indexH][indexV] = false;
    			dSet.union(index, index + horiz);
    		}else if(dSet.find(index) == index ^ dSet.find(index + horiz) == index + horiz) {
    			int a = 0;
    			int b = 0;
    			if(dSet.find(index) != index){ // index go for the root
    				a = dSet.find(index);
    				b = index + horiz;
    			}else { //if(dSet.find(index+horiz) != index+ horiz){ // index + horizthen go for the root 
    				a = dSet.find(index + horiz);
    				b = index;
    			}    			
    			if(a != index && a !=index + horiz){
    				dSet.union(a, b);
    				hWalls[indexH][indexV] = false;
    			}
    		}else {   //if(dSet.find(index) != index && dSet.find(index + horiz) != index + horiz)
    			//
    			int root1 = dSet.find(index);
    			int root2 = dSet.find(index+horiz);
    			if(root1 != root2)	{
    				dSet.union(root1, root2);
    				hWalls[indexH][indexV] = false;
    			}
    		}
    	}	 	
    	if(numVWalls > 0){
    		int index = randVWalls[numVWalls-1];
    		int indexH = index % (horiz - 1);
    		int indexV = index / (horiz - 1);
    		numVWalls--;
    		//break or not
    		//the index of cell and index of vertical wall is not match
    		//to fix that we:
    		int indexC = index + indexV;
    		
    		if(dSet.find(indexC) == indexC && dSet.find(indexC+1) == indexC+1) {
    			vWalls[indexH][indexV] = false;
    			dSet.union(indexC, indexC + 1);
    		}else if(dSet.find(indexC) == indexC || dSet.find(indexC + 1) == indexC+1) {
    			int a = 0;
    			int b = 0;
    			if(dSet.find(indexC) != indexC){ // then the index have to find its root
    				a = dSet.find(indexC);
    				b = (indexC + 1);
    			}else { //if(dSet.find(indexH * (indexV + 1)) > 0){
    				//then the index+1 have to find its root
    				a = dSet.find(indexC + 1);
    				b = indexC;
    			}
    			if(a != indexC && a != indexC+1){
    				dSet.union(a, b);
    				vWalls[indexH][indexV] = false;
    			}
    		}else {//if(dSet.find(index) == index || dSet.find(index+1) == index+1)	{
    				// both cell are children of trees (same or different)
    			int root1 = indexC;
    			int root2 = indexC + 1;
    			root1 = dSet.find(root1);
    			root2 = dSet.find(root2);
    			if(root1 != root2)	{
    				dSet.union(root1, root2);
    				vWalls[indexH][indexV] = false;
    			}
    		}
    		
    	}
    }
  }

  /**
   *  toString() returns a string representation of the maze.
   **/
  public String toString() {
    int i, j;
    String s = "";

    // Print the top exterior wall.
    for (i = 0; i < horiz; i++) {
      s = s + "--";
    }
    s = s + "-\n|";

    // Print the maze interior.
    for (j = 0; j < vert; j++) {
      // Print a row of cells and vertical walls.
      for (i = 0; i < horiz - 1; i++) {
        if (vWalls[i][j]) {
          s = s + " |";
        } else {
          s = s + "  ";
        }
      }
      s = s + " |\n+";
      if (j < vert - 1) {
        // Print a row of horizontal walls and wall corners.
        for (i = 0; i < horiz; i++) {
          if (hWalls[i][j]) {
            s = s + "-+";
          } else {
            s = s + " +";
          }
        }
        s = s + "\n|";
      }
    }

    // Print the bottom exterior wall.  (Note that the first corner has
    // already been printed.)
    for (i = 0; i < horiz; i++) {
      s = s + "--";
    }
    return s + "\n";
  }

  /**
   * horizontalWall() determines whether the horizontal wall on the bottom
   * edge of cell (x, y) exists.  If the coordinates (x, y) do not correspond
   * to an interior wall, true is returned.
   **/
  public boolean horizontalWall(int x, int y) {
    if ((x < 0) || (y < 0) || (x > horiz - 1) || (y > vert - 2)) {
      return true;
    }
    return hWalls[x][y];
  }

  /**
   * verticalWall() determines whether the vertical wall on the right edge of
   * cell (x, y) exists. If the coordinates (x, y) do not correspond to an
   * interior wall, true is returned.
   **/
  public boolean verticalWall(int x, int y) {
    if ((x < 0) || (y < 0) || (x > horiz - 2) || (y > vert - 1)) {
      return true;
    }
    return vWalls[x][y];
  }

  /**
   * randInt() returns a random integer from 0 to choices - 1.
   **/
  private static int randInt(int choices) {
    if (random == null) {       // Only executed first time randInt() is called
      random = new Random();       // Create a "Random" object with random seed
    }
    int r = random.nextInt() % choices;      // From 1 - choices to choices - 1
    if (r < 0) {
      r = -r;                                          // From 0 to choices - 1
    }
    return r;
  }

  /**
   * diagnose() checks the maze and prints a warning if not every cell can be
   * reached from the upper left corner cell, or if there is a cycle reachable
   * from the upper left cell.
   *
   * DO NOT CHANGE THIS METHOD.  Your code is expected to work with our copy
   * of this method.
   **/
  protected void diagnose() {
    if ((horiz < 1) || (vert < 1) || ((horiz == 1) && (vert == 1))) {
      return;                                    // There are no interior walls
    }

    boolean mazeFine = true;

    // Create an array that indicates whether each cell has been visited during
    // a depth-first traversal.
    boolean[][] cellVisited = new boolean[horiz][vert];
    // Do a depth-first traversal.
    if (depthFirstSearch(0, 0, STARTHERE, cellVisited)) {
      System.out.println("Your maze has a cycle.");
      mazeFine = false;
    }

    // Check to be sure that every cell of the maze was visited.
  outerLoop:
    for (int j = 0; j < vert; j++) {
      for (int i = 0; i < horiz; i++) {
        if (!cellVisited[i][j]) {
          System.out.println("Not every cell in your maze is reachable from " +
                             "every other cell.");
          mazeFine = false;
          break outerLoop;
        }
      }
    }

    if (mazeFine) {
      System.out.println("What a fine maze you've created!");
    }
  }

  /**
   * depthFirstSearch() does a depth-first traversal of the maze, marking each
   * visited cell.  Returns true if a cycle is found.
   *
   * DO NOT CHANGE THIS METHOD.  Your code is expected to work with our copy
   * of this method.
   */
  protected boolean depthFirstSearch(int x, int y, int fromWhere,
                                     boolean[][] cellVisited) {
    boolean cycleDetected = false;
    cellVisited[x][y] = true;

    // Visit the cell to the right?
    if ((fromWhere != FROMRIGHT) && !verticalWall(x, y)) {
      if (cellVisited[x + 1][y]) {
        cycleDetected = true;
      } else {
        cycleDetected = depthFirstSearch(x + 1, y, FROMLEFT, cellVisited) ||
                        cycleDetected;
      }
    }

    // Visit the cell below?
    if ((fromWhere != FROMBELOW) && !horizontalWall(x, y)) {
      if (cellVisited[x][y + 1]) {
        cycleDetected = true;
      } else {
        cycleDetected = depthFirstSearch(x, y + 1, FROMABOVE, cellVisited) ||
                        cycleDetected;
      }
    }

    // Visit the cell to the left?
    if ((fromWhere != FROMLEFT) && !verticalWall(x - 1, y)) {
      if (cellVisited[x - 1][y]) {
        cycleDetected = true;
      } else {
        cycleDetected = depthFirstSearch(x - 1, y, FROMRIGHT, cellVisited) ||
                        cycleDetected;
      }
    }

    // Visit the cell above?
    if ((fromWhere != FROMABOVE) && !horizontalWall(x, y - 1)) {
      if (cellVisited[x][y - 1]) {
        cycleDetected = true;
      } else {
        cycleDetected = depthFirstSearch(x, y - 1, FROMBELOW, cellVisited) ||
                        cycleDetected;
      }
    }

    return cycleDetected;
  }

  /**
   * main() creates a maze of dimensions specified on the command line, prints
   * the maze, and runs the diagnostic method to see if the maze is good.
   */
  public static void main(String[] args) {
    int x = 6;
    int y = 5;

    /**
     *  Read the input parameters.
     */

    if (args.length > 0) {
      try {
        x = Integer.parseInt(args[0]);
      }
      catch (NumberFormatException e) {
        System.out.println("First argument to Simulation is not an number.");
      }
    }

    if (args.length > 1) {
      try {
        y = Integer.parseInt(args[1]);
      }
      catch (NumberFormatException e) {
        System.out.println("Second argument to Simulation is not an number.");
      }
    }

    Maze maze = new Maze(x, y);
    System.out.print(maze);
    maze.diagnose();
  }

}
