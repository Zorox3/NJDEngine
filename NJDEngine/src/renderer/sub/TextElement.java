package renderer.sub;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class TextElement {

	private String text;
	private int x;
	private int y;
	private Color color;
	private int size;
	
	public TextElement(String text, int x, int y, Color c, int size){
		this.text = text;
		this.x = x;
		this.y = y;
		this.color = c;
		this.size = size;
	}
	
	public void render(Graphics g){
		Font font = new Font("Franklin Gothic Demi", Font.PLAIN, size);
		
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, x, y);
	}
	
}
