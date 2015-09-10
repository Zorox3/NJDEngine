package test;

import display.Display;

public class Main {

	static Main test;
	
	public static void main(String[] args) {
		
		test = new Main();
		
		
		Display display = new Display(2000, 200);
		display.start();

}

}
