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
	private static final int ROWS = 4; // number of tiles in a row
    private static final int COLUMNS = 4;  // number of tiles in a column
    private static final int CELL_SIZE = 100;  //tile size in pixels

 public static void main(String[] args) {
     JFrame window = new JFrame("15 Puzzle");
     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     window.setContentPane(new FifteensGUI(ROWS, COLUMNS, CELL_SIZE));
     window.pack();
     window.setResizable(false);
     window.setLocationRelativeTo(null);
     window.setVisible(true);
     
 }
}