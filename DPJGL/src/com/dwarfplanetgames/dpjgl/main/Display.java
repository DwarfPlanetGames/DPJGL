package com.dwarfplanetgames.dpjgl.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Display extends Canvas implements Runnable {
	//s
	private Thread thread;
	private int width, height;
	private String title;
	private JFrame frame;
	private boolean running = false;
	private Core core;
	private int threadSleep = 3;
	
	public Display(int width, int height, String title, Core core) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.core = core;
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setSize(width, height);
	}
	
	public synchronized void start() {
		if (!running) {
			running = true;
			frame.setVisible(true);
			thread = new Thread(this, "game");
			thread.start();
		}
	}
	
	public void run() {
		long startTime = System.nanoTime();
		while (running) {
			long newTime = System.nanoTime();
			if (newTime - startTime > 1000000000 / 60.0) {
				startTime = newTime;
				if (this.hasFocus())
					core.update();
			}
			render();
			try {
				Thread.sleep(threadSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics go = bs.getDrawGraphics();
		Graphics2D g = (Graphics2D) go;
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		core.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public void openTerminal() {
		//TODO initialize terminal
	}
	
}
