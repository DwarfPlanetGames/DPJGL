package com.dwarfplanetgames.dpjgl.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import javax.swing.JFrame;

public class Terminal extends Canvas {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<TerminalLine> lines = new LinkedList<TerminalLine>();
	private JFrame frame;
	
	public Terminal(String title) {
		frame = new JFrame(title + " Terminal");
		frame.add(this);
		frame.setSize(300, 500);
		frame.setVisible(true);
		println("Terminal started");
		println("Powered by DPJGL");
		println("Dwarf Planet Games");
	}
	
	public void show() {
		frame.setVisible(true);
		render();
	}
	
	public void hide() {
		frame.setVisible(false);
	}
	
	public void println(String str) {
		print(str, Color.WHITE);
	}
	
	public void print(String str, Color col) {
		lines.addFirst(new TerminalLine(str, col));
		if (lines.size() > getHeight() / 12 + 1) {
			lines.removeLast();
		}
		render();
	}
	
	public void errorln(String str) {
		print(str, Color.RED);
	}
	
	public void errorln() {
		errorln("An error occured!");
	}
	
	public void errorln(Exception e) {
		e.printStackTrace();
		errorln(e.toString());
		errorln("Class:  " + e.getStackTrace()[0].getClassName());
		errorln("Method: " + e.getStackTrace()[0].getMethodName());
		errorln("Line:   " + Integer.toString(e.getStackTrace()[0].getLineNumber()));
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics go = bs.getDrawGraphics();
		Graphics2D g = (Graphics2D) go;
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < lines.size(); i++) {
			g.setColor(lines.get(i).color);
			g.drawString(lines.get(i).str, 0, getHeight() - i * 12 - 12);
		}
		
		g.dispose();
		bs.show();
	}
	
}
