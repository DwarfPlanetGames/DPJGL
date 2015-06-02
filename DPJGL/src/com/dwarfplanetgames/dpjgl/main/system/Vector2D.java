package com.dwarfplanetgames.dpjgl.main.system;


public class Vector2D {

	public FloatPoint from;
	public FloatPoint to;

	public Vector2D(FloatPoint from, FloatPoint to) {
		this.from = from;
		this.to = to;
	}

	public FloatPoint move(float dist) {
		if (to.x == from.x) {
			if (to.y > from.y) {
				return new FloatPoint(from.x, from.y + dist);
			} else if (to.y < from.y) {
				return new FloatPoint(from.x, from.y - dist);
			} else {
				return new FloatPoint(from.x, from.y);
			}
		} else {
			float m = (to.y - from.y) / (to.x - from.x);
			float X = (float) (Math.sqrt(Math.pow(dist, 2) / (Math.pow(m, 2) + 1)));
			if (to.x < from.x) {
				X *= -1;
			}
			return new FloatPoint(from.x + X, from.y + m * X);
		}

		/*
		FloatPoint f = new FloatPoint(from.x, from.y);
		if(to.x > from.x) f.x += dist;
		if(to.x < from.x) f.x -= dist;
		if(to.y > from.y) f.y += dist;
		if(to.y < from.y) f.y -= dist;
		return f;
		*/
	}

	public Side getSide() {
		if (Math.abs(to.x - from.x) > Math.abs(to.y - from.y)) {
			// this is x (left and right)
			if (to.x > from.x) return Side.right;
			else return Side.left;
		} else {
			// this is y (top and bottom)
			if (to.y > from.y) return Side.bottom;
			else return Side.top;
		}
	}

}
