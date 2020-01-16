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

    Solver solver;

    private static int[][] tempboard = { {0,0,0,0,8,0,0,0,0},
                              {8,0,9,0,7,1,0,2,0},
                              {4,0,3,5,0,0,0,0,1},
                              {0,0,0,1,0,0,0,0,7},
                              {0,0,2,0,3,4,0,8,0},
                              {7,3,0,0,0,9,0,0,4},
                              {9,0,0,0,0,0,7,0,2},
                              {0,0,8,2,0,5,0,9,0},
                              {1,0,0,0,4,0,3,0,0} };

    public Soduku() {
        Board board = new Board(tempboard);
        solver = new Solver(board);
        solver.solveBoard();
    }

    public void importBoard() {

    }

    public Cell getNextStep() {
        return solver.getNextStep();
    }

    public Board getSolvedBoard() {
        return solver.getBoard();
    }
    
}
