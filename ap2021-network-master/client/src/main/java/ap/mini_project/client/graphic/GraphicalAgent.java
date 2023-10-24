package ap.mini_project.client.graphic;

import ap.mini_project.client.graphic.panels.*;
import ap.mini_project.client.listener.EventListener;
import ap.mini_project.client.listener.StringListener;
import ap.mini_project.shared.events.GetALiveGameEvent;
import ap.mini_project.shared.events.GetGameDetails;
import ap.mini_project.shared.events.GetLiveGames;
import ap.mini_project.shared.events.GetPlayerList;
import ap.mini_project.shared.model.*;
import ap.mini_project.shared.response.PersonalInfoResponse;
import ap.mini_project.shared.util.Loop;

import javax.swing.*;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

public class GraphicalAgent {
    private final EventListener listener;
    private final MyFrame frame;

    private final Map<PanelType, AbstractPanel> panels;

    private Loop loop;

    public GraphicalAgent(EventListener listener) {
        this.listener = listener;
        this.frame = new MyFrame();
        this.panels = new EnumMap<>(PanelType.class);

    }

    public void initialize() {
        frame.setVisible(true);
    }

    public void gotoAuthPanel(){
        AuthPanel panel = new AuthPanel(this);
        frame.setContentPane(panel);
        panels.put(PanelType.AUTH_PANEL, panel);
        if (loop != null)
            loop.stop();
    }

    public void gotoLoginPanel(){
        LoginPanel panel = new LoginPanel(listener);
        frame.setContentPane(panel);
        panels.put(PanelType.LOGIN_PANEL, panel);
        panel.setStringListener(new StringListener() {
            @Override
            public void stringEventOccurred(String string) {
                if (string.equals("backToAuth")){
                    panels.remove(PanelType.LOGIN_PANEL);
                    gotoAuthPanel();
                }
            }
        });
    }

    public void gotoRegistrationPanel(){
        RegistrationPanel panel = new RegistrationPanel(listener);
        frame.setContentPane(panel);
        panels.put(PanelType.REGISTRATION_PANEL, panel);
        panel.setStringListener(new StringListener() {
            @Override
            public void stringEventOccurred(String string) {
                if (string.equals("backToAuth")){
                    panels.remove(PanelType.REGISTRATION_PANEL);
                    gotoAuthPanel();
                }
            }
        });
    }

    public void gotoMainMenu() {
        MainMenuPanel panel = new MainMenuPanel(listener);
        frame.setContentPane(panel);
        panels.put(PanelType.MAIN_MENU, panel);
        if (loop != null)
            loop.stop();
    }
    public void gotoInfoPanel(PersonalInfoResponse info){
            InfoPanel infoPanel = new InfoPanel();
            panels.put(PanelType.PERSONAL_INFO_PANEL , infoPanel);
            frame.setContentPane(infoPanel);
            infoPanel.initialize(info);
            infoPanel.setStringListener(string -> {
                if (string.equals("backToMainMenu"))
                    gotoMainMenu();
            });



    }

    public void gotoGamePanel(GameDetails gameDetails) {
        if (frame.getContentPane() != panels.get(PanelType.GAME_PANEL)) {
            GamePanel gamePanel = new GamePanel(listener,  gameDetails);
            gamePanel.setStringListener(new StringListener() {
                @Override
                public void stringEventOccurred(String string) {
                    if (string.equals("backToMainMenu")){
                        gotoMainMenu();
                    }
                }
            });
            frame.setContentPane(gamePanel);
            panels.put(PanelType.GAME_PANEL, gamePanel);
            loop = new Loop(2, this::updateBoard);
            loop.start();
        } else {
            GamePanel gamePanel = (GamePanel) panels.get(PanelType.GAME_PANEL);
            gamePanel.getDetails(gameDetails);
        }
    }

    public void goToResultPanel(boolean won){
        loop.stop();
        ResultPanel resultPanel = new ResultPanel(won);
        panels.put(PanelType.RESULT_PANEL , resultPanel);
        frame.setContentPane(resultPanel);
        resultPanel.setStringListener(string -> {
            if (string.equals("backToMainMenu")) {
//                panels.remove(PanelType.GAME_PANEL);
//                panels.remove(PanelType.RESULT_PANEL);
                gotoMainMenu();
            }
        });
    }

    public void gotoScorePanel(LinkedList<PlayerData> playerList) {
        if (frame.getContentPane() != panels.get(PanelType.SCORE_PANEL)) {
            ScorePanel panel = new ScorePanel(playerList);
            panel.setStringListener(string -> {
                if (string.equals("backToMainMenu")){
                    gotoMainMenu();
                }
            });
            frame.setContentPane(panel);
            panels.put(PanelType.SCORE_PANEL, panel);
            loop = new Loop(2, this::updateScorePanel);
            loop.start();
        } else {
            ScorePanel panel = (ScorePanel) panels.get(PanelType.SCORE_PANEL);
            panel.setPlayers(playerList);
        }
    }

    public void gotoLiveGamesPanel(LinkedList<LiveGame> liveGames) {
        if (frame.getContentPane() != panels.get(PanelType.LIVE_GAMES_PANEL)) {
            LiveGamesPanel panel = new LiveGamesPanel(listener);
            panel.setStringListener(string -> {
                if (string.equals("backToMainMenu")){
                    gotoMainMenu();
                }
            });
            frame.setContentPane(panel);
            panels.put(PanelType.LIVE_GAMES_PANEL, panel);
            if (loop != null)
                loop.stop();
            loop = new Loop(2, this::updateLiveGamesPanel);
            loop.start();
        } else {
            LiveGamesPanel panel = (LiveGamesPanel) panels.get(PanelType.LIVE_GAMES_PANEL);
            panel.setPanels(liveGames);
        }
    }


    private void updateScorePanel() {
        listener.listen(new GetPlayerList());
    }

    private void updateLiveGamesPanel() {
        listener.listen(new GetLiveGames());
    }

    private void updateLiveGame(int id) {
        listener.listen(new GetALiveGameEvent(id));
    }

    private void updateBoard() {
        listener.listen(new GetGameDetails());
    }


    public void gotoWatchPanel(LiveGame liveGame) {
        if (frame.getContentPane() != panels.get(PanelType.WATCH_LIVE_GAME_PANEL)) {
            WatchLiveGame panel = new WatchLiveGame(liveGame);
            panel.setStringListener(string -> {
                if (string.equals("backToMainMenu")){
                    gotoMainMenu();
                }
            });
            frame.setContentPane(panel);
            panels.put(PanelType.WATCH_LIVE_GAME_PANEL, panel);
            if (loop != null)
            loop.stop();
            loop = new Loop(2, ()->updateLiveGame(liveGame.getGameId()));
            loop.start();
        } else {
            WatchLiveGame panel = (WatchLiveGame) panels.get(PanelType.WATCH_LIVE_GAME_PANEL);
            panel.setDetails(liveGame);
            if (liveGame.getGameStatutes() == GameStatutes.FINISHED){

                if (liveGame.getResult() == Side.PLAYER_ONE){
                    JOptionPane.showMessageDialog(null,"player one is winner");
                }else {
                    JOptionPane.showMessageDialog(null,"player two is winner");

                }
                gotoMainMenu();
            }
            if (liveGame == null){

            }



        }
    }




    public Map<PanelType, AbstractPanel> getPanels() {
        return panels;
    }
}
