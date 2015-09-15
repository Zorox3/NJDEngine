package listener;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import management.NJDE;

public class KeyboardListener implements KeyListener {

	private boolean[] keys = new boolean[256];
	
	public KeyboardListener(Component c) {

		c.addKeyListener(this);
		
	}

	
	public boolean isKeyDown(int keycode){
		if(keycode > 0 && keycode < 256){
			return keys[keycode];
		}
		
		return false;
	}
		
	@Override
	public void keyPressed(KeyEvent e) {

		NJDE.print("Key Pressed: " + e.getKeyChar());
		
		if(e.getKeyCode() > 0 && e.getKeyCode() < 256){
			keys[e.getKeyCode()] = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() > 0 && e.getKeyCode() < 256){
			keys[e.getKeyCode()] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
