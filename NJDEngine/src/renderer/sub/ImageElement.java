package renderer.sub;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageElement {
	private BufferedImage image;
	private int x1, width;
	private int y1, height;

	private boolean resize = false;

	public ImageElement(BufferedImage image, int x, int y) {
		this.image = image;
		this.x1 = x;
		this.y1 = y;

	}

	public ImageElement(BufferedImage image, int x1, int y1, int width, int height) {
		this.image = image;
		this.x1 = x1;
		this.y1 = y1;
		this.width = width;
		this.height = height;

		resize = true;
	}

	public void render(Graphics g) {
		if (resize) {
			g.drawImage(image, x1, y1, width, height, null);
		} else {
			g.drawImage(image, x1, y1, null);
		}
	}
}
