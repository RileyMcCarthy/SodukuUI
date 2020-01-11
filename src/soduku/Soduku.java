/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soduku;

/**
 *
 * @author Riley McCarthy
 */
public class Soduku {

    /**
     * @param args the command line arguments
     */
    
    private static int[][] tempboard = { {0,0,0,0,8,0,0,0,0},
                              {8,0,9,0,7,1,0,2,0},
                              {4,0,3,5,0,0,0,0,1},
                              {0,0,0,1,0,0,0,0,7},
                              {0,0,2,0,3,4,0,8,0},
                              {7,3,0,0,0,9,0,0,4},
                              {9,0,0,0,0,0,7,0,2},
                              {0,0,8,2,0,5,0,9,0},
                              {1,0,0,0,4,0,3,0,0} };
    
    public static void main(String[] args) {
        // TODO code application logic here
        Board board = new Board(tempboard);
        Solver solver = new Solver(board);
        System.out.println("Soduku Game Generator");
       // board.printBoard();
        solver.solveBoard();
        //System.out.println(board.getQuadrent(1,2));
    }
    
}
