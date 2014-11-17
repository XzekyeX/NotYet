package net.teamfps.ny.gfx.level.block;

import net.teamfps.ny.gfx.Screen;
import net.teamfps.ny.gfx.Sprite;
import net.teamfps.ny.gfx.level.item.Spade;

/**
 * @author Zekye
 *
 */
public class Snow_Grass extends Snow_Dirt {

	public Snow_Grass(int x, int y) {
		super(x, y);
		this.sprite = Sprite.snow_grass;
		this.name = "Snow Grass";
		this.hardness = 64;
		this.breaking = 0;
	}

	@Override
	public boolean solid() {
		return true;
	}
	
	@Override
	public boolean breakable() {
		if (tool instanceof Spade) {
			hardness = 64;
		} else {
			hardness = 96;
		}
		return true;
	}

	@Override
	public void render(Screen screen) {
		super.render(screen);
	}
}
