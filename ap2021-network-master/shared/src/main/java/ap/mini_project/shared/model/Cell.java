package ap.mini_project.shared.model;

public class Cell {
    private int r, g, b;
    private final int x , y;
    private String pieceName;
    private Ship ship;
    private boolean destroyed;


    public Cell(int x , int y) {
        ship = null;
        this.x = x;
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPieceName() {
        return pieceName;
    }
    public void applyDamage(){
        if(isDestroyed())
            return;
        this.destroyed = true;
        if(this.ship != null)
            this.ship.applyDamage();
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }
}
