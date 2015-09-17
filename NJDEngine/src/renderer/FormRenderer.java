package renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import renderer.sub.FormRoundElement;

public class FormRenderer implements Renderable {

	private static List<FormRoundElement> toRender = new ArrayList<>();

	public static void oval(int x, int y, int width, int height, Color color,
			boolean filled) {
		toRender.add(new FormRoundElement(x, y, width, height, color, filled));
	}

	public static void oval(int x, int y, int radius, Color color,
			boolean filled) {
		toRender.add(new FormRoundElement(x, y, radius * 2, radius * 2, color,
				filled));
	}

	public static void filledOval(int x, int y, int width, int height,
			Color color) {
		toRender.add(new FormRoundElement(x, y, width, height, color, true));
	}

	public static void filledOval(int x, int y, int radius, Color color) {
		toRender.add(new FormRoundElement(x, y, radius * 2, radius * 2, color,
				true));
	}

	public static void drawOval(int x, int y, int width, int height, Color color) {
		toRender.add(new FormRoundElement(x, y, width, height, color));
	}

	public static void drawOval(int x, int y, int radius, Color color) {
		toRender.add(new FormRoundElement(x, y, radius * 2, radius * 2, color));
	}

	@Override
	public void render(Graphics g) {
		for (FormRoundElement f : toRender) {
			f.render(g);
		}
		toRender.clear();

	}

}
