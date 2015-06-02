package com.dwarfplanetgames.dpjgl.main.templates.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.dwarfplanetgames.dpjgl.main.Display;
import com.dwarfplanetgames.dpjgl.main.GameObject;

public class Flame1 extends GameObject {
	
	public Color color;
	public int smokeValue;
	public int size;
	
	public Flame1(Display display, float x, float y, int size, Color color, int smokeValue) {
		super(display, "flame1", x, y, 1, 1);
		this.color = color;
		this.smokeValue = smokeValue;
		this.size = size;
	}

	@Override
	public void render(Graphics2D g) {
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void unconditionalTick() {
		for (int i = 0; i < random.nextInt(size*1); i++)
			display.handler.objects.add(new Ember(display,x, y,7,7,random.nextInt(size /4) + 1, color, smokeValue));
	}

}
