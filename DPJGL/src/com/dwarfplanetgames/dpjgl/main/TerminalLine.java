package com.dwarfplanetgames.dpjgl.main;

import java.awt.Color;

public class TerminalLine {
	
	public Color color = Color.WHITE;
	public String str = "";
	
	public TerminalLine(String str) {
		this.str = str;
	}
	
	public TerminalLine(String str, Color color) {
		this.str = str;
		this.color = color;
	}
	
}