package BattleShipSever;

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
    * @param playServer playServer object
    * @param s socket
    */
   public GameServerThread(PlayServer playServer, Socket s) {

      // save playServer into the field.
      this.playServer = playServer;

      // save socket into the field.
      this.socket = s;

      /**
       * create I/O stream for the data transfer.
       */
      try {
         // input - socket.getInputStream() => get socket object's inputStream.
         br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         // output - socket.getOutputStream() => get socket object's outputStream.
         pw = new PrintWriter(socket.getOutputStream(), true);
         
      } catch (Exception e) {
         System.out.println("Erorr..>>>>>" + e);
      }
   }

   /**
    * 
    * @param str message
    * This method print input string(message). 
    */
   public void send(String str) {
      pw.println(str);
   }

   /**
    * This run() method is executed when the instance of GameServerThread call start() method.
    */
   public void run() {
      try {

         // Wait indefinitely and continuously forward the entered message to each client.
         while ((str = (String) br.readLine()) != null) {
            // call broadCast of playServer.
            playServer.broadCast(str, this);
         }
      } catch (Exception e) {
         // Call removeThread function to remove a thread in vector.
         playServer.removeThread(this); 
         
         System.out.println(socket.getInetAddress() + " connection finished!");
      }
   }

}