package romang.fifteens;

// FifteensGUI.java - Fifteen game's GUI
//
// The FifteensGUI class creates a panel with 
//     1. "New Game" button.
//     2. Playing area.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import romang.fifteens.model.FifteensModel;

// The GUI interface
class FifteensGUI extends JPanel {
	private static final long serialVersionUID = 7859479819793861259L;
	//=============================================== instance variables
    private GraphicsPanel fifteensGraphicsPanel;
    private FifteensModel fifteensModel;
    private Color backgroundColor;


    //constructor
    public FifteensGUI(int rows, int cols, int cellSize) {
    	backgroundColor = Color.red;
    	fifteensModel = new FifteensModel(rows, cols);
        //Create a "New Game" button.
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new NewGameAction());

        //Control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(newGameButton);
        
        //Graphics panel
        fifteensGraphicsPanel = new GraphicsPanel(rows, cols, cellSize);
        
        this.setLayout(new BorderLayout());
        this.add(controlPanel, BorderLayout.SOUTH);
        this.add(fifteensGraphicsPanel, BorderLayout.CENTER);
    }


    class GraphicsPanel extends JPanel implements MouseListener {
		private static final long serialVersionUID = -1699133196813600273L;
		
		private final int ROWS;
        private final int COLS;
        private final int CELL_SIZE; // Pixels
        private Font textFont;
        
        
        //constructor
        public GraphicsPanel(int rows, int cols, int cellSize) {
        	ROWS = rows;
        	COLS = cols;
        	CELL_SIZE = cellSize;
            textFont = new Font("SansSerif", Font.BOLD, CELL_SIZE/2);
            this.setPreferredSize(
                   new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
            this.setBackground(Color.lightGray);
            this.addMouseListener(this);
        }
        
        
        //Draw tails
        public void paintComponent(Graphics gr) {
            super.paintComponent(gr);
            for (int r=0; r<ROWS; r++) {
                for (int c=0; c<COLS; c++) {
                    int x = c * CELL_SIZE;
                    int y = r * CELL_SIZE;
                    String text = fifteensModel.getText(r, c);
                    if (text != null) {
                        gr.setColor(backgroundColor);
                        gr.fillRoundRect(x+2, y+2, CELL_SIZE-4, CELL_SIZE-4, 20, 20);
                        gr.setColor(Color.black);
                        gr.setFont(textFont);
                        gr.drawRoundRect(x+2, y+2, CELL_SIZE-4, CELL_SIZE-4, 20, 20);
                        showText(gr, text, x , y);
                    }
                }
            }
        }
        
        //Draw tail's label
        private void showText(Graphics gr, String s, int x, int y) {
            FontMetrics fontMetrics = gr.getFontMetrics();
            int ascent = fontMetrics.getAscent();
            int descent = fontMetrics.getDescent();
            gr.drawString(s,  x + (CELL_SIZE - fontMetrics.stringWidth(s)) / 2,
                y + (ascent + (CELL_SIZE - (ascent + descent)) / 2));
          }
        
        //mousePressed listener
        public void mousePressed(MouseEvent e) {
            int col = e.getX()/CELL_SIZE;
            int row = e.getY()/CELL_SIZE;
            
            if (!fifteensModel.moveTile(row, col)) {
                // beep if tail can't be moved
                Toolkit.getDefaultToolkit().beep();
            } else {
            	//Change tails color to green if solved
            	backgroundColor = fifteensModel.didWin() ? Color.green : Color.red;
            }
            
            this.repaint(); 
        }
                
        public void mouseClicked (MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered (MouseEvent e) {}
        public void mouseExited  (MouseEvent e) {}
    }
    

    public class NewGameAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	backgroundColor = Color.red;
            fifteensModel.initialize();
            fifteensGraphicsPanel.repaint();
        }
    }

}