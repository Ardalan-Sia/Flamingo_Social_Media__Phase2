package ap.mini_project.shared.response;

import ap.mini_project.shared.model.GameDetails;

public class UpdateGameResponse extends Response {
    GameDetails gameDetails;

    public UpdateGameResponse(GameDetails gameDetails) {
        this.gameDetails = gameDetails;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.getGameDetails(gameDetails);
    }
}
