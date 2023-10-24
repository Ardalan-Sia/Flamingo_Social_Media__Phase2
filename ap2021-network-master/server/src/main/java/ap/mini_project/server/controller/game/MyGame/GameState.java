package ap.mini_project.server.controller.game.MyGame;

import ap.mini_project.shared.model.Board;
import ap.mini_project.shared.model.GameStatutes;
import ap.mini_project.shared.model.Player;
import ap.mini_project.shared.model.Side;


import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;


public class GameState {
    private Side turn;
    private GameStatutes gameStatutes;
    private Side result;
    private final BoardBuilder boardBuilder;
    private LinkedHashMap<Side, Board> boards = new LinkedHashMap<>();
    private volatile LinkedHashMap<Side, Integer> remainingTime = new LinkedHashMap<>();
    private volatile LinkedHashMap<Side, Boolean> isReady =
            new LinkedHashMap<>() {{
                put(Side.PLAYER_ONE, false);
                put(Side.PLAYER_TWO, false);
            }};
    private LinkedHashMap<Side, Integer> changeTimes =
            new LinkedHashMap<>() {{
                put(Side.PLAYER_ONE, 0);
                put(Side.PLAYER_TWO, 0);
            }};    private LinkedHashMap<Side, Player> players = new LinkedHashMap<>();

    GameState( Player player1 , Player player2 , Side side  ) {
        this.turn = side;
        this.boardBuilder = new BoardBuilder(10, 10);
        players.put(Side.PLAYER_ONE, player1);
        players.put(Side.PLAYER_TWO, player2);

        boards.put(Side.PLAYER_ONE, new BoardBuilder(10 , 10).buildRandomBoard());
        boards.put(Side.PLAYER_TWO, new BoardBuilder(10,10).buildRandomBoard());

        startTimer(Side.PLAYER_ONE , 30);
        startTimer(Side.PLAYER_TWO , 30);
    }

    synchronized void putPiece(int i, int j, Side side) {

    }
    public synchronized void changeBoard(){

    }


    private void startTimer(Side side , int time) {
        Timer timer = new Timer();
        remainingTime.put(side , time);
        remainingTime.put(side , time);

        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                if (remainingTime.get(side) > 0) {
                    remainingTime.put(side , remainingTime.get(side)-1);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000); //1000ms = 1sec
    }


    public Side getTurn() {
        return turn;
    }
    public void setTurn (Side turn){
        if (gameStatutes == GameStatutes.IS_STARTED) {
            remainingTime.put(turn, 25);
            remainingTime.put(turn.getOther() , 0);
        }
        this.turn = turn;
    }

    public LinkedHashMap<Side, Board> getBoards() {
        return boards;
    }

    public LinkedHashMap<Side , Integer> getChangeTimes() {
        return changeTimes;
    }


    public void setResult(Side result) {
        this.result = result;
    }


    public Side getResult(){
        return result;
    }
    public int getRemainingTime(Side side){
        return remainingTime.get(side);
    }
    public void setReady(Side side , boolean isReady){
        this.isReady.put(side , isReady);
        if (this.isReady.get(side.getOther()) && this.isReady.get(side)) {
            setGameStatutes(GameStatutes.IS_STARTED);
            System.out.println(gameStatutes);
        }
    }


    public LinkedHashMap<Side, Integer> getRemainingTime() {
        return remainingTime;
    }

    public void setGameStatutes(GameStatutes gameStatutes) {
        this.gameStatutes = gameStatutes;
        if (gameStatutes == GameStatutes.IS_STARTED) {
            remainingTime.put(turn, 25);
            remainingTime.put(turn.getOther(), 0);

        }
    }

    public GameStatutes getGameStatutes() {
        return gameStatutes;
    }

    public LinkedHashMap<Side, Boolean> getIsReady() {
        return isReady;
    }

    public LinkedHashMap<Side, Player> getPlayers() {
        return players;
    }
}