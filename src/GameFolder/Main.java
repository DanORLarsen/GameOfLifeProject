package GameFolder;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.lang.reflect.Array;

public class Main extends Application {



@Override
public void start(Stage stage) {
    Game game = new Game();
    game.createGame();

            stage.setTitle("Game of Life");
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.setHgap(0);
    grid.setVgap(0);


//This is used to add a circle for each cell in the array. and make visible/Not visible if alive or dead.
    draw(game,grid);

    //pane = buttom panel for controlling the "Game"
    Pane pane = new Pane();
    Button updateBtn = new Button();
    updateBtn.setOnMouseClicked(event -> {
        game.update();
        game.updateCells();
            draw(game,grid);
    });
    pane.getChildren().add(updateBtn);
    grid.add(pane,0,game.getGameSizeWidth()+1);

            stage.setScene(new Scene(grid, 1620, 1000));

            //CodeTest
            stage.show();
}



    public static void main(String[] args) {
        launch(args);
    }

    //When a cell gets clicked do this..
    public static void updateCell(Cell[][] cells , int i, int j, Game game, GridPane grid){
        System.out.println("I was " + cells[i][j].isAlive());
        if (cells[i][j].isAlive())
        {cells[i][j].setAlive(false);}
        else {cells[i][j].setAlive(true);}
        System.out.println("I am " + cells[i][j].isAlive());
        System.out.println("I GOT CLICKED!! :)  [" + i +", "+ j +"]" );
        draw(game,grid);
    }
    //To draw a new Game, on a GridPane
    public static void draw(Game game, GridPane grid){
        for (int r = 0; r < game.getGameSizeHeight(); r++) {
            for (int c = 0; c < game.getGameSizeWidth(); c++) {
                Circle circle = new Circle(8);
                final int height = r;
                final int width = c;
                circle.setOnMouseClicked(event -> {
                    //USING THREAD TO AVOID DOWNTIME WHILE DRAWING
                    Runnable runnable = () -> updateCell(game.getGameArray(),height,width,game,grid);
                    Platform.runLater(runnable);
                });
                if (game.getGameArray()[r][c].isAlive())
                {circle.setFill(Color.BLUE);}
                else {
                    circle.setFill(Color.WHITE);
                }
                grid.add(circle, c, r);
            }
        }
    }
}
