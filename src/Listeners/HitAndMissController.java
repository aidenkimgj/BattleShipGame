package Listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Game.BoardPanel_left;
import Game.BoardPanel_right;
import Game.GamePanel;

/**
 * This class decide control hit and miss
 * 
 * @author Hong, Kim and Sung
 * @version Feb 11, 2021
 */
public class HitAndMissController implements ActionListener, Runnable {
	/**
	 * attributes
	 */
	private int row;
	private int column;
	int enemyClickedRow = 0;
	int enemyClickedColumn = 0;
	int[][] enemyField;
	URL imgUrl = this.getClass().getResource("./miss.png");
	URL imgUr2 = this.getClass().getResource("./bomb.png");
	ImageIcon imgIcon1 = new ImageIcon(imgUrl);
	ImageIcon imgIcon2 = new ImageIcon(imgUr2);
	private BufferedReader br;
	private PrintWriter pw;
	public static String str;

	/**
	 * Constructor set field row, column and enemyField
	 * 
	 * @param row row
	 * @param column column
	 */
	public HitAndMissController(int row, int column) {
		this.row = row;
		this.column = column;
		this.enemyField = BoardPanel_right.enemyPosition;

	}

	/**
	 * Overrides actionPerformed method for attack enemy field
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// clicked button
		JButton buttonAttack = (JButton) e.getSource();
		// ship positioned
		if (enemyField[row][column] == 1) {
			buttonAttack.setIcon(imgIcon2);
		// nothing on the field	
		} else {
			buttonAttack.setIcon(imgIcon1);
		}
 
		Thread tr = new Thread(this);

		tr.start();
	}
/**
 * Thread execute method
 */
	@Override
	public void run() {

		try {
			// data Stream
			br = new BufferedReader(new InputStreamReader(GamePanel.AttackSocket.getInputStream()));
			pw = new PrintWriter(GamePanel.AttackSocket.getOutputStream(), true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// make a String type variable in order to send to Game Sever
		String rowColumn = Integer.toString(row) + " " + column;

		try {
			// Send message to Sever
			pw.println(rowColumn);
			while ((str = (String) br.readLine()) != null) {
				// Accept message from Sever
				enemyClickedRow = Integer.parseInt(str.split(" ")[0]);
				enemyClickedColumn = Integer.parseInt(str.split(" ")[1]);
				
				// Attack enemy ship
				if (BoardPanel_left.updatePosition[enemyClickedRow][enemyClickedColumn] == 1) {
					BoardPanel_left.boatPositions[enemyClickedRow][enemyClickedColumn].setIcon(imgIcon2);
				// Miss enemy ship
				} else {
					
					BoardPanel_left.boatPositions[enemyClickedRow][enemyClickedColumn].setIcon(imgIcon1);
				}

			}

		} catch (IOException e) {

		}
	}

}
