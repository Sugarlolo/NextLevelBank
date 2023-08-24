package nlb_core;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TransferFrame2 extends JFrame implements ActionListener{
	final String[] buttonLabels = new String[] {
			"1", "2","3","4","5","6","7","8","9","00","0","Áö¿ì±â",
	};
	
	JFrame frame;
	JTextField textField_account;
	JTextField textField_money;
	String number="";
	JButton buttons[];
	public TransferFrame2() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 500, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC774\uCCB4\uD558\uAE30");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lblNewLabel.setBounds(30, 30, 80, 32);
		frame.getContentPane().add(lblNewLabel);
		
		textField_account = new JTextField();
		textField_account.setText("\uACC4\uC88C\uBC88\uD638");
		textField_account.setFont(new Font("±¼¸²", Font.PLAIN, 17));
		textField_account.setBounds(30, 82, 425, 46);
		frame.getContentPane().add(textField_account);
		textField_account.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 290, 425, 394);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(4, 3, 10, 10));
		
		buttons = new JButton[12];
		
		for (int i =0 ; i<12; i++) {
			JButton button = new JButton(buttonLabels[i]);
			button.addActionListener(this);
			panel.add(buttons[i] = button);
		}

		
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
		
		validate();
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
//		System.out.println(e.getActionCommand());
		for (int i = 0; i<buttons.length; i++) {
			if (buttons[i]==obj) {
				String btnText = buttons[i].getText();
				
				if (!(btnText.equals("Áö¿ì±â"))){
					number+=btnText;
					textField_money.setText(number);
				} else {
					number="";
					textField_money.setText(number);
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		new TransferFrame2();
	}
}
