package ap.mini_project.shared.events;

import ap.mini_project.shared.response.Response;

public interface EventVisitor {
    Response startGame(StartGame startGame);

    Response signIn(SignInEvent signInEvent);

    Response signUp(SignUpEvent signUpEvent);

    Response clickOnBoard(int x, int y);

    Response getBoard();

    Response getPersonalInfo();

    Response getPlayerList();

    Response getGameDetails();

    Response getStringEvent(String event);

    Response getLiveGames();

    Response getALiveGame(int id);

    // ...
}
