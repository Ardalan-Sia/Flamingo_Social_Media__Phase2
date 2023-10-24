package ap.mini_project.shared.response;

import ap.mini_project.shared.model.PlayerData;

import java.util.LinkedList;

public class ScoreListResponse extends Response {
    private final LinkedList<PlayerData> playerList;

    public ScoreListResponse(LinkedList<PlayerData> playerList) {
        this.playerList = playerList;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.getPlayersList(playerList);
    }
}
