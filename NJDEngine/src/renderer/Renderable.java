package renderer;

import java.awt.Graphics;


public interface Renderable {

	public abstract void render(Graphics g);
	public abstract void tick();

}
