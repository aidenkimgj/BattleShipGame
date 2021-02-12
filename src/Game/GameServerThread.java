package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

class GameServerThread extends Thread {

	// 클라이언트 소켓 저장
	Socket socket;

	// ChatGUIServer 클래스의 객체를 멤버 변수로 선언, has-a 관계를 위함
	PlayServer playServer;

	private BufferedReader br; 
	private PrintWriter pw;

	// 전달할 문자열
	String str;

	// 대화명(ID)
	String name;

	// 생성자
	public GameServerThread(PlayServer playServer, Socket s) {
		/*
		 * cg = new ChatGUIServer(); → 작성 불가, 서버가 두 번 가동되기 때문에 충돌이 일어남 따라서 매개변수를 이용해서
		 * 객체를 얻어온(call by reference) 뒤에 cg와 s값을 초기화해야 함
		 */
		this.playServer = playServer;

		// 접속한 클라이언트 정보 저장
		this.socket = s;

		// 데이터 전송을 위한 입출력 스트림 생성
		try {
			// =========== 입력 ===========
			// s.getInputStream() => 접속 클라이언트(소켓 객체)의 InputStream을 얻어 옴
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(), true);

		} catch (Exception e) {
			System.out.println("Erorr..>>>>>" + e);
		}
	}

	// 메세지(입력 문자열) 출력 메소드
	public void send(String str) {

		
		pw.println(str);
//		pw.flush();
	}

	public void run() {

		try {

			// 무한 대기하며 입력한 메세지를 각 클라이언트에 계속 전달
			while ((str = (String) br.readLine()) != null) {
				playServer.broadCast(str, this);
				

			}
			
			
		} catch (Exception e) {

			playServer.removeThread(this); // this: ServerThread 객체, 접속 클라이언트

			System.out.println(socket.getInetAddress() + " connection finished!");
		}
	}

}