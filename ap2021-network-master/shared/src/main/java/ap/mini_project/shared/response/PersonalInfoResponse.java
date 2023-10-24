package ap.mini_project.shared.response;

public class PersonalInfoResponse extends Response{
    private final String username;
    private final int wins;
    private final int loses;
    private final int score;

    public PersonalInfoResponse(String username, int wins, int loses, int score) {
        this.username = username;
        this.wins = wins;
        this.loses = loses;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.getPersonalInfo(this);
    }
}
