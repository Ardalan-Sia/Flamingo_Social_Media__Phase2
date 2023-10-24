package ap.mini_project.shared.response;

import ap.mini_project.shared.model.LiveGame;

public class LiveGameResponse extends Response{
    private LiveGame liveGame;
    public LiveGameResponse(LiveGame liveGame) {
        this.liveGame = liveGame;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.getALiveGame(liveGame);
    }
}
