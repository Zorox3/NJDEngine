package management;

import loader.ImageLoader;

public class NJDE {

	
	public static void init(){
		
		new ImageLoader("res/images");
		
	}
	
	public static void print(String text){
		System.out.println(text);
	}
	
}
