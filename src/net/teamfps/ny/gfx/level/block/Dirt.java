package net.teamfps.ny.gfx.level.block;

import net.teamfps.ny.gfx.Screen;
import net.teamfps.ny.gfx.Sprite;
import net.teamfps.ny.gfx.level.Level;
import net.teamfps.ny.gfx.level.item.Spade;

/**
 * @author Zekye
 *
 */
public class Dirt extends Block {

	private Block above = null;
	private int timer = 0;
	private int r = 256;

	public Dirt(int x, int y) {
		super(x, y);
		this.sprite = Sprite.dirt;
		this.name = "Dirt";
		this.hardness = 64;
		this.breaking = 0;
	}

	@Override
	public Block initLVL(Level level) {
		above = level.AboveBlock(this);
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

	@Override
	public void update() {
		if (level != null) {
			if (above == null) {
				timer++;
				if (timer >= r) {
					timer = 0;
					level.ReplaceBlock(this, new Grass(x, y));
				}
			}
			above = level.AboveBlock(this, -64, -64);
		}
	}

	@Override
	public void render(Screen screen) {
		super.render(screen);
	}
}
