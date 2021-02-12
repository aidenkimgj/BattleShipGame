package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Listeners.GameStartListener;
import Listeners.GameStartListener;
import Listeners.HitAndMissController;

/**
 * 
 * @author Hong, Kim, and Sung.
 * @version Feb 11, 2021
 */

public class BoardPanel_right extends JPanel {

   /**
    * Two private instances and one public instance fields.
    */
   private JButton[][] boatPositions = new JButton[10][10];
   private Color colorBlue = Color.BLUE;
   public static int[][] enemyPosition = GameStartListener.enemyField;

   /**
    * This is BoardPanel_right class constructor
    */
   public BoardPanel_right() {
      // set grid layout with 10 by 10
      this.setLayout(new GridLayout(10, 10));

      /**
       * Create field using 100 buttons on the right side
       */
      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 10; j++) {
            // create a new button.
            boatPositions[i][j] = new JButton();
            // set button color.
            boatPositions[i][j].setBackground(colorBlue);
            // add button on the Game panel.
            this.add(boatPositions[i][j]);
            // when click button, action listener will be called. This function is to
            // attack.
            boatPositions[i][j].addActionListener(new HitAndMissController(i, j));
         }
      }
   }
}