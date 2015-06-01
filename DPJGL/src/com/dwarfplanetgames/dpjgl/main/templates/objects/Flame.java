package com.dwarfplanetgames.dpjgl.main.templates.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import com.dwarfplanetgames.dpjgl.main.Display;
import com.dwarfplanetgames.dpjgl.main.GameObject;

public class Flame extends GameObject {

	public int size;
	private Random rand;
	private long fireSeed;

	public Flame(Display display, float x, float y, int size) {
		super(display, "flame", x, y, 1, 1);
		this.size = size;
		rand = new Random();
	}

	@Override
	public void render(Graphics2D g) {
		rand.setSeed(fireSeed);
		int dist = 0;
		g.setColor(Color.BLUE);
		int x = (int) this.x;
		int y = (int) this.y;
		float step = 0.13f;
		for (float i = 0; i < Math.PI; i += step) {
			int distp = dist;
			dist = rand.nextInt(size / 2) + size / 2;
			dist *= Math.sin(i);
			int[] xs = new int[] { (int) (Math.cos(i) * dist) + x, (int) (Math.cos(i - step) * distp) + x, x };
			int[] ys = new int[] { (int) -(Math.sin(i) * dist) + y, (int) -(Math.sin(i - step) * distp) + y, y };
			g.fillPolygon(xs, ys, 3);
		}
		g.setColor(Color.CYAN);
		for (float i = 0; i < Math.PI; i += step) {
			int distp = dist;
			dist = rand.nextInt(size / 2) + size / 2;
			dist *= Math.sin(i);
			int[] xs = new int[] { (int) (Math.cos(i) * dist) / 2 + x, (int) (Math.cos(i - step) * distp) / 2 + x, x };
			int[] ys = new int[] { (int) -(Math.sin(i) * dist) / 2 + y, (int) -(Math.sin(i - step) * distp) / 2 + y, y };
			g.fillPolygon(xs, ys, 3);
		}

	}

	public void tick() {
		fireSeed = display.time;
	}

}
