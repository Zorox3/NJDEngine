package display;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

public class Display extends Applet implements Runnable {

	private boolean isRunning = true;

	private String name = "NJDEngine";

	private int height;
	private int width;
	private Dimension size;

	private JFrame frame;
	private Image screen;
	private Graphics g;

	private Dimension pixel;
	
	private boolean vsync = true;
	private int syncToFrames = 1;

	private int frames, ticks;

	private Thread thread;
	
	public Display(String name, int width, int height) {

		this.name = name;
		this.height = height;
		this.width = width;
		
		this.size = new Dimension(width, height);
		setPreferredSize(size);
		createFrame();

	}

	public Display(int width, int height) {

		this.height = height;
		this.width = width;

		this.size = new Dimension(width, height);
		setPreferredSize(size);
		createFrame();
	}

	private Display(DisplaySize sizeType) {

	}
	
	public void start(){
		thread = new Thread(this, "Display Thread");
		thread.start();
	}


	private void createFrame() {

		frame = new JFrame();
		frame.setTitle(name);
		frame.add(this);
		frame.pack();
		//frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		pixel = new Dimension(frame.getWidth(), frame.getHeight());

		screen = createVolatileImage(pixel.width, pixel.height);
		
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		double nsPerTickRender = 1000000000D / syncToFrames;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		double deltaRender = 0;

		int ticks = 0;
		int frames = 0;

		while (isRunning) {
			
			render();
			
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			deltaRender += (now - lastTime) / nsPerTickRender;
			lastTime = now;
			
			// Frame limit;

			boolean shouldRender = !vsync;

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

	public void tick() {

		//size = new Dimension(frame.getWidth(), frame.getHeight());
		
	}
	
	

	public void render() {
		g = screen.getGraphics();
		
		System.out.println("rendering");
		
		g.setColor(Color.RED);
		g.fillRect(0, 0, width, height);
		
	
		
		g = getGraphics();
		g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width,
				pixel.height, null);
		g.dispose();
	}
}
