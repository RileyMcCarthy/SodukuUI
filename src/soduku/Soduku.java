/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soduku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Riley McCarthy
 */
public class Soduku {

    Solver answer;

    private int[][] boardArray = { {0,0,0,0,8,0,0,0,0},
                              {8,0,9,0,7,1,0,2,0},
                              {4,0,3,5,0,0,0,0,1},
                              {0,0,0,1,0,0,0,0,7},
                              {0,0,2,0,3,4,0,8,0},
                              {7,3,0,0,0,9,0,0,4},
                              {9,0,0,0,0,0,7,0,2},
                              {0,0,8,2,0,5,0,9,0},
                              {1,0,0,0,4,0,3,0,0} };
    //private int[][] boardArray;

    private Board board;

    public Soduku() {
        newGame();
    }

    public void newGame() {
        board = new Board(boardArray);
        answer = new Solver(board);
        answer.solveBoard();
    }

    public void getGame(String adrs) {
        boardArray = importBoard(adrs);
        if (boardArray==null) {
            System.out.println("cant find board");
            return;
        }
        board = new Board(boardArray);
        answer = new Solver(board);
        answer.solveBoard();
    }

    private int[][] importBoard(String adrs) {
        System.out.println(adrs);
        BufferedReader reader;
        int[][] tempBoard = new int[9][9];
        try {
            reader = new BufferedReader(new FileReader(adrs));
            String row;
            int i = 0;
            while((row = reader.readLine())!=null) {
                if (i==9)
                    return null;
                String[] temp = row.split(",");
                int j = 0;
                for (String str : temp) {
                    if (j==9)
                        return null;
                    tempBoard[i][j] = Integer.parseInt(str);
                    j++;
                }
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempBoard;
    }

    public Cell getNextStep() {
        return answer.getNextStep();
    }

    public Cell getAnswer(int pos) {
        if (answer == null) {
            System.out.println("No Answer");
        }

        return answer.getBoard().getCell(pos);
    }

    public Cell getCell(int pos) {
        return board.getCell(pos);
    }
    
}
