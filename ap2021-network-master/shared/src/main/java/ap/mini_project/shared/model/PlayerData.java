package ap.mini_project.shared.model;

import ap.mini_project.shared.model.PlayerState;

public class PlayerData {
    private final String username;
    private final int score;
    private final PlayerState playerState;

    public PlayerData(String username, int score, PlayerState playerState) {
        this.username = username;
        this.score = score;
        this.playerState = playerState;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }
}
