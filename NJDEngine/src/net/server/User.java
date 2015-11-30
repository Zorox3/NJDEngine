package net.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

class User implements Runnable {

	DataOutputStream out;
	DataInputStream in;
	User[] user = new User[10];

	public User(DataOutputStream out, DataInputStream in, User[] user) {
		this.out = out;
		this.in = in;
		this.user = user;
	}

	@Override
	public void run() {
		while (true) {

			
			try {
				String message = in.readUTF();
				for(int i = 0; i < 10; i++){
					if(user[i] != null){
						user[i].out.writeUTF(message);
					}
				}
			} catch (Exception e) {
				this.in = null;
				this.out = null;
			}
			
		}

	}

}
