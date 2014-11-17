package net.teamfps.ny.menu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import net.teamfps.ny.gfx.Screen;
import net.teamfps.ny.gfx.level.Level;
import net.teamfps.ny.gfx.level.entity.Player;
import net.teamfps.ny.gfx.level.item.Item;

/**
 * @author Zekye
 *
 */
public class PlayMenu extends Menu {
	private Level level;
	private Player player;

	public PlayMenu(Screen screen) {
		super(screen);
	}

	private List<String> progress = new ArrayList<String>();
	private Task task;

	public void init() {
		task = new Task();
		task.execute();
	}

	class Task extends SwingWorker<Void, Void> {
		@Override
		protected Void doInBackground() throws Exception {
			progress.add("Loading ToolBar");
			progress.add("Loading Level");
			level = new Level(screen, 1024, 512);
			progress.add("Generating Level");
			level.generate();
			int py = 64 * 30;
			progress.add("Loading Player");
			player = new Player(32, py);
			progress.add("Adding Player");
			level.add(player);
			progress.add("Loading Images");
			System.out.println("Item.items.size: " + Item.items.size());
			return null;
		}

		@Override
		protected void done() {
			screen.setMenu(screen.getPlayMenu());
		}
	}

	/**
	 * @return the progress
	 */
	public List<String> getProgress() {
		return progress;
	}

	@Override
	public void update() {
		if (level != null) {
			int xOffs = player.x - (screen.width / 2);
			int yOffs = player.y - (screen.height / 2);
			level.update(xOffs, yOffs);
		}
	}

	@Override
	public void render() {
		if (level != null) {
			int xOffs = player.x - (screen.width / 2);
			int yOffs = player.y - (screen.height / 2);
			level.render(xOffs, yOffs);
		}
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
}
