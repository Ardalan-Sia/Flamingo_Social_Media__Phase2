package ap.mini_project.shared.model;

public class GameDetails {
    private GameStatutes gameStatutes;
    private Board myBoard;
    private Board enemyBoard;
    private int remainingTime;
    private final String enemyUsername;
    private final String myUsername;

    public String getMyUsername() {
        return myUsername;
    }

    public GameDetails(GameStatutes gameStatutes,
                       Board myBoard,
                       Board enemyBoard,
                       int remainingTime ,
                       String enemyUsername,
                       String myUsername) {
        this.gameStatutes = gameStatutes;
        this.myBoard = myBoard;
        this.enemyBoard = enemyBoard;
        this.remainingTime = remainingTime;

        this.enemyUsername = enemyUsername;
        this.myUsername = myUsername;
    }

    public Board getMyBoard() {
        return myBoard;
    }

    public Board getEnemyBoard() {
        return enemyBoard;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public GameStatutes getGameStatutes() {
        return gameStatutes;
    }

    public void setGameStatutes(GameStatutes gameStatutes) {
        this.gameStatutes = gameStatutes;
    }

    public String getEnemyUsername() {
        return enemyUsername;
    }
}
