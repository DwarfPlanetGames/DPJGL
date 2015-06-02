package com.dwarfplanetgames.dpjgl.main.templates.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import com.dwarfplanetgames.dpjgl.main.Display;
import com.dwarfplanetgames.dpjgl.main.GameObject;

public class Ember extends GameObject {

	public float maxTime;
	public Random rand;
	public double flameX, flameWidth;
	public Color color;
	public Color smokeColor = new Color(0xffff00);
	public int smokeValue;

	public Ember(Display display, float x, float y, float width, float height, float maxTime, Color color, int smokeValue) {
		super(display, "ember", x, y, width, height);
		this.maxTime = maxTime;
		rand = new Random();
		this.color = color;
		this.smokeValue = smokeValue;
	}

	@Override
	public void render(Graphics2D g) {
		if (color == smokeColor) {
			if (time > maxTime / 1.5) {
				g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
				g.fillOval((int) (x - width), (int) (y - height), (int) (width * 2), (int) (height * 2));
			}
		} else {
			g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue()));// ,100));
			g.fillOval((int) (x - (maxTime*2 - time / 2)), (int) (y - (maxTime*2 - time / 2)), (int) ((maxTime*2 - time / 2)) * 2, (int) ((maxTime*2 - time / 2)) * 2);

		}
	}

	public void tick() {
		time--;
	}

	public void unconditionalTick() {
		time++;
		if (color != smokeColor) {
			y -= rand.nextInt(8);
			x += 1 - rand.nextInt(3);
		} else {
			y -= rand.nextInt(2);
			x += 1 - rand.nextInt(3);
		}
		if (time / 4 > maxTime) {
			display.handler.objects.remove(this);
			 for (int i = 0; i < 1; i++)
				 if (color != smokeColor && rand.nextInt(4) == 0) display.handler.objects.add(new Ember(display, x + 2-rand.nextInt(5), y + 2-rand.nextInt(5), 10, 10, (maxTime / 8) * smokeValue, smokeColor, smokeValue));
		}
	}

}
