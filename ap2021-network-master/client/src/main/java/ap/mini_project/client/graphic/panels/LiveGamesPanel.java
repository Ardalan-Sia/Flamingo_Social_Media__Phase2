package ap.mini_project.client.graphic.panels;

import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.listener.EventListener;
import ap.mini_project.client.listener.StringListener;
import ap.mini_project.shared.events.GetALiveGameEvent;
import ap.mini_project.shared.model.GameStatutes;
import ap.mini_project.shared.model.LiveGame;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class LiveGamesPanel extends AbstractPanel{
    private ComponentsScrollPanel<JoinLiveGamePanel> scrollPane;
    private StringListener stringListener;
    EventListener listener;
    JButton backBtn;


    public LiveGamesPanel(EventListener listener) {
        this.listener = listener;
        setLayout(new BorderLayout());
        scrollPane = new ComponentsScrollPanel<>(new Dimension(Constant.WIDTH -30,
                Constant.HEIGHT - Constant.BUTTON_HEIGHT - 40));
        add(scrollPane , BorderLayout.CENTER);


        backBtn = new JButton("back");
        backBtn.setPreferredSize(new Dimension(Constant.BUTTON_WIDTH , Constant.BUTTON_HEIGHT));
        backBtn.addActionListener(e -> back());
        add(backBtn , BorderLayout.NORTH);


//        setPanels(liveGames);
    }
    public void setPanels(LinkedList<LiveGame> liveGames){
        LinkedList<JoinLiveGamePanel> panels = new LinkedList<>();
        for (LiveGame liveGame :liveGames) {
            JoinLiveGamePanel panel = new JoinLiveGamePanel(liveGame);
            panel.setStringListener(string -> {
                if (string.equals("join")) {
                    if (liveGame.getGameStatutes() != GameStatutes.SETTING_MAPS)
                        listener.listen(new GetALiveGameEvent(liveGame.getGameId()));
                    else JOptionPane.showMessageDialog(null,"not started yet");
                }
            });
            panels.add(panel);
        }
        scrollPane.setComponentsList(panels);

    }

    private void back(){
        stringListener.stringEventOccurred("backToMainMenu");
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }


}
