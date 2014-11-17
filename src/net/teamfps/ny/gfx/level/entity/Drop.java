package net.teamfps.ny.gfx.level.entity;

import java.util.List;

import net.teamfps.ny.gfx.Screen;
import net.teamfps.ny.gfx.level.item.Item;

/**
 * @author Zekye
 *
 */
public class Drop extends Entity {
	public Item item;
	private boolean removed = false;

	public Drop(int x, int y, Item item) {
		this.x = x;
		this.y = y;
		this.w = 32;
		this.h = 32;
		this.item = item;
		this.sprite = item.getSprite();
		this.name = item.getName();
	}

	public void update() {
		if (level != null) {
			List<Player> players = level.getPlayers(this, 6);
			if (players.size() > 0) {
				Player player = players.get(0);
				if (player != null) {
					int speed = 1;
					if (x < player.getX()) x += speed;
					if (x > player.getX()) x -= speed;
					if (y < player.getY()) y += speed;
					if (y > player.getY()) y -= speed;
					if (!player.getInventory().isFull()) {
						if (Collision(player)) {
							remove();
							player.getInventory().add(item);
							System.out.println("Added: " + item);
						}
					}
				}
			}
		}
	}

	public void render(Screen screen) {
		screen.renderSprite(sprite, x + (w / 2), y + (h / 2), w, h, true);
	}

	public void remove() {
		removed = true;
	}

	/**
	 * @return the removed
	 */
	public boolean isRemoved() {
		return removed;
	}
}
