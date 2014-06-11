package main;

import java.awt.EventQueue;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Color;

public class ButtonColorChooser extends JDialog {
	private JTextField buttonX;
	private JTextField buttonY;

	/**
	 * Launch the application.
	 */
	public static void launch() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ButtonColorChooser dialog = new ButtonColorChooser();
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
	public ButtonColorChooser() {
		setBounds(100, 100, 472, 464);
		getContentPane().setLayout(null);
		
		
		final JColorChooser colorChooser = new JColorChooser();
		colorChooser.setBounds(6, 6, 460, 362);
		getContentPane().add(colorChooser);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(370, 407, 96, 29);
		getContentPane().add(btnCancel);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setForeground(new Color(0, 0, 0));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.assignColorToButton(colorChooser.getColor(), Integer.parseInt(buttonX.getText()), Integer.parseInt(buttonY.getText()));
			}
		});
		btnOk.setBounds(278, 407, 85, 29);
		getContentPane().add(btnOk);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setBounds(6, 370, 69, 16);
		getContentPane().add(lblLocation);
		
		buttonX = new JTextField();
		buttonX.setBounds(72, 366, 24, 28);
		getContentPane().add(buttonX);
		buttonX.setColumns(10);
		
		buttonY = new JTextField();
		buttonY.setColumns(10);
		buttonY.setBounds(104, 366, 24, 28);
		getContentPane().add(buttonY);
		
		JLabel label = new JLabel(", ");
		label.setBounds(97, 372, 12, 16);
		getContentPane().add(label);
		
		JLabel lblThisWontWork = new JLabel("This won't work on OSX");
		lblThisWontWork.setBounds(208, 370, 182, 16);
		getContentPane().add(lblThisWontWork);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblLocation, label}));

	}
}
