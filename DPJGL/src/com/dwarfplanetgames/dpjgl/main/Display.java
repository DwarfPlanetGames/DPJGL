package com.dwarfplanetgames.dpjgl.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;

public class Display extends Canvas implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6393818626729414718L;
	//s
	private Thread thread;
	@SuppressWarnings("unused")
	private int width, height;
	@SuppressWarnings("unused")
	private String title;
	private JFrame frame;
	private boolean running = false;
	private Core core;
	private int threadSleep = 3;
	public Handler handler;
	public int time = 0;
	public static Terminal terminal;
	public static final Random rand = new Random();
	
	public Display(int width, int height, String title, Core core) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.core = core;
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setSize(width, height);
		handler = new Handler();
		addMouseListener(handler);
		addMouseMotionListener(handler);
		addKeyListener(handler);
		core.begin(this);
	}
	
	public synchronized void start() {
		if (!running) {
			running = true;
			frame.setVisible(true);
			thread = new Thread(this, "game");
			thread.start();
		}
	}
	
	public static void add(Terminal terminal) {
		Display.terminal = terminal;
	}
	
	public void run() {
		requestFocus();
		long startTime = System.nanoTime();
		while (running) {
			long newTime = System.nanoTime();
			if (newTime - startTime > 1000000000 / 60.0) {
				startTime = newTime;
				if (this.hasFocus()) {
					core.update();
					for (int i = 0; i < handler.objects.size(); i++) {
						GameObject temp = handler.objects.get(i);
						temp.update();
					}
				}
				for (int i = 0; i < handler.objects.size(); i++) {
					GameObject temp = handler.objects.get(i);
					temp.unconditionalTick();
				}
				time++;
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
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		core.render(g);
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject temp = handler.objects.get(i);
			temp.render(g);
		}
		g.dispose();
		bs.show();
	}
	
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
