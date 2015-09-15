package loader;

import java.util.Map;

import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;

public class DataLoader {

	private String text;

	
	private Map<?, ?> jsonMap;

	public DataLoader(String text) {
		this.text = text;
		
		parseJson();
	}
	
	private void parseJson(){
		JsonParserFactory factory=JsonParserFactory.getInstance();
		JSONParser parser=factory.newJsonParser();
		jsonMap = parser.parseJson(text);
	}

	public Map<?, ?> getData() {
		return this.jsonMap;
	}
}
