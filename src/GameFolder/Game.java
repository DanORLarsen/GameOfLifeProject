package GameFolder;

public class Game {
    private int gameSizeHeight = 58;
    private int gameSizeWidth = 100;

    private Cell[][] gameArray = new Cell[getGameSizeHeight()][getGameSizeWidth()];

    //To create standard size.
    public Game(){
        createGame();
    }

    /*
    public Game(int height, int width){
        setGameSizeHeight(height);
        setGameSizeWidth(width);
        createGame();
    }*/


//Dont know how to start it yet, but made it so every second vertical line is alive.
    public void createGame(){
        for (int i = 0; i < getGameSizeHeight(); i++) {
            for (int j = 0; j < getGameSizeWidth(); j++) {
                getGameArray()[i][j] = new Cell();
               // if (j%2 == 0){
                   // getGameArray()[i][j].setAlive(true);}
            }

        }
    }

    public void updateCells(){
        for (int i = 0; i < getGameSizeHeight(); i++) {
            for (int j = 0; j < getGameSizeWidth(); j++) {
        getGameArray()[i][j].update();
            }
        }
    }

//UPDATE Neighbours, but shouldnt update their true or false
    public void update(){
        for (int i = 0; i < getGameSizeHeight(); i++) {
            for (int j = 0; j < getGameSizeWidth(); j++) {
                //Making a neighbourCounter
                int neighbours = 0;
                //To avoid top arrayOutOfBounce on the top line
                if (i != 0) {
                    if (getGameArray()[i - 1][j].isAlive()) {
                        neighbours++;
                    }
                    if (j != 0)
                        if (getGameArray()[i - 1][j - 1].isAlive()) {
                            neighbours++;
                        }
                    if (j != getGameSizeWidth()-1){
                        if (getGameArray()[i-1][j+1].isAlive()){
                            neighbours++;
                        }
                    }
                } //To avoid outOfBounce on the sides
                if (j != 0){
                    if (getGameArray()[i][j-1].isAlive()){
                        neighbours++;
                    }
                }
                if (j != getGameSizeWidth()-1){
                    if (getGameArray()[i][j+1].isAlive()){
                        neighbours++;
                    }
                }
                if (i != getGameSizeHeight()-1) {
                    if (getGameArray()[i+1][j].isAlive()) {
                    neighbours++;
                }
                    if (j != 0){
                        if (getGameArray()[i + 1][j - 1].isAlive()) {
                            neighbours++;
                        }}
                    if (j != getGameSizeWidth()-1){
                        if (getGameArray()[i+1][j+1].isAlive()){
                            neighbours++;
                        }
                    }
                }
                getGameArray()[i][j].setLivingNeighbours(neighbours);
                //System.out.println(getGameArray()[i][j].isAlive());
            }

        }
    }
    public int getGameSizeWidth() {
        return gameSizeWidth;
    }

    public void setGameSizeWidth(int gameSizeWidth) {
        this.gameSizeWidth = gameSizeWidth;
    }
    public Cell[][] getGameArray() {
        return gameArray;
    }

    public void setGameSizeHeight(int gameSizeHeight) {
        this.gameSizeHeight = gameSizeHeight;
    }

    public int getGameSizeHeight() {
        return gameSizeHeight;
    }
}
