package ap.mini_project.server.controller;
import ap.mini_project.server.controller.game.GameLobby;
import ap.mini_project.server.controller.game.MyGame.MyGame;
import ap.mini_project.server.db.Context;
import ap.mini_project.shared.events.EventVisitor;
import ap.mini_project.shared.events.SignInEvent;
import ap.mini_project.shared.events.SignUpEvent;
import ap.mini_project.shared.events.StartGame;
import ap.mini_project.shared.model.*;
import ap.mini_project.shared.response.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class ClientHandler extends Thread implements EventVisitor {
    private final ResponseSender sender;
    private final GameLobby gameLobby;
    private Context context;
    private Player player;
    private Side side;
    private MyGame game;

    public ClientHandler(ResponseSender sender, GameLobby gameLobby , Context context) throws IOException {
        this.sender = sender;
        this.gameLobby = gameLobby;
        this.context = context;
    }

    public void run() {
        while (true) {
            try {
                sender.sendResponse(sender.getEvent().visit(this));
            }catch (NoSuchElementException e1){
                if (player!=null) {
                    player.setState(PlayerState.OFFLINE);
                    synchronized (gameLobby) {
                        if (gameLobby.getWaiting() == this)
                            gameLobby.setWaiting(null);
                    }
                }
                context.Players.update(player);

                break;

            }
        }
    }

    @Override
    public Response startGame(StartGame startGame) {
        gameLobby.startGameRequest(this);
        return getGameDetails();
    }

    @Override
    public Response signIn(SignInEvent signInEvent) {
        Player player = context.Players.get(signInEvent.getUsername());
        if (player != null) {
            if (player.getState() == PlayerState.ONLINE)
                return new SignInResponse(false,"this user is in use");
            if (player.getPassword().equals(signInEvent.getPassword())) {
                this.player = player;
                player.setState(PlayerState.ONLINE);
                context.Players.save(player);
                player.setState(PlayerState.ONLINE);
                context.Players.update(player);
                return new SignInResponse(true, player.getUsername());
            } else {
                return new SignInResponse(false, "wrong password");
            }
        } else {
            return new SignInResponse(false, "username not exist");
        }
    }

    @Override
    public Response signUp(SignUpEvent signUpEvent) {
        Player player = context.Players.get(signUpEvent.getUsername());
        if (player == null){
            player = new Player(signUpEvent.getUsername() , signUpEvent.getPassword());
            context.Players.save(player);
            this.player = player;
            player.setState(PlayerState.ONLINE);
            context.Players.update(player);
            return new SignUpResponse(true , player.getUsername());
        }else return new SignUpResponse(false , "this username already exists");
    }

    @Override
    public Response clickOnBoard(int x, int y) {
        game.click(x, y, side);
        return getGameDetails();
    }

    @Override
    public Response getBoard() {
            return null;
    }



    @Override
    public Response getPersonalInfo() {
        return new PersonalInfoResponse(player.getUsername(),
                player.getWins(),
                player.getLoses(),
                player.getScore());
    }

    @Override
    public Response getPlayerList() {
        LinkedList<PlayerData> list = new LinkedList<>();
        for (int i = 0; i <context.Players.all().size() ; i++) {
            Player player = context.Players.all().get(i);
            PlayerData playerData = new PlayerData(player.getUsername(),
                    player.getScore(),
                    player.getState());
            list.add(playerData);
        }
        return new ScoreListResponse(list);
    }

    @Override
    public Response getGameDetails() {
        GameDetails gameDetails = null;
            if (game == null) {
                gameDetails = new GameDetails(GameStatutes.FINDING_OPPONENTS,new Board(10,10),
                        new Board(10,10),
                        0,
                        "_",
                        player.getUsername());
                gameDetails.getMyBoard().setMessage("waiting for another player");
            }else {
                if (game.getGameState().getGameStatutes() == null)
                game.getGameState().setGameStatutes(GameStatutes.SETTING_MAPS);
                if (game.getGameState().getGameStatutes() != GameStatutes.FINISHED){
                    gameDetails = game.getGameDetails(side);
                }else {
                    MyGame game = this.game;
                    MyGame.liveGames.remove(this.game);
                    this.game = null;
                    Side side = this.side;
                    this.side = null;
                    if (game.getGameState().getResult() == side) {
                        return new StringResponse("won");
                    }
                    else return new StringResponse("lost");

                }

            }

        return new UpdateGameResponse(gameDetails);

    }

    @Override
    public Response getStringEvent(String s) {
        switch (s) {
            case "changeMap" -> game.changeBoard(side);
            case "ready" -> {
                game.getGameState().setReady(side, true);
            }
            case "notReady" -> game.getGameState().setReady(side, false);
        }
                if (game.getGameState().getRemainingTime(side) == 0 ||
                        game.getGameState().getChangeTimes().get(side) == 3)
                    return new StringResponse("timeIsUp");

        return getGameDetails();
    }

    @Override
    public Response getLiveGames() {
        LinkedList<LiveGame> list = new LinkedList<>();

        for (MyGame game :MyGame.liveGames) {
            list.add(new LiveGame(game.getGameState().getPlayers().get(Side.PLAYER_ONE).getUsername(),
                    game.getGameState().getPlayers().get(Side.PLAYER_TWO).getUsername(),
                    game.getGameState().getBoards(),
                    game.getGameState().getTurn(),
                    game.getGameState().getResult(),
                    game.getId(),
                    game.getGameState().getGameStatutes()));
        }
        return new LiveGamesResponse(list);
    }

    @Override
    public Response getALiveGame(int id) {
        for (MyGame game1 :MyGame.liveGames) {
            if (game1.getId() == id)
                return new LiveGameResponse(new LiveGame(game1.getGameState().getPlayers().get(Side.PLAYER_ONE).getUsername(),
                        game1.getGameState().getPlayers().get(Side.PLAYER_TWO).getUsername(),
                        game1.getGameState().getBoards(),
                        game1.getGameState().getTurn(),
                        game1.getGameState().getResult(),
                        game1.getId(),
                        game1.getGameState().getGameStatutes()));
        }
        return null;

    }


    public void setSide(Side side) {
        this.side = side;
    }



    public void setGame(MyGame game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }
}
