package renderer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import renderer.sub.ImageElement;
import display.Display;
import display.DisplaySize;

public class ImageRenderer implements Renderable {
	private static List<ImageElement> toRender = new ArrayList<>();
	private Graphics g;

	public void setG(Graphics g) {
		this.g = g;
	}

	public static void image(BufferedImage image, int x, int y) {

		toRender.add(new ImageElement(image, x, y));

	}

	public static void image(BufferedImage image, int x, int y, int width,
			int height) {

		toRender.add(new ImageElement(image, x, y, width, height));

	}

	public static void image(BufferedImage image, DisplaySize size) {
		switch (size) {
		case FULLSIZE:
			toRender.add(new ImageElement(image, 0, 0, Display.WIDTH,
					Display.HEIGHT));
			break;
		case HALFSIZE:
			toRender.add(new ImageElement(image, 0, 0, Display.WIDTH / 2,
					Display.HEIGHT / 2));
			break;

		default:
			break;
		}
	}

	public static void imageCentered(BufferedImage image) {
		toRender.add(new ImageElement(image, Display.WIDTH / 2
				- image.getWidth() / 2, Display.HEIGHT / 2 - image.getHeight()
				/ 2));
	}

	public static void imageCentered(BufferedImage image, int width, int height) {
		toRender.add(new ImageElement(image, Display.WIDTH / 2 - width / 2,
				Display.HEIGHT / 2 - height / 2, width, height));
	}

	public static void imageCentered(BufferedImage image, DisplaySize size) {

		switch (size) {
		case FULLSIZE:
			imageCentered(image, Display.WIDTH, Display.HEIGHT);
			break;
		case HALFSIZE:
			imageCentered(image, Display.WIDTH / 2, Display.HEIGHT / 2);
			break;

		default:
			break;
		}

	}

	public static void image(BufferedImage image, int x, int y, DisplaySize size) {
		switch (size) {
		case FULLSIZE:
			toRender.add(new ImageElement(image, x, y, Display.WIDTH,
					Display.HEIGHT));
			break;
		case HALFSIZE:
			toRender.add(new ImageElement(image, x, y, Display.WIDTH / 2,
					Display.HEIGHT / 2));
			break;

		default:
			break;
		}
	}

	public void render(Graphics g) {

		for (ImageElement e : new ArrayList<>(toRender)) {
			e.render(g);
		}

		toRender.clear();

	}

}
