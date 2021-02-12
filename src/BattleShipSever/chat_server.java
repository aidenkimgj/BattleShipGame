package BattleShipSever;

import java.net.*;
import java.io.*;
import java.util.Vector;

/**
 * The chatServer class is a server for implementing a battleship program that communicates
 * between game users.
 * 
 * @author Hong, Kim, and Sung.
 * @version Feb 11, 2021
 */
public class chat_server {

	ServerSocket ss;
	Socket s;
	Vector v;
	ServerThread st;

	/**
	 * Call Vector and ServerSocket classes in this method.
	 */
	public chat_server() {
		v = new Vector();

		try {
			ss = new ServerSocket(5454);
			System.out.println("Chat server is running...");

			while (true) {
				s = ss.accept();
				st = new ServerThread(this, s);
				this.addThread(st);
				st.start();
			}
		} catch (Exception e) {
			System.out.println("Fail to connect..>>>" + e);
		}
	}


	/**
	 * Connected users is saved Thread into Vector.
	 * @param st ServerThread
	 */
	public void addThread(ServerThread st) {
		// Server thread add in the vector.
		v.add(st);
	}


	/**
	 * This method remove server thread.
	 * @param st ServerThread
	 */
	public void removeThread(ServerThread st) {
		v.remove(st);
	}


	/**
	 * Each users display message with send method.
	 * @param str message
	 */
	public void broadCast(String str) {
		for (int i = 0; i < v.size(); i++) {
			ServerThread st = (ServerThread) v.elementAt(i);
			st.send(str);
		}
	}

	
	/**
	 * This method is main.
	 * @param args arguments
	 */
	public static void main(String[] args) {
		new chat_server();
	}
}


/**
 * Create ServerThread class.
 * 
 * @author Hong, Kim, and Sung.
 * @version Feb 11, 2021
 */
class ServerThread extends Thread {
	Socket s;
	chat_server cg;
	BufferedReader br;
	PrintWriter pw;
	String str;
	String name;


	/**
	 * Constructor
	 * 
	 * @param cg chat_server
	 * @param s socket
	 */
	public ServerThread(chat_server cg, Socket s) {
		this.cg = cg;
		this.s = s;
		
		// Read text messages by using BufferedReader.
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream(), true);
		} catch (Exception e) {
			System.out.println("Error..>>>>>" + e);
		}
	}


	/**
	 * The send method send messages.
	 * 
	 * @param str message
	 */
	public void send(String str) {
		pw.println(str);
		pw.flush();
	}

	
	@Override
	/**
	 * Override the run() method for chat_server.
	 */
	public void run() {
		try {
			// Display message for users.
			pw.println("Please enter your name..");
			name = br.readLine();
			cg.broadCast("[" + name + "]" + " is joined.");

			while ((str = br.readLine()) != null) {
				cg.broadCast("[" + name + "]: " + str);
			}
			
		} catch (Exception e) {
			cg.removeThread(this);
			cg.broadCast("[" + name + "]" + " left.");
			System.out.println(s.getInetAddress() + " connection finished.");
		}
	}

}