package ap.mini_project.client.graphic.panels;

import ap.mini_project.client.listener.EventListener;
import ap.mini_project.client.listener.StringListener;
import ap.mini_project.shared.events.SignInEvent;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends AbstractPanel {
    JButton loginBtn;
    JButton backBtn;
    JLabel message;
    JTextField usernameField;
    JTextField passField;
    StringListener stringListener;

    private final EventListener eventListener;

    public LoginPanel(EventListener eventListener) {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        this.eventListener = eventListener;


        usernameField = new JTextField(10);

        passField = new JTextField(10);

        loginBtn = new JButton("login");
        loginBtn.addActionListener(e -> loginAction());

        backBtn = new JButton("back");
        backBtn.addActionListener(e-> back());

        initializeMessage();


        ////////////////1
        gc.gridx = 0;
        gc.gridy = 0;

        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("username : ") , gc);

        gc.gridx = 1;
        gc.gridy = 0;

        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(usernameField , gc);

        /////////////////2
        gc.gridx = 0;
        gc.gridy = 1;

        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("password : ") , gc);

        gc.gridx = 1;
        gc.gridy = 1;

        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(passField , gc);

        ////////////////3
        gc.gridx = 1;
        gc.gridy = 2;

        gc.insets = new Insets(5,0,0,0);
        gc.anchor = GridBagConstraints.CENTER;

        add(message, gc);
        /////////////////4

        gc.gridx = 0;
        gc.gridy = 3;

        gc.insets = new Insets(5,0,0,0);
        gc.anchor = GridBagConstraints.CENTER;

        add(loginBtn, gc);

        gc.gridx = 1;
        gc.gridy = 3;

        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_END;

        add(backBtn,gc);



    }


    private void loginAction(){
        eventListener.listen(new SignInEvent(usernameField.getText() , passField.getText()));

    }
    private void back(){

        stringListener.stringEventOccurred("backToAuth");
    }
    public void setStringListener(StringListener stringListener){
        this.stringListener = stringListener;

    }
    public void setMessage(String message){
        this.message.setText(message);
    }
    private void initializeMessage() {
        message = new JLabel("", SwingConstants.CENTER);
        message.setForeground(Color.RED);
    }


}
