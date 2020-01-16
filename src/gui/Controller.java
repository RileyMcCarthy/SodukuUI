package gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import soduku.Board;
import soduku.Cell;
import soduku.Soduku;

public class Controller {
    private Soduku level;
    private Interface gui;
    private Timeline timeline;
    private double period = 0.01;
    private int row = 0;
    private int col = 0;

    public Controller(Interface theGui) {
        gui = theGui;
        level = new Soduku();
    }

    public void getBoard(String adrs) {
        level.getGame(adrs);
        updateBoard();
    }

    public void newBoard() {
        level.newGame();
        updateBoard();
    }

    private void updateBoard() {
        for (int i=0;i<81;i++) {
            Cell temp = level.getCell(i);
            if (temp.isOriginal()) {
                gui.updateCell(temp.getRow(), temp.getCol(), temp.getValue(),true);
            }else {
                gui.updateCell(temp.getRow(), temp.getCol(), 0,false);
            }
        }
    }

    public void startSolving() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(period), e-> {

            Cell temp = level.getNextStep();
            if (temp==null) {
                timeline.stop();
            }else {
                gui.updateCell(temp.getRow(), temp.getCol(), temp.getValue(), false);
                col++;
                if (col % 9 == 0) {
                    row++;
                    col = 0;
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void pause() {
        timeline.pause();
    }

    public void resume() {
        timeline.play();
    }

    public void setPeriod(double thePeriod) {
        period = thePeriod;
        timeline.stop();
        startSolving();
    }

}
