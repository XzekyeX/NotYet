package net.teamfps.ny.gfx.level.entity;

import java.util.Random;

import net.teamfps.ny.Input;
import net.teamfps.ny.gfx.Screen;
import net.teamfps.ny.gfx.level.Level;
import net.teamfps.ny.gfx.level.block.Block;
import net.teamfps.ny.gfx.Sprite;

/**
 * @author Zekye
 *
 */
public class Entity {
	public int x, y, w, h;
	public Sprite sprite;
	public String name;
	public double hp;
	public boolean removed = false;
	protected Level level;
	protected final Random rand = new Random();

	public void update() {

	}

	public void render(Screen screen) {

	}

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}

	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * w + 64) / 64;
			int yt = ((y + ya + 32) + c / 2 * h + 32) / 64;
			if (level != null) {
				Block b = level.getBlock(xt - 1, yt - 1);
				if (b != null) {
					if (b.solid()) {
						solid = true;
					}
				}
			}
		}
		return solid;
	}

	public boolean Collision(Entity e) {
		boolean solid = false;
		int ex = e.getX();
		int ey = e.getY();
		int ew = e.getW();
		int eh = e.getH();
		if (x + w >= ex && x <= ex + ew && y + ey >= ey && y <= ey + eh) {
			solid = true;
		}
		return solid;
	}

	public boolean MouseCollision(Screen screen) {
		boolean solid = false;
		if (Input.MX + screen.xOffs >= x && Input.MX + screen.xOffs <= x + w && Input.MY + screen.yOffs >= y && Input.MY + screen.yOffs <= y + h) {
			solid = true;
		}
		return solid;
	}

	public boolean MouseDragCollision(Screen screen) {
		boolean solid = false;
		if (Input.DX + screen.xOffs >= x && Input.DX + screen.xOffs <= x + w && Input.DY + screen.yOffs >= y && Input.DY + screen.yOffs <= y + h) {
			solid = true;
		}
		return solid;
	}

	public void init(Level level) {
		this.level = level;
	}

	public void remove() {
		removed = true;
	}

	/**
	 * @return is removed
	 */
	public boolean isRemoved() {
		return removed;
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
