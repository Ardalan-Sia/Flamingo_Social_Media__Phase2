package ap.mini_project.client.graphic.panels;

import ap.mini_project.client.graphic.Constant;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractPanel extends JPanel {

    public AbstractPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(Constant.WIDTH, Constant.HEIGHT));
        setSize(Constant.WIDTH, Constant.HEIGHT);
        setBackground(new Color(128,0,128));
    }
}
