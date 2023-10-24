package ap.mini_project.client.graphic.panels;

import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.graphic.GraphicalAgent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthPanel extends AbstractPanel implements ActionListener {
    JButton registrationBtn;
    JButton loginBtn;
    private final GraphicalAgent graphicalAgent;

    public AuthPanel(GraphicalAgent graphicalAgent) {
        this.graphicalAgent = graphicalAgent;
        loginBtn = new JButton("login");
        loginBtn.setBounds(Constant.BUTTON_X, Constant.LOGIN_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
        loginBtn.setFocusable(false);
        loginBtn.addActionListener(this);
        add(loginBtn);

        registrationBtn = new JButton("register");
        registrationBtn.setBounds(Constant.BUTTON_X, Constant.REGISTER_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
        registrationBtn.setFocusable(false);
        registrationBtn.addActionListener(this);
        add(registrationBtn);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBtn){
            graphicalAgent.gotoLoginPanel();

        }else if (e.getSource() == registrationBtn){
            graphicalAgent.gotoRegistrationPanel();
        }
    }
}
