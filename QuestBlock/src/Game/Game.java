package Game;

import javax.swing.*;

public class Game{

	public static void main(String[] args) {
		JFrame window = new JFrame("QuestBlock");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new GamePanel());
		window.setResizable(false);
		window.setVisible(true);
		window.pack();
		window.setLocationRelativeTo(null);
	}
}
