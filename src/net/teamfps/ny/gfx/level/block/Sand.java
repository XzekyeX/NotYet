package net.teamfps.ny.gfx.level.block;

import net.teamfps.ny.gfx.Screen;
import net.teamfps.ny.gfx.Sprite;
import net.teamfps.ny.gfx.level.Level;
import net.teamfps.ny.gfx.level.item.Spade;
import net.teamfps.ny.util.Vector2i;

/**
 * @author Zekye
 *
 */
public class Sand extends Block {
	// private Block under = null;
	private boolean air = false;

	public Sand(int x, int y) {
		super(x, y);
		this.sprite = Sprite.sand;
		this.name = "Sand";
		this.hardness = 64;
		this.breaking = 0;
	}

	@Override
	public Block initLVL(Level level) {
		return super.initLVL(level);
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

	// private int timer = 0;
	private Vector2i vec = null;

	@Override
	public void update() {
		if (level != null) {
			Vector2i[] blocks = getAround();
			for (int i = 0; i < blocks.length; i++) {
				if (blocks[i] == null) {
					if (i == 0) {
						vec = level.getBlockDownPos(this);
						System.out.println("vec: " + vec);
					}
				}
			}
			if (vec != null) {
				int vy = vec.getY() * 64;
				if (y < vy) {
					air = true;
					y += 1;
				} else {
					air = false;
				}
			}
		}
	}

	public boolean isAir() {
		return air;
	}

	@Override
	public void render(Screen screen) {
		super.render(screen);
		if (isAir()) {
			screen.renderString("" + isAir(), 12, x, y + 32, 0x000000, true);
		}
	}

	@Override
	public void renderAroundBlocks(Screen screen) {
		super.renderAroundBlocks(screen);
	}
}
