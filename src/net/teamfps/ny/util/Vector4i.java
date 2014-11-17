package net.teamfps.ny.util;

/**
 * @author Zekye
 * 
 */
public class Vector4i {
	private int x;
	private int y;
	private int w;
	private int h;

	public Vector4i(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public int length() {
		return (int) Math.sqrt(x * x + y * y + w * w + h * h);
	}

	public int dot(Vector4i r) {
		return x * r.getX() + y * r.getY() + w * r.getW() + h * r.getH();
	}

	public Vector4i normalize() {
		int length = length();
		x /= length;
		y /= length;
		w /= length;
		h /= length;
		return this;
	}

	public Vector4i rotate(int angle) {
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		return new Vector4i((int) (x * cos - y * sin), (int) (x * sin + y * cos), (int) (w * cos - h * sin), (int) (w * sin + h * cos));
	}

	public Vector4i add(Vector4i r) {
		return new Vector4i(x + r.getX(), y + r.getY(), w + r.getW(), h + r.getH());
	}

	public Vector4i add(int r) {
		return new Vector4i(x + r, y + r, w + r, h + r);
	}

	public Vector4i sub(Vector4i r) {
		return new Vector4i(x - r.getX(), y - r.getY(), w - r.getW(), h - r.getH());
	}

	public Vector4i sub(int r) {
		return new Vector4i(x - r, y - r, w - r, h - r);
	}

	public Vector4i mul(Vector4i r) {
		return new Vector4i(x * r.getX(), y * r.getY(), w * r.getW(), h * r.getH());
	}

	public Vector4i mul(int r) {
		return new Vector4i(x * r, y * r, w * r, h * r);
	}

	public Vector4i div(Vector4i r) {
		return new Vector4i(x / r.getX(), y / r.getY(), w / r.getW(), h / r.getH());
	}

	public Vector4i div(int r) {
		return new Vector4i(x / r, y / r, w / r, h / r);
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
	 * @param w
	 *            the w to set
	 */
	public void setW(int w) {
		this.w = w;
	}

	/**
	 * @param h
	 *            the h to set
	 */
	public void setH(int h) {
		this.h = h;
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

	/**
	 * @return the w
	 */
	public int getW() {
		return w;
	}

	/**
	 * @return the h
	 */
	public int getH() {
		return h;
	}
}
