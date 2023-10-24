package ap.mini_project.shared.response;

import ap.mini_project.shared.model.LiveGame;

import java.util.LinkedList;

public class LiveGamesResponse extends Response {
    private final LinkedList<LiveGame> liveGames;
    public LiveGamesResponse(LinkedList<LiveGame> liveGames) {
        this.liveGames = liveGames;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.getLiveGames(liveGames);
    }
}
