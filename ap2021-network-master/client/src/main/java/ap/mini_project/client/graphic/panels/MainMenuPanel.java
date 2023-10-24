package ap.mini_project.client.graphic.panels;

import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.listener.EventListener;
import ap.mini_project.shared.events.GetLiveGames;
import ap.mini_project.shared.events.GetPersonalInfo;
import ap.mini_project.shared.events.GetPlayerList;
import ap.mini_project.shared.events.StartGame;
import ap.mini_project.shared.model.LiveGame;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends AbstractPanel {
    private JButton start;
    private JButton exit;
    private JButton scoreBoard;
    private JButton myInfo;
    private JButton liveGames;

    private final EventListener eventListener;

    public MainMenuPanel(EventListener eventListener) {
        this.eventListener = eventListener;
        start = new JButton("start");
        start.setBounds(Constant.BUTTON_X, Constant.START_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
        start.addActionListener(e -> this.startAction());
        add(start);
        ////////////////////
        myInfo = new JButton("MyInfo");
        myInfo.setBounds(Constant.BUTTON_X, Constant.MY_INFO_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
        myInfo.addActionListener(e ->this.getInfo());
        add(myInfo);
        ///////////////////
        scoreBoard = new JButton("Scores");
        scoreBoard.setBounds(Constant.BUTTON_X, Constant.SCORE_BOARD_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
        scoreBoard.addActionListener(e -> this.getPlayersListAction());
        add(scoreBoard);
        /////////////////
        liveGames = new JButton("LiveGames");
        liveGames.setFont(new Font("" , Font.BOLD , 11));
        liveGames.setBounds(Constant.BUTTON_X, Constant.LIVE_GAMES_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
        liveGames.addActionListener(e -> this.getLiveGames());
        add(liveGames);

        exit = new JButton("exit");
        exit.setBounds(Constant.BUTTON_X, Constant.EXIT_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
        exit.addActionListener(e -> this.exitAction());
        add(exit);
    }


    private void startAction() {
        eventListener.listen(new StartGame());
    }
    private void getInfo(){
        eventListener.listen(new GetPersonalInfo());
    }
    private void getPlayersListAction(){
        eventListener.listen(new GetPlayerList());
    }
    private void getLiveGames(){
        eventListener.listen(new GetLiveGames());
    }

    private void exitAction() {
        System.exit(0);
    }


}
