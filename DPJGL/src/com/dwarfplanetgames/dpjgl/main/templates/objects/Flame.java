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
	public int thining;

	public Flame(Display display, float x, float y, int size, int thining) {
		super(display, "flame", x, y, 1, 1);
		this.size = size / thining;
		this.thining = thining;
		rand = new Random();
	}

	@Override
	public void render(Graphics2D g) {
		rand.setSeed(fireSeed);
		float dist = 0;
		g.setColor(Color.RED);
		int x = (int) this.x;
		int y = (int) this.y;
		float step = 0.3f;
		for (float i = 0; i < Math.PI; i += step) {
			float distp = dist;
			dist = rand.nextInt(size / 2) + size / 2 + rand.nextFloat();
			dist *= Math.sin(i);
			int[] xs = new int[] { (int) (Math.cos(i) * dist) + x, (int) (Math.cos(i - step) * distp) + x, x };
			int[] ys = new int[] { (int) -(Math.sin(i) * dist) * thining + y, (int) -(Math.sin(i - step) * distp) * thining + y, y };
			g.fillPolygon(xs, ys, 3);
		}
		g.setColor(new Color(255,150,0));
		for (float i = 0; i < Math.PI; i += step) {
			float distp = dist;
			dist = rand.nextInt(size / 2) + size / 2 + rand.nextFloat();
			dist *= Math.sin(i);
			int[] xs = new int[] { (int) (Math.cos(i) * dist) / 2 + x, (int) (Math.cos(i - step) * distp) / 2 + x, x };
			int[] ys = new int[] { (int) -(Math.sin(i) * dist) / 1 + y, (int) -(Math.sin(i - step) * distp) / 1 + y, y };
			g.fillPolygon(xs, ys, 3);
		}

	}

	public void tick() {
		
	}
	
	public void unconditionalTick() {
		fireSeed = display.time / 4;
	}

}
