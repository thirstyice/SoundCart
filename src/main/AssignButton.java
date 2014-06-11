package main;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class AssignButton extends JDialog {
	JTextField filePath;
	JTextField buttonX;
	JTextField buttonY;
	private JTextField name;

	/**
	 * Launch the application.
	 */
	public static void launch() {
		try {
			AssignButton dialog = new AssignButton();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AssignButton() {
		setResizable(false);
		setBounds(100, 100, 450, 133);
		getContentPane().setLayout(null);
		
		filePath = new JTextField();
		filePath.setBounds(10, 23, 331, 20);
		filePath.setFont(MainWindow.mainFont);
		getContentPane().add(filePath);
		filePath.setColumns(10);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFont(MainWindow.mainFont);
				int status = fileChooser.showOpenDialog(null);
				if (status == JFileChooser.APPROVE_OPTION) {
					filePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
				} else if (status == JFileChooser.CANCEL_OPTION) {
					System.out.println("calceled");

				}
			}
		});
		btnBrowse.setBounds(347, 22, 89, 23);
		btnBrowse.setFont(MainWindow.mainFont);
		getContentPane().add(btnBrowse);

		JLabel lblSelectFile = new JLabel("Select file:");
		lblSelectFile.setBounds(10, 5, 69, 14);
		lblSelectFile.setFont(MainWindow.mainFont);
		getContentPane().add(lblSelectFile);

		JLabel lblButtonLocation = new JLabel("Select button location:");
		lblButtonLocation.setBounds(229, 52, 138, 14);
		lblButtonLocation.setFont(MainWindow.mainFont);
		getContentPane().add(lblButtonLocation);

		buttonX = new JTextField();
		buttonX.setBounds(357, 49, 26, 20);
		buttonX.setFont(MainWindow.mainFont);
		getContentPane().add(buttonX);
		buttonX.setColumns(10);

		JLabel label = new JLabel(",");
		label.setBounds(390, 52, 10, 14);
		label.setFont(MainWindow.mainFont);
		getContentPane().add(label);

		buttonY = new JTextField();
		buttonY.setBounds(406, 49, 26, 20);
		buttonY.setFont(MainWindow.mainFont);
		getContentPane().add(buttonY);
		buttonY.setColumns(10);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.assignFileToButton(filePath.getText(), Integer.parseInt(buttonX.getText()), Integer.parseInt(buttonY.getText()));
				MainWindow.assignNameToButton(name.getText(), Integer.parseInt(buttonX.getText()), Integer.parseInt(buttonY.getText()));
				dispose();
			}
		});
		btnOk.setBounds(291, 81, 50, 23);
		btnOk.setFont(MainWindow.mainFont);
		getContentPane().add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(367, 81, 69, 23);
		btnCancel.setFont(MainWindow.mainFont);
		getContentPane().add(btnCancel);
		
		JLabel lblSetName = new JLabel("Set name:");
		lblSetName.setFont(MainWindow.mainFont);
		lblSetName.setBounds(6, 50, 61, 16);
		getContentPane().add(lblSetName);
		
		name = new JTextField();
		name.setBounds(10, 77, 134, 28);
		getContentPane().add(name);
		name.setColumns(10);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), lblSelectFile, lblButtonLocation, label, lblSetName}));
	}
}
