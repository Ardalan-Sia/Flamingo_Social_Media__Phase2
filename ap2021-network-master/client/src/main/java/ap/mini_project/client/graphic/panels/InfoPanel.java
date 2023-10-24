package ap.mini_project.client.graphic.panels;

import ap.mini_project.client.listener.StringListener;
import ap.mini_project.shared.response.PersonalInfoResponse;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends AbstractPanel{
    private JLabel username;
    private JLabel score;
    private JLabel wins;
    private JLabel loses;
    private JButton backBtn;
    private StringListener stringListener;

    public InfoPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        username = new JLabel();
        wins = new JLabel();
        loses = new JLabel();
        score = new JLabel();


        gbc.weighty = 0.1;
        gbc.weightx = 0.1;

        //////////////////1

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,0);
        add(new JLabel("username : ") , gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        add(username , gbc);

        //////////////////2

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,0);
        add(new JLabel("wins : ") , gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        add(wins , gbc);

        //////////////////3

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,0);
        add(new JLabel("loses : ") , gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        add(loses , gbc);

        //////////////////4

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,0);
        add(new JLabel("score : ") , gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        add(score , gbc);


        backBtn = new JButton("back");

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,0);
        backBtn.addActionListener(e -> backAction());
        add(backBtn , gbc);



        setBackground(Color.ORANGE);







    }
    public void initialize(PersonalInfoResponse info){
        username.setText(info.getUsername());
        wins.setText(String.valueOf(info.getWins()));
        loses.setText(String.valueOf(info.getLoses()));
        score.setText(String.valueOf(info.getScore()));

    }
    public void backAction(){
        stringListener.stringEventOccurred("backToMainMenu");
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }
}
