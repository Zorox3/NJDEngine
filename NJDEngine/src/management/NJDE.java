package management;

import listener.KeyboardListener;
import loader.ImageLoader;

public class NJDE {

	
	public static KeyboardListener key;
	
	public static void init(){
				
		
		new ImageLoader("res/images");
		
	}
	
	public static void print(Object text){
		System.out.println(text);
	}
	
}
