package gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Interface extends Application {
    private Stage stage;
    private Controller controller;

    @Override
    public void start(Stage theStage) throws Exception {
    stage = theStage;
    stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
