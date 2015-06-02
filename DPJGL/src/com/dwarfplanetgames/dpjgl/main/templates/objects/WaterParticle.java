package com.dwarfplanetgames.dpjgl.main.templates.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.dwarfplanetgames.dpjgl.main.Display;
import com.dwarfplanetgames.dpjgl.main.GameObject;

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
		g.fillOval((int) (x - radius*2), (int) (y - radius*2), (int) (radius * 4), (int) (radius * 4));
	}

	@Override
	public void tick() {
		for (int i = 0; i < display.handler.objects.size(); i++) {
			GameObject temp = display.handler.objects.get(i);
			switch (temp.id) {
			case "water_collider":
				//TODO add code for colliding with rectangles
				break;
			case "water_particle":
				//TODO add code for colliding with other water particles
				break;
				default:
					break;
			}
		}
		if (x > display.getWidth() ||
				y > display.getHeight()) {
			dispose();
		}
	}

	@Override
	public void unconditionalTick() {

	}

}
