package net.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Input implements Runnable {

	private DataInputStream in;
	private int hashCode;

	private Map<String, String> data = new HashMap<>();

	private String pattern = "[a-zA-Z0-9\\s:]*";
	private String pattern2 = "[a-zA-Z0-9]*";

	public Input(DataInputStream in, int hashCode) {
		this.in = in;
		this.hashCode = hashCode;
	}

	@Override
	public void run() {
		while (true) {
			String message;
			try {
				if (in != null) {
					message = in.readUTF();
					if (message.matches(pattern)) {
						String parts[] = message.split(":");
						if (parts[0].matches(pattern2)) {
							int receivedHash = Integer.valueOf(parts[0]);

							String key = parts[1];
							String value = parts[2];
							if (receivedHash != hashCode) {
								if (data.containsKey(parts[1])) {
									data.remove(key);
									data.put(key, value);

								} else {
									data.put(key, value);
									System.err.println(hashCode + "-> " + key
											+ ": " + value);
								}

								// System.err.println(hashCode + "-> " + key +
								// ": "
								// + value);
							}
						}
					}
				}
			} catch (IOException e) {

				in = null;
				// Action.manageActions(Action.gameExit);

			}

		}
	}

	public String getData(String key) {
		String data = this.data.get(key);
		if (data != null)
			if (data.matches(pattern)) {
				// this.data.remove(key);
				return data;
			}
		return "-1";

	}

}
