package GameFolder;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameOfLife extends Application {
Timeline timeline = null;


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
    GridPane updatePane = new GridPane();
    Button updateBtn = new Button();
    updateBtn.setText("UPDATE");
    updateBtn.setOnMouseClicked(event -> {
        game.update();
        game.updateCells();
            draw(game,grid);
    });

    Pane startPane = new Pane();
    Button start = new Button();
    start.setText("Start");
    //Checks if timeline allready runs, else create new timeline which updates every 350 milis
    start.setOnAction(event -> {
        if (timeline != null)
        {return;}

        timeline = new Timeline(new KeyFrame(Duration.millis(350),event1 -> {
            game.update(); game.updateCells(); draw(game,grid);
            System.out.println("CHECKING");
        }
            ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    });


    Pane stopPane = new Pane();
    Button stop = new Button();
    stop.setText("Stop");
    //Stop timeline and sets it to null, so it can be launched again (IGNORES IF TIMELINE == NULL)
    stop.setOnAction(event -> {
        if (timeline == null) {
            return;
        }
        timeline.stop();
        timeline=null;
    });

    Pane paternPane = new Pane();
    Button paternPlease = new Button();
    paternPlease.setText("Give me a pattern");
    paternPlease.setOnAction(event -> {
        Cell[][] d = game.getGameArray();
        d[22][47].setAlive(true);
        d[22][46].setAlive(true);
        d[23][46].setAlive(true);
        d[23][47].setAlive(true);

        d[19][17].setAlive(true);
        d[20][17].setAlive(true);
        d[19][16].setAlive(true);
        d[18][17].setAlive(true);
        d[19][18].setAlive(true);

        d[12+30][29+60].setAlive(true);
        d[12+30][28+60].setAlive(true);
        d[12+30][27+60].setAlive(true);
        d[13+30][27+60].setAlive(true);
        d[14+30][28+60].setAlive(true);

        d[40][40].setAlive(true);
        d[41][39].setAlive(true);
        d[42][39].setAlive(true);
        d[43][39].setAlive(true);
        d[43][40].setAlive(true);
        d[43][41].setAlive(true);
        d[43][42].setAlive(true);
        d[42][43].setAlive(true);
        d[40][43].setAlive(true);



        draw(game,grid);
            });

    //Adds buttons to grid, for display
    updatePane.getChildren().add(updateBtn);
    startPane.getChildren().add(start);
    stopPane.getChildren().add(stop);
    paternPane.getChildren().add(paternPlease);

    grid.add(paternPane,30, game.getGameSizeHeight()+1,6,3);
    grid.add(updatePane,(game.getGameSizeWidth()/2)-5,game.getGameSizeHeight()+1,6,3);
    grid.add(startPane,0,game.getGameSizeHeight()+1,6,3);
    grid.add(stopPane,game.getGameSizeWidth()-3,game.getGameSizeHeight()+1,6,3);
    grid.setAlignment(Pos.CENTER);
            stage.setScene(new Scene(grid, 1620, 1000));

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
    //To draw a Game object, on a GridPane
    public synchronized static void draw(Game game, GridPane grid){
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
