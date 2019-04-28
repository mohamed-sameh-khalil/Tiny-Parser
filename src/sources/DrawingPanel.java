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
        Tree.last_x = 0;
        tree.draw(g);
    }
}
