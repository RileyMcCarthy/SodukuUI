/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soduku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author Riley McCarthy
 */
public class Solver {
    
    private Board board;
    private ArrayList<Cell> steps;
    private Iterator<Cell> theSteps;
    
    public Solver (Board theBoard) {
        board = theBoard;
        steps = new ArrayList<Cell>();
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
                steps.add(copyCell(solvedBoard.getCell(pos)));
                return null;
            }
        }
        
        //try each cell option until branch succeds
        ArrayList<Integer> options = solvedBoard.getCellOptions(pos);
        for (Integer num : options) {
           solvedBoard.setCellValue(pos,num);
           steps.add(copyCell(solvedBoard.getCell(pos)));
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
        steps.add(copyCell(solvedBoard.getCell(pos)));
        return null;
    }

    public Cell copyCell(Cell cell) {
        Cell temp = new Cell();
        temp.setRow(cell.getRow());
        temp.setCol(cell.getCol());
        temp.setOptions(cell.getOptions());
        temp.setOriginal(cell.isOriginal());
        temp.setValue(cell.getValue());
        return temp;
    }

    public Cell getNextStep() {
        if (theSteps == null)
            theSteps = steps.iterator();
        if (!theSteps.hasNext())
            return null;
        return theSteps.next();
    }

    public Board getBoard() {
        return board;
    }
    
}
