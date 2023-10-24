package ap.mini_project.shared.response;

import ap.mini_project.shared.model.Board;
import ap.mini_project.shared.model.GameDetails;
import ap.mini_project.shared.model.LiveGame;
import ap.mini_project.shared.model.PlayerData;

import java.util.LinkedList;

public interface ResponseVisitor {
    void visitBoard(Board board);

    void signIn(boolean success, String message);

    void signUp(boolean success, String message);

    void showMessage(String message);

    void getPersonalInfo(PersonalInfoResponse personalInfoResponse);

    void getPlayersList(LinkedList<PlayerData> playerData);

    void getGameDetails(GameDetails gameDetails);

    void getStringResponse(String s);

    void getLiveGames(LinkedList<LiveGame> liveGames);

    void getALiveGame(LiveGame liveGame);
}