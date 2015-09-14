package loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class TextLoader {

	private String path = "";

	private File file;
	private HashMap<String, File> filesInDir;
	private File dir;

	public TextLoader(String dirPath) {
		this.path = dirPath;

		this.dir = new File(dirPath);

		FileLoader f = new FileLoader(dirPath);

		this.filesInDir = f.getFiles();
	}	

	public String getFileContent(String name) {
		try {
			return new Scanner(filesInDir.get(name)).useDelimiter("\\A").next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "-1";
	}

	public File getFile(String name) {
		return filesInDir.get(name);
	}

}
