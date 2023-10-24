//package ap.mini_project.server.controller;
//
//import ap.mini_project.shared.events.LoginEvent;
//import ap.mini_project.shared.events.RegisterEvent;
//import ap.mini_project.shared.model.Player;
//
//public class Authentication extends Controller{
//
//    public void loginRequest(LoginEvent loginEvent) throws Exception {
//
//        for (Player player: context.Players.all()) {
//            if (player.getUsername().equals(loginEvent.getUsername()))
//                throw new Exception("duplicatedUsername");
//            if (player.getPassword().equals(""))
//                throw new Exception("emptyPassword");
//        }
//        Player player = new Player(loginEvent.getUsername() , loginEvent.getPassword());
//    }
//
//    public void registrationRequest(RegisterEvent registerEvent){
//
//    }
//}
