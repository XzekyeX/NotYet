package net.teamfps.ny.util;

/**
 * @author Zekye
 * 
 */
public class Vector3i {
	private int x;
	private int y;
	private int z;

	public Vector3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int length() {
		return (int) Math.sqrt(x * x + y * y + z * z);
	}

	public int max() {
		return Math.max(x, Math.max(y, z));
	}

	public int dot(Vector3i r) {
		return x * r.getX() + y * r.getY() + z * r.getZ();
	}

	public Vector3i normalize() {
		int length = length();
		x /= length;
		y /= length;
		z /= length;
		return this;
	}

	public Vector3i cross(Vector3i r) {
		int x1 = y * r.getZ() - z * r.getY();
		int y1 = z * r.getX() - x * r.getZ();
		int z1 = x * r.getY() - y * r.getX();
		return new Vector3i(x1, y1, z1);
	}

	public Vector3i normalized() {
		int length = length();

		return new Vector3i(x / length, y / length, z / length);
	}

	public Vector3i rotate(Vector3i axis, int angle) {
		int sinAngle = (int) Math.sin(-angle);
		int cosAngle = (int) Math.cos(-angle);

		return this.cross(axis.mul(sinAngle)).add( // Rotation on local X
				(this.mul(cosAngle)).add( // Rotation on local Z
						axis.mul(this.dot(axis.mul(1 - cosAngle))))); // Rotation on local Y
	}

	public Vector3i rotate(Quaternion rotation) {
		Quaternion conjugate = rotation.conjugate();

		Quaternion w = rotation.mul(this).mul(conjugate);

		return new Vector3i((int) w.getX(), (int) w.getY(), (int) w.getZ());
	}

	public Vector3i lerp(Vector3i dest, int lerpFactor) {
		return dest.sub(this).mul(lerpFactor).add(this);
	}

	public Vector3i add(Vector3i r) {
		return new Vector3i(x + r.getX(), y + r.getY(), z + r.getZ());
	}

	public Vector3i add(int r) {
		return new Vector3i(x + r, y + r, z + r);
	}

	public Vector3i sub(Vector3i r) {
		return new Vector3i(x - r.getX(), y - r.getY(), z - r.getZ());
	}

	public Vector3i sub(int r) {
		return new Vector3i(x - r, y - r, z - r);
	}

	public Vector3i mul(Vector3i r) {
		return new Vector3i(x * r.getX(), y * r.getY(), z * r.getZ());
	}

	public Vector3i mul(int r) {
		return new Vector3i(x * r, y * r, z * r);
	}

	public Vector3i div(Vector3i r) {
		return new Vector3i(x / r.getX(), y / r.getY(), z / r.getZ());
	}

	public Vector3i div(int r) {
		return new Vector3i(x / r, y / r, z / r);
	}

	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
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
	 * @param z
	 *            the z to set
	 */
	public void setZ(int z) {
		this.z = z;
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
	 * @return the z
	 */
	public int getZ() {
		return z;
	}

}
