package renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import renderer.sub.ImageElement;

public class ImageRenderer {
	private static List<ImageElement> toRender = new ArrayList<>();
	private Graphics g;
	
	public void setG(Graphics g) {
		this.g = g;
	}
	
	public static void image(String text, int x, int y, Color c, int size){
		
		toRender.add(new ImageElement(text, x, y, c, size));
		
	}
	
	public void render(){
		
		for(ImageElement e : new ArrayList<>(toRender)){
			e.render(g);
		}
		
		toRender.clear();
		
		
	}
}
