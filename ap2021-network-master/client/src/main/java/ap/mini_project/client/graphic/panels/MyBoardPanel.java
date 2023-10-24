package ap.mini_project.client.graphic.panels;

import ap.mini_project.client.graphic.ImageLoader;
import ap.mini_project.shared.model.Board;
import ap.mini_project.shared.model.Cell;

import javax.swing.*;
import java.awt.*;

public class MyBoardPanel extends JPanel {
    private Board board;
    private final int cellH;
    private final int cellW;

    public MyBoardPanel(Board board, int cellW, int cellH) {
        setLayout(null);
        this.board = board;
        this.cellW = cellW;
        this.cellH = cellH;
        this.setBounds(100, 100, cellW * board.getW(), cellH * board.getH());
    }

    public synchronized void setBoard(Board board) {
        this.board = board;
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < board.getW(); i++) {
            for (int j = 0; j < board.getH(); j++) {
                Cell tem = board.getCells()[i][j];
                if (tem.getShip()  != null) {
                    if (tem.isDestroyed())
                        g.setColor(Color.RED);
                    else
                    g.setColor(Color.BLUE);
                }
                else {
                    g.setColor(new Color(tem.getR(), tem.getG(), tem.getB()));
                }
                g.fillRect(i * cellW, j * cellH, cellW, cellH);
                if (tem.getPieceName() != null) {
                    g.drawImage(ImageLoader.getInstance().getImage(tem.getPieceName()), i * cellW, j * cellH,
                            cellW, cellH, null);
                }
            }
        }
    }
}
