package listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import management.NJDE;
import events.Clickable;
import events.Scrollable;

public class MouseListener implements java.awt.event.MouseListener, MouseWheelListener {

	private int x, y;
	private List<Clickable> toListen = new ArrayList<>();
	private List<Scrollable> toScroll = new ArrayList<>();

	public MouseListener(Component c) {
		c.addMouseListener(this);
		c.addMouseWheelListener(this);
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		NJDE.print("CLICKED");
		this.x = e.getX();
		this.y = e.getY();

		for (Clickable clickable : new ArrayList<>(toListen)) {

			if ((clickable.x <= x && clickable.y <= y)
					&& (x <= (clickable.x + clickable.width) && y <= (clickable.y + clickable.height))) {

				clickable.actionPerformed(e);

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
	public void add(Scrollable s) {
		toScroll.add(s);
	}
	public void remove(Clickable c) {
		toListen.remove(c);
	}
	public void remove(Scrollable s) {
		toScroll.remove(s);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		NJDE.print("SCROLLED");
		
		for (Scrollable scrollable : new ArrayList<>(toScroll)) {

			scrollable.actionOnScoll(e.getPreciseWheelRotation());

		}

	}

}
