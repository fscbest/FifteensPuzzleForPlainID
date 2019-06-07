package romang.fifteens.model;

//FifteensModel.java - Slide pieces to correct position.

/////////////////////////////////////////// class FifteensModel
public class FifteensModel {
 //============================================== instance vars
 private int _rows;
 private int _cols;
 private Tile[][] _contents;  // All tiles.

 //================================================= constructor
 public FifteensModel(int rows, int cols) {
     assert ((rows > 1) && (cols > 1));
     _rows = rows;
     _cols = cols;
     _contents = new Tile[_rows][_cols];
     initialize();
 }

 //================================================ initialize
 public void initialize() {
     reset();
     shuffle();
 }

private void reset() {
	for (int r=0; r<_rows; r++) {
         for (int c=0; c<_cols; c++) {
             int position = (r * _cols) + c + 1;
             String tileFace = String.valueOf(position);
             _contents[r][c] = new Tile(tileFace);
         }
     }
     _contents[_rows -1][_cols-1].setText("");
}

 //============================================= getNumberOfRows
 public int getNumberOfRows() {
     return _rows;
 }


 //============================================= getNumberOfCols
 public int getNumberOfColumnss() {
     return _cols;
 }


 //===================================================== getFace
 // Return the string to display at given row, col.
 public String getText(int row, int col) {
     return _contents[row][col].getText();
 }


 //===================================================== shuffle
 // Shuffle the tiles in random order.
 //   Note: This shuffle algorithm distributes the tiles
 //         almost completely randomly.   The extremely
 //         small deviation from random can be ignored.
 private void shuffle() {
     //-- Shuffle - Exchange each tile with random tile.
	 
     do {
		for (int r = 0; r < _rows; r++) {
			for (int c = 0; c < _cols; c++) {
				swapTiles(r, c, (int) (Math.random() * _rows), (int) (Math.random() * _cols));
			}
		} 
	} while (!canBeSolved());
 }


 private boolean canBeSolved() {
	// TODO Auto-generated method stub
	return true;
}

//==================================================== moveTile
 // Move a tile to empty position beside it, if possible.
 // Return true if it was moved, false if not legal.
 // BAD BAD BAD - should move tile here, not in checkEmpty
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 public boolean moveTile(int r, int c) {
     //--- It's a legal move if the empty cell is next to it.
	 if(checkEmpty(r, c, -1, 0) || checkEmpty(r, c, 1, 0)
	         || checkEmpty(r, c, 0, -1) || checkEmpty(r, c, 0, 1)) {
		 return true;
	 }
     return false;
 }


 //================================================== checkEmpty
 // Check to see if there is an empty position beside tile.
 // Return true and exchange if possible, else return false.
 private boolean checkEmpty(int r, int c, int rdelta, int cdelta) {
     int rNeighbor = r + rdelta;
     int cNeighbor = c + cdelta;
     //--- Check to see if this neighbor is on board and is empty.
     if (isLegalRowCol(rNeighbor, cNeighbor)
               && _contents[rNeighbor][cNeighbor].isEmpty()) {
         swapTiles(r, c, rNeighbor, cNeighbor);
         return true;
     }
     return false;
 }


 //=============================================== isLegalRowCol
 // Check for legal row, col
 private boolean isLegalRowCol(int r, int c) {
     return r>=0 && r<_rows && c>=0 && c<_cols;
 }


 //=============================================== exchangeTiles
 // Exchange two tiles.
 private void swapTiles(int r1, int c1, int r2, int c2) {
     Tile temp = _contents[r1][c1];
     _contents[r1][c1] = _contents[r2][c2];
     _contents[r2][c2] = temp;
 }


 //=================================================== isGameOver
 public boolean didWin() {
     for (int r=0; r<_rows; r++) {
         for (int c=0; c<_cols; c++) {
             Tile tile = _contents[r][c];
             if(tile.isEmpty()){
            	 if(r != _rows - 1 || c != _cols - 1) { //If empty tile on a right position
                	 return false;
                 } 
             } else {
            	 int tileValue = (r * _cols) + c + 1;
                 if (Integer.parseInt(tile.getText()) != tileValue) {
                     return false;
                 }
             }
         }
     }

     //--- Falling thru loop means nothing out of place.
     return true;
 }
}//end class FifteensModel
