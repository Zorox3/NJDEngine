package test;

import java.awt.Color;

import display.Display;
import renderer.TextRenderer;

public class Main implements Runnable {

	static Main test;
	private static Display display;

	public static void main(String[] args) {

		test = new Main();

		display = new Display(1280, 720);
		// Display display = new Display(DisplaySize.FULLSIZE);

		display.setBorder(true);

		display.createDisplay();

		//display.start();

		Thread thread = new Thread(test);
		thread.start();

	}

	@Override
	public void run() {

		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		double nsPerTickRender = 1000000000D / display.getSyncToFrames();
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		double deltaRender = 0;

		int ticks = 0;
		int frames = 0;

		display.render();
		render();
		
		while (true) {

			

			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			deltaRender += (now - lastTime) / nsPerTickRender;
			lastTime = now;

			boolean shouldRender = display.getSync();

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
				
				display.render();
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

	
	private int frames = 0, ticks = 0;
	
	
	public void tick(){
		
		
	}
	
	
	
	
	public void render(){
		TextRenderer.text("FPS: " + frames + " | UPS: " + ticks, 20, 20, Color.YELLOW, 20);
		
		
		TextRenderer.text("Test", 100, 100, Color.RED, 80);
	}

}
