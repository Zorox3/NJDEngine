package renderer.sub;

import java.awt.Color;
import java.awt.Graphics;

import renderer.Renderable;

public class FormPolyElement implements Renderable {

	private boolean filled = false;
	private Color color;

	private int[] xPoints;
	private int[] yPoints;
	private int nPoints;

	public FormPolyElement(int[] xPoints, int[] yPoints, Color color,
			boolean filled) {
		this.filled = filled;
		this.color = color;

		this.xPoints = xPoints;
		this.yPoints = yPoints;

		if (xPoints.length == yPoints.length) {
			nPoints = xPoints.length;
		} else {
			nPoints = 0;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		if (filled) {
			g.fillPolygon(xPoints, yPoints, nPoints);
		} else {
			g.drawPolygon(xPoints, yPoints, nPoints);
		}

	}

}
