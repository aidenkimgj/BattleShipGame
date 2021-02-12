package Game;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class PlayServer {

	// 클라이언트와 연결할 때만 필요한 ServerSocket 클래스
	ServerSocket ss;

	// 서버로 접속한 클라이언트 Socket을 저장할 멤버 변수
	Socket s;

	// 접속 클라이언트 정보 실시간 저장
	Vector v;

	// ServerThread 자료형 멤버 변수 선언, has-a 관계 설정을 위함
	GameServerThread gameServerThread;

	ArrayList<String> firstData = new ArrayList<String>();

	int count = 0;

	public PlayServer() {
		v = new Vector();

		try {

			ss = new ServerSocket(9999);
			System.out.println("Game server is running...");
			while (true) {
				s = ss.accept();

				gameServerThread = new GameServerThread(this, s);
				this.addThread(gameServerThread);

				// Thread 가동 -> run() -> broadCast() -> send() 실시간 메소드 호출
				gameServerThread.start();
			}

		} catch (Exception e) {
			// 접속 실패시 간단한 Error 메세지 출력
			System.out.println("Fail to connect..>>>" + e);
		}
	}

	// 벡터 v에 접속 클라이언트의 스레드 저장
	public void addThread(GameServerThread st) {
		v.add(st);
	}

	// 퇴장한 클라이언트 스레드 제거
	public void removeThread(GameServerThread st) {
		v.remove(st);
	}

	// 각 클라이언트에게 메세지를 출력하는 메소드, send() 호출
	public void broadCast(String str, GameServerThread gameServerThread2) {
		if (v.size() == 3) {

			firstData.add(str);
		} else if (v.size() == 4) {

			if (str.length() < 3) {
				GameServerThread st_01 = (GameServerThread) v.elementAt(3);
				GameServerThread st_00 = (GameServerThread) v.elementAt(2);

				st_01.send(firstData.get(count));
				count++;
				st_00.send(str);

			} else {
				GameServerThread st_01_01 = (GameServerThread) v.elementAt(1);
				GameServerThread st_00_01 = (GameServerThread) v.elementAt(0);
				if (gameServerThread2 == v.elementAt(0)) {
					st_01_01.send(str);
//					System.out.println("두번째로 넘긴다.... " + str);
				} else if (gameServerThread2 == v.elementAt(1)) {
					st_00_01.send(str);
//					System.out.println("첫번째로 넘긴다... " + str);
				}
			}

		}

	}

	public static void main(String[] args) {
		new PlayServer();
	}

}
