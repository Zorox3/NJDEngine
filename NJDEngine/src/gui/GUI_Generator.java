package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import events.Action;
import loader.DataLoader;
import loader.TextLoader;
import management.NJDE;

public class GUI_Generator {

	private DataLoader loader;

	private HashMap<String, GUI> guiList = new HashMap<>();

	public GUI_Generator(String path, String file) {

		this.loader = new DataLoader(new TextLoader(path).getFileContent(file));
		makeGui();
	}

	public GUI getGUI(String guiName) {
		return guiList.get(guiName);
	}

	public HashMap<String, GUI> getGuiList() {
		return guiList;
	}

	String listKey = "";
	
	
	String headline = "[HEADLINE]";
	Color textColor = Color.RED;
	String fontsize = "0";
	String x = "0";
	String y = "0";
	
	public void makeGui() {

		Map<?, ?> jsonData = loader.getData();


		for (Entry<?, ?> mainEntry : jsonData.entrySet()) {
			


			List<GUI_Elements> elements = new ArrayList<>();
			
			
			listKey = mainEntry.getKey().toString();

			
			for (Entry<?, ?> entry : ((Map<?, ?>) mainEntry.getValue())
					.entrySet()) {
				


				if (entry.getKey().toString().compareTo("headline") == 0) {
					headline = entry.getValue().toString();
				} else if (entry.getKey().toString().compareTo("xOffset") == 0) {
					x =  entry.getValue().toString();
					
				} else if (entry.getKey().toString().compareTo("yOffset") == 0) {
					y = entry.getValue().toString();
					
				} else if (entry.getKey().toString().compareTo("fontsize") == 0) {
					fontsize = entry.getValue().toString();
					
				} else if (entry.getKey().toString().compareTo("elements") == 0
						&& entry.getValue() instanceof List<?>) {


					
					List<?> objList = (List<?>) entry.getValue();
					
					for (Object o : objList) {
						String text = "[TEXT]";
						Action action = Action.START;
						
						Map<?, ?> m = (Map<?, ?>) o;
						for (Entry<?, ?> e : m.entrySet()) {
			

							if (e.getKey().toString().compareTo("text") == 0) {
								text = e.getValue().toString();
							} else if (e.getKey().toString()
									.compareTo("action") == 0) {
								action = Action.START;
							}
						
						}
						elements.add(new GUI_Elements(text, action, textColor,
								Integer.valueOf(fontsize)));
					}

				}
			}
			
			guiList.put(listKey, new GUI(headline, elements, textColor,
					Integer.valueOf(fontsize), Integer.valueOf(x), Integer.valueOf(y)));
		}

	}

}
