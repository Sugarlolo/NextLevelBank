package nlb_core;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JPasswordField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import beans.MemberBean;

public class PayPwFrame {

	private JFrame frame;
	private JPasswordField passwordField;
	private String key;
	public MemberBean mBean;
	private int count=3;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PayPwFrame window = new PayPwFrame());
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public PayPwFrame(MemberBean bean) {
		this.mBean = bean;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 162, 0, 18, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 60, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel textLabel = new JLabel("\uBE44\uBC00\uBC88\uD638\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694.");
		GridBagConstraints gbc_textLabel = new GridBagConstraints();
		gbc_textLabel.insets = new Insets(0, 0, 5, 5);
		gbc_textLabel.gridx = 2;
		gbc_textLabel.gridy = 1;
		frame.getContentPane().add(textLabel, gbc_textLabel);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(6);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 2;
		frame.getContentPane().add(passwordField, gbc_passwordField);
		
		JLabel remainLabel = new JLabel("3\uD68C \uB0A8\uC558\uC2B5\uB2C8\uB2E4.");
		GridBagConstraints gbc_remainLabel = new GridBagConstraints();
		gbc_remainLabel.insets = new Insets(0, 0, 5, 5);
		gbc_remainLabel.gridx = 2;
		gbc_remainLabel.gridy = 3;
		frame.getContentPane().add(remainLabel, gbc_remainLabel);
		
		JButton okBtn = new JButton("\uD655\uC778");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] password = passwordField.getPassword();
				String passwordStr = new String(password);
				if (passwordStr.isBlank()) {
					key = "BLANK";
				}
				if (!(passwordStr.equals(mBean.getPAYPW()))) {
					key = "WRONG";
				}
				if (passwordStr.equals(mBean.getPAYPW())) {
					key = "CORRECT";
				}
				switch (key) {
				case "BLANK": {
					JOptionPane.showMessageDialog(okBtn, "비밀번호를 입력해주세요.");
					break;
				}
				
				case "WRONG": {
					if (count==0) {
						JOptionPane.showMessageDialog(okBtn, "비밀번호 입력 제한 횟수를 초과하였습니다.");
						frame.dispose();
						break;
					} else {
						JOptionPane.showMessageDialog(okBtn, "비밀번호가 틀렸습니다.");
						count--;
						break;
					}
					
				}
				case "CORRECT": {
					mBean.setPAYPW(passwordStr);
					frame.dispose();
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + key);
				}
			}
		});
		GridBagConstraints gbc_okBtn = new GridBagConstraints();
		gbc_okBtn.insets = new Insets(0, 0, 0, 5);
		gbc_okBtn.gridx = 2;
		gbc_okBtn.gridy = 4;
		frame.getContentPane().add(okBtn, gbc_okBtn);
		
		JButton cancelBtn = new JButton("\uCDE8\uC18C");
		GridBagConstraints gbc_cancelBtn = new GridBagConstraints();
		gbc_cancelBtn.insets = new Insets(0, 0, 0, 5);
		gbc_cancelBtn.gridx = 3;
		gbc_cancelBtn.gridy = 4;
		frame.getContentPane().add(cancelBtn, gbc_cancelBtn);
	}

}
