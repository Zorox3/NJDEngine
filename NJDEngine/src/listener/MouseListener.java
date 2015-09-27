package listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import management.NJDE;
import events.Clickable;

public class MouseListener implements java.awt.event.MouseListener {

	private int x, y;
	private List<Clickable> toListen = new ArrayList<>();

	public MouseListener(Component c) {
		c.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		NJDE.print("CLICKED");
		this.x = e.getX();
		this.y = e.getY();

		for (Clickable clickable : toListen) {

			if ((clickable.x <= x && clickable.y <= y)
					&& (x <= (clickable.x + clickable.width) && y <= (clickable.y + clickable.height))) {

				clickable.actionPerformed();

			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void add(Clickable c) {
		toListen.add(c);
	}

}
