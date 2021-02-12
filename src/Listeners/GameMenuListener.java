package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Game.ButtonPressed;

/**
 * This class is GameMenuListener in order to set vertical and horizontal
 * 
 * @author Hong, Kim and Sung
 * @version Feb 11, 2021
 *
 */
public class GameMenuListener implements ActionListener {
/**
 * attribute
 */
	private boolean vertical;
	private boolean horizontal;
	private JButton verticalBtn;
	private JButton horizontalBtn;
/**
 * GameMenuListener constructor
 * @param vertical boolean type 
 * @param horizontal boolean type
 * @param verticalBtn jbutton
 * @param horizontalBtn jbutton
 */
	public GameMenuListener(boolean vertical, boolean horizontal, JButton verticalBtn, JButton horizontalBtn) {
		this.vertical = vertical;
		this.horizontal = horizontal;
		this.verticalBtn = verticalBtn;
		this.horizontalBtn = horizontalBtn;
	}
	
/**
 * actionPerformed method for use vertical and horizontal button
 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if (vertical) {
			btn.setEnabled(false);
			new ButtonPressed (vertical, horizontal);
			horizontalBtn.setEnabled(true);
		} else if (horizontal) {
			btn.setEnabled(false);
			verticalBtn.setEnabled(true);
			new ButtonPressed (vertical, horizontal);
		}
	}
}
