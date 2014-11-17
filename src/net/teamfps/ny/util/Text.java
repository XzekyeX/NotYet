package net.teamfps.ny.util;

/**
 * @author Zekye
 *
 */
public class Text {
	public String text;
	public int fsize;
	public int color;

	public Text(String text, int fsize, int color) {
		this.text = text;
		this.fsize = fsize;
		this.color = color;
	}

	public Text(String text, int fsize) {
		this.text = text;
		this.fsize = fsize;
		this.color = 0xffffff;
	}
	
	public Text(String text) {
		this.text = text;
		this.fsize = 16;
		this.color = 0xffffff;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the fsize
	 */
	public int getFontSize() {
		return fsize;
	}

	/**
	 * @return the color
	 */
	public int getColor() {
		return color;
	}
}
