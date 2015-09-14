package loader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {

	private String path = "";
	private static HashMap<String, BufferedImage> imageList = new HashMap<>();
	
	public ImageLoader(String dirPath){
		this.path = dirPath;
		listDir(new File(dirPath));
	}
	
	public static HashMap<String, BufferedImage> getImageList() {
		return imageList;
	}
	
	public static BufferedImage getImage(String filename){
		return imageList.get(filename);
	}
	
	public void listDir(File dir) {

	    File[] files = dir.listFiles();
	    if (files != null) { // Erforderliche Berechtigungen etc. sind vorhanden
	        for (int i = 0; i < files.length; i++) {
	            System.out.print(files[i].getAbsolutePath());
	            try {
					imageList.put(removeExtension(files[i].getName()), ImageIO.read(files[i]));
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	    }
	}
	
	private String removeExtension(String name){
		return name.substring(0, name.lastIndexOf("."));
	}
	
}
