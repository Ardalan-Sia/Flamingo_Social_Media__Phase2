package ap.mini_project.server.controller.game.MyGame;

import ap.mini_project.shared.model.Board;
import ap.mini_project.shared.model.Ship;
import ap.mini_project.shared.model.ShipType;

import java.awt.*;
import java.util.Random;

public class BoardBuilder {
    private Board[] boards;

    public BoardBuilder(int x , int y) {
        boards = new Board[5];
        for (int i = 0; i < boards.length; i++) {
            boards[i] = new Board(x, y);
        }
        ///////////////////////////NO_1
        boards[0].addShip(new Ship(
                ShipType.BATTLE_SHIP,
              new Point(1, 6), new Point(1, 9)));
        boards[0].addShip(new Ship(
                ShipType.CRUISER,
                new Point(7, 0), new Point(9, 0)));
        boards[0].addShip(new Ship(
                ShipType.CRUISER,
                new Point(7, 2), new Point(7, 4)));
        boards[0].addShip(new Ship(
                ShipType.DESTROYER,
                new Point(6, 6), new Point(6, 7)));
        boards[0].addShip(new Ship(
                ShipType.DESTROYER,
                new Point(3, 6), new Point(3, 7)));
        boards[0].addShip(new Ship(
                ShipType.DESTROYER,
                new Point(3, 0), new Point(4, 0)));
        boards[0].addShip(new Ship(
                ShipType.FRIGATE ,
                new Point(2,4) , new Point(2,4)));
        boards[0].addShip(new Ship(
                ShipType.FRIGATE ,
                new Point(3,2) , new Point(3,2)));
        boards[0].addShip(new Ship(
                ShipType.FRIGATE ,
                new Point(4,4) , new Point(4,4)));
        boards[0].addShip(new Ship(
                ShipType.FRIGATE ,
                new Point(5,2) , new Point(5,2)));
        //////////////////NO_2
        boards[1].addShip(new Ship(
                ShipType.BATTLE_SHIP,
                new Point(2, 3), new Point(5, 3)));
        boards[1].addShip(new Ship(
                ShipType.CRUISER,
                new Point(0, 1), new Point(2, 1)));
        boards[1].addShip(new Ship(
                ShipType.CRUISER,
                new Point(0, 7), new Point(2, 7)));
        boards[1].addShip(new Ship(
                ShipType.DESTROYER,
                new Point(0, 9), new Point(1, 9)));
        boards[1].addShip(new Ship(
                ShipType.DESTROYER,
                new Point(4, 5), new Point(4, 6)));
        boards[1].addShip(new Ship(
                ShipType.DESTROYER,
                new Point(4, 0), new Point(5, 0)));
        boards[1].addShip(new Ship(
                ShipType.FRIGATE ,
                new Point(3,9) , new Point(3,9)));
        boards[1].addShip(new Ship(
                ShipType.FRIGATE ,
                new Point(5,9) , new Point(5,9)));
        boards[1].addShip(new Ship(
                ShipType.FRIGATE ,
                new Point(8,2) , new Point(8,2)));
        boards[1].addShip(new Ship(
                ShipType.FRIGATE ,
                new Point(8,7) , new Point(8,7)));
        //////////////////NO_3
        boards[2].addShip(new Ship(
                ShipType.BATTLE_SHIP,
                new Point(5, 0), new Point(8, 0)));
        boards[2].addShip(new Ship(
                ShipType.CRUISER,
                new Point(1, 2), new Point(3, 2)));
        boards[2].addShip(new Ship(
                ShipType.CRUISER,
                new Point(3, 4), new Point(5, 4)));
        boards[2].addShip(new Ship(
                ShipType.DESTROYER,
                new Point(2, 0), new Point(3, 0)));
        boards[2].addShip(new Ship(
                ShipType.DESTROYER,
               new Point(5, 2), new Point(6, 2)));
        boards[2].addShip(new Ship(
                ShipType.DESTROYER,
                new Point(9, 2), new Point(9, 3)));
        boards[2].addShip(new Ship(
                ShipType.FRIGATE ,
                new Point(1,6) , new Point(1,6)));
        boards[2].addShip(new Ship(
                ShipType.FRIGATE ,
                new Point(2,8) , new Point(2,8)));
        boards[2].addShip(new Ship(
                ShipType.FRIGATE ,
                new Point(6,8) , new Point(6,8)));
        boards[2].addShip(new Ship(
                ShipType.FRIGATE ,
                new Point(7,4) , new Point(7,4)));
        //////////////////NO_4
        boards[3].addShip(new Ship(
                ShipType.BATTLE_SHIP,
                new Point(1, 0), new Point(1, 3)));
        boards[3].addShip(new Ship(
                ShipType.CRUISER,
                new Point(7, 1), new Point(9, 1)));
        boards[3].addShip(new Ship(
                ShipType.CRUISER,
                new Point(7, 4), new Point(9, 4)));
        boards[3].addShip(new Ship(
                ShipType.DESTROYER,
                new Point(3, 9), new Point(4, 9)));
        boards[3].addShip(new Ship(
                ShipType.DESTROYER,
                new Point(4, 1), new Point(5, 1)));
        boards[3].addShip(new Ship(
                ShipType.DESTROYER,
                new Point(6, 6), new Point(6, 7)));
        boards[3].addShip(new Ship(
                ShipType.FRIGATE ,
                new Point(0,6) , new Point(0,6)));
        boards[3].addShip(new Ship(
                ShipType.FRIGATE ,
                new Point(2,5) , new Point(2,5)));
        boards[3].addShip(new Ship(
                ShipType.FRIGATE ,
                new Point(3,7) , new Point(3,7)));
        boards[3].addShip(new Ship(
                ShipType.FRIGATE ,
                new Point(8,8) , new Point(8,8)));


    }

    public synchronized Board buildRandomBoard() {
        Random random = new Random();
        int temp = random.nextInt(4);
        System.out.println("temp : "+ temp);
        return boards[temp];
    }
}
