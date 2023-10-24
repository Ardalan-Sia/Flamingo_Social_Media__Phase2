package ap.mini_project.shared.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {
    private int h, w;
    private Cell[][] cells;
    private String message;
    private List<Ship> ships;

    public Board(int h, int w) {
        this.h = h;
        this.w = w;
        ships = new LinkedList<>();
        cells = new Cell[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                cells[i][j] = new Cell(i,j);
                cells[i][j].setR((i + j) % 2 == 0 ? 255 : 0);
                cells[i][j].setG((i + j) % 2 == 0 ? 255 : 0);
                cells[i][j].setB((i + j) % 2 == 0 ? 255 : 0);
            }
        }
    }

    public void addShip(Ship ship) {
        ships.add(ship);
        if (!ship.isVertical()) {
            for (int i = Math.min(ship.getFirstCell().x, ship.getLastCell().x); i <= Math.max(ship.getFirstCell().x, ship.getLastCell().x); i++) {
                cells[i][ship.getLastCell().y].setShip(ship);
            }
        } else
            for (int j = Math.min(ship.getFirstCell().y, ship.getLastCell().y); j <= Math.max(ship.getFirstCell().y, ship.getLastCell().y); j++) {
                cells[ship.getLastCell().x][j].setShip(ship);
            }
    }

    public void destroyShip(Ship ship) {
        ship.setDestroyed(true);
        for (Cell[] cell : cells) {
            for (int j = 0; j < cells[0].length; j++) {
                System.out.println(getCellList(ship.getFirstCell(), ship.getLastCell()).size());
                if (ship.isAdjacent(cell[j])) {
                    cell[j].applyDamage();
                }
            }
        }
    }

    public LinkedList<Cell> getCellList(Point firstCell, Point lastCell) {
        if (firstCell.x == lastCell.x) {
            return new LinkedList<>(Arrays.asList(cells[firstCell.x]).subList(firstCell.y, lastCell.y + 1));
        } else if (firstCell.y == lastCell.y) {
            return new LinkedList<>(Arrays.asList(cells[firstCell.y]).subList(firstCell.x, lastCell.x + 1));
        }
        return null;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public Cell getCell(int i , int j){
        return cells[i][j];
    }

    public int damagedShipsCount(){
        int count = 0;
        for (Ship ship :ships) {
            if (ship.isDestroyed())
                count++;
        }
        return count;
    }

    public int damagedCellsCount(){
        int count = 0;
        for (Cell[] cell : cells) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cell[j].isDestroyed())
                    count++;
            }
        }

        return count;
    }


}
