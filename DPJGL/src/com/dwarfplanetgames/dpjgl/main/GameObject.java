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
	public boolean active;
	private boolean ct, cb, cr, cl;
	public int time = 0;

	public GameObject(Display display, String id, float x, float y, float width, float height) {
		this.display = display;
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		active = true;
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
		if (velX > width / 2) velX = width / 2;
		if (velX < -width / 2) velX = -width / 2;
		if (velY > height / 2) velY = height / 2;
		if (velY < -height / 2) velY = -height / 2;
		tick();
	}

	public void dispose() {
		display.handler.objects.remove(this);
	}

	public void collide(String[] args) {
		for (int i = 0; i < display.handler.objects.size(); i++) {
			GameObject temp = display.handler.objects.get(i);
			for (int h = 0; h < args.length; h++) {
				if (temp.id.equals(args[h])) {
					collide(temp);
				}
			}
		}
	}

	public boolean collide(GameObject o) {
		if (this.equals(o)) return false;
		boolean collided = false;
		if (getBounds().intersects(o.getBounds())) {
			collided = true;
		}
		if (getBounds(Side.top).intersects(o.getBounds(/*Direction.VER,velY*/))) {
			y = o.y + o.height;
			if (o.active) {
				o.velY = (o.velY + velY) / 2f;
			}
			velY = o.velY;
			ct = true;
		}
		if (getBounds(Side.bottom).intersects(o.getBounds(/*Direction.VER,velY*/))) {
			y = o.y - height;
			if (o.active) {
				o.velY = (o.velY + velY) / 2f;
			}
			velY = o.velY;
			cb = true;
		}
		if (getBounds(Side.left).intersects(o.getBounds(/*Direction.HOR,velX*/))) {
			x = o.x + o.width;
			if (o.active) {
				o.velX = (o.velX + velX) / 2f;
			}
			velX = o.velX;
			cl = true;
		}
		if (getBounds(Side.right).intersects(o.getBounds(/*Direction.HOR,velX*/))) {
			x = o.x - width;
			if (o.active) {
				o.velX = (o.velX + velX) / 2f;
			}
			velX = o.velX;
			cr = true;
		}
		if (getBounds().intersects(o.getBounds())) {
			velX += (0.5f - Display.rand.nextFloat()) * 4;
			velY += (0.5f - Display.rand.nextFloat()) * 4;
			x += velX;
			y += velY;
		}
		return collided;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	public Rectangle getBounds(Direction d, float velocity) {
		velocity *= 0.5f;
		return new Rectangle((int) x - (d == Direction.HOR ? (int) velocity : 0), (int) y - (d == Direction.VER ? (int) velocity : 0), d == Direction.HOR ? (int) width + 2 * (int) velocity : (int) width, d == Direction.VER ? (int) height + 2 * (int) velocity : (int) height);
	}

	public Rectangle getBounds(Side s) {
		switch (s) {
		case top:
			return new Rectangle((int) (x + width / 2 - width / 4), (int) y, (int) (width / 2), (int) (height / 2));
		case bottom:
			return new Rectangle((int) (x + width / 2 - width / 4), (int) (y + height / 2), (int) (width / 2), (int) (height / 2));
		case left:
			return new Rectangle((int) (x), (int) (y + height / 2 - height / 4), (int) (width / 2), (int) (height/2));
		case right:
			return new Rectangle((int) (x + width / 2), (int) (y + height / 2 - height / 4), (int) (width / 2), (int) (height / 2));
		default:
			// Display.terminal.errorln("An object by ID " + id + "failed with bad side");
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
