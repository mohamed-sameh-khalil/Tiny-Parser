package sources;

import java.awt.*;
import java.util.List;
enum NodeType{CIRCLE, SQUARE}
public class Node {
    int x, y;
    Node sibling;
    List<Node> children;
    String text;
    NodeType type;
    void draw(Graphics g, int x, int y){
        this.x = x;
        this.y = y;
        //TODO add the drawing mechanism based on the type
        //also add the text
    }
}
