package ap.mini_project.server.controller.game.MyGame;

import ap.mini_project.server.db.Context;
import ap.mini_project.shared.model.*;

import java.util.LinkedList;

public class MyGame  {
    private final static Object value = new Object();
    public final static LinkedList<MyGame> liveGames = new LinkedList<>();
    private final BoardBuilder boardBuilder;
    private GameState gameState;
    private int id;
    private static volatile int lastId = 0;


    public MyGame(Player player1, Player player2) {
        id = lastId;
        lastId++;
        this.boardBuilder = new BoardBuilder(10, 10);
        Side side = ((int) (Math.random() * 2)) == 0 ? Side.PLAYER_ONE : Side.PLAYER_TWO;
        gameState = new GameState(player1 , player2, side);
        synchronized (liveGames) {
            liveGames.add(this);
        }
    }


    public synchronized void click(int i, int j, Side side) {
        if (gameState.getGameStatutes() != GameStatutes.IS_STARTED)
            return;
        if (side != gameState.getTurn()) {
            return;
        }
        if (-1 >= i || i >= gameState.getBoards().get(gameState.getTurn().getOther()).getW() || -1 >= j || j >= gameState.getBoards().get(gameState.getTurn().getOther()).getH()) {
            return;
        }

        Cell clickedCell = gameState.getBoards().get(gameState.getTurn().getOther()).getCell(i, j);
        if (clickedCell.isDestroyed())
            return;
        clickedCell.applyDamage();
        check(i, j);
    }


    public GameDetails getGameDetails(Side side){
        checkForEnd();
        return new GameDetails(gameState.getGameStatutes(),getBoard(side) ,
                getBoard(side.getOther()) ,
                gameState.getRemainingTime(side),
                gameState.getPlayers().get(side.getOther()).getUsername(),
                gameState.getPlayers().get(side).getUsername());
    }
    public Board getBoard(Side side) {

        Board board = gameState.getBoards().get(side);
        for (int i = 0; i < board.getH(); i++) {
            for (int j = 0; j < board.getW(); j++) {
                if (board.getCell(i, j).isDestroyed()) {
                    if (board.getCell(i,j).getShip() != null) {
                        board.getCell(i,j).setR(250);
                        board.getCell(i,j).setG(0);
                        board.getCell(i,j).setB(0);
                    }else {
                        board.getCell(i,j).setR(169);
                        board.getCell(i,j).setG(169);
                        board.getCell(i,j).setB(169);
                    }
                }
            }
        }

        switch (gameState.getGameStatutes()) {
            case IS_STARTED -> {
                if (gameState.getTurn() == side) {
                    board.setMessage("your turn");


                } else {
                    board.setMessage("enemy turn");
                }
                if (gameState.getRemainingTime(gameState.getTurn()) == 0)
                gameState.setTurn(gameState.getTurn().getOther());

            }
            case SETTING_MAPS -> {
                readyCheck(side);
                if (gameState.getIsReady().get(side)){
                    gameState.getBoards().get(side.getOther()).setMessage("enemy is ready");
                }else gameState.getBoards().get(side.getOther()).setMessage("enemy is not ready");

            }
        }
        return board;
    }

    public Side getResult() {
        return gameState.getResult();
    }

    private void readyCheck(Side side){
        if (gameState.getRemainingTime(side) == 0 || gameState.getChangeTimes().get(side) == 3)
            gameState.setReady(side , true);

        if (gameState.getIsReady().get(side) && gameState.getIsReady().get(side.getOther()))
            gameState.setGameStatutes(GameStatutes.IS_STARTED);
    }


    private void check(int i, int j) {
        Ship ship = gameState.getBoards().get(gameState.getTurn().getOther()).getCell(i, j).getShip();
        if (ship != null) {
            gameState.getRemainingTime().put(gameState.getTurn() , 25);
            if (ship.getHealth() == 0) {
                gameState.getBoards().get(gameState.getTurn().getOther()).destroyShip(ship);
                checkForEnd();
            }
        } else gameState.setTurn(gameState.getTurn().getOther());
    }

    public synchronized void changeBoard(Side side) {
        if (gameState.getRemainingTime(side) == 0 || gameState.getChangeTimes().get(side) == 3) {
            return;
        }
        System.out.println("changed");
            gameState.getBoards().put(side, boardBuilder.buildRandomBoard());
            gameState.getRemainingTime().put(side, gameState.getRemainingTime().get(side) + 10);
            gameState.getChangeTimes().put(side, gameState.getChangeTimes().get(side) + 1);

    }

    public GameState getGameState() {
        return gameState;
    }


    public String getOpponentUsername(Side side) {
        return gameState.getPlayers().get(side.getOther()).getUsername();
    }

    private void checkForEnd(){
        Context context = new Context();
        if (gameState.getPlayers().get(gameState.getTurn()).getState() == PlayerState.OFFLINE) {
            gameState.setGameStatutes(GameStatutes.FINISHED);
            gameState.getPlayers().get(gameState.getTurn().getOther()).win();
            gameState.getPlayers().get(gameState.getTurn().getOther()).lose();
            gameState.setResult(gameState.getTurn().getOther());
            context.Players.update(gameState.getPlayers().get(gameState.getTurn().getOther()));
            context.Players.update(gameState.getPlayers().get(gameState.getTurn().getOther()));
            return;
        }
        if (gameState.getPlayers().get(gameState.getTurn().getOther()).getState() == PlayerState.OFFLINE) {
            gameState.setGameStatutes(GameStatutes.FINISHED);
            gameState.getPlayers().get(gameState.getTurn()).win();
            gameState.getPlayers().get(gameState.getTurn().getOther()).lose();
            gameState.setResult(gameState.getTurn());
            context.Players.update(gameState.getPlayers().get(gameState.getTurn().getOther()));
            context.Players.update(gameState.getPlayers().get(gameState.getTurn().getOther()));
            return;
        }

        for (Ship ship:gameState.getBoards().get(gameState.getTurn().getOther()).getShips()) {
            if (!ship.isDestroyed()) {
                System.out.println(ship.getFirstCell());
                return;
            }
        }
        gameState.setGameStatutes(GameStatutes.FINISHED);
        gameState.getPlayers().get(gameState.getTurn()).win();
        gameState.getPlayers().get(gameState.getTurn().getOther()).lose();
        gameState.setResult(gameState.getTurn());
        context.Players.update(gameState.getPlayers().get(gameState.getTurn().getOther()));
        context.Players.update(gameState.getPlayers().get(gameState.getTurn().getOther()));
    }

    public int getId() {
        return id;
    }
}