package GameFolder;

public class Game {
    private int gameSize = 10;

    private Cell[][] gameArray = new Cell[getGameSize()][getGameSize()];

    public Cell[][] getGameArray() {
        return gameArray;
    }

    public Game(){
        createGame();
    }

    public int getGameSize() {
        return gameSize;
    }

//Dont know how to start it yet, but made it so every second vertical line is alive.
    public void createGame(){
        for (int i = 0; i < getGameSize(); i++) {
            for (int j = 0; j < getGameSize(); j++) {
                getGameArray()[i][j] = new Cell();
                if (j%2 == 0){
                    getGameArray()[i][j].setAlive(true);}
            }

        }
    }

    public void update(){
        for (int i = 0; i < getGameSize(); i++) {
            for (int j = 0; j < getGameSize(); j++) {
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
                    if (j != getGameSize()-1){
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
                if (j != getGameSize()-1){
                    if (getGameArray()[i][j+1].isAlive()){
                        neighbours++;
                    }
                }
                if (i != getGameSize()-1) {
                    if (getGameArray()[i+1][j].isAlive()) {
                    neighbours++;
                }
                    if (j != 0)
                        if (getGameArray()[i + 1][j - 1].isAlive()) {
                            neighbours++;
                        }
                    if (j != getGameSize()-1){
                        if (getGameArray()[i+1][j+1].isAlive()){
                            neighbours++;
                        }
                    }
                }
                getGameArray()[i][j].setLivingNeighbours(neighbours);
                getGameArray()[i][j].update();
                System.out.println(getGameArray()[i][j].isAlive());
            }

        }
    }

}
