package application;

import application.view.MainWindowView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Explorer");
        Scene root = new Scene(new MainWindowView().asParent());
        primaryStage.setScene(root);
        primaryStage.show();
    }
}
