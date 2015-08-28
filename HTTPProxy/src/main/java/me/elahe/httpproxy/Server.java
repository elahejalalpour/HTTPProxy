/*
 * In The Name Of God
 * ========================================
 * [] File Name : Server.java
 *
 * [] Creation Date : 28-08-2015
 *
 * [] Created By : Elahe Jalalpour (el.jalalpour@gmail.com)
 * =======================================
*/
/**
 * @author Elahe Jalalpour
 */
package me.elahe.httpproxy;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {

	private ArrayList<String> blocked;
	JTextArea txtLog;
	private boolean running;
	private int port;

	ServerSocket ss;

	public Server(JTextArea txtLog, int port) {
		blocked = new ArrayList<>();
		this.running = false;
		this.port = port;
		this.txtLog = txtLog;
	}

	public void setBlocked(ArrayList<String> blocked) {
		this.blocked = blocked;
	}

	@Override
	public void run() {
		try {
			ss = new ServerSocket(port);
			while (running) {
				try {
					Socket client = ss.accept();
					Client c = new Client(client, txtLog, blocked);
					c.start();
				} // Now loop again, waiting for the next connection
				catch (Exception e) {
					e.printStackTrace();
				}
			} // If anything goes wrong, print an error message
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void startServer(int portNumber) {
		running = true;
		port = portNumber;
		new Thread(this).start();
	}

	public static void main(String[] args) {
		HTTPFrame Window;
		try {
			Window = new HTTPFrame();
			Window.setVisible(true);
			Window.setSize(1000, 1000);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
