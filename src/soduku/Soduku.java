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

    Solver solver;

   // private static int[][] tempboard = { {0,0,0,0,8,0,0,0,0},
    //                          {8,0,9,0,7,1,0,2,0},
    //                       {4,0,3,5,0,0,0,0,1},
    //                          {0,0,0,1,0,0,0,0,7},
    //                          {0,0,2,0,3,4,0,8,0},
    //                          {7,3,0,0,0,9,0,0,4},
    //                          {9,0,0,0,0,0,7,0,2},
    //                          {0,0,8,2,0,5,0,9,0},
    //                          {1,0,0,0,4,0,3,0,0} };
    private int[][] boardArray;

    public Soduku() {
        boardArray = importBoard();
        Board board = new Board(boardArray);
        solver = new Solver(board);
        solver.solveBoard();
    }

    public int[][] importBoard() {
        BufferedReader reader;
        int[][] tempBoard = new int[9][9];
        try {
            reader = new BufferedReader(new FileReader("gameData.txt"));
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
        return solver.getNextStep();
    }

    public Board getSolvedBoard() {
        return solver.getBoard();
    }
    
}
