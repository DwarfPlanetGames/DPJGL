package com.dwarfplanetgames.dpjgl.main.templates.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.dwarfplanetgames.dpjgl.main.Display;
import com.dwarfplanetgames.dpjgl.main.GameObject;

public class WaterSpout extends GameObject {
	
	public float radius;
	public float xm, ym;
	public Color color;
	
	public WaterSpout(Display display, float x, float y, float radius, float xm, float ym, Color color) {
		super(display, "water_spout", x, y, 1, 1);
		this.radius = radius;
		this.xm = xm; this.ym = ym;
	}

	@Override
	public void render(Graphics2D g) {
				
	}

	@Override
	public void tick() {
		display.handler.objects.add(new WaterParticle(display, x, y, radius, color));
	}

	@Override
	public void unconditionalTick() {
				
	}

}
