/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soduku;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Riley McCarthy
 */
public class Solver {
    
    private Board board;
    
    public Solver (Board theBoard) {
        board = theBoard;
    }
    
    public void solveBoard() {
        Board solved = solveRecursivly(board, 0);
        if (solved != null) {
            solved.printBoard();
        }else {
            System.out.println("solving failed!");
        }
    }
    
    public Board solveRecursivly(Board solvedBoard, int pos) {
        
        //if true the board is solved and we return the complete board
        if (pos == 81) {
            System.out.println("Complete!");
            return solvedBoard;
        }

        //skips any original cells
        if (solvedBoard.isOriginal(pos)) {
            return solveRecursivly(solvedBoard,pos+1);
        }else {
            //if cell has no more possible options for values then this branch is dead
            int length = solvedBoard.getCellOptions(pos).size();
            if (length == 0) {
                //branch is dead so reset cell value and return null
                solvedBoard.setCellValue(pos, 0);
                return null;
            }
        }
        
        //try each cell option until branch succeds
        ArrayList<Integer> options = solvedBoard.getCellOptions(pos);
        for (Integer num : options) {
           solvedBoard.setCellValue(pos,num);
           Board temp = solveRecursivly(solvedBoard,pos+1);
           if (temp != null) {
               return temp;
           }
        }
        
        /*
          loop exits meaning no succesful branch was found, this branch is dead
          and will be reset 
        */
        solvedBoard.setCellValue(pos, 0);
        return null;
    }
    
}
