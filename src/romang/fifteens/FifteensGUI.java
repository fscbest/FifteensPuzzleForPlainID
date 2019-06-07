package romang.fifteens;

// FifteensGUI.java - Fifteen game's GUI
//
// The FifteensGUI class creates a panel which 
//     contains two subpanels.
//     1. In the north is a subpanel for controls (just a button now).
//     2. In the center a graphics
// This needs a few improvements.  
//   Both the GUI and Model define the number or rows and columns.
//          How would you set both from one place? 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import romang.fifteens.model.FifteensModel;

/////////////////////////////////////////////////// class FifteensGUI
// This class contains all the parts of the GUI interface
class FifteensGUI extends JPanel {
	private static final long serialVersionUID = 7859479819793861259L;
	//=============================================== instance variables
    private GraphicsPanel fifteensGraphicsPanel;
    private FifteensModel fifteensModel;
    private Color backgroundColor;
    //end instance variables


    //====================================================== constructor
    public FifteensGUI(int rows, int cols, int cellSize) {
    	backgroundColor = Color.red;
    	fifteensModel = new FifteensModel(rows, cols);
        //--- Create a button.  Add a listener to it.
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new NewGameAction());

        //--- Create control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(newGameButton);
        
        //--- Create graphics panel
        fifteensGraphicsPanel = new GraphicsPanel(rows, cols, cellSize);
        
        //--- Set the layout and add the components
        this.setLayout(new BorderLayout());
        this.add(controlPanel, BorderLayout.NORTH);
        this.add(fifteensGraphicsPanel, BorderLayout.CENTER);
    }//end constructor


    //////////////////////////////////////////////// class GraphicsPanel
    // This is defined inside the outer class so that
    // it can use the outer class instance variables.
    class GraphicsPanel extends JPanel implements MouseListener {
		private static final long serialVersionUID = -1699133196813600273L;
		
		private final int ROWS;
        private final int COLS;
        private final int CELL_SIZE; // Pixels
        private Font _biggerFont;
        
        
        //================================================== constructor
        public GraphicsPanel(int rows, int cols, int cellSize) {
        	ROWS = rows;
        	COLS = cols;
        	CELL_SIZE = cellSize;
            _biggerFont = new Font("SansSerif", Font.BOLD, CELL_SIZE/2);
            this.setPreferredSize(
                   new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
            this.setBackground(Color.black);
            this.addMouseListener(this);  // Listen own mouse events.
        }//end constructor
        
        
        //=======================================x method paintComponent
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int r=0; r<ROWS; r++) {
                for (int c=0; c<COLS; c++) {
                    int x = c * CELL_SIZE;
                    int y = r * CELL_SIZE;
                    String text = fifteensModel.getText(r, c);
                    if (text != null) {
                        g.setColor(backgroundColor);
                        g.fillRect(x+4, y+4, CELL_SIZE-4, CELL_SIZE-4);
                        g.setColor(Color.black);
                        g.setFont(_biggerFont);
                        g.drawString(text, x+20, y+(3*CELL_SIZE)/4);
                    }
                }
            }
        }//end paintComponent
        
        
        //======================================== listener mousePressed
        public void mousePressed(MouseEvent e) {
            //--- map x,y coordinates into a row and col.
            int col = e.getX()/CELL_SIZE;
            int row = e.getY()/CELL_SIZE;
            
            if (!fifteensModel.moveTile(row, col)) {
                // moveTile moves tile if legal, else returns false.
                Toolkit.getDefaultToolkit().beep();
            } else {
            	backgroundColor = fifteensModel.didWin() ? Color.green : Color.red;
            }
            
            this.repaint();  // Show any updates to model.
        }//end mousePressed
        
        
        //========================================== ignore these events
        public void mouseClicked (MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered (MouseEvent e) {}
        public void mouseExited  (MouseEvent e) {}
    }//end class GraphicsPanel
    
    ////////////////////////////////////////// inner class NewGameAction
    public class NewGameAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	backgroundColor = Color.red;
            fifteensModel.initialize();
            fifteensGraphicsPanel.repaint();
        }
    }//end inner class NewGameAction

}//end class FifteensGUI