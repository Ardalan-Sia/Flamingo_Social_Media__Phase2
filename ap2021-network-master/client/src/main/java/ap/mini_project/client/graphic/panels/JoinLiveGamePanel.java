package ap.mini_project.client.graphic.panels;

import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.listener.StringListener;
import ap.mini_project.shared.model.LiveGame;
import ap.mini_project.shared.model.Side;

import javax.swing.*;
import java.awt.*;

public class JoinLiveGamePanel extends JPanel {
    private JButton joinBtn;
    private JLabel P1UsernameLabel;
    private JLabel P2UsernameLabel;
    private JLabel P1DestroyedShipNumLabel;
    private JLabel P2DestroyedShipNumLabel;
    private JLabel P1DestroyedCellsNumLabel;
    private JLabel P2DestroyedCellsNumLabel;
    private StringListener stringListener;



    public JoinLiveGamePanel(LiveGame liveGame) {
        P1UsernameLabel = new JLabel();
        P1DestroyedCellsNumLabel = new JLabel();
        P2DestroyedCellsNumLabel = new JLabel();
        P2UsernameLabel = new JLabel();
        P2DestroyedShipNumLabel = new JLabel();
        P1DestroyedShipNumLabel = new JLabel();
//        setPanel(liveGame);
        this.setPreferredSize(new Dimension(Constant.WIDTH-50 , 80));
        this.setBackground(Color.YELLOW);
        this.setLayout(new GridLayout(3,2));


        P1UsernameLabel = new JLabel("player1 :");
        P2UsernameLabel = new JLabel("player2 :");

        P1DestroyedShipNumLabel = new JLabel("destroyed ships : ");
        P2DestroyedShipNumLabel = new JLabel("destroyed ships : ");

        P1DestroyedCellsNumLabel = new JLabel("destroyed cells : ");
        P2DestroyedCellsNumLabel = new JLabel("destroyed cells : ");

        this.add(P1UsernameLabel);
        this.add(P1DestroyedShipNumLabel);
        this.add(P1DestroyedCellsNumLabel);

        this.add(P2UsernameLabel);
        this.add(P2DestroyedShipNumLabel);
        this.add(P2DestroyedCellsNumLabel);
        joinBtn = new JButton("join");
        joinBtn.addActionListener(e ->JoinGameAction());
        this.add(joinBtn);

        setPanel(liveGame);
    }
    public void JoinGameAction( ){
        stringListener.stringEventOccurred("join");
    }
//    public void addStringListener(StringEventListener stringEventListener){
//        stringEventListeners.add(stringEventListener);
//    }
    public void setPanel(LiveGame liveGame ) {
        System.out.println(liveGame.getUsernames().get(Side.PLAYER_ONE));
        P1UsernameLabel.setText("player1 :" + liveGame.getUsernames().get(Side.PLAYER_ONE));
        P2UsernameLabel.setText("player2 :" + liveGame.getUsernames().get(Side.PLAYER_TWO));

        P1DestroyedShipNumLabel.setText("destroyed ships : " + liveGame.getDestroyedShips().get(Side.PLAYER_ONE));
        P2DestroyedShipNumLabel.setText("destroyed ships : " + liveGame.getDestroyedShips().get(Side.PLAYER_TWO));

        P1DestroyedCellsNumLabel.setText("destroyed cells : " + liveGame.getDestroyedCells().get(Side.PLAYER_ONE));
        P2DestroyedCellsNumLabel.setText("destroyed cells : " + liveGame.getDestroyedCells().get(Side.PLAYER_TWO));

    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }
}


//    public static void main(String[] args) {
//        Frame frame =  new MyFrame();
//        frame.setVisible(true);
//        frame.setSize(Constant.WIDTH,Constant.HEIGHT);
//        frame.add(new LivePagePanel(request -> {
//            return;
//        }));
//    }