package com.dwarfplanetgames.dpjgl.main.templates.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.dwarfplanetgames.dpjgl.main.Display;
import com.dwarfplanetgames.dpjgl.main.GameObject;

public class WaterSpout extends GameObject {
	
	public float radius;
	public float xm, ym;
	public Color color;
	public int flow;
	public int particles;
	
	public WaterSpout(Display display, float x, float y, float radius, float xm, float ym, Color color, int flow) {
		super(display, "water_spout", x, y, 1, 1);
		this.radius = radius;
		this.xm = xm; this.ym = ym;
		this.color = color;
		this.flow = flow;
		particles = 0;
	}

	@Override
	public void render(Graphics2D g) {
				
	}

	@Override
	public void tick() {
		if (time % radius == 0 && (particles < flow || flow == 0)) {
			WaterParticle w = new WaterParticle(display, x, y, radius, color);
			w.velX = xm;
			w.velY = ym;
			display.handler.objects.add(w);
			particles++;
		}
	}

	@Override
	public void unconditionalTick() {
				
	}

}
