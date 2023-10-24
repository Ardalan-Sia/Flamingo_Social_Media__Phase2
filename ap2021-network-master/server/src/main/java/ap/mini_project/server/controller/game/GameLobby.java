package ap.mini_project.server.controller.game;

import ap.mini_project.server.controller.ClientHandler;
import ap.mini_project.server.controller.game.MyGame.MyGame;
import ap.mini_project.shared.model.Side;

public class GameLobby {
    private ClientHandler waiting;

    public GameLobby() {
    }

    public synchronized void startGameRequest(ClientHandler clientHandler) {
        if (waiting == null) {
            waiting = clientHandler;
            clientHandler.setSide(Side.PLAYER_ONE);
        } else {
            if (waiting != clientHandler) {
                clientHandler.setSide(Side.PLAYER_TWO);
                MyGame game = new MyGame(waiting.getPlayer(), clientHandler.getPlayer()); // new
                waiting.setGame(game);
                clientHandler.setGame(game);
                waiting = null;
            }
        }

    }

    public ClientHandler getWaiting() {
        return waiting;
    }

    public void setWaiting(ClientHandler waiting) {
        this.waiting = waiting;
    }
}
