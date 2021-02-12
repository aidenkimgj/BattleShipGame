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

public class HitAndMissController implements ActionListener, Runnable {

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

	public HitAndMissController(int row, int column) {
		this.row = row;
		this.column = column;
		this.enemyField = BoardPanel_right.enemyPosition;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonAttack = (JButton) e.getSource();
		if (enemyField[row][column] == 1) {
			buttonAttack.setIcon(imgIcon2);

	

		} else {
			buttonAttack.setIcon(imgIcon1);
		}
		
		
		Thread tr = new Thread(this);

		tr.start();
	}

	@Override
	public void run() {
		
		try {
			
			br = new BufferedReader(new InputStreamReader(GamePanel.AttackSocket.getInputStream()));
			pw = new PrintWriter(GamePanel.AttackSocket.getOutputStream(), true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
		
		String rowColumn = Integer.toString(row) + " " + column;

		try {
			System.out.println("공격하라!!"+rowColumn);
			pw.println(rowColumn);
			while ((str = (String) br.readLine()) != null) {
				System.out.println("맞았다: " + str);
				enemyClickedRow = Integer.parseInt(str.split(" ")[0]);
				enemyClickedColumn = Integer.parseInt(str.split(" ")[1]);
				System.out.println(enemyClickedRow+ ", "+ enemyClickedColumn);
				
					if(	BoardPanel_left.updatePosition[enemyClickedRow][enemyClickedColumn] == 1) {
						BoardPanel_left.boatPositions[enemyClickedRow][enemyClickedColumn].setIcon(imgIcon2);
					}else {
						BoardPanel_left.boatPositions[enemyClickedRow][enemyClickedColumn].setIcon(imgIcon1);
					}
				
			}
			
		} catch (IOException e) {

		}
	}

}
