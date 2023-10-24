package ap.mini_project.client.graphic.panels;

import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.listener.StringListener;
import ap.mini_project.shared.model.GameDetails;
import ap.mini_project.shared.model.GameStatutes;
import ap.mini_project.shared.model.LiveGame;
import ap.mini_project.shared.model.Side;

import javax.swing.*;
import java.util.LinkedHashMap;

public class WatchLiveGame extends AbstractPanel{
    private JButton backBtn;
    private AttackBoardPanel p1BoardPanel;
    private AttackBoardPanel p2BoardPanel;
    private final int cellH;
    private final int cellW;
    private JLabel p1username;
    private JLabel p2username;
    private JLabel messageLabel;
    private StringListener stringListener;

    public WatchLiveGame(LiveGame liveGame) {
        this.cellH = (Constant.HEIGHT - 200) / liveGame.getBoards().get(Side.PLAYER_ONE).getH();
        this.cellW = (Constant.WIDTH/2 - 200) / liveGame.getBoards().get(Side.PLAYER_ONE).getW();
        this.backBtn = new JButton("back");
        this.messageLabel = new JLabel("turn : "+liveGame.getTurn().toString());
        messageLabel.setBounds(Constant.WIDTH/2, 40, 200, 100);


        p1username = new JLabel("PlayerOne : "+"@"+liveGame.getUsernames().get(Side.PLAYER_ONE));
        p2username = new JLabel("PlayerTwo : "+"@"+liveGame.getUsernames().get(Side.PLAYER_TWO));

        p1BoardPanel = new AttackBoardPanel(liveGame.getBoards().get(Side.PLAYER_ONE),cellW,cellH);
        p2BoardPanel = new AttackBoardPanel(liveGame.getBoards().get(Side.PLAYER_TWO),cellW,cellH);

        p1BoardPanel.setLocation(100, 100);
        backBtn.setBounds(0,0,70,30);
        backBtn.addActionListener(e->back());

        p2username.setBounds(p2BoardPanel.getBounds().x
                , p2BoardPanel.getBounds().y+p2BoardPanel.getBounds().height+10,
                200,40);

        p1username.setBounds(p1BoardPanel.getBounds().x
                , p1BoardPanel.getBounds().y+p1BoardPanel.getBounds().height+10,
                200,40);

        add(p1username);
        add(p2username);
        add(p1BoardPanel);
        add(p2BoardPanel);
        add(messageLabel);
        add(backBtn);
    }

    private void back(){
        stringListener.stringEventOccurred("backToMainMenu");
    }

    public void setDetails(LiveGame liveGame) {
        p1BoardPanel.setBoard(liveGame.getBoards().get(Side.PLAYER_ONE));
        p2BoardPanel.setBoard(liveGame.getBoards().get(Side.PLAYER_TWO));
        this.messageLabel = new JLabel("turn : "+liveGame.getTurn().toString());

    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }
}
