package gui;

import java.awt.Color;
import java.awt.Graphics;

import renderer.Renderable;
import renderer.TextRenderer;
import events.Action;

public class GUI_Elements{

	private String text;
	private Action action;
	
	private int x, y;
	private Color color;
	private int size = 0;
	
	
	
	public GUI_Elements(String text, Action action, Color color, int size) {
	this.action = action;
	this.text = text;
	this.color = color;
	this.size = size;
	
	
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

	public String getText() {
		return text;
	}

	public Action getAction() {
		return action;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getColor() {
		return color;
	}

	public int getSize() {
		return size;
	}


	public void render() {

		TextRenderer.text(text, x, y, color, size);
		
	}

	
}
