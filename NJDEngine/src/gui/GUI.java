package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import display.Display;
import renderer.Renderable;
import renderer.TextRenderer;

public class GUI implements Renderable{

	private String headline;
	private Color textColor = Color.WHITE;
	private int fontsize = 0;
	private int x, y;
	
	private List<GUI_Elements> elements = new ArrayList<>();
	
	public GUI(String headline, List<GUI_Elements> elements, Color textColor, int fontsize, int x, int y) {
		this.elements = elements;
		this.fontsize = fontsize;
		this.headline = headline;
		this.textColor = textColor;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void render(Graphics g) {
		
		TextRenderer.text(headline, x, y, textColor, fontsize*2);
		
		int elementOffset = (int) (fontsize * 6);
		
		for(GUI_Elements e : elements){
			
			e.setX(x);
			e.setY(elementOffset);
			
			e.render();
			elementOffset += fontsize * 1.5;
		}
	}

	@Override
	public void tick() {
		
	}

	public String getHeadline() {
		return headline;
	}

	public Color getTextColor() {
		return textColor;
	}

	public int getFontsize() {
		return fontsize;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public List<GUI_Elements> getElements() {
		return elements;
	}
	
	
}
