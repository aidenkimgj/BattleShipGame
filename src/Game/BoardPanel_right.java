package Game;

import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Listeners.GameStartListener;
import Listeners.GameStartListener;
import Listeners.HitAndMissController;

public class BoardPanel_right extends JPanel {

   
   private JButton[][] boatPositions = new JButton[10][10]; 
   private Color colorBlue = Color.BLUE;
   public static int[][] enemyPosition = GameStartListener.enemyField;
   
   public BoardPanel_right() {
            
      this.setLayout(new GridLayout(10, 10));
      

      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 10; j++) {
            boatPositions[i][j] = new JButton();

            boatPositions[i][j].setBackground(colorBlue);

            this.add(boatPositions[i][j]);
            boatPositions[i][j].addActionListener(new HitAndMissController(i, j));
         }
      }
   }
}