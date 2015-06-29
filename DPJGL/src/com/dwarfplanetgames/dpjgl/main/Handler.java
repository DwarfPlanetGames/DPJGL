package com.dwarfplanetgames.dpjgl.main;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

public class Handler implements KeyListener, MouseListener, MouseMotionListener {
	
	public LinkedList<GameObject> objects = new LinkedList<GameObject>();
	public Point mouse;
	public boolean[] buttons = new boolean[17];
	public boolean[] keys = new boolean[6880];
	
	public Handler() {
		mouse = new Point();
		for (int i = 0; i < keys.length; i++) {
			keys[i] = false;
		}
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = false;
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		mouse.x = arg0.getX();
		mouse.y = arg0.getY();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		mouse.x = arg0.getX();
		mouse.y = arg0.getY();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		buttons[arg0.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		buttons[arg0.getButton()] = false;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		keys[arg0.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		keys[arg0.getKeyCode()] = false;		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
