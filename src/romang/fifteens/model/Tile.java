/**
 * 
 */
package romang.fifteens.model;

/**
 * @author roman
 *
 */
public class Tile {
	private String text = "";
	/**
	 * @param text
	 */
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
