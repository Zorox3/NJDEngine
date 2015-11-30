package renderer.sub;

import java.awt.Color;
import java.awt.Graphics;

import renderer.Renderable;

public class FormQuadElement implements Renderable{

	private Color color;
	private int x, y;
	private int width, height;
	private boolean filled = false;
	
	public FormQuadElement(int x, int y, int width, int height, Color color) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public FormQuadElement(int x, int y, int width, int height, Color color, boolean filled) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.filled = filled;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		if(filled){
			g.fillRect(x, y, width, height);
		}else{
			g.drawRect(x, y, width, height);
		}
		
	}

}
