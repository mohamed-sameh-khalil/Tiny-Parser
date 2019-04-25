package sources;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
enum NodeType{CIRCLE, SQUARE}
public class Node {
    int x, y;
    Node sibling = null;
    List<Node> children = new ArrayList<>();
    String text;
    NodeType type = NodeType.SQUARE;
    public Node() {
    }
    public Node(String text) {
        this.text = text;
    }

    void draw(Graphics g, int x, int y){
        this.x = x;
        this.y = y;
        //TODO add the drawing mechanism based on the type
        //also add the text
    }
    public String toString(){//for testing purposes only
        StringBuilder r = new StringBuilder();
        r.append("___"+text + "___\n");
        for (Node n:
             children) {
            r.append(n.toString());
        }
        if(sibling != null)
            r.append(sibling.toString());
        return r.toString();
    }
}
