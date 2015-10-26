package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import management.NJDE;
import display.Display;
import events.Tickable;
import renderer.FormRenderer;
import renderer.Renderable;
import renderer.TextRenderer;

public class GUI implements Renderable, Tickable {

	private String headline;
	private Color textColor = Color.WHITE;
	private int fontsize = 0;
	private int x, y;
	private int selected = 0;

	private List<GUI_Elements> elements = new ArrayList<>();

	public GUI(String headline, List<GUI_Elements> elements, Color textColor,
			int fontsize, int x, int y) {
		this.elements = elements;
		this.fontsize = fontsize;
		this.headline = headline;
		this.textColor = textColor;
		this.x = x;
		this.y = y;

		
		
	}

	@Override
	public void render(Graphics g) {

		TextRenderer.text(headline, x, y, textColor, fontsize * 2);

		int elementOffset = (int) (fontsize * 6);

		int counter = 0;
		
		
		
		for (GUI_Elements e : elements) {
			
			e.setX(x);
			e.setY(elementOffset);

			if(counter == selected){
				FormRenderer.filledOval(x - 30, elementOffset - fontsize / 2, fontsize/2, fontsize/2, textColor);
			}
			
			
			e.render();
			elementOffset += fontsize * 1.5;
			
			counter++;
		}
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

	@Override
	public void tick() {

		if (NJDE.key.isKeyDown(KeyEvent.VK_UP)
				|| NJDE.key.isKeyDown(KeyEvent.VK_W)) {
			selected--;
			NJDE.print("UP");
			if (selected < 0) {
				selected = elements.size();
			}
		} else if (NJDE.key.isKeyDown(KeyEvent.VK_DOWN)
				|| NJDE.key.isKeyDown(KeyEvent.VK_S)) {
			selected++;
			if (selected > elements.size() - 1) {
				selected = 0;
			}
		}
		
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

}
