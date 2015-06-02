package com.dwarfplanetgames.dpjgl.main.templates.objects;

import java.awt.Graphics2D;

import com.dwarfplanetgames.dpjgl.main.Display;
import com.dwarfplanetgames.dpjgl.main.GameObject;

public class WaterParticle extends GameObject {

	public float radius;

	public WaterParticle(Display display, String id, float x, float y, float radius) {
		super(display, "water_particle", x, y, 1, 1);
		this.radius = radius;
	}

	@Override
	public void render(Graphics2D g) {
		g.fillRect((int) (x - radius), (int) (y - radius), (int) (x + radius), (int) (y + radius));
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void unconditionalTick() {
		// TODO Auto-generated method stub

	}

}
