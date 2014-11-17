package net.teamfps.ny.gfx.level.block;

import net.teamfps.ny.gfx.Screen;
import net.teamfps.ny.gfx.Sprite;
import net.teamfps.ny.gfx.level.item.Pick;

/**
 * @author Zekye
 *
 */
public class Stone extends Block {

	public Stone(int x, int y) {
		super(x, y);
		this.sprite = Sprite.stone;
		this.name = "Stone";
		this.hardness = 160;
		this.breaking = 0;
	}

	@Override
	public boolean solid() {
		return true;
	}

	@Override
	public boolean breakable() {
		if (tool instanceof Pick) {
			return true;
		}
		return false;
	}

	@Override
	public void render(Screen screen) {
		super.render(screen);
	}
}
