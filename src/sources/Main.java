package sources;

import javax.swing.*;
import java.awt.*;

public class Main {

	public static void main(String[] args) {

		Tree tree = Parser.parse();

		JFrame frame = new JFrame();
		DrawingPanel drawingPanel = new DrawingPanel(tree);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height= Toolkit.getDefaultToolkit().getScreenSize().height;
		frame.setBounds(0,0,width,height);
		frame.setSize(width,height);
		frame.setResizable(true);

		frame.add(drawingPanel);

		frame.setVisible(true);
	}
}
