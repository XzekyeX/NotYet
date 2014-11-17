package net.teamfps.ny.gfx.level.block;

import net.teamfps.ny.gfx.Screen;
import net.teamfps.ny.gfx.Sprite;
import net.teamfps.ny.util.Vector2i;

/**
 * @author Zekye
 *
 */
public class Water extends Block {
	public Water(int x, int y) {
		super(x, y);
		this.sprite = Sprite.water;
	}

	@Override
	public boolean solid() {
		return false;
	}
	
	@Override
	public boolean breakable() {
		return false;
	}

	@Override
	public void update() {
		if (level != null) {
			Vector2i[] blocks = getAround();
			for (int i = 0; i < blocks.length; i++) {
				if (blocks[i] == null) {
					switch (i) {
					case 0:
						level.CopyBlock(this, new Water(x, y + 64).initLVL(level));
						break;
					case 2:
						level.CopyBlock(this, new Water(x - 64, y).initLVL(level));
						break;
					case 6:
						level.CopyBlock(this, new Water(x + 64, y).initLVL(level));
						break;
					}
				}
			}
		}
	}

	@Override
	public void render(Screen screen) {
		super.render(screen);
	}

	@Override
	public void renderAroundBlocks(Screen screen) {
		super.renderAroundBlocks(screen);
	}
}
