package renderer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import renderer.sub.ImageElement;

public class ImageRenderer {
	private static List<ImageElement> toRender = new ArrayList<>();
	private Graphics g;

	public void setG(Graphics g) {
		this.g = g;
	}

	public static void image(BufferedImage image, int x, int y) {

		toRender.add(new ImageElement(image, x, y));

	}

	public static void image(BufferedImage image, int x, int y, int width, int height) {

		toRender.add(new ImageElement(image, x, y, width, height));

	}

	public void render() {

		for (ImageElement e : new ArrayList<>(toRender)) {
			e.render(g);
		}

		toRender.clear();

	}
}
