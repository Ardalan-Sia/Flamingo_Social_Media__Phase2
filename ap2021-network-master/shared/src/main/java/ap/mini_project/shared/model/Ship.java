package ap.mini_project.shared.model;
import java.awt.*;


public class Ship {

    private final int length;
    private int health;
    private boolean destroyed;
    private final ShipType shipType;
    private boolean isVertical;
    private final Point firstCell , lastCell; 

    public Ship(ShipType shipType, Point firstCell, Point lastCell) {
        this.firstCell = firstCell;
        this.lastCell = lastCell;
        this.shipType = shipType;
        int temp = 0;

        isVertical = firstCell.getX() == lastCell.getX();

        temp = isVertical ? Math.abs(firstCell.x - lastCell.x) : Math.abs(firstCell.y - lastCell.y) + 1;
        switch (shipType) {
            case CRUISER -> temp = 3;
            case BATTLE_SHIP -> temp = 4;
            case FRIGATE -> temp = 1;
            case DESTROYER -> temp = 2;
        }

        length = temp;
        health = length;


    }

    public boolean isAdjacent(Cell cell) {
        if (!isVertical) {
            if (Math.abs(cell.getY() - firstCell.y) > 1)
                return false;
            for (int i = Math.min(firstCell.x , lastCell.x); i <= Math.max(firstCell.x,lastCell.x) ; i++) {
                if(Math.abs(i-cell.getX()) <= 1)
                    return true;
            }
        }else {
            if (Math.abs(cell.getX() - firstCell.x) > 1)
                return false;
            for (int i = Math.min(firstCell.y , lastCell.y); i <= Math.max(firstCell.y,lastCell.y) ; i++) {
                if(Math.abs(i-cell.getY()) <= 1)
                    return true;
            }

            
        }
        return false;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public Point getFirstCell() {
        return firstCell;
    }

    public Point getLastCell() {
        return lastCell;
    }

    public int getLength() {
        return length;
    }

    public void applyDamage() {
        if (health > 0)
            health--;
        else if (health == 0)
            setDestroyed(true);
    }

    public ShipType getShipType() {
        return shipType;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public int getHealth() {
        return health;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
