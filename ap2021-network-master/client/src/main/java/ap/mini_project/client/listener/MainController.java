package ap.mini_project.client.listener;
import ap.mini_project.client.graphic.GraphicalAgent;
import ap.mini_project.client.graphic.PanelType;
import ap.mini_project.client.graphic.panels.LoginPanel;
import ap.mini_project.client.graphic.panels.RegistrationPanel;
import ap.mini_project.shared.events.Event;
import ap.mini_project.shared.model.Board;
import ap.mini_project.shared.model.GameDetails;
import ap.mini_project.shared.model.LiveGame;
import ap.mini_project.shared.model.PlayerData;
import ap.mini_project.shared.response.PersonalInfoResponse;
import ap.mini_project.shared.response.Response;
import ap.mini_project.shared.response.ResponseVisitor;
import ap.mini_project.shared.util.Loop;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class MainController implements ResponseVisitor {
    private final EventSender eventSender;
    private final List<Event> events;
    private final Loop loop;
    private final GraphicalAgent graphicalAgent;

    public MainController(EventSender eventSender) {
        this.eventSender = eventSender;
        this.events = new LinkedList<>();
        this.loop = new Loop(10, this::sendEvents);
        this.graphicalAgent = new GraphicalAgent(this::addEvent);
    }

    public void start() {
        loop.start();
        graphicalAgent.initialize();
        graphicalAgent.gotoAuthPanel();

    }


    private void addEvent(Event event) {
        synchronized (events) {
            events.add(event);
        }
    }

    private void sendEvents() {
        List<Event> temp;
        synchronized (events) {
            temp = new LinkedList<>(events);
            events.clear();
        }
        for (Event event : temp) {
            Response response = eventSender.send(event);
            response.visit(this);

        }
    }

    @Override
    public void visitBoard(Board board) {
//        graphicalAgent.gotoGamePanel(board);
    }

    @Override
    public void signIn(boolean success , String message) {
        LoginPanel loginPanel = (LoginPanel) graphicalAgent.getPanels().get(PanelType.LOGIN_PANEL);
        if (success)
            graphicalAgent.gotoMainMenu();
        else {
            System.out.println(graphicalAgent.getPanels().size());
            loginPanel.setMessage(message);
        }
    }

    @Override
    public void signUp(boolean success, String message) {
        RegistrationPanel registrationPanel = (RegistrationPanel) graphicalAgent.getPanels().get(PanelType.REGISTRATION_PANEL);
        if (success)
            graphicalAgent.gotoMainMenu();
        else {
            System.out.println(graphicalAgent.getPanels().size());
            registrationPanel.setMessage(message);
        }

    }

    @Override
    public void showMessage(String s) {
        JOptionPane.showMessageDialog(null, s);
        graphicalAgent.gotoMainMenu();
        synchronized (events) {
            events.clear();
        }
    }

    @Override
    public void getPersonalInfo(PersonalInfoResponse personalInfoResponse) {
        graphicalAgent.gotoInfoPanel(personalInfoResponse);
    }

    @Override
    public void getPlayersList(LinkedList<PlayerData> playerList) {
        graphicalAgent.gotoScorePanel(playerList);
    }

    @Override
    public void getGameDetails(GameDetails gameDetails) {
        graphicalAgent.gotoGamePanel(gameDetails);
    }

    @Override
    public void getStringResponse(String s) {
        switch (s) {
            case "lost" -> graphicalAgent.goToResultPanel(false);
            case "won" -> graphicalAgent.goToResultPanel(true);
        }
    }

    @Override
    public void getLiveGames(LinkedList<LiveGame> liveGames) {
        graphicalAgent.gotoLiveGamesPanel(liveGames);
    }

    @Override
    public void getALiveGame(LiveGame liveGame) {

        graphicalAgent.gotoWatchPanel(liveGame);
    }
}
