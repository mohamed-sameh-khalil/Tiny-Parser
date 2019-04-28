package sources;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

enum NodeType {CIRCLE, SQUARE}

public class Node {
	static final int width = 100; //ADDED
	static final int height = 50;//ADDED
	static final int y_gap = 50; //ADDED
	static final int x_gap = 30; //ADDED
	static final int fontSize = 20;//ADDED
	int x, y;
	int level = 0; //solve y problem //ADDED
	Node sibling = null;
	List<Node> children = new ArrayList<>();
	String text;
	NodeType type = NodeType.SQUARE;

	public Node() {
	}

	public Node(String text) {
		this.text = text;
	}

	void draw(Graphics g, int level) { //ADDED

		this.level = level;
		this.y = level * (y_gap + height);
		this.x = Tree.last_x + width + x_gap + getNumberOfLeftChildren(this,0)*(width+x_gap);

		//------------DRAW Children------------\\
		for (Node i : this.children){
			i.draw(g,level + 1);
			g.drawLine(x+(width/2),y+(height),i.x+(width/2),i.y);
		}

		//------------DRAW ITSELF------------\\

		if (type == NodeType.CIRCLE){
			g.setColor(Color.BLUE);
			g.drawOval(x,y,width,height);
		}
		else{
			g.setColor(Color.BLACK);
			g.drawRect(x,y,width,height);
		}
		//--------------DRAW TEXT----------\\
		int text_width = g.getFontMetrics().stringWidth(text);
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
		g.drawString(text,x+((width-text_width)/2),y+(height/2));

		Tree.last_x = getXPosOfLastRightChildren(this);
		//------------DRAW Sibling------------\\
		if (this.sibling != null) {
			this.sibling.draw(g, level);
			g.drawLine(x+width,y+(height/2),this.sibling.x,this.sibling.y+(height/2));
		}
	}
	//ADDED
	public int getNumberOfLeftChildren(Node node,int number){
		if (node.children.size() == 0)
			return number;
		else if (node.children.size() == 1)
			return node.children.get(0).getNumberOfLeftChildren(node.children.get(0),number);
		else
			return node.children.get(0).getNumberOfLeftChildren(node.children.get(0),number+1);
	}
	//ADDED
	public int getXPosOfLastRightChildren(Node node){
		if (node.children.size()==0)
			return node.x;
		else {
			int indexOfLastRight = node.children.size()-1;
			return getXPosOfLastRightChildren(node.children.get(indexOfLastRight));
		}
	}

	public String toString() {//for testing purposes only
		StringBuilder r = new StringBuilder();
		r.append("___" + text + "___\n");
		for (Node n :
				children) {
			r.append(n.toString());
		}
		if (sibling != null)
			r.append(sibling.toString());
		return r.toString();
	}
}
