package display;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TexturePaint;

import javax.swing.JFrame;

import renderer.TextRenderer;

public class Display extends Applet {

	private boolean isRunning = true;

	private String name = "NJDEngine";

	private int height = 100;
	private int width = 100;
	private Dimension size;

	private JFrame frame;
	private Image screen;
	private Graphics g;

	private Dimension pixel;

	private boolean vsync = true;
	private boolean renderTick = false;
	private int syncToFrames = 30;
	private boolean maximize = false;
	private boolean border = true;

	private int frames, ticks;

	private Thread thread;
	
	
	
	private TextRenderer text = new TextRenderer();
	

	public Display(String name, int width, int height) {

		this.name = name;
		this.height = height;
		this.width = width;

		this.size = new Dimension(width, height);
		setPreferredSize(size);
		

	}

	public Display(int width, int height) {

		this.height = height;
		this.width = width;

		this.size = new Dimension(width, height);
		setPreferredSize(size);
		
	}

	public Display(DisplaySize sizeType) {
		
		switch (sizeType) {
		case FULLSIZE:
			maximize = true;
		default:
			break;
		}
		
		
		this.size = new Dimension(width, height);
		setPreferredSize(size);
		

	}
	
	public void setBorder(boolean border) {
		this.border = border;
	}
	public void setSyncToFrames(int syncToFrames) {
		this.syncToFrames = syncToFrames;
	}
	
	public int getSyncToFrames() {
		return syncToFrames;
	}
	

	public void createDisplay() {

		frame = new JFrame();
		
		if(!border){
			frame.setUndecorated(true);
			
		}
		frame.setLocationRelativeTo(null);
		
		frame.setTitle(name);
		frame.add(this);
		frame.pack();

		if (maximize) {
			frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		}


		frame.setResizable(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		pixel = new Dimension(frame.getWidth(), frame.getHeight());

		screen = createVolatileImage(pixel.width, pixel.height);
		
		width = frame.getWidth();
		height = frame.getHeight();

	}

	public boolean getSync(){
		return !vsync;
	}
	
	
	

	public void render() {
		g = screen.getGraphics();

		
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
				

		text.setG(g);
		text.render();
		//guiRenderer.render(g);

		g = getGraphics();
		g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);
		g.dispose();
	}
}
