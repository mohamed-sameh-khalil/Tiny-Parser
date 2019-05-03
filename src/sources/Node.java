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
	static final int fontSize = 16;//ADDED
	static final Color cir_color= Color.RED;//ADDED
	static final Color rec_color= Color.lightGray;//ADDED
	static final Color lin_color= Color.black;//ADDED

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

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3)); //To draw thick lines

		this.level = level;
		this.y = level * (y_gap + height);
		//this.x = Tree.last_x + width + x_gap + (getNumberOfLeftChildren(this,0)*(width+x_gap)); //Old version

		//------------DRAW Children------------\\
		for (Node i : this.children)
			i.draw(g,level + 1);

		//------------DRAW ITSELF------------\\
		if (this.children.size() > 0)
			this.x =((getXPosOfLastRightChildren(this) + getXPosOfLastLeftChildren(this ))/2);
		else
			this.x = Tree.last_x + width + x_gap;

		if (type == NodeType.CIRCLE){
			g2.setColor(cir_color);
			g2.drawOval(x,y,width,height);
		}
		else{
			g2.setColor(rec_color);
			g2.drawRect(x,y,width,height);
		}

		//-------DRAW CHILDREN LINES----------\\\\\\\\\\\\
		for (Node i : this.children) {
			g2.setColor(lin_color);
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(x + (width / 2), y + (height), i.x + (width / 2), i.y);
		}

		//--------------DRAW TEXT----------\\
		int text_width = g.getFontMetrics().stringWidth(text);
		g2.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
		g2.drawString(text,x+((width-text_width)/2),y+(height/2));

		Tree.last_x = getXPosOfLastRightChildren(this);
		//------------DRAW Sibling------------\\
		if (this.sibling != null) {
			this.sibling.draw(g, level);

			g2.setColor(lin_color);
			g2.drawLine(x+width,y+(height/2),this.sibling.x,this.sibling.y+(height/2));
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
	//ADDED
	public int getXPosOfLastLeftChildren(Node node){
		if (node.children.size()==0)
			return node.x;
		else {
			int indexOfLastRight = 0;
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
