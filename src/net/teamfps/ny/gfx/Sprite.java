/**
 * 
 */
package net.teamfps.ny.gfx;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

//import java.awt.
import javax.imageio.ImageIO;

/**
 * @author Zekye
 * 
 */
public class Sprite {
	private BufferedImage image;
	private String path;
	private String name = "";
	private int width;
	private int height;
	private int[] pixels;

	public static Sprite button = new Sprite("/button.png");
	public static Sprite button_pressed = new Sprite("/button_pressed.png");
	public static Sprite button_enabled = new Sprite("/button_enabled.png");

	// Blocks!
	public static Sprite dirt = new Sprite("/blocks/dirt.png");
	public static Sprite grass = new Sprite("/blocks/grass_side.png");
	public static Sprite snow_grass = new Sprite("/blocks/grass_side_snowed.png");
	public static Sprite stone = new Sprite("/blocks/stone.png");
	public static Sprite sand = new Sprite("/blocks/sand.png");
	public static Sprite water = new Sprite("/blocks/water.png");

	// Walls
	public static Sprite wall_dirt = new Sprite("/walls/wall_dirt.png");

	public static Sprite[] player_left = new Sprite[] { new Sprite("/player/left_stand.png"), new Sprite("/player/left_walk_anim_0.png"), new Sprite("/player/left_walk_anim_1.png") };
	public static Sprite[] player_right = new Sprite[] { new Sprite("/player/right_stand.png"), new Sprite("/player/right_walk_anim_0.png"), new Sprite("/player/right_walk_anim_1.png") };

	public static Sprite[] breaker = new Sprite[] { new Sprite("/breaker/destroy_0.png"), new Sprite("/breaker/destroy_1.png"), new Sprite("/breaker/destroy_2.png"), new Sprite("/breaker/destroy_3.png"), new Sprite("/breaker/destroy_4.png"), new Sprite("/breaker/destroy_5.png"), new Sprite("/breaker/destroy_6.png"), new Sprite("/breaker/destroy_7.png"), new Sprite("/breaker/destroy_8.png"), new Sprite("/breaker/destroy_9.png") };

	public Sprite(int color, int width, int height) {
		this.width = width;
		this.height = height;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}

	public static Sprite[] loadFolder(String path) {
		try {
			URL url = Sprite.class.getResource(path);
			if (url != null) {
				URI uri = url.toURI();
				if (uri != null) {
					File f = new File(uri);
					if (!f.mkdirs()) {
						File[] files = f.listFiles();
						int size = files.length;
						Sprite[] sprites = new Sprite[size];
						for (int i = 0; i < size; i++) {
							String name = files[i].getName().toLowerCase();
							if (name.contains(".png")) {
								String n = name.replaceAll(".png", "");
								sprites[i] = new Sprite(path + "/" + name, n);
								System.out.println("new image: " + n);
							}
						}
						return sprites;
					} else {
						System.out.println("loadFolder!");
						loadFolder(path);
					}
				}
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Sprite ColorChanger(Sprite sprite, int color) {
		WritableRaster wr = sprite.getImage().getRaster();
		int w = sprite.getImage().getWidth();
		int h = sprite.getImage().getHeight();
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				int[] pixels = wr.getPixel(x, y, (int[]) null);
				Color c = new Color(color);
				pixels[0] = c.getRed();
				pixels[1] = c.getGreen();
				pixels[2] = c.getBlue();
				// pixels[3] = c.getAlpha();
				wr.setPixel(x, y, pixels);
				// System.out.println("pixels.length: " + pixels.length);
			}
		}
		return sprite;
	}

	public static Sprite ColorChanger(Sprite sprite, Color color) {
		WritableRaster wr = sprite.getImage().getRaster();
		int w = sprite.getImage().getWidth();
		int h = sprite.getImage().getHeight();
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				int[] pixels = wr.getPixel(x, y, (int[]) null);
				pixels[0] = color.getRed();
				pixels[1] = color.getGreen();
				pixels[2] = color.getBlue();
				// pixels[3] = color.getAlpha();
				// pixels[3] = c.getAlpha();
				wr.setPixel(x, y, pixels);
				// System.out.println("pixels.length: " + pixels.length);
			}
		}
		return sprite;
	}

	public static Sprite getSprite(Sprite[] sprites, String name) {
		if (sprites != null) {
			for (int i = 0; i < sprites.length; i++) {
				Sprite sprite = sprites[i];
				if (sprite != null) {
					String path = sprite.getPath();
					if (path.contains(name)) {
						System.out.println("path.contains(" + name + ")");
						return sprite;
					}
				}
			}
		}
		return null;
	}

	public Sprite(String path) {
		this.path = path;
		load();
	}

	public Sprite(String path, String name) {
		this.path = path;
		this.name = name;
		load();
	}

	private void load() {
		try {
			image = ImageIO.read(getClass().getResource(path));
			this.width = image.getWidth();
			this.height = image.getHeight();
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[] getPixels() {
		return pixels;
	}

	public BufferedImage getImage() {
		return image;
	}

	public String getPath() {
		return path;
	}

	/**
	 * @return
	 */
	public String getName() {
		return "" + name;
	}
}
