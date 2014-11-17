package net.teamfps.ny.gfx.level.wall;

import net.teamfps.ny.gfx.Screen;
import net.teamfps.ny.gfx.level.Level;
import net.teamfps.ny.gfx.level.entity.Entity;

/**
 * @author Zekye
 *
 */
public class Wall extends Entity {
	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
		this.w = 64;
		this.h = 64;
	}
	
	public Wall initLVL(Level level) {
		this.level = level;
		return this;
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
}
