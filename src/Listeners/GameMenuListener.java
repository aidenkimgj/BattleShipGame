package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Game.ButtonPressed;

public class GameMenuListener implements ActionListener {

	public boolean vertical;
	public boolean horizontal;
	JButton verticalBtn;
	JButton horizontalBtn;

	public GameMenuListener(boolean vertical, boolean horizontal, JButton verticalBtn, JButton horizontalBtn) {
		this.vertical = vertical;
		this.horizontal = horizontal;
		this.verticalBtn = verticalBtn;
		this.horizontalBtn = horizontalBtn;
	}
	

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
