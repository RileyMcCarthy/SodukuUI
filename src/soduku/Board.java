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
public class Board {
    
    private ArrayList<Cell> boardList;
    
    public Board(int[][] theBoard) {
        boardList = new ArrayList<Cell>();
        initBoardList(theBoard);
    }

    public Cell getCell(int pos) {
        return boardList.get(pos);
    }
    
    public ArrayList<Integer> getCellOptions(int pos) {
        updateCellOptions(pos);
        return boardList.get(pos).getOptions();
    }
    
    public int getCellValue(int pos) {
        return boardList.get(pos).getValue();
    }

    public int getCellValue(int row, int col) {
        int pos = row*9+col;
        return boardList.get(pos).getValue();
    }
    
    public void setCellValue(int pos, int num) {
        boardList.get(pos).setValue(num);
    }
    
    public boolean isOriginal(int pos) {
        return boardList.get(pos).isOriginal();
    }
    
    public void printBoard() {
        int count = 1;
        for (Cell cell : boardList) {
            System.out.print(cell.getValue()+" ");
            if (count%9==0) {
                System.out.println("");
            }
            count++;
        }
    }
    
    private void initBoardList(int[][] theBoard) {
        for (int row=0;row<9;row++) {
            for (int col=0;col<9;col++) {
                Cell temp = new Cell();
                
                if (theBoard[row][col] == 0) {
                    temp.setOriginal(false);
                }else {
                    temp.setOriginal(true);
                }
                temp.setValue(theBoard[row][col]);
                System.out.println("ROWS: "+row);
                temp.setRow(row);
                temp.setCol(col);
                boardList.add(temp);
            }
        }
    }
    
    private void updateCellOptions(int pos) {
        ArrayList<Integer> options = new ArrayList<Integer>();
        Collections.addAll(options, 1,2,3,4,5,6,7,8,9);
        Cell cell = boardList.get(pos);
        int row = pos/9;
        int col = pos%9;
        if (cell.isOriginal()) {
            cell.setOptions(new ArrayList<Integer>());
        }
        //Check row
        ArrayList<Integer> rowCells = getRow(row);
        for (Integer num : rowCells) {
            if (options.contains(num)) {
                options.remove(num);
            }
        }
        
        //Check col
        ArrayList<Integer> colCells = getCol(col);
        for (Integer num : colCells) {
            if (options.contains(num)) {
                options.remove(num);
            }
        }
        
        //Check quadrent
        int quadRow = row/3;
        int quadCol = col/3;
        ArrayList<Integer> quad = getQuadrent(quadRow,quadCol);
        for (Integer num : quad) {
            if (options.contains(num)) {
                options.remove(num);
            }
        }
        cell.setOptions(options);
    }
    
    private ArrayList<Integer> getQuadrent(int row, int col) {
        ArrayList<Integer> quadrent = new ArrayList<Integer>();
        
        for (int i=(row*3);i<(row*3+3);i++) {
            for (int j=(col*3);j<(col*3+3);j++) {
                int pos = j + i*9;
                quadrent.add(boardList.get(pos).getValue());
            }
        }
        return quadrent;
    }
    
    private ArrayList<Integer> getRow(int r) {
        ArrayList<Integer> row = new ArrayList<Integer>();
        for (int i=0;i<9;i++) {
            int pos = i + r*9;
            row.add(boardList.get(pos).getValue());
        }
        return row;
    }
    private ArrayList<Integer> getCol(int c) {
        ArrayList<Integer> col = new ArrayList<Integer>();
        for (int i=0;i<9;i++) {
            int pos = i*9 + c;
            col.add(boardList.get(pos).getValue());
        }
        return col;
    }
}
