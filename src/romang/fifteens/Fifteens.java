package romang.fifteens;

//Fifteens.java - implementation of single-user game 15 Puzzle.
//
//The Fifteens program consists of three files:
//Fifteens.java      - MAIN to run the game.
//FifteensGUI.java   - the GUI.
//FifteensModel.java - the logic of game.

import javax.swing.JFrame;

///////////////////////////////////////////// class Fifteens
class Fifteens {
	private static final int ROWS = 4;
    private static final int COLUMNS = 4;
    private static final int CELL_SIZE = 120;

 public static void main(String[] args) {
     JFrame window = new JFrame("Slide Puzzle");
     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     window.setContentPane(new FifteensGUI(ROWS, COLUMNS, CELL_SIZE));
     window.pack();  // finalize layout
     window.setResizable(false);
     window.setLocationRelativeTo(null);
     window.setVisible(true);
     
 }
}