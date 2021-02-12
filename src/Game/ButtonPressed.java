package Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JRadioButton;

/**
 * The ButtonPressed class is to implement for ActionListener.
 * 
 * @author Hong, Kim, and Sung.
 * @version Feb 11, 2021
 */
public class ButtonPressed implements ActionListener {

	// Fields.
	private int[][] field;
	int rows;
	int columns;
	static Boolean vertical = true;
	static Boolean horizontal = false;
	private static int boatSize;

	
	/**
	 * Constructor for selecting boat size.
	 * 
	 * @param boatSize boatsize
	 * @param btn raido button
	 */
	public ButtonPressed(int boatSize, JRadioButton btn) {
		this.boatSize = boatSize;
		btn.setEnabled(false);
	}

	
	/**
	 * Constructor for updating the position of the left panel.
	 * @param rows rows
	 * @param columns columns
	 * @param board board
	 */
	public ButtonPressed(int rows, int columns, BoardPanel_left board) {
		this.rows = rows;
		this.columns = columns;
		this.field = board.updatePosition;
	}

	
	/**
	 * Constructor for vertical and horizontal on the grid layout.
	 * @param vertical vertical	
	 * @param horizontal horizontal
	 */
	public ButtonPressed(boolean vertical, boolean horizontal) {
		this.vertical = vertical;
		this.horizontal = horizontal;
	}

	
	/**
	 * This method implements action event from boat position.
	 */
	public void actionPerformed(ActionEvent e) {
		JButton buttonclicked = (JButton) e.getSource();

		for (int row = 0; row < 10; row++) {
			for (int column = 0; column < 10; column++) {
				if (field[row][column] == 0) {
					setBoatPosition(e);
				}
			}
		}
	}

	
	/**
	 * This method is to set the boat position.
	 * @param e event
	 */
	private void setBoatPosition(ActionEvent e) {
		// Create JButton class from action event.
		JButton buttonclicked = (JButton) e.getSource();
		int clickedBoatSize = this.boatSize;
		
		// Check rows and columns values.
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
			
			// Set color on the board.
			BoardPanel_left.updatePosition = field;
			buttonclicked.setBackground(Color.GRAY);
			buttonclicked.setEnabled(false);

			// Initialize boat size.
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
			
			// Set color on the board.
			BoardPanel_left.updatePosition = field;
			buttonclicked.setBackground(Color.GRAY);
			buttonclicked.setEnabled(false);

			// Initialize boat size.
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

	
	/**
	 * This method is to give a margin for a battleship.
	 */
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

	
	/**
	 * This method checks free spot.
	 * @return boolean
	 */
	private Boolean freeSpot() {
		if (field[rows][columns] == 0) {
			return true;
		}
		return false;
	}

	
	/**
	 * The validSpot method checks valid spot.
	 * @param boatNumber boat size
	 * @return true and false
	 */
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