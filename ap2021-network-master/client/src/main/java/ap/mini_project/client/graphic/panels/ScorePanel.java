package ap.mini_project.client.graphic.panels;

import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.listener.StringListener;
import ap.mini_project.shared.model.PlayerData;
import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class ScorePanel extends AbstractPanel {
    private ComponentsScrollPanel<JPanel> scrollPane;
    private StringListener stringListener;
    private JButton backBtn;

    public ScorePanel(LinkedList<PlayerData> playerList) {
        setLayout(new BorderLayout());
        scrollPane = new ComponentsScrollPanel<>(new Dimension(Constant.WIDTH -30,
                Constant.HEIGHT - Constant.BUTTON_HEIGHT - 40));
        add(scrollPane , BorderLayout.CENTER);

        backBtn = new JButton("back");
        backBtn.setPreferredSize(new Dimension(Constant.BUTTON_WIDTH , Constant.BUTTON_HEIGHT));
        backBtn.addActionListener(e -> back());
        add(backBtn , BorderLayout.NORTH);

    }

    private void back(){

        stringListener.stringEventOccurred("backToMainMenu");
    }

    public void setPlayers(LinkedList<PlayerData> playerList){
//        Collections.reverse(playerList);
        playerList.sort(Comparator.comparingInt(PlayerData::getScore));
        LinkedList<JPanel> panels = new LinkedList<>();

        for (PlayerData player : playerList) {
            JPanel playerInfoPanel = new JPanel(new GridLayout());
            playerInfoPanel.setPreferredSize(new Dimension(scrollPane.getWidth()-50 , 50));
            playerInfoPanel.setBackground(Color.ORANGE);
            playerInfoPanel.setLayout(new GridLayout());
            playerInfoPanel.add(new JLabel(" username : "+player.getUsername()));
            playerInfoPanel.add(new JLabel(player.getPlayerState().toString()));
            playerInfoPanel.add(new JLabel("score : "+String.valueOf(player.getScore())));

        panels.add(playerInfoPanel);
        }
        Collections.reverse(panels);
        scrollPane.setComponentsList(panels);





    }


    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }
}
