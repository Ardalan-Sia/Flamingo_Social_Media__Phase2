package ap.mini_project.shared.model;

public class Player {
    private String username;

    private String password;
    private PlayerState state;
    private int score;
    private int wins;
    private int loses;
    private int draws;
    private int games;

    public Player() {
    }

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.score = 0;
        this.games = 0;
        this.loses = 0;
        this.wins = 0;
        this.draws = 0;
    }

    public void win(){
        wins++;
        games++;
        score++;
    }

    public void draw(){
        draws++;
        games++;
    }

    public void lose(){
        loses++;
        games++;
        score--;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

}
