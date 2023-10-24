package ap.mini_project.client.graphic.panels;

import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.listener.StringListener;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends AbstractPanel{

    private JLabel label;
    private JButton okBtn;
    private StringListener stringListener;
    public ResultPanel(boolean result) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setBackground(result ? Color.GREEN : Color.red);


        label = new JLabel(result ? "winner" : "loser");
        label.setFont(new Font("Verdana" ,Font.BOLD , 80 ));

        okBtn = new JButton("ok");
        okBtn.setFocusable(false);
        okBtn.addActionListener(e -> okAction());
        okBtn.setPreferredSize(new Dimension(Constant.BUTTON_WIDTH ,Constant.BUTTON_HEIGHT));

        gbc.weighty = 0;
        gbc.weightx = 0;

        ////////////////////1
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,0,0,0);
        add(label , gbc);

        ///////////////////////2
        gbc.gridx = 0;
        gbc.gridy = 1;

        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(50,0,0,0);
        add(okBtn , gbc);

    }

    private void okAction(){
        stringListener.stringEventOccurred("backToMainMenu");
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }
}
