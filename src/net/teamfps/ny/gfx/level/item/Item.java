package net.teamfps.ny.gfx.level.item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.teamfps.ny.gfx.Sprite;
import net.teamfps.ny.gfx.level.block.Block;

/**
 * @author Zekye
 *
 */
public class Item {
	public String name;
	public Sprite sprite;

	public static HashMap<Integer, Item> items = new HashMap<Integer, Item>();
	// Picks
	public static Tool wooden_pickaxe = new Pick("Wooden Pick", "/items/pickaxewood.png", 0.5D, 0.8D, 64);
	public static Tool stone_pickaxe = new Pick("Stone Pick", "/items/pickaxestone.png", 1.5D, 1.8D, 128);
	public static Tool iron_pickaxe = new Pick("Iron Pick", "/items/pickaxeiron.png", 1.9D, 2.2D, 256);
	public static Tool golden_pickaxe = new Pick("Golden Pick", "/items/pickaxegold.png", 2.8D, 3.4D, 320);
	public static Tool diamond_pickaxe = new Pick("Diamond Pick", "/items/pickaxediamond.png", 1.2D, 3.0D, 1024);
	// Spades
	public static Tool wooden_spade = new Spade("Wooden Spade", "/items/shovelwood.png", 0.5D, 0.8D, 64);
	public static Tool stone_spade = new Spade("Stone Spade", "/items/shovelstone.png", 1.5D, 1.8D, 128);
	public static Tool iron_spade = new Spade("Iron Spade", "/items/shoveliron.png", 1.9D, 2.2D, 256);
	public static Tool golden_spade = new Spade("Golden Spade", "/items/shovelgold.png", 2.8D, 3.4D, 320);
	public static Tool diamond_spade = new Spade("Diamond Spade", "/items/shoveldiamond.png", 1.2D, 3.0D, 1024);
	// Hatchets
	public static Tool wooden_hatchet = new Hatchet("Wooden Hatchet", "/items/hatchetwood.png", 0.5D, 0.8D, 64);
	public static Tool stone_hatchet = new Hatchet("Stone Hatchet", "/items/hatchetstone.png", 1.5D, 1.8D, 128);
	public static Tool iron_hatchet = new Hatchet("Iron Hatchet", "/items/hatchetiron.png", 1.9D, 2.2D, 256);
	public static Tool golden_hatchet = new Hatchet("Golden Hatchet", "/items/hatchetgold.png", 2.8D, 3.4D, 320);
	public static Tool diamond_hatchet = new Hatchet("Diamond Hatchet", "/items/hatchetdiamond.png", 1.2D, 3.0D, 1024);

	static {
		// WOODEN
		add(0, wooden_pickaxe);
		add(1, wooden_spade);
		add(2, wooden_hatchet);
		// STONE
		add(3, stone_pickaxe);
		add(4, stone_spade);
		add(5, stone_hatchet);
		// IRON
		add(6, iron_pickaxe);
		add(7, iron_spade);
		add(8, iron_hatchet);
		// GOLDEN
		add(9, golden_pickaxe);
		add(10, golden_spade);
		add(11, golden_hatchet);
		// DIAMOND
		add(12, diamond_pickaxe);
		add(13, diamond_spade);
		add(14, diamond_hatchet);
	}

	public static void add(int id, Item item) {
		items.put(id, item);
	}

	public static Item getItem(int id) {
		return items.get(id);
	}

	public static Item getItem(String name) {
		Iterator<Entry<Integer, Item>> it = items.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Item> me = (Map.Entry<Integer, Item>) it.next();
			int id = me.getKey();
			String n = items.get(id).getName();
			if (n.toUpperCase().contains(name.toUpperCase())) {
				return getItem(id);
			}
		}
		return null;
	}

	/**
	 * @return the items
	 */
	public static HashMap<Integer, Item> getItems() {
		return items;
	}

	public static int Size() {
		return items.size();
	}

	/**
	 * 
	 */
	public Item(Block block) {
		this.name = block.name;
		this.sprite = block.sprite;
	}

	public Item(String name, Sprite sprite) {
		this.name = name;
		this.sprite = sprite;
	}

	public Item(String name, String path) {
		this.name = name;
		this.sprite = new Sprite(path);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}
}
