package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BoardPanel_left extends JPanel {

   
   public static JButton[][] boatPositions = new JButton[10][10]; 
   private Color colorBlue = Color.BLUE;   
   public static int[][] updatePosition = new int[10][10];
   
   public BoardPanel_left() {
	 
      this.setLayout(new GridLayout(10, 10));
      
      // Create event handlers.
  
      
      // Create and add board components.
      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 10; j++) {
            boatPositions[i][j] = new JButton();

            boatPositions[i][j].setBackground(colorBlue);

            this.add(boatPositions[i][j]);
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