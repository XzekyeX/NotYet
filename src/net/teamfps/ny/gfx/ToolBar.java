package net.teamfps.ny.gfx;

import java.awt.event.KeyEvent;

import net.teamfps.ny.Input;
import net.teamfps.ny.gfx.level.entity.Player;
import net.teamfps.ny.gfx.level.item.Item;
import net.teamfps.ny.gfx.level.item.Tool;

/**
 * @author Zekye
 *
 */
public class ToolBar {
	public Slot[] slots;
	public Slot selected;
	private int sx = 20;
	private int sy = 32;
	private Player player;

	public ToolBar() {
		slots = new Slot[5];
		for (int i = 0; i < slots.length; i++) {
			int j = i + 1;
			slots[i] = new Slot("[" + j + "]", sx, sy + i * 38);
		}
		selected = slots[0];
	}

	private int delay = 20;

	public void update() {
		if (delay > 0) delay--;
		int[] keys = new int[] { KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5 };
		for (int i = 0; i < slots.length; i++) {
			if (slots[i] != null) {
				slots[i].update();
				Item item = slots[i].getItem();
				if (item != null) {
					if (item instanceof Tool) {
						Tool tool = (Tool) item;
						if (tool != null) {
							int dur = tool.durability;
							if (dur <= 0) {
								slots[i].RemoveLast();
							}
						}
					}
				}
				if ((slots[i].ClickedLeft() || Input.equalsKey(keys[i])) && delay == 0) {
					delay = 20;
					selected = slots[i];
					if (player != null) {
						if (selected.getItems().isEmpty() && player.getInventory().getDragItem() != null) {
							selected.addItem(player.getInventory().getDragItem());
							player.getInventory().setDragItem(null);
						}
					}
				}
				if (slots[i].ClickedRight() && delay == 0) {
					delay = 20;
					selected = slots[i];
					if (player != null) {
						Item it = selected.getItem();
						if (it != null) {
							player.getInventory().add(it);
							selected.RemoveLast();
						}
					}
				}
			}
		}
		if (selected != null) {
			sy = selected.y + 16;
		}
	}

	public void render(Screen screen) {
		for (int i = 0; i < slots.length; i++) {
			if (slots[i] != null) {
				slots[i].render(screen);
			}
		}
		screen.renderString(">", 20, sx - 18, sy + 8, 0xff0000);
		if (selected != null) {
			Item item = selected.getItem();
			if (item != null) {
				String name = item.getName();
				if (item instanceof Tool) {
					Tool tool = (Tool) item;
					int dur = tool.durability;
					int max = tool.maximum_durability;
					screen.renderString("< " + name + " " + dur + "/" + max, 16, sx + 40, sy + 4, 0xffff00);
				} else {
					screen.renderString("< " + name, 16, sx + 40, sy + 4, 0xffff00);
				}
			}
		}
	}

	public void setPos(int x, int y) {
		for (int i = 0; i < slots.length; i++) {
			slots[i].setPos(x, y + i * 38);
		}
	}

	/**
	 * @return the selected
	 */
	public Slot getSelected() {
		return selected;
	}

	public Slot getEmptySlot() {
		for (int i = 0; i < slots.length; i++) {
			if (slots[i].getItems().isEmpty()) {
				return slots[i];
			}
		}
		return selected;
	}

	/**
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the slots
	 */
	public Slot[] getSlots() {
		return slots;
	}

}
