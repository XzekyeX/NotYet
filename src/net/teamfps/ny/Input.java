package net.teamfps.ny;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.event.MouseInputListener;

/**
 * @author Zekye
 *
 */
public class Input implements KeyListener, MouseListener, MouseInputListener, MouseMotionListener, MouseWheelListener {
	private static boolean[] keys = new boolean[65536];
	public static int MX = -1;
	public static int MY = -1;
	public static int DX = -1;
	public static int DY = -1;
	public static int SCROLL = 0;
	public static boolean ML = false;
	public static boolean MR = false;
	public static boolean MC = false;

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		SCROLL = e.getWheelRotation();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		DX = e.getX();
		DY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MX = e.getX();
		MY = e.getY();
		DX = e.getX();
		DY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1) {
			ML = true;
		}
		if (e.getButton() == 2) {
			MC = true;
		}
		if (e.getButton() == 3) {
			MR = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == 1) {
			ML = false;
		}
		if (e.getButton() == 2) {
			MC = false;
		}
		if (e.getButton() == 3) {
			MR = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		keys[key] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		keys[key] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * @param vkA
	 * @return
	 */
	public static boolean equalsKey(int key) {
		return keys[key];
	}

}
