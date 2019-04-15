package GameFolder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Game of Life");
        primaryStage.setScene(new Scene(root, 800, 700));
        Game d = new Game();
        d.createGame();
        d.update();
        //CodeTest
        System.out.println(d.getGameArray()[0][0].getLivingNeighbours());
        primaryStage.show();
}


    public static void main(String[] args) {
        launch(args);
    }
}
