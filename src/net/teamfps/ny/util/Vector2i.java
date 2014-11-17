package net.teamfps.ny.util;

/**
 * @author Zekye
 * 
 */
public class Vector2i {
	private int x;
	private int y;

	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2i(Vector2i v) {
		this.x = v.x;
		this.y = v.y;
	}

	public int length() {
		return (int) Math.sqrt(x * x + y * y);
	}

	public int dot(Vector2i r) {
		return x * r.getX() + y * r.getY();
	}

	public Vector2i normalize() {
		int length = length();
		x /= length;
		y /= length;
		return this;
	}

	public Vector2i rotate(int angle) {
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		return new Vector2i((int) (x * cos - y * sin), (int) (x * sin + y * cos));
	}

	public Vector2i add(Vector2i r) {
		return new Vector2i(x + r.getX(), y + r.getY());
	}

	public Vector2i add(int r) {
		return new Vector2i(x + r, y + r);
	}

	public Vector2i add(int x, int y) {
		this.x += x;
		this.y += y;
		return new Vector2i(this.x, this.y);
	}

	public Vector2i sub(Vector2i r) {
		return new Vector2i(x - r.getX(), y - r.getY());
	}

	public Vector2i sub(int r) {
		return new Vector2i(x - r, y - r);
	}

	public Vector2i sub(int x, int y) {
		this.x -= x;
		this.y -= y;
		return new Vector2i(this.x, this.y);
	}

	public Vector2i mul(Vector2i r) {
		return new Vector2i(x * r.getX(), y * r.getY());
	}

	public Vector2i mul(int r) {
		return new Vector2i(x * r, y * r);
	}

	public Vector2i mul(int x, int y) {
		this.x *= x;
		this.y *= y;
		return new Vector2i(this.x, this.y);
	}

	public Vector2i div(Vector2i r) {
		return new Vector2i(x / r.getX(), y / r.getY());
	}

	public Vector2i div(int r) {
		return new Vector2i(x / r, y / r);
	}

	public Vector2i div(int x, int y) {
		this.x /= x;
		this.y /= y;
		return new Vector2i(this.x, this.y);
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

}
