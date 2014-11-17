package net.teamfps.ny.gfx;

import net.teamfps.ny.Input;
import net.teamfps.ny.gfx.level.entity.Player;
import net.teamfps.ny.gfx.level.item.Item;

/**
 * @author Zekye
 *
 */
public class Inventory {
	public int width;
	public int height;
	public Slot[][] slots;
	public boolean open = false;
	private Player player;

	public Inventory(int width, int height) {
		this.width = width;
		this.height = height;
		slots = new Slot[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				slots[x][y] = new Slot();
			}
		}
	}

	private int delay = 20;
	private Item drag = null;

	public void update() {
		if (delay > 0) delay--;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (slots[x][y] != null) {
					slots[x][y].update();
					if (slots[x][y].ClickedLeft() && delay == 0) {
						delay = 20;
						Drag(slots[x][y]);
					}
					if (slots[x][y].ClickedRight() && delay == 0) {
						delay = 20;
						Slot slot = slots[x][y];
						Item item = slot.getItem();
						if (item != null) {
							player.getToolBar().getEmptySlot().addItem(item);
							slot.RemoveLast();
						}
					}
				}
			}
		}
	}

	/**
	 * @param slot
	 */
	private void Drag(Slot slot) {
		if (drag == null) {
			Item item = slot.getItem();
			if (item != null) {
				drag = item;
				removeSlotItem(slot);
			}
		} else {
			addItem(slot, drag);
			drag = null;
		}
	}

	public void render(Screen screen) {
		int m = 38;
		int xp = (screen.width - ((width * m) + 32));
		// int yp = (screen.height / 2 - ((height * m) / 2));
		int yp = 32;
		screen.renderFillRect(xp - 12, yp - 12, width * m + 16, height * m + 16, 3, 0x000000, 0xffffff);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (slots[x][y] != null) {
					slots[x][y].render(screen, xp + x * m, yp + y * m);
				}
			}
		}
		if (drag != null) {
			Sprite s = drag.getSprite();
			if (s != null) {
				screen.renderSprite(s, Input.DX - 16, Input.DY - 16, 32, 32);
			}
		}
	}

	public Item getDragItem() {
		return drag;
	}

	public void setDragItem(Item drag) {
		this.drag = drag;
	}

	public void add(Item item) {
		// Item item = Item.getItem(id);
		Slot empty = getEmptySlot();
		Slot s = getStackSlot(item);
		if (s == null) {
			if (empty != null && item != null) {
				addItem(empty, item);
			}
		} else {
			if (item != null) {
				addItem(s, item);
			}
		}
	}

	public void add(int id) {
		Item item = Item.getItem(id);
		Slot empty = getEmptySlot();
		Slot s = getStackSlot(item);
		if (s == null) {
			if (empty != null && item != null) {
				addItem(empty, item);
			}
		} else {
			if (item != null) {
				addItem(s, item);
			}
		}
	}

	public void add(String name) {
		Item item = Item.getItem(name);
		Slot empty = getEmptySlot();
		Slot s = getStackSlot(item);
		if (s == null) {
			if (empty != null && item != null) {
				addItem(empty, item);
			}
		} else {
			if (item != null) {
				addItem(s, item);
			}
		}
	}

	public void addItem(Slot s, Item item) {
		s.addItem(item);
	}

	public void removeSlotItem(Slot s) {
		int l = s.getItems().size() - 1;
		if (l > -1) {
			s.getItems().remove(l);
		}
	}

	/**
	 * @return the open
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * @param open
	 *            the open to set
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}

	public Slot getEmptySlot() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (slots[x][y].getItems().isEmpty()) {
					return slots[x][y];
				}
			}
		}
		return null;
	}

	public Slot getStackSlot(Item item) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Slot slot = slots[x][y];
				if (slot.getItem() == item && slots[x][y].getItems().size() < 64) {
					return slots[x][y];
				}
			}
		}
		return null;
	}

	public boolean isFull() {
		boolean[][] full = new boolean[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (!(slots[x][y].getItems().isEmpty())) {
					full[x][y] = true;
				}
			}
		}
		boolean f = false;
		if (full[0][0] && full[0][1] && full[0][2] && full[0][3] && full[0][4] && full[0][5] && full[1][0] && full[1][1] && full[1][2] && full[1][3] && full[1][4] && full[1][5] && full[2][0] && full[2][1] && full[2][2] && full[2][3] && full[2][4] && full[2][5] && full[3][0] && full[3][1] && full[3][2] && full[3][3] && full[3][4] && full[3][5]) {
			f = true;
		}
		return f;
	}

	/**
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

}
