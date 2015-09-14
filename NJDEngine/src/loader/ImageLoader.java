package loader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageLoader {

	private String path = "";
	private static HashMap<String, BufferedImage> imageList = new HashMap<>();

	public ImageLoader(String dirPath) {
		this.path = dirPath;

		FileLoader f = new FileLoader(dirPath);

		loadImages(f.getFiles());
	}

	private void loadImages(HashMap<String, File> files) {

		for (Map.Entry<String, File> entry : files.entrySet()) {

			try {
				imageList.put(entry.getKey(), ImageIO.read(entry.getValue()));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static HashMap<String, BufferedImage> getImageList() {
		return imageList;
	}

	public static BufferedImage getImage(String filename) {
		return imageList.get(filename);
	}

}
