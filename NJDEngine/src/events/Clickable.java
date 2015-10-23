package events;

import java.awt.event.MouseEvent;

public abstract class Clickable {

	public abstract void actionPerformed(MouseEvent e);
	public int x = 0,y = 0;
	public int width = 10, height = 10;
	
}
