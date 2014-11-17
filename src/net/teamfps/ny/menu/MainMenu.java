package net.teamfps.ny.menu;

import net.teamfps.ny.gfx.Button;
import net.teamfps.ny.gfx.Screen;
import net.teamfps.ny.util.Text;
import net.teamfps.ny.util.Vector4i;

/**
 * @author Zekye
 *
 */
public class MainMenu extends Menu {
	private Button play;

	public MainMenu(Screen screen) {
		super(screen);
		int cw = screen.width / 2;
		play = new Button(new Text("Play", 22, 0x00ffff), new Vector4i(cw, 64, 128, 32));
	}

	private int delay = 0;

	@Override
	public void update() {
		int cw = screen.width / 2;
		play.update();
		play.setPos(cw - 64, 64);
		play.setTextPos(38, 22);
		if (delay > 0) delay--;
		if (play.isPressed() && delay == 0) {
			delay = 20;
			screen.setMenu(screen.getLoadMenu().init());
		}
	}

	@Override
	public void render() {
		play.render(screen);
	}

}
