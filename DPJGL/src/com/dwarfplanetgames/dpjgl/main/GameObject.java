package com.dwarfplanetgames.dpjgl.main;

import java.awt.Graphics2D;
import java.util.Random;

public abstract class GameObject {
	
	public float x, y, width, height, velX = 0, velY = 0, gravity = 0;
	public String id;
	public Random random;
	protected Display display;
	public int time = 0;
	
	public GameObject(Display display, String id, float x, float y, float width, float height) {
		this.display = display;
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		random = new Random();
	}
	
	public void update() {
		velY += gravity;
		x += velX;
		y += velY;
		time++;
		tick();
	}
	
	public abstract void render(Graphics2D g);
	public abstract void tick();
	public abstract void unconditionalTick();
	
}
