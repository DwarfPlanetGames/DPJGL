
// Brandon Dyer
// Creates and manages windows and graphics

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class DPJGL {
	
	public static JFrame frame;
	public static Graphics2D graphics;
	
	private static Canvas canvas;
	private static BufferStrategy bs;
	
	public static void init() {
		frame = new JFrame("Dwarf Planet Java Game Library");
		canvas = new Canvas();
		
		frame.add(canvas);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640,400);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
	
	public static void render() {
		graphics.dispose();
		bs.show();
		
		BufferStrategy bs1 = canvas.getBufferStrategy();
		while (bs1 == null) {
			canvas.createBufferStrategy(2);
		}
		graphics = (Graphics2D) bs.getDrawGraphics();
	}
	
	public static Graphics2D g() {
		return graphics;
	}
	
	public static void showDisplay() {
		try {
			frame.setVisible(true);
			BufferStrategy bs1 = canvas.getBufferStrategy();
			while (bs1 == null) {
				canvas.createBufferStrategy(2);
			}
			graphics = (Graphics2D) bs.getDrawGraphics();
		} catch (NullPointerException e) {
			System.err.println("You forgot to call DPJGL.init()");
		}
	}
	
}
