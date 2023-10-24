package ap.mini_project.client.graphic.panels;

import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.listener.EventListener;
import ap.mini_project.client.listener.StringListener;
import ap.mini_project.shared.events.ClickOnBoard;
import ap.mini_project.shared.events.GetPlayerList;
import ap.mini_project.shared.events.StringEvent;
import ap.mini_project.shared.model.GameDetails;
import ap.mini_project.shared.model.GameStatutes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends AbstractPanel {
    private final EventListener listener;
    private GameDetails gameDetails;
    private final MyBoardPanel myBoardPanel;
    private final AttackBoardPanel attackBoardPanel;
    private final int cellH;
    private final int cellW;
    private final JLabel messageLabel;
    private StringListener stringListener;
    private final JButton backBtn;
    private JButton changeBtn;
    private JCheckBox readyCheck;
    private JLabel timer;
    private JLabel myName;
    private JLabel enemyName;
    private void initialize(){

    }

    public GamePanel(EventListener listener, GameDetails gameDetails) {
        this.gameDetails = gameDetails;
        this.backBtn = new JButton("back");
        this.timer = new JLabel(""+30);
        this.listener = listener;
        this.cellH = (Constant.HEIGHT - 200) / gameDetails.getMyBoard().getH();
        this.cellW = (Constant.WIDTH/2 - 200) / gameDetails.getMyBoard().getW();
        this.myBoardPanel = new MyBoardPanel(gameDetails.getMyBoard(), cellW, cellH);
        this.attackBoardPanel = new AttackBoardPanel(gameDetails.getEnemyBoard() , cellW , cellH);
        this.messageLabel = new JLabel(gameDetails.getMyBoard().getMessage());
        this.changeBtn = new JButton("Change");
        this.readyCheck = new JCheckBox("ready");

        messageLabel.setBounds(Constant.WIDTH/2, 40, 200, 100);
        timer.setBounds(Constant.WIDTH/2 , 300 ,  100,100);
        backBtn.setBounds(0,0,70,30);
        changeBtn.setBounds(20,
                backBtn.getBounds().y+backBtn.getBounds().height,
                90,30);
        changeBtn.setBackground(Color.red);
        readyCheck.setBounds(20,
                changeBtn.getBounds().y+changeBtn.getBounds().height,
                90,30);


        attackBoardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    click(e.getX() / cellW, e.getY() / cellH);
            }
        });

        enemyName = new JLabel();
        myName = new JLabel("my username : "+"@"+gameDetails.getMyUsername());

        enemyName.setBounds(attackBoardPanel.getBounds().x
                , attackBoardPanel.getBounds().y+attackBoardPanel.getBounds().height+10,
                200,40);

        myName.setBounds(myBoardPanel.getBounds().x
                , myBoardPanel.getBounds().y+myBoardPanel.getBounds().height+10,
                200,40);

        add(myName);
        add(enemyName);


        backBtn.addActionListener(e->back());
        changeBtn.addActionListener(e->changeAction());
        readyCheck.addActionListener(e->isReady());


        changeBtn.setVisible(false);
        changeBtn.setEnabled(false);
        readyCheck.setVisible(false);
        readyCheck.setEnabled(false);


        add(attackBoardPanel);
        add(messageLabel);
        add(myBoardPanel);
        add(changeBtn);
        add(backBtn);
        add(readyCheck);
        add(timer);

    }

    public void getDetails(GameDetails gameDetails) {
        if (gameDetails.getGameStatutes() == GameStatutes.SETTING_MAPS){
            changeBtn.setVisible(true);
            changeBtn.setEnabled(true);
            readyCheck.setVisible(true);
            readyCheck.setEnabled(true);

        }

        String s = gameDetails.getEnemyUsername() .equals("_")?"_":"@";
        enemyName.setText("enemy's username : "+s+gameDetails.getEnemyUsername());
        this.gameDetails = gameDetails;
        timer.setText(""+gameDetails.getRemainingTime());
        myBoardPanel.setBoard(gameDetails.getMyBoard());
        attackBoardPanel.setBoard(gameDetails.getEnemyBoard());
        messageLabel.setText(gameDetails.getMyBoard().getMessage());
        checkGameIsStarted();
    }

    private void click(int x, int y) {
        listener.listen(new ClickOnBoard(x, y));
    }

    private void back(){

        stringListener.stringEventOccurred("backToMainMenu");
    }

    private void changeAction(){
        listener.listen(new StringEvent("changeMap"));

    }

    private void isReady(){
        if (readyCheck.isSelected())
        listener.listen(new StringEvent("ready"));
        else listener.listen(new StringEvent("notReady"));
    }

    public void setStringListener(StringListener stringListener){

        this.stringListener = stringListener;
    }

    public void checkGameIsStarted(){

        if (gameDetails.getGameStatutes() == GameStatutes.IS_STARTED) {
            remove(changeBtn);
            remove(readyCheck);
        }
    }


    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
