package net.teamfps.ny.gfx;

import java.util.ArrayList;
import java.util.List;

import net.teamfps.ny.Input;
import net.teamfps.ny.gfx.level.item.Item;

/**
 * @author Zekye
 *
 */
public class Slot {
	public int x, y, w, h;
	public String name = "";
	private int color = 0xffffff;
	private List<Item> items = new ArrayList<Item>();

	public Slot(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.w = 32;
		this.h = 32;
	}

	public Slot(Item item, int x, int y) {
		this.items.add(item);
		this.x = x;
		this.y = y;
		this.w = 32;
		this.h = 32;
		this.name = item.getName();
	}

	public Slot(Item item) {
		this.items.add(item);
		this.w = 32;
		this.h = 32;
		// this.name = "" + item.getName();
	}

	public Slot(int x, int y) {
		this.x = x;
		this.y = y;
		this.w = 32;
		this.h = 32;
	}

	public Slot() {
		this.w = 32;
		this.h = 32;
	}

	public void update() {
		if (MouseOver()) {
			color = 0x0000ff;
		} else {
			color = 0xffffff;
		}
	}

	public void render(Screen screen) {
		screen.renderFillRect(x, y, w, h, 2, 0x000000, color);
		if (items != null && Size() > 0) {
			String str = "" + Size();
			screen.renderString(str, 8, x + (w - 13) - str.length(), y + (h - 3), 0xffffff);
			Item item = getItem();
			if (item != null) {
				Sprite s = item.getSprite();
				if (s != null) {
					screen.renderSprite(s, x, y, w, h);
				}
			}
		}
	}

	public void render(Screen screen, int x, int y) {
		this.x = x;
		this.y = y;
		screen.renderFillRect(x, y, w, h, 2, 0x000000, color);
		// screen.renderString(name, 8, x + (w - 13), y + (h - 3), color);
		if (items != null && Size() > 0) {
			String str = "" + Size();
			screen.renderString(str, 8, x + (w - 13) - str.length(), y + (h - 3), 0xffffff);
			Item item = getItem();
			if (item != null) {
				Sprite s = item.getSprite();
				if (s != null) {
					screen.renderSprite(s, x, y, w, h);
				}
			}
		}
	}

	public void addItem(Item item) {
		if (items != null) {
			if (Size() > 0) {
				Item first = items.get(0);
				if (first != null && item != null) {
					Sprite fs = first.getSprite();
					Sprite is = item.getSprite();
					if (fs == is) {
						items.add(item);
					}
				}
			} else {
				items.add(item);
			}
		}
	}

	public Item getItem() {
		if (items != null && Size() > 0) {
			return items.get(0);
		}
		return null;
	}

	public void RemoveLast() {
		if (items != null) {
			int size = Size() - 1;
			if (size >= -1) {
				items.remove(size);
			}
		}
	}

	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item getItem(int index) {
		return items.get(index);
	}

	public int Size() {
		if (items != null) {
			return items.size();
		}
		return -1;
	}

	/**
	 * @param x
	 * @param y
	 */
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(int color) {
		this.color = color;
	}

	public boolean MouseOver() {
		boolean over = false;
		if (Input.MX >= x && Input.MX <= x + w && Input.MY >= y && Input.MY <= y + h) {
			over = true;
		}
		return over;
	}

	public boolean Clicked() {
		boolean click = false;
		if (MouseOver() && Input.ML) {
			click = true;
		}
		return click;
	}

	public boolean ClickedLeft() {
		boolean click = false;
		if (MouseOver() && Input.ML) {
			click = true;
		}
		return click;
	}

	public boolean ClickedCenter() {
		boolean click = false;
		if (MouseOver() && Input.MC) {
			click = true;
		}
		return click;
	}

	public boolean ClickedRight() {
		boolean click = false;
		if (MouseOver() && Input.MR) {
			click = true;
		}
		return click;
	}
}
