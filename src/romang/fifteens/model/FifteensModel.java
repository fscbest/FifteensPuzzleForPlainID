package romang.fifteens.model;

//FifteensModel.java - Fifteens Game's logic.

public class FifteensModel {

	private int gameRows;
	private int gameColumns;
	private Tile[][] gameTiles;

	public FifteensModel(int rows, int cols) {
		assert ((rows > 1) && (cols > 1));
		gameRows = rows;
		gameColumns = cols;
		gameTiles = new Tile[gameRows][gameColumns];
		initialize();
	}

	// Initialize the new game
	public void initialize() {
		reset();
		shuffle();
	}

	// Reset tiles
	private void reset() {
		for (int r = 0; r < gameRows; r++) {
			for (int c = 0; c < gameColumns; c++) {
				int position = (r * gameColumns) + c + 1;
				String tileFace = String.valueOf(position);
				gameTiles[r][c] = new Tile(tileFace);
			}
		}
		gameTiles[gameRows - 1][gameColumns - 1].setText("");
	}

	// Return the tile's label at given row, column.
	public String getText(int row, int column) {
		return gameTiles[row][column].getText();
	}

	// Shuffle the tiles in random order.
	private void shuffle() {
		do {
			for (int r = 0; r < gameRows; r++) {
				for (int c = 0; c < gameColumns; c++) {
					swapTiles(r, c, (int) (Math.random() * gameRows), (int) (Math.random() * gameColumns));
				}
			}
		} while (!canBeSolved());
	}

	// Check if current puzzle combination is solvable
	private boolean canBeSolved() {
		int tailsNumber = gameRows * gameColumns;
		int[] arr = new int[tailsNumber];
		int arrIndex = 0;
		int blankRow = 0;
		int inversions = 0;
		for (int r = 0; r < gameRows; r++) {
			for (int c = 0; c < gameColumns; c++) {
				if (gameTiles[r][c].isEmpty()) {
					arr[arrIndex] = 0;
					blankRow = r;
				} else {
					arr[arrIndex] = Integer.parseInt(gameTiles[r][c].getText());
				}
				arrIndex++;
			}
		}

		for (int i = 0; i < tailsNumber; i++) {
			for (int j = i + 1; j < tailsNumber; j++) {
				if (arr[i] > arr[j] && arr[j] != 0) {
					inversions++;
				}
			}
		}

		if (gameColumns % 2 == 0) {
			if (blankRow % 2 == 0) {
				return inversions % 2 == 0;
			} else {
				return inversions % 2 != 0;
			}
		} else {
			return inversions % 2 == 0;
		}
	}

	// Move a tile to empty position, if possible.
	// Return true if moved, false if not.
	public boolean moveTile(int r, int c) {
		if (checkAndMoveIfEmpty(r, c, -1, 0) || checkAndMoveIfEmpty(r, c, 1, 0) || checkAndMoveIfEmpty(r, c, 0, -1)
				|| checkAndMoveIfEmpty(r, c, 0, 1)) {
			return true;
		}
		return false;
	}

	// Check if tile can be moved and move it, if possible
	private boolean checkAndMoveIfEmpty(int r, int c, int rdelta, int cdelta) {
		int rNeighbor = r + rdelta;
		int cNeighbor = c + cdelta;

		if (isRowColInRange(rNeighbor, cNeighbor) && gameTiles[rNeighbor][cNeighbor].isEmpty()) {
			swapTiles(r, c, rNeighbor, cNeighbor);
			return true;
		}
		return false;
	}

	private boolean isRowColInRange(int r, int c) {
		return r >= 0 && r < gameRows && c >= 0 && c < gameColumns;
	}

	// Swap two tiles.
	private void swapTiles(int r1, int c1, int r2, int c2) {
		Tile temp = gameTiles[r1][c1];
		gameTiles[r1][c1] = gameTiles[r2][c2];
		gameTiles[r2][c2] = temp;
	}

	// Check if puzzle solved
	public boolean didWin() {
		for (int r = 0; r < gameRows; r++) {
			for (int c = 0; c < gameColumns; c++) {
				Tile tile = gameTiles[r][c];
				if (tile.isEmpty()) {
					if (r != gameRows - 1 || c != gameColumns - 1) { // If empty tile on a right position
						return false;
					}
				} else {
					int tileValue = (r * gameColumns) + c + 1;
					if (Integer.parseInt(tile.getText()) != tileValue) {
						return false;
					}
				}
			}
		}

		return true;
	}
}
