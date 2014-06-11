package main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
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

import javax.swing.JLabel;

public class MainWindow {

	JFrame frame;
	static JButton[][] button;
	static JScrollPane scrollPane = new JScrollPane();
	static Font mainFont = new Font("Liberation Sans", Font.PLAIN, 11);
	static JPanel panel = new JPanel();
	static int rows = 10;
	static int cols = 10;
	static String[][] sound = new String[rows*2][cols*2];;
	static MainWindow window = new MainWindow();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
		
		JLabel lblReady = new JLabel("Ready");
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
			}
		});
		btnSaveButtonGrid.setFont(mainFont);
		toolBar.add(btnSaveButtonGrid);
		
		JButton btnAddremoveButtons = new JButton("Add/remove buttons");
		btnAddremoveButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRemoveButtons.launch(rows, cols);
			}
		});
		btnAddremoveButtons.setFont(mainFont);
		toolBar.add(btnAddremoveButtons);
		
		JButton btnChangeColor = new JButton("Change color");
		btnChangeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ButtonColorChooser.launch();
			}
		});
		toolBar.add(btnChangeColor);
		
		scrollPane.setViewportView(panel);
		
		//loadButtons(rows, cols);
		
		frame.getContentPane().setLayout(groupLayout);
	}
	static void loadButtons(int colsOfButtons, int rowsOfButtons) {
		panel.removeAll();
		rows=rowsOfButtons;
		cols=colsOfButtons;
		colsOfButtons*=2;
		rowsOfButtons*=2;
		
		
		String[][] backup = new String[rowsOfButtons][colsOfButtons];
		int shorterLength;
		int shorterWidth;
		if (backup[0].length<sound[0].length) {
			shorterLength=backup[0].length;
		} else {
			shorterLength=sound[0].length;
		}
		if (backup.length<sound.length) {
			shorterWidth=backup.length;
		} else {
			shorterWidth=sound.length;
		}
		for (int c =0;c<shorterWidth;c++) {
			System.arraycopy(sound[c], 0, backup[c], 0, shorterLength);
		}
		sound = backup;
		
		
		button = new JButton[rowsOfButtons][colsOfButtons];
		ColumnSpec[] col = new ColumnSpec[colsOfButtons];
		for (int i=0; i<colsOfButtons; i+=2) {
			col[i]=FormFactory.RELATED_GAP_COLSPEC;
			col[i+1]=FormFactory.DEFAULT_COLSPEC;
		}
		RowSpec[] row = new RowSpec[rowsOfButtons];
		for (int i=0; i<rowsOfButtons; i+=2) {
			row[i]=FormFactory.RELATED_GAP_ROWSPEC;
			row[i+1]=RowSpec.decode("max(35dlu;default)");
		}
		panel.setLayout(new FormLayout(col, row));
		
		for (int c=2; c<=rowsOfButtons; c+=2) {
			for (int i=2; i<=colsOfButtons; i+=2) {
				final int colNum = i/2;
				final int rowNum = c/2;
				button[rowNum][colNum] = new JButton(rowNum + ", " + colNum + "  Unassigned");
				button[rowNum][colNum].setFont(mainFont);
				button[rowNum][colNum].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Sound sound=new Sound();
						sound.url=MainWindow.sound[rowNum][colNum];
						sound.run();
					}
				});
				panel.add(button[rowNum][colNum], i + "," + c + ", default, fill");
			}
		}
		panel.revalidate();
	}
	static void assignFileToButton(String file, int buttonX, int buttonY) {
		sound[buttonX][buttonY] = file;
	}
	static void assignNameToButton(String name, int buttonX, int buttonY) {
		button[buttonX][buttonY].setText(buttonX + ", " + buttonY + " " + name);
	}
	static void assignColorToButton(Color color, int buttonX, int buttonY) {
		button[buttonX][buttonY].setBackground(color);
	}
}
