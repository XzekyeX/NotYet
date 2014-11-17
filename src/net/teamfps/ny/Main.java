package net.teamfps.ny;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import net.teamfps.ny.gfx.Screen;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private static JFrame f = new JFrame("Not Yet © Zekye, 2014");
	private static int width = 680;
	private static int height = 480;
	private boolean isRunning;
	private Thread thread;
	private Screen screen;
	private Input input;
	private int fps = 0;
	private int ups = 0; 

	/**
	 * Create the frame.
	 */
	private void init() {
		input = new Input();
		screen = new Screen(width, height);
		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		addMouseWheelListener(input);
	}

	public void start() {
		if (isRunning) return;
		isRunning = true;
		thread = new Thread(this, "Main Thread!");
		thread.start();
	}

	public void stop() {
		if (!isRunning) return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int ups = 0;
		int fps = 0;
		init();
		requestFocus();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				delta--;
				update();
				ups++;
			}
			render();
			fps++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				setFpsAndUps(fps, ups);
				fps = 0;
				ups = 0;
			}
		}
		stop();
	}

	private void setFpsAndUps(int fps, int ups) {
		this.fps = fps;
		this.ups = ups;
	}

	public String getFpsAndUps() {
		return "fps[" + fps + "], ups[" + ups + "]";
	}

	private void update() {
		screen.update();
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.width = getWidth();
		screen.height = getHeight();

		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());
		screen.render(g);
		screen.renderString(getFpsAndUps(), 12, 16, 22, 0xffffff);
		g.dispose();
		bs.show();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Main m = new Main();
		f.add(m, "Center");
		f.pack();
		f.setTitle("NotYet");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(0, 0, width, height);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		m.start();
	}
}
