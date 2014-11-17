/**
 * 
 */
package net.teamfps.ny.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * @author Zekye
 * 
 */
public class Bitmap {
	public int xOffs;
	public int yOffs;
	public int width;
	public int height;
	public Graphics g;

	public Bitmap(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void initGFX(Graphics g) {
		this.g = g;
	}

	public void renderSprite(Sprite sprite, int x, int y, int w, int h) {
		if (sprite != null) {
			g.drawImage(sprite.getImage(), x, y, w, h, null);
		} else {
			System.out.println("sprite = null");
		}
	}

	public void renderSprite(Sprite sprite, int x, int y, int w, int h, boolean offset) {
		if (offset) {
			x -= xOffs;
			y -= yOffs;
		}
		if (sprite != null) {
			g.drawImage(sprite.getImage(), x, y, w, h, null);
		} else {
			System.out.println("sprite = null");
		}
	}

	public void renderSprite(Sprite sprite, int x, int y, int w, int h, boolean flipX, boolean flipY, boolean offset) {
		if (offset) {
			x -= xOffs;
			y -= yOffs;
		}
		if (flipY) {
			h = -h;
		}
		if (flipX) {
			w = -w;
		}
		if (sprite != null) {
			g.drawImage(sprite.getImage(), x, y, w, h, null);
		} else {
			System.out.println("sprite = null");
		}
	}

	public void renderString(String str, int fsize, int x, int y, int color) {
		g.setColor(new Color(color));
		g.setFont(new Font("Tahoma", 1, fsize));
		g.drawString(str, x, y);
		g.setColor(new Color(0xffffff));
	}

	public void renderString(String str, int fsize, int x, int y, int color, boolean offset) {
		if (offset) {
			x -= xOffs;
			y -= yOffs;
		}
		g.setColor(new Color(color));
		g.setFont(new Font("Tahoma", 1, fsize));
		g.drawString(str, x, y);
		g.setColor(new Color(0xffffff));
	}

	public void renderRect(int x, int y, int w, int h, int b, int color) {
		g.setColor(new Color(color));
		g.fillRect(x, y, w, b);
		g.fillRect(x, y, b, h);
		g.fillRect(x + w, y, b, h + b);
		g.fillRect(x, y + h, w, b);
		g.setColor(new Color(0xffffff));
	}

	public void renderRect(int x, int y, int w, int h, int b, int color, boolean offset) {
		if (offset) {
			x -= xOffs;
			y -= yOffs;
		}
		g.setColor(new Color(color));
		g.fillRect(x, y, w, b);
		g.fillRect(x, y, b, h);
		g.fillRect(x + w, y, b, h + b);
		g.fillRect(x, y + h, w, b);
		g.setColor(new Color(0xffffff));
	}

	public void renderFill(int x, int y, int w, int h, int color) {
		g.setColor(new Color(color));
		g.fillRect(x, y, w, h);
		g.setColor(new Color(0xffffff));
	}

	public void renderFill(int x, int y, int w, int h, int color, boolean offset) {
		if (offset) {
			x -= xOffs;
			y -= yOffs;
		}
		g.setColor(new Color(color));
		g.fillRect(x, y, w, h);
		g.setColor(new Color(0xffffff));
	}

	public void renderFillRect(int x, int y, int w, int h, int fcolor, int rcolor) {
		renderFill(x, y, w, h, fcolor);
		renderRect(x, y, w, h, 4, rcolor);
	}

	public void renderFillRect(int x, int y, int w, int h, int b, int fcolor, int rcolor) {
		renderFill(x, y, w, h, fcolor);
		renderRect(x, y, w, h, b, rcolor);
	}

	public void setOffset(int xOffs, int yOffs) {
		this.xOffs = xOffs;
		this.yOffs = yOffs;
	}
}
