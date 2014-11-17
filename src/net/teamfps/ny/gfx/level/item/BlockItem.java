package net.teamfps.ny.gfx.level.item;

import net.teamfps.ny.gfx.Sprite;
import net.teamfps.ny.gfx.level.block.Block;

/**
 * @author Zekye
 *
 */
public class BlockItem extends Item {
	public Block block;

	public BlockItem(Block block) {
		super(block);
		this.block = block;
	}

	public BlockItem(String name, Sprite sprite) {
		super(name, sprite);
	}

	/**
	 * @return the block
	 */
	public Block getBlock() {
		return block;
	}
}
