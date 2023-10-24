package ap.mini_project.client.graphic.panels;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ComponentsScrollPanel<Child extends JComponent> extends JPanel {
    private LinkedList<Child> childrenList = new LinkedList<>();
    private JPanel panel = new JPanel();
    private JScrollPane scrollPane;

    public ComponentsScrollPanel(Dimension dimension) {
        this.setBackground(Color.black);
        scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JPanel contentPane = new JPanel(new GridLayout());

        contentPane.setPreferredSize(dimension);
        contentPane.add(scrollPane);
        this.add(contentPane);
//        setTweets();
        revalidate();
        repaint();
    }

    public void setComponent() {
        panel.setLayout(new GridBagLayout());
        if (!childrenList.isEmpty()) {
            int i = 0;
            for (Child child : childrenList) {
                GridBagConstraints gc = new GridBagConstraints();
                gc.gridx = 0;
                gc.gridy = i;
                gc.insets = new Insets(20, 0, 0, 0);
                gc.anchor = GridBagConstraints.SOUTH;
                panel.add(child, gc);
                i++;
            }
        }
        revalidate();
        repaint();
    }
    public void setComponentsList(LinkedList<Child> childrenList) {
        panel.removeAll();
        this.childrenList = childrenList;
        setComponent();
    }
}