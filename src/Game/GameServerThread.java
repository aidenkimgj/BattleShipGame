package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * @author Hong, Kim, and Sung.
 * @version Feb 11, 2021
 */
class GameServerThread extends Thread {

   /**
    * field - socket, playServer, br, pw, str, name.
    */
   Socket socket;
   PlayServer playServer;
   private BufferedReader br; 
   private PrintWriter pw;
   String str;
   String name;

   /**
    * This is GameServerThread class constructor with playserver and socket parameters.
    */
   public GameServerThread(PlayServer playServer, Socket s) {

      // save playServer into the field.
      this.playServer = playServer;

      // save socket into the field.
      this.socket = s;

      // 데이터 전송을 위한 입출력 스트림 생성
      /**
       * create I/O stream for the data transfer.
       */
      try {
         // input - s.getInputStream() => get socker object's inputStream.
         br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         
         pw = new PrintWriter(socket.getOutputStream(), true);

      } catch (Exception e) {
         System.out.println("Erorr..>>>>>" + e);
      }
   }

   // 메세지(입력 문자열) 출력 메소드
   public void send(String str) {

      
      pw.println(str);
//      pw.flush();
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