//Zak Haider

import javax.swing.JFrame;

public class Main{
	public Main(Chess chess) {
		JFrame frame = new JFrame("Chess by Zak Haider");
        frame.pack();
		//688 x 559
		frame.setSize(688,559);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(chess);
		frame.setVisible(true);
		frame.setResizable(false);
		chess.start();
	}
}
