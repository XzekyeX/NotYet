package net.teamfps.ny.gfx.level;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.teamfps.ny.Input;
import net.teamfps.ny.gfx.Screen;
import net.teamfps.ny.gfx.level.block.Block;
import net.teamfps.ny.gfx.level.block.Dirt;
import net.teamfps.ny.gfx.level.block.Sand;
import net.teamfps.ny.gfx.level.block.Snow_Dirt;
import net.teamfps.ny.gfx.level.block.Stone;
import net.teamfps.ny.gfx.level.block.Water;
import net.teamfps.ny.gfx.level.entity.Drop;
import net.teamfps.ny.gfx.level.entity.Entity;
import net.teamfps.ny.gfx.level.entity.Player;
import net.teamfps.ny.gfx.level.item.BlockItem;
import net.teamfps.ny.gfx.level.item.Item;
import net.teamfps.ny.gfx.level.item.Tool;
import net.teamfps.ny.util.Vector2i;

/**
 * @author Zekye
 *
 */
public class Level {
	public Screen screen;
	public int width;
	public int height;

	private Block[][] blocks;
	// private Wall[][] walls;

	private Random rand = new Random();

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Player> players = new ArrayList<Player>();
	private List<Drop> drops = new ArrayList<Drop>();

	public Level(Screen screen, int width, int height) {
		this.screen = screen;
		this.width = width;
		this.height = height;
	}

	public void generate() {
		blocks = new Block[width][height];
		// walls = new Wall[width][height];
		Biome[] biomes = { Biome.FOREST, Biome.SNOW, Biome.DESERT };
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				for (int j = 0; j < biomes.length; j++) {
					int bl = rand.nextInt(512) + 8;
					biomes[j].Generate(this, x, y, j * bl, bl, 32, 0);
				}

			}
		}
	}

	enum Biome {
		FOREST {
			@Override
			public void Generate(Level level, int x, int y, int sx, int lx, int sy, int ly) {
				if (x >= sx && x <= sx + lx && y >= sy) {
					// level.setWall(x, y, new Wall_Dirt(x * 64, y * 64));
					level.setBlock(x, y, new Dirt(x * 64, y * 64).initLVL(level));
					int r = level.rand.nextInt(4) + sy + 4;
					if (x >= sx + 4 && x <= sx + 8 && y >= sy && y <= sy) {
						level.setBlock(x, y, new Water(x * 64, y * 64).initLVL(level));
					}
					if (y >= r) {
						level.setBlock(x, y, new Stone(x * 64, y * 64));
					}
				}
			}
		},
		SNOW {
			@Override
			public void Generate(Level level, int x, int y, int sx, int lx, int sy, int ly) {
				if (x >= sx && x <= sx + lx && y >= sy) {
					level.setBlock(x, y, new Snow_Dirt(x * 64, y * 64).initLVL(level));
					int r = level.rand.nextInt(4) + sy + 4;
					if (y >= r) {
						level.setBlock(x, y, new Stone(x * 64, y * 64));
					}
				}
			}
		},
		DESERT {
			@Override
			public void Generate(Level level, int x, int y, int sx, int lx, int sy, int ly) {
				if (x >= sx && x <= sx + lx && y >= sy) {
					level.setBlock(x, y, new Sand(x * 64, y * 64).initLVL(level));
					int r = level.rand.nextInt(4) + sy + 4;
					if (y >= r) {
						level.setBlock(x, y, new Stone(x * 64, y * 64));
					}
				}
			}
		};
		public abstract void Generate(Level level, int x, int y, int sx, int lx, int sy, int ly);
	}

	private int delay = 20;
	private int selectedX = 0;
	private int selectedY = 0;

	public void update(int xOffs, int yOffs) {
		int px = xOffs / 64;
		int py = yOffs / 64;
		int rx = (screen.width / 64);
		int ry = (screen.height / 64);
		if (delay > 0) delay--;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (py <= y && py + ry >= y - 1 && px <= x + 8 && px + rx >= x - 8) {
					if (blocks[x][y] != null) {
						blocks[x][y].update();
						if (MouseDragCollision(x * 64, y * 64, 64, 64)) {
							if (Input.ML) {
								selectedX = x;
								selectedY = y;
							}
						}
					}
				}
			}
		}
		if (MouseDragCollision(selectedX * 64, selectedY * 64, 64, 64)) {
			if (Input.ML) {
				if (blocks[selectedX][selectedY] != null) {
					blocks[selectedX][selectedY].setTool(getClientPlayer().getItem());
					if (blocks[selectedX][selectedY].breakable()) {
						double hardness = blocks[selectedX][selectedY].hardness;
						double breaking = blocks[selectedX][selectedY].breaking;
						Item item = blocks[selectedX][selectedY].getTool();
						if (item != null) {
							if (item instanceof Tool) {
								Tool tool = (Tool) item;
								blocks[selectedX][selectedY].breaking += 16;
								if (breaking >= hardness) {
									DropItem(blocks[selectedX][selectedY]);
									blocks[selectedX][selectedY] = null;
									tool.durability -= 1;
								}
							} else {
								blocks[selectedX][selectedY].breaking += 0.5D;
								if (breaking >= hardness) {
									DropItem(blocks[selectedX][selectedY]);
									blocks[selectedX][selectedY] = null;
								}
							}
						} else {
							blocks[selectedX][selectedY].breaking += 0.5D;
							if (breaking >= hardness) {
								DropItem(blocks[selectedX][selectedY]);
								blocks[selectedX][selectedY] = null;
							}
						}
					} else {
						System.out.println("Inventory is full!");
					}
				}
			}
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
			if (entities.get(i).isRemoved()) {
				entities.remove(i);
			}
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).update();
			if (players.get(i).isRemoved()) {
				players.remove(i);
			}
		}
		for (int i = 0; i < drops.size(); i++) {
			drops.get(i).update();
			if (drops.get(i).isRemoved()) {
				drops.remove(i);
			}
		}
		if (Input.equalsKey(KeyEvent.VK_G) && delay == 0) {
			delay = 20;
			showAround = !showAround;
		}
		if (getClientPlayer() != null) {
			getClientPlayer().updateToolBar();
			getClientPlayer().updateInventory();
		}
	}

	private void DropItem(Block block) {
		// getClientPlayer().getInventory().add(new BlockItem(block));
		BlockItem bi = new BlockItem(block);
		System.out.println("BlockItem.getSprite: " + bi.getSprite());
		add(new Drop(block.x, block.y, bi));
	}

	private boolean showAround = false;

	public void render(int xOffs, int yOffs) {
		screen.setOffset(xOffs, yOffs);
		int px = xOffs / 64;
		int py = yOffs / 64;
		int rx = (screen.width / 64);
		int ry = (screen.height / 64);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (py <= y && py + ry >= y - 1 && px <= x && px + rx >= x - 1) {
					// if (walls[x][y] != null) {
					// walls[x][y].render(screen);
					// }
					if (blocks[x][y] != null) {
						blocks[x][y].render(screen);
					}
				}
			}
		}
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (py <= y && py + ry >= y - 1 && px <= x && px + rx >= x - 1) {
					if (blocks[x][y] != null) {
						if (blocks[x][y].MouseCollision(screen) && showAround) {
							blocks[x][y].renderAroundBlocks(screen);
						}
					}
					if (MouseDragCollision(x * 64, y * 64, 64, 64)) {
						screen.renderRect(x * 64, y * 64, 64, 64, 2, 0xffffff, true);
					}
				}
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}
		for (int i = 0; i < drops.size(); i++) {
			drops.get(i).render(screen);
		}
		if (getClientPlayer() != null) {
			getClientPlayer().renderToolBar(screen);
			getClientPlayer().renderInventory(screen);
		}
	}

	private boolean MouseDragCollision(int x, int y, int w, int h) {
		boolean solid = false;
		if (Input.DX + screen.xOffs >= x && Input.DX + screen.xOffs <= x + w && Input.DY + screen.yOffs >= y && Input.DY + screen.yOffs <= y + h) {
			solid = true;
		}
		return solid;
	}

	public void add(Entity e) {
		e.init(this);
		if (e instanceof Player) {
			players.add((Player) e);
		} else if (e instanceof Drop) {
			drops.add((Drop) e);
		} else {
			entities.add(e);
		}
	}

	public Player getClientPlayer() {
		if (players.size() > 0) {
			return players.get(0);
		}
		return null;
	}

	public boolean Collision(Entity e, int xa, int ya) {
		boolean solid = false;
		int x = e.getX();
		int y = e.getY();
		int w = e.getW();
		int h = e.getH();
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * w + 64) / 64;
			int yt = ((y + ya + 32) + c / 2 * h + 32) / 64;
			Block b = getBlock(xt - 1, yt - 1);
			if (b != null) {
				if (b.solid()) {
					solid = true;
				}
			}
		}
		return solid;
	}

	public Block UnderBlock(Entity e) {
		int x = e.getX();
		int y = e.getY();
		int w = e.getW();
		int h = e.getH();
		int xa = 0;
		int ya = 0;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * w + 64) / 64;
			int yt = ((y + ya + 4) + c / 2 * h + 64) / 64;
			Block b = getBlock(xt - 1, yt - 1);
			if (b != null) {
				return b;
			}
		}
		return null;
	}

	public Block AboveBlock(Entity e) {
		int x = e.getX();
		int y = e.getY();
		int w = e.getW();
		int h = e.getH();
		int xa = 0;
		int ya = 0;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * w + 64) / 64;
			int yt = ((y + ya - 4) + c / 2 * h + 64) / 64;
			Block b = getBlock(xt - 1, yt - 1);
			if (b != null) {
				return b;
			}
		}
		return null;
	}

	public Block AboveBlock(Entity e, int xa, int ya) {
		int x = e.getX();
		int y = e.getY();
		int w = e.getW();
		int h = e.getH();
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * w + 64) / 64;
			int yt = ((y + ya - 4) + c / 2 * h + 64) / 64;
			Block b = getBlock(xt - 1, yt - 1);
			if (b != null) {
				return b;
			}
		}
		return null;
	}

	public void ReplaceBlock(Block old, Block replacement) {
		int x = old.getX() / old.getW();
		int y = old.getY() / old.getH();
		Block b = getBlock(x, y);
		if (b != null) {
			if (b == old) {
				blocks[x][y] = replacement;
			}
		}
	}

	/**
	 * @return the blocks
	 */
	public Block getBlock(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return null;
		return blocks[x][y];
	}

	public Vector2i getBlockPos(int x, int y) {
		Vector2i pos = new Vector2i(x * 64, y * 64);
		Block b = getBlock(x, y);
		if (b != null) {
			pos = new Vector2i(b.getX(), b.getY());
		}
		return pos;
	}

	public void setBlockPos(Block block, Vector2i pos) {
		int x = block.getX() / block.getW();
		int y = block.getY() / block.getH();
		Block b = getBlock(x, y);
		if (b != null) {
			if (b == block) {
				blocks[x][y].setPos(pos);
			}
		}
	}

	public void setBlockDown(Block block) {
		int x = block.getX() / block.getW();
		int y = block.getY() / block.getH();
		Block b = getBlock(x, y);
		if (b != null) {
			if (b == block) {
				blocks[x][y] = null;
				blocks[x][y + 1] = block;
				// block.setPos(x * 64, (y + 1) * 64);
				System.out.println("setBlockDown: " + block);
			}
		}
	}

	public boolean isBlockDown(Block block) {
		int x = block.getX() / block.getW();
		int y = block.getY() / block.getH();
		Block b = getBlock(x, y);
		if (b != null) {
			if (b == block) {
				blocks[x][y] = null;
				blocks[x][y + 1] = block;
				System.out.println("setBlockDown: " + block);
				return true;
			}
		}
		return false;
	}

	public Vector2i getBlockDownPosInstanceof(Block block) {
		int x = block.getX() / block.getW();
		int y = block.getY() / block.getH();
		Block b = getBlock(x, y);
		if (b != null) {
			if (b == block) {
				blocks[x][y] = null;
				blocks[x][y + 1] = block;
				System.out.println("setBlockDown: " + block);
				return new Vector2i(x, y + 1);
			}
		}
		return new Vector2i(x, y);
	}

	public Vector2i getBlockDownPos(Block block) {
		int x = block.getX() / block.getW();
		int y = block.getY() / block.getH();
		Block b = getBlock(x, y);
		if (b != null) {
			if (b == block) {
				blocks[x][y] = null;
				blocks[x][y + 1] = block;
				System.out.println("setBlockDown: " + block);
				return new Vector2i(x, y + 1);
			}
		}
		return new Vector2i(x, y);
	}

	public Vector2i CopyBlock(Block block, Block copy) {
		int x = block.getX() / block.getW();
		int y = block.getY() / block.getH();
		int cx = copy.getX() / copy.getW();
		int cy = copy.getY() / copy.getH();
		Block b = getBlock(x, y);
		if (b != null) {
			if (b == block) {
				// setBlock(x, y, block);
				setBlock(cx, cy, copy);
				return new Vector2i(cx, cy);
			}
		}
		return new Vector2i(x, y);
	}
	
	public List<Player> getPlayers(Entity e, int radius) {
		List<Player> result = new ArrayList<Player>();
		int ex = e.getX();
		int ey = e.getY();
		int block = 5;
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int x = player.getX();
			int y = player.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= (radius << block)) {
				result.add(player);
			}
		}
		return result;
	}


	/**
	 * @param block
	 *            [x][y] the block to set
	 */
	public void setBlock(int x, int y, Block b) {
		if (!(x < 0 || y < 0 || x >= width || y >= height)) this.blocks[x][y] = b;
	}
	//
	// /**
	// *
	// * @param x
	// * @param y
	// * @param wall
	// */
	// public void setWall(int x, int y, Wall w) {
	// this.walls[x][y] = w;
	// }

}
