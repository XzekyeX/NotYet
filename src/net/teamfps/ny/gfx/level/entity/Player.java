package net.teamfps.ny.gfx.level.entity;

import java.awt.event.KeyEvent;

import net.teamfps.ny.Input;
import net.teamfps.ny.gfx.Inventory;
import net.teamfps.ny.gfx.Screen;
import net.teamfps.ny.gfx.Sprite;
import net.teamfps.ny.gfx.ToolBar;
import net.teamfps.ny.gfx.level.block.Block;
import net.teamfps.ny.gfx.level.item.Item;

/**
 * @author Zekye
 *
 */
public class Player extends Entity {
	private double gravity = 2.2D;
	private int speed = 2;
	private int timer = 0;
	private int AnimTimer = 0;
	private int anim = 0;
	private int dir = 0;
	private int delay = 20;
	private boolean jumping = false;
	private boolean air = false;
	private boolean showInv = false;

	private Block under = null;
	private Block above = null;

	private ToolBar tb;
	private Inventory inv;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.w = 48;
		this.h = 60;
		this.name = "Zekye";
		this.hp = 20.0D;
		this.sprite = new Sprite(0x00ffff, w, h);
		tb = new ToolBar();
		tb.setPlayer(this);
		inv = new Inventory(4, 6);
		inv.setPlayer(this);
	}

	private int ix = 0;
	private int iy = 0;

	public void update() {
		int xa = 0;
		int ya = 0;
		under = level.UnderBlock(this);
		above = level.AboveBlock(this);

		if (Input.equalsKey(KeyEvent.VK_E) && delay == 0) {
			delay = 20;
			showInv = !showInv;
		}
		inv.setOpen(showInv);
		if (Input.equalsKey(KeyEvent.VK_A)) {
			xa -= speed;
			dir = 0;
			AnimTimer += speed;
		}
		if (Input.equalsKey(KeyEvent.VK_D)) {
			xa += speed;
			dir = 1;
			AnimTimer += speed;
		}
		if (Input.equalsKey(KeyEvent.VK_SPACE) && !air && !jumping) {
			jumping = true;
		}
		if (AnimTimer >= 32) {
			AnimTimer = 0;
			anim++;
		}
		if (anim == 2) {
			anim = 0;
		}

		if (anim == 1) {
			ix = 2;
			iy = -2;
		} else {
			ix = 0;
			iy = 0;
		}
		if (delay > 0) delay--;
		if (Input.equalsKey(KeyEvent.VK_F4) && delay == 0) {
			delay = 20;
			DEBUG = !DEBUG;
		}
		if (Input.equalsKey(KeyEvent.VK_F5) && delay == 0) {
			delay = 20;
			DEBUG1 = !DEBUG1;
		}
		if (jumping) {
			ya -= 8;
			timer++;
			if (timer >= 22) {
				timer = 0;
				jumping = false;
			}
		} else {
			timer = 0;
		}

		if (under == null) {
			air = true;
		} else {
			air = false;
		}
		gravity = 3.2D;
		ya += gravity;
		move(xa, ya);
	}

	private boolean DEBUG = false;
	private boolean DEBUG1 = false;

	public void updateToolBar() {
		tb.update();
	}

	public void renderToolBar(Screen screen) {
		tb.render(screen);
	}

	public void updateInventory() {
		if (inv.isOpen()) {
			inv.update();
		}
	}

	public void Drop(Item item) {
		if (level != null) {
			level.add(new Drop(x, y, item));
		}
	}

	public void renderInventory(Screen screen) {
		if (inv.isOpen()) {
			inv.render(screen);
		}
	}

	public void render(Screen screen) {
		// screen.renderSprite(sprite, x, y, w, h, true);
		switch (dir) {
		case 0:
			screen.renderSprite(Sprite.player_left[anim], x, y, w, h, true);
			if (getItem() != null) {
				screen.renderSprite(getItem().getSprite(), x - ix + 38, y + iy + 26, 32, 32, true, false, true);
			}
			break;
		case 1:
			screen.renderSprite(Sprite.player_right[anim], x, y, w, h, true);
			if (getItem() != null) {
				screen.renderSprite(getItem().getSprite(), x + ix + 10, y + iy + 26, 32, 32, false, false, true);
			}
			break;
		}

		if (DEBUG) {
			String HP = String.format("%.1f", hp);
			String str = "NAME[" + name + "], HP[" + HP + "]";
			screen.renderString(str, 12, x - ((str.length() * 6) / 2) + 16, y - 16, 0xffffff, true);
		}
		if (DEBUG1) {
			screen.renderString("isAir: " + air, 12, x, y - 16, 0xffffff, true);
			screen.renderString("isJump: " + jumping, 12, x, y - 32, 0xffffff, true);
			if (under != null) {
				int bx = under.getX();
				int by = under.getY();
				int bw = under.getW();
				int col = 0x696969;
				screen.renderFill(bx, by, bw, 4, col, true);
				screen.renderString("[" + bx / 64 + "]" + "[" + by / 64 + "]", 12, bx, by + 15, 0xffffff, true);
			}
			if (above != null) {
				int bx = above.getX();
				int by = above.getY();
				int bw = above.getW();
				int bh = above.getH();
				int col = 0x696969;
				screen.renderFill(bx, by + bh - 4, bw, 4, col, true);
			}
		}
	}

	public ToolBar getToolBar() {
		return tb;
	}

	public Inventory getInventory() {
		return inv;
	}

	public Item getItem() {
		return tb.getSelected().getItem();
	}

}
