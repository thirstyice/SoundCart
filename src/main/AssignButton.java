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
import java.io.File;

public class AssignButton extends JDialog {

	final JPanel contentPanel = new JPanel();
	JTextField filePath;
	JTextField buttonX;
	JTextField buttonY;

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
		setBounds(100, 100, 450, 113);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		filePath = new JTextField();
		filePath.setBounds(10, 23, 331, 20);
		contentPanel.add(filePath);
		filePath.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int status = fileChooser.showOpenDialog(null);
				if (status == JFileChooser.APPROVE_OPTION) {
					filePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
				} else if (status == JFileChooser.CANCEL_OPTION) {
					System.out.println("calceled");

				}
			}
		});
		btnBrowse.setBounds(347, 22, 89, 23);
		contentPanel.add(btnBrowse);
		
		JLabel lblSelectFile = new JLabel("Select file:");
		lblSelectFile.setBounds(10, 5, 69, 14);
		contentPanel.add(lblSelectFile);

		JLabel lblButtonLocation = new JLabel("Select button location:");
		lblButtonLocation.setBounds(10, 49, 116, 14);
		contentPanel.add(lblButtonLocation);
		
		buttonX = new JTextField();
		buttonX.setBounds(124, 49, 26, 20);
		contentPanel.add(buttonX);
		buttonX.setColumns(10);
		
		JLabel label = new JLabel(",");
		label.setBounds(157, 52, 10, 14);
		contentPanel.add(label);
		
		buttonY = new JTextField();
		buttonY.setBounds(173, 49, 26, 20);
		contentPanel.add(buttonY);
		buttonY.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.assignFileToButton(filePath.getText(), Integer.parseInt(buttonX.getText()), Integer.parseInt(buttonY.getText()));
				dispose();
			}
		});
		btnOk.setBounds(237, 49, 50, 23);
		contentPanel.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(293, 49, 69, 23);
		contentPanel.add(btnCancel);
	}
}
