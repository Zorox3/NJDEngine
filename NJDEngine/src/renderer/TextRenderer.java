package renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import renderer.sub.TextElement;

public class TextRenderer {

	private static List<TextElement> toRender = new ArrayList<>();
	private Graphics g;
	
	public void setG(Graphics g) {
		this.g = g;
	}
	
	public static void text(String text, int x, int y, Color c, int size){
		
		toRender.add(new TextElement(text, x, y, c, size));
		
	}
	
	public void render(){
		
		for(TextElement e : new ArrayList<>(toRender)){
			e.render(g);
		}
		
		toRender.clear();
		
		
	}
	
}
