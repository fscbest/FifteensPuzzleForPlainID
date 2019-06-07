package romang.fifteens.model;

//Tile.java - Fifteen game's tile

public class Tile {
	private String text = "";

	public Tile(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public boolean isEmpty() {
		return text.isEmpty();
	}

}
