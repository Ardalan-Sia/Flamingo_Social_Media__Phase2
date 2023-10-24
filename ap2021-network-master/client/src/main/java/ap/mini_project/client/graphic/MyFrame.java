package ap.mini_project.client.graphic;

import ap.mini_project.shared.util.Loop;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private final Loop loop;

    public MyFrame() {
        this.setLayout(null);
        this.setSize(Constant.WIDTH, Constant.HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.loop = new Loop(Constant.FPS, this::update);
    }


    @Override
    public void setContentPane(Container contentPane) {
        super.setContentPane(contentPane);
        repaint();
        revalidate();
    }

    private void update() {
        this.repaint();
        this.revalidate();
    }

    @Override
    public void setVisible(boolean b) {
        if (b) loop.start();
        else loop.stop();
        super.setVisible(b);
    }
}
