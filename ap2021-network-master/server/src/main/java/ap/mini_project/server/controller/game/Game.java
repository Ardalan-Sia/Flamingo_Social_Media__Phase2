package ap.mini_project.server.controller.game;

import ap.mini_project.shared.model.Board;
import ap.mini_project.shared.model.GameDetails;
import ap.mini_project.shared.model.Side;

public interface Game {
    void click(int x, int y, Side side);

    Board getBoard(Side side);

    int getResult();

    GameDetails getGameDetails(Side side);
}
