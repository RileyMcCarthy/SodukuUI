package soduku;

public class GameGenerator {

    Board board;
    Solver solved;

    public GameGenerator() {
        board = new Board();
        solved = new Solver(board);
        solved.solveBoard();
    }

    public void generateBoard() {

    }
}
