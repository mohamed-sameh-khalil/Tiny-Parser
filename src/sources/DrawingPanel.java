package sources;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {

    Tree tree;
    public DrawingPanel(Tree tree) {
        this.tree = tree;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        tree.draw(g);
    }
}
