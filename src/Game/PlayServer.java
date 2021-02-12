package Game;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

/**
 * 
 * @author Hong, Kim, and Sung.
 * @version Feb 11, 2021
 */

public class PlayServer {
   /**
    * field - ss, s, v, gameServerThread, firstData.
    */
   // ServerSocket class to connect client and server.
   ServerSocket ss;
   // socket to save Socket class which comes from client.
   Socket s;
   // vector class to save all clients.
   Vector v;
   // Thread class to create unique client.
   GameServerThread gameServerThread;
   // ArrayList to save all field value which comes from client.
   ArrayList<String> firstData = new ArrayList<String>();

   int count = 0;
   /**
    * This is PlayServer class constructor.
    */
   public PlayServer() {
      v = new Vector();
      try {
         // create new serverSocket class to connect port number 9999.
         ss = new ServerSocket(9999);
         System.out.println("Game server is running...");
         // infinity loop, wait for the client connection.
         while (true) {
            // when client connect to this server, accept method would catch that request.
            s = ss.accept();
            // create new GameServerTherad with two parameters.
            gameServerThread = new GameServerThread(this, s);
            // call addThread to add client info.
            this.addThread(gameServerThread);
            // Thread runs => run() => broadCast() => send() real-time call method.
            gameServerThread.start();
         }

      } catch (Exception e) {
         // print fail message.
         System.out.println("Fail to connect..>>>" + e);
      }
   }

   /**
    * 
    * @param st
    * save client thread into vector.
    */
   public void addThread(GameServerThread st) {
      v.add(st);
   }

   /**
    * 
    * @param st
    * remove client thread in vector.
    */
   public void removeThread(GameServerThread st) {
      v.remove(st);
   }


   /**
    * 
    * @param str
    * @param gameServerThread2
    *  This method send data to clients.
    */
   public void broadCast(String str, GameServerThread gameServerThread2) {
      // runs when there are 3 client threads, then save all string data into firstData(ArrayList).
      if (v.size() == 3) {
         firstData.add(str);
         // runs when there are 4 client threads.
      } else if (v.size() == 4) {
         // share each panel's field here.
         if (str.length() < 3) {
            // save each panel's identity into st_01 and st_00.
            // st_01 - the GamePanel which is the second opened.
            // st_00 - the GamePanel which is the first opened .
            GameServerThread st_01 = (GameServerThread) v.elementAt(3);
            GameServerThread st_00 = (GameServerThread) v.elementAt(2);
            // send the first opened GamePanel's field values to second opened GamePanel.
            st_01.send(firstData.get(count));
            count++;
            // send second opened GamePanel's field value to the first opened GamePanel.
            st_00.send(str);

            // runs when the user click the button on the panel to attack.
         } else {
            // save each panel's identity into st_01_01 and st_00_01.
            // st_00_01 - the right BoardPanel.
            // st_01_01 - the left BoardPanel.
            GameServerThread st_01_01 = (GameServerThread) v.elementAt(1);
            GameServerThread st_00_01 = (GameServerThread) v.elementAt(0);
            // determines between left and right panels.
            if (gameServerThread2 == v.elementAt(0)) {
               // send row and column to left BoardPanel.
               st_01_01.send(str);
            } else if (gameServerThread2 == v.elementAt(1)) {
               // send row and column to right BoardPanel.
               st_00_01.send(str);
            }
         }

      }

   }

   public static void main(String[] args) {
      new PlayServer();
   }

}