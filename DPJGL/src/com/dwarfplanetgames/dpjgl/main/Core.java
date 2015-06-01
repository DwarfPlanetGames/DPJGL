package com.dwarfplanetgames.dpjgl.main;

import java.awt.Graphics2D;

public interface Core {
	
	public void init(Display d);
	public void update();
	public void render(Graphics2D g);
	
}
