package game;

import javax.swing.*;

/**
 * The main frame for the game
 */
public final class Game{

    private Game() {
    }

    public static void main(String[] args) {
		JFrame window = new JFrame("QuestBlock");
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setContentPane(new GamePanel());
		window.setResizable(false);
		window.setVisible(true);
		window.pack();
		window.setLocationRelativeTo(null);
	}
}
