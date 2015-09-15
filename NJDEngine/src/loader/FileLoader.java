package loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class FileLoader {

	private String path;
	private HashMap<String, File> files = new HashMap<>();

	public FileLoader(String path) {
		this.path = path;

		listDir(new File(path));

	}

	public HashMap<String, File> getFiles() {
		return files;
	}
	
	private void listDir(File dir) {

		File[] filesList = dir.listFiles();
		if (filesList != null) {
			for (int i = 0; i < filesList.length; i++) {
				files.put(removeExtension(filesList[i].getName()), filesList[i]);
			}
		}
	}

	public String removeExtension(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}
	
	public FileReader getFileAsReader(String name){
		try {
			return new FileReader(files.get(name));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public InputStream getFileAsInputStream(String name){
		InputStream is = null;
		FileInputStream fis;
		try {
			fis = new FileInputStream(files.get(name));
			is = fis;
			fis.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}

		return is;
	}
	
	public File getFile(String name){
		return files.get(name);
	}

}
