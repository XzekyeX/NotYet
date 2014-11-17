package net.teamfps.ny.gfx.level.item;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Zekye
 *
 */
public class Tool extends Item {
	public double weight;
	public double efficiency;
	public int durability;
	public int maximum_durability;

	public static Tool getTool(int id) {
		Item item = items.get(id);
		if (item instanceof Tool) {
			Tool tool = (Tool) item;
			return tool;
		}
		return null;
	}

	public static Tool getTool(String name) {
		Iterator<Entry<Integer, Item>> it = items.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Item> me = (Map.Entry<Integer, Item>) it.next();
			int id = me.getKey();
			String n = items.get(id).getName();
			if (n.toUpperCase().contains(name.toUpperCase())) {
				return getTool(id);
			}
		}
		return null;
	}

	public Tool(String name, String path, double weight, double efficiency, int durability, int maximum_durability) {
		super(name, path);
		this.weight = weight;
		this.efficiency = efficiency;
		this.durability = durability;
		this.maximum_durability = maximum_durability;
	}

	public Tool(String name, String path, double weight, double efficiency, int maximum_durability) {
		this(name, path, weight, efficiency, maximum_durability, maximum_durability);
	}
}
