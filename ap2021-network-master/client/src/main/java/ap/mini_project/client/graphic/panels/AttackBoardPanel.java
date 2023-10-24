package ap.mini_project.client.graphic.panels;

import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.graphic.ImageLoader;
import ap.mini_project.shared.model.Board;
import ap.mini_project.shared.model.Cell;

import javax.swing.*;
import java.awt.*;

public class AttackBoardPanel extends JPanel {
    private Board board;
    private final int cellH;
    private final int cellW;
    public AttackBoardPanel(Board board  , int cellW , int cellH) {
        this.board = board;
        this.cellH = cellH;
        this.cellW = cellW;
        this.setBounds(Constant.WIDTH - (100+cellW * board.getW()), 100, cellW * board.getW(), cellH * board.getH());

    }
    public synchronized void setBoard(Board board) {
        this.board = board;
    }
    public void setLocation(int x  , int y){
        setBounds(x,y,cellW * board.getW(), cellH * board.getH());
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < board.getW(); i++) {
            for (int j = 0; j < board.getH(); j++) {
                Cell tem = board.getCells()[i][j];
                    g.setColor(new Color(tem.getR(), tem.getG(), tem.getB()));

                g.fillRect(i * cellW, j * cellH, cellW, cellH);
                if (tem.getPieceName() != null) {
                    g.drawImage(ImageLoader.getInstance().getImage(tem.getPieceName()), i * cellW, j * cellH,
                            cellW, cellH, null);
                }
            }
        };
    }
}


