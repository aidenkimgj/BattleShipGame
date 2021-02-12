package Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import Listeners.GameMenuListener;

public class ButtonPressed implements ActionListener {

	private int[][] field;
	int rows;
	int columns;
	static Boolean vertical = true;
	static Boolean horizontal = false;
	private static int boatSize;

	public ButtonPressed(int boatSize, JRadioButton btn) {
		this.boatSize = boatSize;
		btn.setEnabled(false);
	}

	public ButtonPressed(int rows, int columns, BoardPanel_left board) {
		this.rows = rows;
		this.columns = columns;
		this.field = board.updatePosition;
	}

	public ButtonPressed(boolean vertical, boolean horizontal) {

		this.vertical = vertical;
		this.horizontal = horizontal;
	}

	public void actionPerformed(ActionEvent e) {
//      Object source = e.getSource();
		JButton buttonclicked = (JButton) e.getSource();

		for (int row = 0; row < 10; row++) {
			for (int column = 0; column < 10; column++) {
				if (field[row][column] == 0) {
//               buttonclicked.setBackground(Color.RED);
					setBoatPosition(e);
				}
			}
		}

	}

	private void setBoatPosition(ActionEvent e) {

		JButton buttonclicked = (JButton) e.getSource();
		int clickedBoatSize = this.boatSize;
		if (clickedBoatSize == 2 && freeSpot() && validSpot(clickedBoatSize)) {
			field[rows][columns] = 1;

			if (vertical) {
				field[rows + 1][columns] = 1;
				BoardPanel_left.boatPositions[rows + 1][columns].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows + 1][columns].setEnabled(false);
			} else if (horizontal) {
				field[rows][columns + 1] = 1;
				BoardPanel_left.boatPositions[rows][columns + 1].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows][columns + 1].setEnabled(false);
			}

			blockSurroundSpace();

			BoardPanel_left.updatePosition = field;
			buttonclicked.setBackground(Color.GRAY);
			buttonclicked.setEnabled(false);
			// BoardPanel_left.boatPositions[rows + 1][columns].setBackground(Color.RED);

			boatSize = 0;

		} else if (clickedBoatSize == 3 && freeSpot() && validSpot(clickedBoatSize)) {

			field[rows][columns] = 1;
			if (vertical) {

				field[rows + 1][columns] = 1;
				field[rows + 2][columns] = 1;
				BoardPanel_left.boatPositions[rows + 1][columns].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows + 2][columns].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows + 1][columns].setEnabled(false);
				BoardPanel_left.boatPositions[rows + 2][columns].setEnabled(false);

			} else if (horizontal) {
				field[rows][columns + 1] = 1;
				field[rows][columns + 2] = 1;
				BoardPanel_left.boatPositions[rows][columns + 1].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows][columns + 2].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows][columns + 1].setEnabled(false);
				BoardPanel_left.boatPositions[rows][columns + 2].setEnabled(false);
			}

			blockSurroundSpace();
			BoardPanel_left.updatePosition = field;
			buttonclicked.setBackground(Color.GRAY);
			buttonclicked.setEnabled(false);

			boatSize = 0;

		} else if (clickedBoatSize == 4 && freeSpot() && validSpot(clickedBoatSize)) {

			field[rows][columns] = 1;
			if (vertical) {

				field[rows + 1][columns] = 1;
				field[rows + 2][columns] = 1;
				field[rows + 3][columns] = 1;
				BoardPanel_left.boatPositions[rows + 1][columns].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows + 2][columns].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows + 3][columns].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows + 1][columns].setEnabled(false);
				BoardPanel_left.boatPositions[rows + 2][columns].setEnabled(false);
				BoardPanel_left.boatPositions[rows + 3][columns].setEnabled(false);

			} else if (horizontal) {
				field[rows][columns + 1] = 1;
				field[rows][columns + 2] = 1;
				field[rows][columns + 3] = 1;

				BoardPanel_left.boatPositions[rows][columns + 1].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows][columns + 2].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows][columns + 3].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows][columns + 1].setEnabled(false);
				BoardPanel_left.boatPositions[rows][columns + 2].setEnabled(false);
				BoardPanel_left.boatPositions[rows][columns + 3].setEnabled(false);
			}

			blockSurroundSpace();
			BoardPanel_left.updatePosition = field;
			buttonclicked.setBackground(Color.GRAY);
			buttonclicked.setEnabled(false);

			boatSize = 0;
		} else if (clickedBoatSize == 5 && freeSpot() && validSpot(clickedBoatSize)) {

			field[rows][columns] = 1;

			if (vertical) {
				field[rows + 1][columns] = 1;
				field[rows + 2][columns] = 1;
				field[rows + 3][columns] = 1;
				field[rows + 4][columns] = 1;

				BoardPanel_left.boatPositions[rows + 1][columns].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows + 2][columns].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows + 3][columns].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows + 4][columns].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows + 1][columns].setEnabled(false);
				BoardPanel_left.boatPositions[rows + 2][columns].setEnabled(false);
				BoardPanel_left.boatPositions[rows + 3][columns].setEnabled(false);
				BoardPanel_left.boatPositions[rows + 4][columns].setEnabled(false);
			} else if (horizontal) {
				field[rows][columns + 1] = 1;
				field[rows][columns + 2] = 1;
				field[rows][columns + 3] = 1;
				field[rows][columns + 4] = 1;

				BoardPanel_left.boatPositions[rows][columns + 1].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows][columns + 2].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows][columns + 3].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows][columns + 4].setBackground(Color.GRAY);
				BoardPanel_left.boatPositions[rows][columns + 1].setEnabled(false);
				BoardPanel_left.boatPositions[rows][columns + 2].setEnabled(false);
				BoardPanel_left.boatPositions[rows][columns + 3].setEnabled(false);
				BoardPanel_left.boatPositions[rows][columns + 4].setEnabled(false);
			}

			blockSurroundSpace();
			BoardPanel_left.updatePosition = field;
			buttonclicked.setBackground(Color.GRAY);
			buttonclicked.setEnabled(false);

			boatSize = 0;
		}
	}

	public void blockSurroundSpace() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j] == 1) {
					try {
						if (field[i - 1][j - 1] != 1) {
							field[i - 1][j - 1] = -1;
						}
					} catch (ArrayIndexOutOfBoundsException e) {

					}
					try {
						if (field[i - 1][j + 1] != 1) {
							field[i - 1][j + 1] = -1;
						}
					} catch (ArrayIndexOutOfBoundsException e) {

					}
					try {
						if (field[i - 1][j] != 1) {
							field[i - 1][j] = -1;
						}
					} catch (ArrayIndexOutOfBoundsException e) {

					}
					try {
						if (field[i + 1][j] != 1) {
							field[i + 1][j] = -1;
						}
					} catch (ArrayIndexOutOfBoundsException e) {

					}
					try {
						if (field[i + 1][j - 1] != 1) {
							field[i + 1][j - 1] = -1;
						}
					} catch (ArrayIndexOutOfBoundsException e) {

					}
					try {
						if (field[i + 1][j + 1] != 1) {
							field[i + 1][j + 1] = -1;
						}
					} catch (ArrayIndexOutOfBoundsException e) {

					}
					try {
						if (field[i][j - 1] != 1) {
							field[i][j - 1] = -1;
						}
					} catch (ArrayIndexOutOfBoundsException e) {

					}
					try {
						if (field[i][j + 1] != 1) {
							field[i][j + 1] = -1;
						}
					} catch (ArrayIndexOutOfBoundsException e) {

					}
				}
			}
		}
	}

	private Boolean freeSpot() {
		if (field[rows][columns] == 0) {
			return true;
		}
		return false;
	}

	private Boolean validSpot(int boatNumber) {
		try {
			if (vertical) {
				for (int i = 1; i < boatNumber; i++) {
					if (field[rows + i][columns] != 0) {
						return false;
					}
				}
			} else if (horizontal) {
				for (int i = 1; i < boatNumber; i++) {
					if (field[rows][columns + i] != 0) {
						return false;
					}
				}
			}
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

}