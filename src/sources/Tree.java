package sources;

import java.awt.*;
import java.util.List;

public class Tree {
	Node root;
	static int last_x = 0;
	int level = 1; //each one sets in sibling and child
	List<Integer> maxX;//list to keep all the maximum x coordinates for each level

	void draw(Graphics g) {
		Tree.last_x = 0;
		root.draw(g,level);
	}

	public String toString() {//for testing purposes only

		return root.toString();
	}
}
