package main;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddRemoveButtons extends JDialog {
	private JTextField widthText;
	private JTextField heightText;
	static int oldRows;
	static int oldCols;
	/**
	 * Launch the application.
	 */
	public static void launch(int oldRowCount, int oldColCount) {
		oldRows=oldRowCount;
		oldCols=oldColCount;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRemoveButtons dialog = new AddRemoveButtons();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public AddRemoveButtons() {
		setBounds(100, 100, 406, 163);
		getContentPane().setLayout(null);
		
		JLabel lblOldGridSize = new JLabel("Old grid size:");
		lblOldGridSize.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOldGridSize.setBounds(10, 48, 68, 14);
		getContentPane().add(lblOldGridSize);
		
		JLabel lblNewGridSize = new JLabel("New grid size:");
		lblNewGridSize.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewGridSize.setBounds(138, 48, 68, 14);
		getContentPane().add(lblNewGridSize);
		
		JLabel lblCautionYouWill = new JLabel("Caution: you will lose all button assignments");
		lblCautionYouWill.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCautionYouWill.setBounds(10, 10, 370, 32);
		getContentPane().add(lblCautionYouWill);
		
		JLabel lblWide = new JLabel(oldCols+" Wide");
		lblWide.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblWide.setBounds(10, 73, 46, 14);
		getContentPane().add(lblWide);
		
		JLabel lblTall = new JLabel(oldRows+" Tall");
		lblTall.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTall.setBounds(10, 93, 46, 14);
		getContentPane().add(lblTall);
		
		JLabel label = new JLabel("Tall");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(227, 93, 46, 14);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Wide");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(227, 73, 46, 14);
		getContentPane().add(label_1);
		
		widthText = new JTextField();
		widthText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		widthText.setBounds(135, 70, 86, 20);
		getContentPane().add(widthText);
		widthText.setColumns(10);
		
		heightText = new JTextField();
		heightText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		heightText.setBounds(135, 90, 86, 20);
		getContentPane().add(heightText);
		heightText.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.loadButtons(Integer.parseInt(widthText.getText()), Integer.parseInt(heightText.getText()));
				dispose();
			}
		});
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOk.setBounds(291, 84, 89, 23);
		getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCancel.setBounds(291, 59, 89, 23);
		getContentPane().add(btnCancel);

	}
}
