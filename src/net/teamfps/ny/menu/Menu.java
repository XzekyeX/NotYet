package net.teamfps.ny.menu;

import net.teamfps.ny.gfx.Screen;

public abstract class Menu {
	public Screen screen;

	public Menu(Screen screen) {
		this.screen = screen;
	}

	public abstract void update();

	public abstract void render();
	
}
