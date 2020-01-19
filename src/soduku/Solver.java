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
    private CellNode head;
    
    public Solver (Board theBoard) {
        board = theBoard;
        board.printBoard();
        steps = new ArrayList<Cell>();
    }
    
    public void solveBoard() {
        head = createTree(head);
        if (board != null) {
            board.printBoard();
        }else {
            System.out.println("solving failed!");
        }
    }

    public CellNode createTree(CellNode node) {
        board.printBoard();
        System.out.println("New Node:");
        int depth;
        if (node==null) {
            System.out.println("    First Node!");
            node = new CellNode();
            node.setDepth(-1);
        }

        depth = node.getDepth();
        System.out.println("    Depth: "+depth);
        System.out.println("    Value: "+node.getValue());
        if (depth==80) {
            System.out.println("Complete!");
            return node;
        }

        ArrayList<Integer> options = board.getCellOptions(depth+1);
        System.out.println("    next Options: "+options);
        for (Integer num : options) {
            System.out.println("        adding: "+num);
            board.setCellValue(depth+1,num);
            node.addNode(new CellNode(board.getCell(depth+1), depth+1));
        }

        CellNode child;
        while( (child = node.getNextNode()) != null) {
            System.out.println("    Trying: "+child.getValue());
            System.out.println("    Original: "+child.isOriginal());
            board.setCellValue(depth+1,child.getValue());
            steps.add(copyCell(board.getCell(depth+1)));
            CellNode temp = createTree(child);
            if (temp != null)
                return createTree(temp);
        }

        if (!node.isOriginal()) {
            board.setCellValue(depth,0);
            steps.add(copyCell(board.getCell(depth)));
        }
        node.setDead();
        return null;
    }
    
    /*public Board solveRecursivly(Board solvedBoard, CellNode node) {

        if (node==null) {
            node = new CellNode();
            node.setDepth(0);
        }
        //if true the board is solved and we return the complete board
        if (node.getDepth() == 81) {
            System.out.println("Complete!");
            return solvedBoard;
        }

        //skips any original cells
        if (node.isOriginal()) {
            node.addNode(new CellNode(solvedBoard.getCell(node.getDepth())));
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
        ArrayList<Integer> options = solvedBoard.getCellOptions(node.getDepth());
        ArrayList<CellNode> children = new ArrayList<CellNode>();
        for (Integer num : options) {
            solvedBoard.getCell(node.getDepth()+1).setValue(num);
            children.add(new CellNode(solvedBoard.getCell(node.getDepth()+1), node.getDepth()+1));
            steps.add(copyCell(solvedBoard.getCell(node.getDepth())));
        }
        node.addChildren(children);

        while(node.getNextNode()!=null) {
            Board temp = solveRecursivly(solvedBoard,node.getNextNode());
            if (temp != null) {
                return temp;
            }
        }

        /*
          loop exits meaning no succesful branch was found, this branch is dead
          and will be reset

        solvedBoard.setCellValue(node.getDepth(), 0);
        steps.add(copyCell(solvedBoard.getCell(node.getDepth())));
        return null;
    }*/

    public Cell copyCell(Cell cell) {
        Cell temp = new Cell();
        temp.setPos(cell.getPos());
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
