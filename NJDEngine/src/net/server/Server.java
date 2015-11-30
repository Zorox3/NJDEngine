package net.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	private ServerSocket serverSocket;
	private Socket socket;
	private DataOutputStream out;
	private User[] users = new User[10];
	private DataInputStream in;

	private Thread serverThread;
	private int port;

	private int connections = 0;

	public Server(int port) throws IOException {

		this.port = port;

	}

	public void start() throws IOException {
		this.serverThread = new Thread(this, "Server Thread");
		this.serverThread.start();
	}

	@Override
	public void run() {
		try {
			System.out.println("Starting Server...");
			serverSocket = new ServerSocket(port);
			System.out.println("Server startet...");
			while (true) {

				socket = serverSocket.accept();

				for (int i = 0; i < 10; i++) {

					System.out.println("Connection from: "
							+ socket.getInetAddress());
					out = new DataOutputStream(socket.getOutputStream());
					in = new DataInputStream(socket.getInputStream());
					if (users[i] == null) {
						connections++;
						users[i] = new User(out, in, users);
						Thread thread = new Thread(users[i]);
						thread.start();

						
						
						
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getConnections() {
		return connections - 1;
	}

}
