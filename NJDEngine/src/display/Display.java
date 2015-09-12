package display;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import renderer.ImageRenderer;
import renderer.Renderable;
import renderer.TextRenderer;

public class Display extends Applet implements Runnable{

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
	
	private List<Renderable> toRender = new ArrayList<>();
	
	private TextRenderer text = new TextRenderer();
	private ImageRenderer image = new ImageRenderer();
	
	
	public void start(){
		thread = new Thread(this, "Display Thread");
		thread.start();
	}

	public void add(Renderable o){
		toRender.add(o);
	}
	
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
	
	public void run() {

		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		double nsPerTickRender = 1000000000D / getSyncToFrames();
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		double deltaRender = 0;

		int ticks = 0;
		int frames = 0;

		
		render();
		
		while (true) {

			

			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			deltaRender += (now - lastTime) / nsPerTickRender;
			lastTime = now;

			boolean shouldRender = getSync();

			if (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;

			}
			if (deltaRender >= 1) {
				deltaRender -= 1;
				shouldRender = true;
				
			}

			if (shouldRender) {
				frames++;
				
				
				render();
				
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				this.frames = frames;
				this.ticks = ticks;
				frames = 0;
				ticks = 0;
			}
		}

	}
	public int getTicks() {
		return ticks;
	}
	public int getFrames() {
		return frames;
	}
	public void tick(){
		for(Renderable r : toRender){
			r.tick();
		}
	}
	
	public void render() {
		g = screen.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
				
		for(Renderable r : toRender){
			r.render(g);
		}
		
		text.setG(g);
		text.render();
		
		image.setG(g);
		image.render();

		g = getGraphics();
		g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);
		g.dispose();
	}
}
