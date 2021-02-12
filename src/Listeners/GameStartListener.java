package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.net.Socket;

import java.util.Arrays;

import Game.BoardPanel_left;

import Game.GamePanel;

/**
 * 
 * @author Hong, Kim and Sung
 * @version Feb 11, 2021
 *
 */
public class GameStartListener implements Runnable, ActionListener {
	int[][] field;
	GamePanel gamePanel;
	private BufferedReader br;
	private PrintWriter pw;
	private String str;
	public static int[][] enemyField = new int[10][10];
	public Socket socket;
//	private Socket socket;

	public GameStartListener(GamePanel gamePanel) {
//		this.socket = GamePanel.socket;
		this.field = BoardPanel_left.updatePosition;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			socket = new Socket("localhost", 9999);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(), true);

			Thread tr = new Thread(this);

			tr.start();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field.length; j++) {
					pw.println(Integer.toString(field[i][j]));
				}
			}

			int i = 0;
			int j = 0;
			int x = 0;
			int y = 0;
			int count = 0;
			// ë³´ë“œ ëˆŒëŸ¬ë�„ ê³„ì†� ì—¬ê¸°ì„œ ì�½ì�Œ.........................
			while ((str = (String) br.readLine()) != null) {
				enemyField[i][j] = Integer.parseInt(str);
				j++;
				if (j == 10) {
					j = 0;
					i++;
				}

			}
		} catch (IOException e) {

		}
	}
}
