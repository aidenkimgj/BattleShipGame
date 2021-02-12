package BattleShip_chat;

import java.net.*;
import java.io.*;
import java.util.Vector;

/**
 * The chatServer class implements Battleship program that communicate
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
	 * 
	 */
	public chat_server() {
		// Create vector object for users.
		v = new Vector();

		try {
			// Create serverSocket.
			ss = new ServerSocket(5454);
			System.out.println("Chat server is running...");

			while (true) {
				s = ss.accept();
				st = new ServerThread(this, s);
				this.addThread(st);
				st.start();
			}
		} catch (Exception e) {
			// Display error message.
			System.out.println("Fail to connect..>>>" + e);
		}
	}

	// 벡터 v에 접속 클라이언트의 스레드 저장
	/**
	 * 
	 * @param st
	 */
	public void addThread(ServerThread st) {
		v.add(st);
	}

	// 퇴장한 클라이언트 스레드 제거
	/**
	 * 
	 * @param st
	 */
	public void removeThread(ServerThread st) {
		v.remove(st);
	}

	// 각 클라이언트에게 메세지를 출력하는 메소드, send() 호출
	/**
	 * 
	 * @param str
	 */
	public void broadCast(String str) {
		for (int i = 0; i < v.size(); i++) {
			ServerThread st = (ServerThread) v.elementAt(i);
			st.send(str);
		}
	}

	public static void main(String[] args) {
		new chat_server();
	}
}

// ServerThread 클래스 생성 → 서버에서 각 클라이언트의 요청을 처리할 스레드
/**
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

	// 생성자
	/**
	 * 
	 * @param cg
	 * @param s
	 */
	public ServerThread(chat_server cg, Socket s) {
		this.cg = cg;
		this.s = s;

		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream(), true);
		} catch (Exception e) {
			System.out.println("Error..>>>>>" + e);
		}
	}

	// 메세지(입력 문자열) 출력 메소드
	/**
	 * 
	 * @param str
	 */
	public void send(String str) {
		pw.println(str);
		pw.flush();
	}

	
	@Override
	/**
	 * Override the run() method for chatServer.
	 */
	public void run() {
		try {
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