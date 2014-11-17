package net.teamfps.ny.util;

/**
 * @author Zekye
 * 
 */
public class Quaternion {
	private float x;
	private float y;
	private float z;
	private float w;

	public Quaternion(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z + w * w);
	}

	public Quaternion normalize() {
		float length = length();
		x /= length;
		y /= length;
		z /= length;
		w /= length;
		return this;
	}

	public Quaternion conjugate() {
		return new Quaternion(-x, -y, -z, w);
	}

	public Quaternion mul(Quaternion r) {
		float w1 = w * r.getW() - x * r.getX() - y * r.getY() - z * r.getZ();
		float x1 = x * r.getW() + w * r.getX() + y * r.getZ() - z * r.getY();
		float y1 = y * r.getW() + w * r.getY() + z * r.getX() - x * r.getZ();
		float z1 = z * r.getW() + w * r.getZ() + x * r.getY() - y * r.getX();

		return new Quaternion(x1, y1, z1, w1);
	}

	public Quaternion mul(Vector3f r) {
		float w1 = -x * r.getX() - y * r.getY() - z * r.getZ();
		float x1 = w * r.getX() + y * r.getZ() - z * r.getY();
		float y1 = w * r.getY() + z * r.getX() - x * r.getZ();
		float z1 = w * r.getZ() + x * r.getY() - y * r.getX();
		return new Quaternion(x1, y1, z1, w1);
	}

	
	public Quaternion mul(Vector3i r) {
		float w1 = -x * r.getX() - y * r.getY() - z * r.getZ();
		float x1 = w * r.getX() + y * r.getZ() - z * r.getY();
		float y1 = w * r.getY() + z * r.getX() - x * r.getZ();
		float z1 = w * r.getZ() + x * r.getY() - y * r.getX();
		return new Quaternion(x1, y1, z1, w1);
	}
	
	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * @return the z
	 */
	public float getZ() {
		return z;
	}

	/**
	 * @param z
	 *            the z to set
	 */
	public void setZ(float z) {
		this.z = z;
	}

	/**
	 * @return the w
	 */
	public float getW() {
		return w;
	}

	/**
	 * @param w
	 *            the w to set
	 */
	public void setW(float w) {
		this.w = w;
	}
}
