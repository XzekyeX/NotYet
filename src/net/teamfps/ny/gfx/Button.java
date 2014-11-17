/**
 * 
 */
package net.teamfps.ny.gfx;

import net.teamfps.ny.Input;
import net.teamfps.ny.util.Text;
import net.teamfps.ny.util.Vector4i;

/**
 * @author Zekye
 * 
 */
public class Button {
	public Sprite sprite = Sprite.button;
	public int x, y, w, h;
	private boolean visible = true;
	private boolean enabled = true;
	private String text;
	private int fsize;
	private int color = 0xffffff;
	private int tx = 3;
	private int ty = 0;

	public Button(String text, int fsize, int x, int y, int w, int h) {
		this.text = text;
		this.fsize = fsize;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public Button(String text, int x, int y, int w, int h) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.fsize = 16;
	}

	public Button(String text, int fsize, int w, int h) {
		this.text = text;
		this.fsize = fsize;
		this.w = w;
		this.h = h;
	}

	public Button(String text, int w, int h) {
		this.text = text;
		this.w = w;
		this.h = h;
	}

	public Button(String text, int fsize) {
		this.text = text;
		this.fsize = fsize;
	}

	public Button(String text) {
		this.text = text;
	}
	
	public Button(Text text,Vector4i size) {
		this.text = text.getText();
		this.fsize = text.getFontSize();
		this.color = text.getColor();
		this.x = size.getX();
		this.y = size.getY();
		this.w = size.getW();
		this.h = size.getH();
	}

	public boolean isPressed() {
		boolean pressed = false;
		if (isMouseOver() && enabled) {
			if (Input.ML && visible) {
				pressed = true;
				// Sound.click.play();
			}
		}
		return pressed;
	}

	public boolean isLeftPressed() {
		boolean pressed = false;
		if (isMouseOver() && enabled) {
			if (Input.ML && visible) {
				pressed = true;
				// Sound.click.play();
			}
		}
		return pressed;
	}

	public boolean isRightPressed() {
		boolean pressed = false;
		if (isMouseOver() && enabled) {
			if (Input.MR && visible) {
				pressed = true;
				// Sound.click.play();
			}
		}
		return pressed;
	}

	public boolean isMouseOver() {
		boolean over = false;
		if (Input.MX >= x && Input.MX <= x + w && Input.MY >= y && Input.MY <= y + h) {
			over = true;
		}
		return over;
	}

	public void Swap(Button b) {
		int bx = b.getX();
		int by = b.getY();
		int tx = x;
		int ty = y;

		setX(bx);
		setY(by);

		b.setX(tx);
		b.setY(ty);
	}

	public void update() {
		if (visible) {
			if (isMouseOver() && enabled) {
				sprite = Sprite.button_pressed;
			} else {
				if (!enabled) {
					sprite = Sprite.button_enabled;
				} else {
					sprite = Sprite.button;
				}
			}
		}
	}
	private boolean custom = false;

	public void render(Screen screen) {
		if (visible) {
			screen.renderSprite(sprite, x, y, w, h);
			if(!custom) {				
				int c = (w - 8 - text.length() * (fsize / 2)) / 2;
				screen.renderString(text, fsize, x + tx + c, y + ty + (h / 2), color);
			} else {
				screen.renderString(text, fsize, x + tx, y + ty, color);		
			}
		}
	}

	public int getLength() {
		return (text.length() * (fsize / 2));
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setTextPos(int tx, int ty) {
		this.tx = tx;
		this.ty = ty;
		custom = true;
	}

	public void setFontSize(int fsize) {
		this.fsize = fsize;
	}

	public int getFontSize() {
		return fsize;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @param w
	 *            the w to set
	 */
	public void setW(int w) {
		this.w = w;
	}

	/**
	 * @param h
	 *            the h to set
	 */
	public void setH(int h) {
		this.h = h;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
