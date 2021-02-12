package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * @author Hong, Kim, and Sung.
 * @version Feb 11, 2021
 */

public class BoardPanel_left extends JPanel {

   /**
    * Two private instances and one public instance fields.
    */
   public static JButton[][] boatPositions = new JButton[10][10];
   private Color colorBlue = Color.BLUE;
   public static int[][] updatePosition = new int[10][10];

   /**
    * This is BoardPanel_left class constructor
    */
   public BoardPanel_left() {
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
            boatPositions[i][j].addActionListener(new ButtonPressed(i, j, this));
         }
      }
      resetGame(updatePosition);
   }

   public void resetGame(int[][] updateBoats) {
      for (int rows = 0; rows < 10; rows++) {
         for (int columns = 0; columns < 10; columns++) {
            try {
               updateBoats[rows][columns] = 0;
            } catch (ArrayIndexOutOfBoundsException e) {

            }
         }
      }
   }

}