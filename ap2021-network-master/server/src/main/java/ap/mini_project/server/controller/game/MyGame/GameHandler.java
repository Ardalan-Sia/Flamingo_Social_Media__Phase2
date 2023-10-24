//package ap.mini_project.server.controller.game.MyGame;
//
//import ap.mini_project.server.controller.game.Game;
//import ap.mini_project.server.model.Side;
//import ap.mini_project.shared.model.Player;
//import ap.mini_project.shared.response.Response;
//
//public class GameHandler {
//    private final Player player;
//    private final MyGame game;
//    private Side side;
//
//    public GameHandler(Player player, MyGame myGame) {
//        this.player = player;
//        this.game = myGame;
//        this.side = myGame.players.get(Side.PLAYER_ONE).equals(player) ? Side.PLAYER_ONE : Side.PLAYER_TWO;
//    }
//
//public Response setMap(boolean isChosen){
//        if (!isChosen){
//            game.remainingTime.put(side , null);
//        }
//
//}
//
//
//
//}
