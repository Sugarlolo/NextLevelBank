package nlb_core;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class TransferFrame{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_money;
	
	private String number="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferFrame window = new TransferFrame();
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
	public TransferFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 500, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC774\uCCB4\uD558\uAE30");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lblNewLabel.setBounds(30, 30, 80, 32);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setText("\uACC4\uC88C\uBC88\uD638");
		textField.setFont(new Font("±¼¸²", Font.PLAIN, 17));
		textField.setBounds(30, 82, 425, 46);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 290, 425, 394);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(4, 3, 10, 10));
		
		JButton button_1 = new JButton("1");
		panel.add(button_1);
		
		JButton button_2 = new JButton("2");
		panel.add(button_2);
		
		JButton button_3 = new JButton("3");
		panel.add(button_3);
		
		JButton button_4 = new JButton("4");
		panel.add(button_4);
		
		JButton button_5 = new JButton("5");
		panel.add(button_5);
		
		JButton button_6 = new JButton("6");
		panel.add(button_6);
		
		JButton button_7 = new JButton("7");
		panel.add(button_7);
		
		JButton button_8 = new JButton("8");
		panel.add(button_8);
		
		JButton button_9 = new JButton("9");
		panel.add(button_9);
		
		JButton button_10 = new JButton("00");
		panel.add(button_10);
		
		JButton button_11 = new JButton("0");
		panel.add(button_11);
		
		JButton button_12 = new JButton("\uC9C0\uC6B0\uAE30");
		panel.add(button_12);
		
		JButton btnNewButton = new JButton("\uB2E4\uC74C");
		btnNewButton.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		btnNewButton.setBounds(30, 709, 425, 32);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("+1\uB9CC");
		btnNewButton_1.setBounds(30, 242, 97, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("+5\uB9CC");
		btnNewButton_2.setBounds(139, 242, 97, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("+10\uB9CC");
		btnNewButton_3.setBounds(248, 242, 97, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("\uC804\uC561");
		btnNewButton_4.setBounds(358, 242, 97, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		textField_money = new JTextField();
		textField_money.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_money.setFont(new Font("±¼¸²", Font.PLAIN, 17));
		textField_money.setColumns(15);
		textField_money.setBounds(30, 154, 425, 46);
		frame.getContentPane().add(textField_money);
		
	}
}
