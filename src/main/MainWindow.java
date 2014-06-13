package main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JLabel;

public class MainWindow implements ActionListener{

	JFrame frame;
	static JButton[][] button;
	static JScrollPane scrollPane = new JScrollPane();
	static Font mainFont = new Font("Liberation Sans", Font.PLAIN, 11);
	static JPanel panel = new JPanel();
	static MainWindow window = new MainWindow();
	static JLabel lblReady;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 551, 283);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		lblReady = new JLabel("Ready");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
						.addComponent(toolBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
						.addComponent(lblReady, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
					.addGap(3)
					.addComponent(lblReady, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(5))
		);
		
		JButton btnAssignButton = new JButton("Assign button");
		btnAssignButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AssignButton.launch();
			}
		});
		btnAssignButton.setFont(mainFont);
		toolBar.add(btnAssignButton);
		
		JButton btnSaveButtonGrid = new JButton("Save button grid");
		btnSaveButtonGrid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFont(MainWindow.mainFont);
				int status = fileChooser.showSaveDialog(MainWindow.window.frame);
				if (status == JFileChooser.APPROVE_OPTION) {
					saveButtonsToFile(fileChooser.getSelectedFile().getAbsolutePath());
				} else if (status == JFileChooser.CANCEL_OPTION) {
					System.out.println("canceled");

				}
			}
		});
		btnSaveButtonGrid.setFont(mainFont);
		toolBar.add(btnSaveButtonGrid);
		
		JButton btnRestoreButtonGrid = new JButton("Restore button grid");
		btnRestoreButtonGrid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblReady.setText("Restoring");
				Object[] options = new Object[] {"Yes, continue", "Cancel"};
				int value = JOptionPane.showOptionDialog(MainWindow.window.frame,
						"Restoring from file will OVERWRITE existing button layout \n"
						+ "Are you sure you want to continue?",
						"Warning",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE,
						null,
						options,
						options[0]
					);
				if (value == JOptionPane.YES_OPTION) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFont(MainWindow.mainFont);
					int status = fileChooser.showOpenDialog(MainWindow.window.frame);
					if (status == JFileChooser.APPROVE_OPTION) {
						restoreButtonsFromFile(fileChooser.getSelectedFile().getAbsolutePath());
					} else if (status == JFileChooser.CANCEL_OPTION) {
						System.out.println("canceled");
						lblReady.setText("Restore Canceled");

					}
				} else if (value == JOptionPane.NO_OPTION) {
					lblReady.setText("Ready");
				}
			}
		});
		btnRestoreButtonGrid.setFont(mainFont);
		toolBar.add(btnRestoreButtonGrid);
		
		JButton btnAddremoveButtons = new JButton("Add/remove buttons");
		btnAddremoveButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRemoveButtons.launch(button.length, button[0].length);
			}
		});
		btnAddremoveButtons.setFont(mainFont);
		toolBar.add(btnAddremoveButtons);
		
		JButton btnChangeColor = new JButton("Change color");
		btnChangeColor.setFont(mainFont);
		btnChangeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ButtonColorChooser.launch();
			}
		});
		toolBar.add(btnChangeColor);
		
		scrollPane.setViewportView(panel);
		
		makeNewButtons(10,10);
		
		frame.getContentPane().setLayout(groupLayout);
	}
	void loadButtons() {
		lblReady.setText("Loading");
		int colsOfButtons = button.length;
		int rowsOfButtons = button[0].length;
		panel.removeAll();
		colsOfButtons*=2;
		rowsOfButtons*=2;
		
		ColumnSpec[] col = new ColumnSpec[colsOfButtons];
		for (int i=0; i<colsOfButtons; i+=2) {
			col[i]=FormFactory.DEFAULT_COLSPEC;
			col[i+1]=FormFactory.RELATED_GAP_COLSPEC;
		}
		RowSpec[] row = new RowSpec[rowsOfButtons];
		for (int i=0; i<rowsOfButtons; i+=2) {
			row[i]=RowSpec.decode("max(35dlu;default)");
			row[i+1]=FormFactory.RELATED_GAP_ROWSPEC;
		}
		panel.setLayout(new FormLayout(col, row));
		
		for (int c=0; c<rowsOfButtons; c+=2) {
			for (int i=0; i<colsOfButtons; i+=2) {
				panel.add(button[i/2][c/2], (i+1) + "," + (c+1) + ", default, fill");
				button[i/2][c/2].addActionListener(MainWindow.window);
			}
		}
		panel.revalidate();
		panel.repaint(); // Just in case
		lblReady.setText("Ready");
	}
	void makeNewButtons(int rowsOfButtons, int colsOfButtons) {
		button= new JButton[colsOfButtons][rowsOfButtons];
		for (int rowNum=0; rowNum<rowsOfButtons; rowNum++) {
			for (int colNum=0; colNum<colsOfButtons; colNum++) {
				button[rowNum][colNum] = new JButton(rowNum + ", " + colNum + "  Unassigned");
				button[rowNum][colNum].setFont(mainFont.deriveFont(Font.BOLD));
				button[rowNum][colNum].setActionCommand("unassigned");
			}
		}
		loadButtons();
	}
	static void assignFileToButton(final String file, int buttonX, int buttonY) {
		button[buttonX][buttonY].setActionCommand(file);
	}
	static void assignNameToButton(String name, int buttonX, int buttonY) {
		button[buttonX][buttonY].setText(buttonX + ", " + buttonY + " " + name);
	}
	static void assignColorToButton(Color color, int buttonX, int buttonY) {
		button[buttonX][buttonY].setBackground(color);
	}
	static void saveButtonsToFile(String file) {
		lblReady.setText("Saving");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(button);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		lblReady.setText("Ready");
	}
	static void restoreButtonsFromFile(String file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream iis = new ObjectInputStream(fis);
			button = (JButton[][]) iis.readObject();
			iis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		window.loadButtons();
	}
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		if (action.equals("unassigned")) {
			JOptionPane.showMessageDialog(MainWindow.window.frame,
				    "That button is not assigned",
				    "Unassigned button",
				    JOptionPane.WARNING_MESSAGE);
		} else {
			Sound.playSound(action);
		}
	}

}
