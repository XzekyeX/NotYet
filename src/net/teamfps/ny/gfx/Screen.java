package net.teamfps.ny.gfx;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import net.teamfps.ny.Input;
import net.teamfps.ny.menu.LoadMenu;
import net.teamfps.ny.menu.MainMenu;
import net.teamfps.ny.menu.Menu;
import net.teamfps.ny.menu.PlayMenu;

public class Screen extends Bitmap {
	private Console console;
	private Menu mainmenu;
	private Menu playmenu;
	private Menu loadmenu;
	private Menu menu;

	public Screen(int width, int height) {
		super(width, height);
		console = new Console(this);
		mainmenu = new MainMenu(this);
		playmenu = new PlayMenu(this);
		loadmenu = new LoadMenu(this);
		menu = mainmenu;
	}

	private int delay = 20;
	private boolean ConsoleVisibility = false;

	public void update() {
		if (console.isVisible()) {
			console.update();
		} else {
			if (menu != null) {
				menu.update();
			}
		}
		if (delay > 0) delay--;
		if (Input.equalsKey(KeyEvent.VK_INSERT) && delay == 0) {
			delay = 20;
			ConsoleVisibility = !ConsoleVisibility;
		}
		console.setVisible(ConsoleVisibility);
	}

	public void render(Graphics g) {
		initGFX(g);
		if (menu != null) {
			menu.render();
		}
		if (console.isVisible()) {
			console.render();
		}
	}

	public MainMenu getMainMenu() {
		return (MainMenu) mainmenu;
	}

	public PlayMenu getPlayMenu() {
		return (PlayMenu) playmenu;
	}

	public LoadMenu getLoadMenu() {
		return (LoadMenu) loadmenu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	/**
	 * @return the console
	 */
	public Console getConsole() {
		return console;
	}
}
