package net.teamfps.ny.gfx.level.wall;

import net.teamfps.ny.gfx.Screen;
import net.teamfps.ny.gfx.Sprite;

/**
 * @author Zekye
 *
 */
public class Wall_Dirt extends Wall {

	public Wall_Dirt(int x, int y) {
		super(x, y);
		this.sprite = Sprite.wall_dirt;
	}

	public void render(Screen screen) {
		super.render(screen);	
	}
	
}
