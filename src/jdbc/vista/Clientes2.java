package jdbc.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Clientes2 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes2 frame = new Clientes2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Clientes2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 274, 209);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		scrollPane.setColumnHeaderView(table);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(329, 57, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(329, 134, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(444, 60, 265, 250);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 23, 46, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(85, 20, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(10, 70, 46, 14);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(85, 67, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(234, 345, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(339, 345, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(492, 345, 89, 23);
		contentPane.add(btnNewButton_4);
	}
}
