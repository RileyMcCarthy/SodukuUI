package gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Interface extends Application {

    private Stage stage;
    private Controller controller;
    private Label[][] cells;

    @Override
    public void start(Stage theStage) throws Exception {
    stage = theStage;
    stage.setTitle("Sudoku");
    BorderPane borderPane = new BorderPane();
    ToolBar toolBar = getToolBar();
    VBox vBox = getVBoxLeft();
    GridPane pane = getGrid();
    borderPane.setTop(toolBar);
    borderPane.setCenter(pane);
    borderPane.setLeft(vBox);
    borderPane.setRight(null);
    Scene scene = new Scene(borderPane,600,500);
    stage.setScene(scene);
    controller = new Controller(this);
    stage.show();
    }

    private ToolBar getToolBar() {
        ToolBar toolBar = new ToolBar();
        Button newGame = new Button("New");
        Button openGame = new Button("Open");
        toolBar.getItems().addAll(newGame, openGame);
        return toolBar;
    }

    private VBox getVBoxLeft() {
        VBox vBox = new VBox();
        Button solve = new Button("Start");
        solve.setOnAction((ActionEvent even) -> {
            controller.startSolving();
        });
        Button pause = new Button("Pause");
        pause.setOnAction((ActionEvent even) -> {
            controller.pause();
        });

        Slider slider = new Slider(0, 0.1, 0.01);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(0.1f);
        slider.setBlockIncrement(0.05f);

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                controller.setPeriod(new_val.doubleValue());
            }
        });

        vBox.getChildren().addAll(solve,pause,slider);
        return vBox;
    }

    private GridPane getGrid() {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        GridPane[][] quadrents = new GridPane[3][3];
        for (int row=0;row<3;row++) {
            for (int col=0;col<3;col++) {
                quadrents[row][col] = new GridPane();
                quadrents[row][col].setStyle("-fx-border-color: black;-fx-border-width: 2;");
                quadrents[row][col].setMinWidth(150);
                quadrents[row][col].setMinHeight(150);
                pane.add(quadrents[row][col],col,row);
            }
        }

        cells = new Label[9][9];
        for (int i=0;i<9;i++) {
            for (int j=0;j<9;j++) {
                cells[i][j] = new Label();
                String temp = +i+" "+j;
                cells[i][j].setText("0");
                cells[i][j].setFont(new Font("Arial", 30));
                cells[i][j].setStyle("-fx-border-color: black;");
                cells[i][j].setMinWidth(50);
                cells[i][j].setMinHeight(50);
                cells[i][j].setAlignment(Pos.CENTER);
                quadrents[i/3][j/3].add(cells[i][j],j,i);
            }
        }
        return pane;
    }

    public void updateCell(int row, int col, int value, boolean original) {
        if (!original) {
            if (value ==0) {
                cells[row][col].setTextFill(Color.BLACK);
                //cells[row][col].setStyle("-fx-background-color:WHITE;-fx-border-color: black;");
            }else {
                cells[row][col].setTextFill(Color.GREEN);
                //cells[row][col].setStyle("-fx-background-color:GREEN;-fx-border-color: black;");
            }
        }
        cells[row][col].setText(Integer.toString(value));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
