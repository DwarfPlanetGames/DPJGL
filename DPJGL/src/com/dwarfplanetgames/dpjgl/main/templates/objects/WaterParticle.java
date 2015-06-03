package com.dwarfplanetgames.dpjgl.main.templates.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.dwarfplanetgames.dpjgl.main.Display;
import com.dwarfplanetgames.dpjgl.main.GameObject;
import com.dwarfplanetgames.dpjgl.main.system.FloatPoint;
import com.dwarfplanetgames.dpjgl.main.system.Vector2D;

public class WaterParticle extends GameObject {

	public float radius;
	public Color color;

	public WaterParticle(Display display, float x, float y, float radius, Color color) {
		super(display, "water_particle", x, y, 1, 1);
		this.radius = radius;
		gravity = 0.93f;
		velX = 5;
		this.color = color;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int) (x - radius * 2), (int) (y - radius * 2), (int) (radius * 4), (int) (radius * 4));
	}

	@Override
	public void tick() {
		velX *= 0.9f;
		velY *= 0.9f;
		for (int i = 0; i < display.handler.objects.size(); i++) {
			GameObject temp = display.handler.objects.get(i);
			switch (temp.id) {
			case "water_collider":
			case "water_particle":
				
				break;
			default:
				break;
			}
		}
		/*
		if (velX > radius) velX = radius;
		if (velX < -radius) velX = -radius;
		if (velY > radius) velY = radius;
		if (velY < -radius) velY = -radius;
		*/
		if (y > display.getHeight()) {
			y = display.getHeight();
			velY = 0;
		}
		if (y < 0) {
			y = 0;
			velY = 0;
		}
		if (x > display.getWidth()) {
			x = display.getWidth();
			velX = 0;
		}
		if (x < 0) {
			x = 0;
			velX = 0;
		}
		if (x > display.getWidth() || y > display.getHeight()) {
			dispose();
		}
	}

	@Override
	public void unconditionalTick() {

	}

}
