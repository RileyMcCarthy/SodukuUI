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
    int row = 0;
    int col = 0;

    public Controller(Interface theGui) {
        gui = theGui;
        level = new Soduku();
        Board solved = level.getSolvedBoard();
        for (int i=0;i<81;i++) {
            Cell temp = solved.getCell(i);
            if (temp.isOriginal()) {
                gui.updateCell(temp.getRow(), temp.getCol(), temp.getValue());
            }
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), e-> {
            Cell temp = level.getNextStep();
            System.out.println(temp.getValue()+" pos:"+temp.getRow()+" "+temp.getCol());
            gui.updateCell(temp.getRow(), temp.getCol(), temp.getValue());
            col++;
            if (col%9==0) {
                row++;
                col=0;
            }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void updateCellValue(int row, int col, int value) {

    }

}
