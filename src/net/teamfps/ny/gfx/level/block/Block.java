package net.teamfps.ny.gfx.level.block;

import net.teamfps.ny.gfx.Screen;
import net.teamfps.ny.gfx.level.Level;
import net.teamfps.ny.gfx.level.entity.Entity;
import net.teamfps.ny.gfx.level.item.Item;
import net.teamfps.ny.util.Vector2i;

/**
 * @author Zekye
 *
 */
public class Block extends Entity {
	public double hardness;
	public double breaking;
	public Item tool;

	public Block(int x, int y) {
		this.x = x;
		this.y = y;
		this.w = 64;
		this.h = 64;
	}

	public Block(Block block) {
		this.x = block.x;
		this.y = block.y;
		this.w = 64;
		this.h = 64;
	}

	public Block initLVL(Level level) {
		this.level = level;
		return this;
	}

	public boolean solid() {
		return false;
	}

	public boolean breakable() {
		return false;
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, x, y, w, h, true);
	}

	public Block setPos(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public void setPos(Vector2i pos) {
		if (pos != null) {
			this.x = pos.getX();
			this.y = pos.getY();
		}
	}

	public Vector2i getPos() {
		return new Vector2i(x, y);
	}

	public Vector2i[] getAround() {
		Vector2i[] vec = new Vector2i[8];
		if (level != null) {
			int bx = x / 64;
			int by = y / 64;
			Block n = level.getBlock(bx, by + 1);
			if (n != null) {
				vec[0] = new Vector2i(n.getPos());
			}
			Block nw = level.getBlock(bx - 1, by + 1);
			if (nw != null) {
				vec[1] = new Vector2i(nw.getPos());
			}
			Block w = level.getBlock(bx - 1, by);
			if (w != null) {
				vec[2] = new Vector2i(w.getPos());
			}
			Block sw = level.getBlock(bx - 1, by - 1);
			if (sw != null) {
				vec[3] = new Vector2i(sw.getPos());
			}
			Block s = level.getBlock(bx, by - 1);
			if (s != null) {
				vec[4] = new Vector2i(s.getPos());
			}
			Block se = level.getBlock(bx + 1, by - 1);
			if (se != null) {
				vec[5] = new Vector2i(se.getPos());
			}
			Block e = level.getBlock(bx + 1, by);
			if (e != null) {
				vec[6] = new Vector2i(e.getPos());
			}
			Block ne = level.getBlock(bx + 1, by + 1);
			if (ne != null) {
				vec[7] = new Vector2i(ne.getPos());
			}
		}
		return vec;
	}

	private String[] compass = { "N", "NW", "W", "SW", "S", "SE", "E", "NE" };

	public void renderAroundBlocks(Screen screen) {
		Vector2i[] blocks = getAround();
		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i] != null) {
				int bx = blocks[i].getX();
				int by = blocks[i].getY();
				screen.renderRect(bx, by, 64, 64, 4, 0xffffff, true);
				screen.renderString("[" + i + "]:" + compass[i], 12, bx + 16, by + 32, 0x000000, true);
			}
		}
	}

	/**
	 * @return the hardness
	 */
	public double getHardness() {
		return hardness;
	}

	/**
	 * @param tool
	 *            the tool to set
	 */
	public void setTool(Item tool) {
		this.tool = tool;
	}

	/**
	 * @return the tool
	 */
	public Item getTool() {
		return tool;
	}
}
