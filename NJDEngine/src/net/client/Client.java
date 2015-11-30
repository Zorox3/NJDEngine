package net.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Client implements Runnable {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;

	private Thread clientThread;

	private static int id = 2;

	private List<String> outputList = new ArrayList<>();
	private String ip;
	private int port;

	public Input input;
	private boolean canSend;

	public Client(int id, String ip, int port) throws IOException {
		this.id = id;
		this.ip = ip;
		this.port = port;

	}

	public void start() throws UnknownHostException, IOException {

		this.clientThread = new Thread(this, "Client Thread" + id);
		this.clientThread.start();
		canSend = true;

	}

	public void addMessage(String key, String message) {
		outputList.add(key + ":" + message);
	}

	public void addMessage(String key, int x) {
		addMessage(key, String.valueOf(x));
	}

	public void addMessage(String key, int[] array) {
		addMessage(key, array[0] + "," + array[1]);
	}

	public void sendMessages() {
		for (String output : new ArrayList<>(outputList)) {
			try {
				if (canSend) {
					out.writeUTF((id * this.hashCode() + ":" + output).trim());
					outputList.remove(output);
				}
			} catch (IOException e) {
				canSend = false;
			}

		}
	}

	public boolean isConnected() {
		if (socket == null) {
			return false;
		}
		if (socket.isConnected()) {
			return true;
		}

		return false;

	}

	@Override
	public void run() {
		System.out.println("Connection...");
		try {
			socket = new Socket(ip, port);

			System.out.println("Connection succesful");
			System.err.println("ID: " + id);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			input = new Input(in, id * this.hashCode());
			Thread thread = new Thread(input);
			thread.start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
