package com.dwarfplanetgames.dpjgl.main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import com.dwarfplanetgames.dpjgl.main.system.Direction;
import com.dwarfplanetgames.dpjgl.main.system.Side;

public abstract class GameObject {
	
	public float x, y, width, height, velX = 0, velY = 0, gravity = 0;
	public String id;
	public Random random;
	protected Display display;
	private boolean ct,cb,cr,cl;
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
		ct = false;
		cb = false;
		cr = false;
		cl = false;
		tick();
	}
	
	public void dispose() {
		display.handler.objects.remove(this);
	}
	
	public void collide(GameObject o) {
		if (getBounds(Side.top).intersects(o.getBounds(/*Direction.VER,velY*/))) {
			y = o.y + o.height;
			velY = o.velY;
			ct = true;
		}
		if (getBounds(Side.bottom).intersects(o.getBounds(/*Direction.VER,velY*/))) {
			y = o.y - height;
			velY = o.velY;
			cb = true;
		}
		if (getBounds(Side.left).intersects(o.getBounds(/*Direction.HOR,velX*/))) {
			x = o.x + o.width;
			velX = o.velX;
			cl = true;
		}
		if (getBounds(Side.right).intersects(o.getBounds(/*Direction.HOR,velX*/))) {
			x = o.x - width;
			velX = o.velX;
			cr = true;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,(int)width,(int)height);
	}
	
	public Rectangle getBounds(Direction d, float velocity) {
		velocity *= 0.5f;
		return new Rectangle(
				(int)x - (d == Direction.HOR ? (int) velocity : 0),
				(int)y - (d == Direction.VER ? (int) velocity : 0),
				d == Direction.HOR ? (int) width + 2*(int) velocity : (int)width,
				d == Direction.VER ? (int) height + 2*(int) velocity : (int)height
				);
	}
	
	public Rectangle getBounds(Side s) {
		int vSpeed = (int)(velY * 1.0f);
		int hSpeed = (int)(velX * 1.0f);
		switch (s) {
		case top:
			return new Rectangle((int)x,                      (int)y - Math.abs(vSpeed)-1,   (int)width,              (int)Math.abs(vSpeed)+1);
		case bottom:
			return new Rectangle((int)x,                      (int)y + (int)height,        (int)width,              (int)Math.abs(vSpeed)+1);
		case left:
			return new Rectangle((int)x - Math.abs(hSpeed)-1,   (int)y,                      (int)Math.abs(hSpeed)+1,   (int)height);
		case right:
			return new Rectangle((int)x + (int)width,         (int)y,                      (int)Math.abs(hSpeed)+1,   (int)height);
		default:
			//Display.terminal.errorln("An object by ID " + id + "failed with bad side");
			return getBounds();
		}
	}
	
	public boolean getCollided(Side s) {
		switch (s) {
		case top:
			return ct;
		case bottom:
			return cb;
		case right:
			return cr;
		case left:
			return cl;
			default:
				System.out.println("ERROR");
				return false;
		}
	}
	
	public abstract void render(Graphics2D g);

	public abstract void tick();

	public abstract void unconditionalTick();

}
