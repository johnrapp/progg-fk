package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sudoku.fxml"));
        primaryStage.setTitle("Sudoku");

        Scene scene = new Scene(root, 600, 660);
        primaryStage.setScene(scene);

        scene.getStylesheets().add("gui/styles.css");
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
