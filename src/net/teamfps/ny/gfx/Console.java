package net.teamfps.ny.gfx;

import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.teamfps.ny.Input;
import net.teamfps.ny.gfx.level.item.Item;
import net.teamfps.ny.gfx.level.item.Tool;

/**
 * @author Zekye
 *
 */
public class Console {
	public Screen screen;
	public boolean visible = false;
	private boolean focus = true;
	private StringBuilder text = new StringBuilder();
	private int delay = 20;
	private int x = 16;
	private int y = 0;
	private int w = 0;
	private int h = 32;
	private int color = 0xffffff;
	private String COMMAND_ALL_ITEMS = "List of All Items";
	private String COMMAND_GIVE = "Give";

	public Console(Screen screen) {
		this.screen = screen;
	}

	public void update() {
		// if (MouseOver()) {
		// if (Input.ML) {
		// color = 0xffff00;
		// focus = true;
		// }
		// } else {
		// if (Input.ML) {
		// focus = false;
		// }
		// color = 0xffffff;
		// }
		if (focus) {
			if (delay > 0) delay--;
			int[] key = { KeyEvent.VK_A, KeyEvent.VK_B, KeyEvent.VK_C, KeyEvent.VK_D, KeyEvent.VK_E, KeyEvent.VK_F, KeyEvent.VK_G, KeyEvent.VK_H, KeyEvent.VK_I, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_L, KeyEvent.VK_M, KeyEvent.VK_N, KeyEvent.VK_O, KeyEvent.VK_P, KeyEvent.VK_Q, KeyEvent.VK_R, KeyEvent.VK_S, KeyEvent.VK_T, KeyEvent.VK_U, KeyEvent.VK_V, KeyEvent.VK_W, KeyEvent.VK_X, KeyEvent.VK_Y, KeyEvent.VK_Z, KeyEvent.VK_0, KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9, KeyEvent.VK_SPACE, KeyEvent.VK_PERIOD };
			String[] keyName = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", " ", "." };
			for (int i = 0; i < key.length; i++) {
				if (Input.equalsKey(KeyEvent.VK_SHIFT)) {
					if (Input.equalsKey(KeyEvent.VK_PERIOD) && delay == 0) {
						delay = 20;
						text.append(":");
					}
					if (Input.equalsKey(key[i]) && delay == 0) {
						delay = 10;
						text.append(keyName[i].toUpperCase());
					}
				} else {
					if (Input.equalsKey(key[i]) && delay == 0) {
						delay = 10;
						text.append(keyName[i].toLowerCase());
					}
				}
			}

			if (Input.equalsKey(KeyEvent.VK_BACK_SPACE) && delay == 0) {
				delay = 10;
				int length = text.length() - 1;
				if (length > -1) {
					text.setLength(length);
				}
			}
			if (Input.equalsKey(KeyEvent.VK_F1) && delay == 0) {
				delay = 20;
				text.setLength(0);
				text.append(COMMAND_ALL_ITEMS);
			}

			if (Input.equalsKey(KeyEvent.VK_DELETE) && delay == 0) {
				delay = 20;
				text.setLength(0);
			}
			if (Input.equalsKey(KeyEvent.VK_ENTER) && delay == 0) {
				delay = 20;
				Command(getText());
			}
			flashTimer++;
			if (flashTimer >= 32) {
				flashTimer = 0;
				flash = "_";
			}
			if (flashTimer >= 16) {
				flash = "";
			}
		}
		if (slots != null) {
			for (int i = 0; i < slots.length; i++) {
				if (slots[i] != null) {
					slots[i].update();
					if (slots[i].Clicked() && delay == 0) {
						delay = 20;
						Item item = slots[i].getItem();
						if (item != null) {
							if (item instanceof Tool) {
								String name = item.getName();
								text.setLength(0);
								text.append(COMMAND_GIVE + ": " + name);
							} else {
								String name = item.getName();
								text.setLength(0);
								text.append(COMMAND_GIVE + ": " + name);
							}
						}
					}
				}
			}
			if (Input.SCROLL == 1) {
				Input.SCROLL = 0;
				sy -= 10;
			}
			if (Input.SCROLL == -1) {
				Input.SCROLL = 0;
				sy += 10;
			}
			int size = slots.length;
			int max = size * 42 + 32;
			if (-sy > max - sh) {
				sy = -max + sh;
			}
			if (sy > 32) {
				sy = 32;
			}
		}
	}

	private String flash = "";
	private int flashTimer = 0;

	private Slot[] slots;
	private int sy = 32;
	private int sh = 320;
	private int sx = 64;

	public void render() {
		y = screen.height - 48;
		w = screen.width - 32;
		screen.renderRect(x, y, w, h, 2, color);
		screen.renderString("" + text.toString() + flash, 16, x + 8, y + 24, 0xffffff);
		sh = w - 32;
		if (slots != null) {
			for (int i = 0; i < slots.length; i++) {
				if (slots[i] != null) {
					int y = sy + i * 42;
					if (!(y >= sh || y <= 28)) {
						// screen.width - sx, y
						slots[i].render(screen, y, screen.height - (sx + 32));
					}
				}
			}
		}
	}

	private void Command(String t) {
		if (t.startsWith(COMMAND_ALL_ITEMS)) {
			slots = new Slot[Item.Size()];
			Iterator<Entry<Integer, Item>> it = Item.getItems().entrySet().iterator();
			for (int i = 0; i < slots.length; i++) {
				Map.Entry<Integer, Item> me = (Map.Entry<Integer, Item>) it.next();
				int id = me.getKey();
				slots[i] = new Slot(Item.getItem(id));
				// System.out.println("[" + i + "]: " + id);
			}
			System.out.println("Command: " + COMMAND_ALL_ITEMS);
		}
		if (t.startsWith(COMMAND_GIVE)) {
			String[] split = t.split(":");
			if (split.length > 1) {
				Item item = Item.getItem(split[1].trim());
				if (item != null) {
					screen.getPlayMenu().getPlayer().getInventory().add(item);
					System.out.println("Item: " + item.getName());
				}
			}
			for (int i = 0; i < split.length; i++) {
				System.out.println("split[" + i + "]: " + split[i].trim());
			}
		}
		text.setLength(0);
	}

	public boolean MouseOver() {
		boolean over = false;
		if (Input.MX >= x && Input.MX <= x + w && Input.MY >= y && Input.MY <= y + h) {
			over = true;
		}
		return over;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text.toString();
	}

}
