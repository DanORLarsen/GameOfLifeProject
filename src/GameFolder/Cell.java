package GameFolder;

public class Cell {
    private boolean alive = false;
    private int livingNeighbours = 0;

    public void update() {
        //If the cell is alive check if it should stay alive
        if (isAlive()) {
            if (getLivingNeighbours() <= 2 && getLivingNeighbours() >= 3) {
                setAlive(true);
            } else {
                setAlive(false);
            }
        } else {
            //If cell is dead, check if it will be born (3 Neighbours) else it remains dead.
            if (getLivingNeighbours() == 3) {
                setAlive(true);
                System.out.println("NEW CELL WAS BORN");
            }
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getLivingNeighbours() {
        return livingNeighbours;
    }

    public void setLivingNeighbours(int livingNeighbours) {
        this.livingNeighbours = livingNeighbours;
    }
}
