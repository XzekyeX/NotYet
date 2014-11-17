package net.teamfps.ny.menu;

import java.util.List;

import net.teamfps.ny.gfx.Screen;

/**
 * @author Zekye
 *
 */
public class LoadMenu extends Menu {
	public LoadMenu(Screen screen) {
		super(screen);
	}

	public LoadMenu init() {
		screen.getPlayMenu().init();
		return this;
	}

	@Override
	public void update() {
		// progress = String.format("Loading... %d%%", task.getProgress());
	}

	@Override
	public void render() {
		// int cw = screen.width / 2;
		// int ch = screen.height / 2;
		List<String> progress = screen.getPlayMenu().getProgress();
		for (int i = 0; i < progress.size(); i++) {
			screen.renderString(progress.get(i), 22, 64, 64 + i * 22, 0xffff00);
		}
	}
}
