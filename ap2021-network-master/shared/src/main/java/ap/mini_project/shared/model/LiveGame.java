package ap.mini_project.shared.model;

import java.util.LinkedHashMap;

public class LiveGame {
    LinkedHashMap<Side , String> usernames;
    LinkedHashMap<Side , Integer> destroyedShips;
    LinkedHashMap<Side , Integer> destroyedCells;
    LinkedHashMap<Side , Board> boards;
    Side turn;
    private Side result;
    private int gameId;
    private GameStatutes gameStatutes;

    public GameStatutes getGameStatutes() {
        return gameStatutes;
    }

    public LiveGame(String P1username ,
                    String P2username ,
                    LinkedHashMap<Side,Board> boards,
                    Side turn,
                    Side result,
                    int gameId,
                    GameStatutes gameStatutes) {
        this.result = result;
        this.gameId = gameId;
        this.gameStatutes = gameStatutes;
        usernames = new LinkedHashMap<>(){{
            put(Side.PLAYER_ONE , P1username);
            put(Side.PLAYER_TWO , P2username);
        }};
        destroyedShips = new LinkedHashMap<>(){{
            put(Side.PLAYER_ONE , boards.get(Side.PLAYER_ONE).damagedShipsCount());
            put(Side.PLAYER_TWO , boards.get(Side.PLAYER_TWO).damagedShipsCount());
        }};
        destroyedCells = new LinkedHashMap<>(){{
            put(Side.PLAYER_ONE , boards.get(Side.PLAYER_ONE).damagedCellsCount());
            put(Side.PLAYER_TWO , boards.get(Side.PLAYER_TWO).damagedCellsCount());
        }};
        usernames = new LinkedHashMap<>(){{
            put(Side.PLAYER_ONE , P1username);
            put(Side.PLAYER_TWO , P2username);
        }};
        this.boards = boards;
        this.turn = turn;
    }

    public LinkedHashMap<Side, String> getUsernames() {
        return usernames;
    }

    public Side getResult() {
        return result;
    }

    public int getGameId() {
        return gameId;
    }

    public LinkedHashMap<Side, Integer> getDestroyedShips() {
        return destroyedShips;
    }

    public LinkedHashMap<Side, Integer> getDestroyedCells() {
        return destroyedCells;
    }

    public LinkedHashMap<Side, Board> getBoards() {
        return boards;
    }

    public Side getTurn() {
        return turn;
    }
}
