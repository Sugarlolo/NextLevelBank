package nlb_core;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MainFrame {

	private JFrame frmKakaobank;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmKakaobank.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		frmKakaobank.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKakaobank = new JFrame();
		frmKakaobank.getContentPane().setBackground(new Color(255, 255, 255));
		frmKakaobank.setTitle("Next Level Bank Main");
		frmKakaobank.setBounds(100, 100, 450, 700);
		frmKakaobank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKakaobank.getContentPane().setLayout(null);
		
		//���� �׻� ǥ��, ���� ���� : JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(15, 80, 400, 550);
		frmKakaobank.getContentPane().add(scrollPane);
		

		JPanel ACCOUNT_SHOW_PANEL = new JPanel();
		ACCOUNT_SHOW_PANEL.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(ACCOUNT_SHOW_PANEL);
		ACCOUNT_SHOW_PANEL.setLayout(null);
		
		JPanel ACCOUNT1_PANEL = new JPanel();
		ACCOUNT1_PANEL.setBackground(new Color(255, 228, 0)); // NLB �⺻ �÷�
		ACCOUNT1_PANEL.setBorder(new LineBorder(null, 1, true));
		ACCOUNT1_PANEL.setBounds(25, 25, 350, 110);
		// ���� x=25���� y = 25+(����n-1)*(height_110 + 10)  /���� ���� y���� 10
		// width : 350, height : 110 ����
		ACCOUNT_SHOW_PANEL.add(ACCOUNT1_PANEL);
		ACCOUNT1_PANEL.setLayout(null);
		
		//�� �̹��� ��

		ImageIcon PIN_logo_icon = new ImageIcon(LoginFrame.class.getResource("/nlb_core/PIN.png"));
		Image PIN_logo_img = PIN_logo_icon.getImage();
		Image change_PIN_logo_img = PIN_logo_img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon change_PIN_logo_icon = new ImageIcon(change_PIN_logo_img); 
		

		JLabel MAIN_ACCOUNT_PIN_LABEL = new JLabel(change_PIN_logo_icon);
		
		MAIN_ACCOUNT_PIN_LABEL.setBounds(20, 20, 15, 15);
		ACCOUNT1_PANEL.add(MAIN_ACCOUNT_PIN_LABEL);
		
		JLabel ACCOUNT1_MEMBER_ID_LABEL = new JLabel("OOO");
		ACCOUNT1_MEMBER_ID_LABEL.setFont(new Font("�����ٸ����", Font.PLAIN, 12));
		ACCOUNT1_MEMBER_ID_LABEL.setBounds(50, 20, 30, 15);
		ACCOUNT1_PANEL.add(ACCOUNT1_MEMBER_ID_LABEL);
		
		//ACCOUNT1_BALANCE_LABEL X=50 ���� WIDTH = �ݾ��ڸ��� * 20
		// �ݾ��� WIDTH �ڵ� ���� �Լ� �ʿ�
		JLabel ACCOUNT1_BALANCE_LABEL = new JLabel("O"); 
		// �ݾ� �ѱ��ڴ� ���� +=20, �ݾ� �ִ� �ڸ��� 7�ڸ�, 8�ڸ� �Ѿ�� ���ڼ� �پ�� �ʿ� �� ���ڴ� ���� ũ�� ���� �ʿ�
		ACCOUNT1_BALANCE_LABEL.setFont(new Font("�����ٸ����", Font.BOLD, 25));
		ACCOUNT1_BALANCE_LABEL.setBounds(50, 40, 20, 25);
		ACCOUNT1_PANEL.add(ACCOUNT1_BALANCE_LABEL);
		
		JButton ACCOUNT1_TRANSFER_DO_BTN = new JButton("\uC774\uCCB4");
		ACCOUNT1_TRANSFER_DO_BTN.setForeground(new Color(64, 0, 64));
		ACCOUNT1_TRANSFER_DO_BTN.setBackground(new Color(240, 218, 0));
		ACCOUNT1_TRANSFER_DO_BTN.setFont(new Font("�����ٸ����", Font.BOLD, 15));
		ACCOUNT1_TRANSFER_DO_BTN.setBounds(230, 20, 90, 30); //��ư x=230, y = 20, width: 90  ���� 
		ACCOUNT1_PANEL.add(ACCOUNT1_TRANSFER_DO_BTN);
		
		JButton TRANSFER_HISTROY_BTN = new JButton("\uC774\uCCB4\uB0B4\uC5ED");  //��ư x=230, y = 20, width: 90 ����
		TRANSFER_HISTROY_BTN.setBackground(new Color(240, 218, 0));
		TRANSFER_HISTROY_BTN.setFont(new Font("�����ٸ����", Font.BOLD, 15));
		TRANSFER_HISTROY_BTN.setBounds(230, 60, 90, 30); // y = ��ư1_y + 30 + 10
		ACCOUNT1_PANEL.add(TRANSFER_HISTROY_BTN);
		
		JLabel ACCOUNT1_ID_AFTER_LABEL = new JLabel("\uC758 \uD1B5\uC7A5");
		ACCOUNT1_ID_AFTER_LABEL.setFont(new Font("�����ٸ����", Font.PLAIN, 12));
		ACCOUNT1_ID_AFTER_LABEL.setBounds(80, 20, 80, 15);
		ACCOUNT1_PANEL.add(ACCOUNT1_ID_AFTER_LABEL);
		
		//ACCOUNT1_WON_LABEL �� X = ACCOUNT1_BALANCE_LABEL(X)+ACCOUNT1_BALANCE_LABEL(WIDTH) 
		// ���� X ��ǥ �ڵ� ���� �Լ� �ʿ�
		JLabel ACCOUNT1_WON_LABEL = new JLabel("\uC6D0");
		ACCOUNT1_WON_LABEL.setFont(new Font("�����ٸ����", Font.BOLD, 25));
		ACCOUNT1_WON_LABEL.setBounds(70, 40, 25, 25);
		ACCOUNT1_PANEL.add(ACCOUNT1_WON_LABEL);
		
		JButton NEW_ACCOUNT_ADD_BTN = new JButton("+");
		NEW_ACCOUNT_ADD_BTN.setBackground(new Color(246, 246, 246));
		NEW_ACCOUNT_ADD_BTN.setFont(new Font("�����ٸ����", Font.BOLD, 20));
		NEW_ACCOUNT_ADD_BTN.setBounds(25, 145, 350, 40);
		ACCOUNT_SHOW_PANEL.add(NEW_ACCOUNT_ADD_BTN);
		
		textField = new JTextField();
		textField.setBounds(25, 501, 330, 186);
		ACCOUNT_SHOW_PANEL.add(textField);
		textField.setColumns(10);
//		
//		//��ũ���� �۵� Ȯ�ο� ��ư~
//		for(int i = 0 ; i<10;i++) {
//			JButton NEW_ACCOUNT_ADD_BTNi = new JButton("+");
//			NEW_ACCOUNT_ADD_BTNi.setBackground(new Color(246, 246, 246));
//			NEW_ACCOUNT_ADD_BTNi.setFont(new Font("�����ٸ����", Font.BOLD, 20));
//			NEW_ACCOUNT_ADD_BTNi.setBounds(25, 145+(10+40)*i, 350, 40);
//			ACCOUNT_SHOW_PANEL.add(NEW_ACCOUNT_ADD_BTNi);
//		}
//		//~��ũ���� �۵� Ȯ�ο� ��ư
		
		JLabel MEMBER_ID_LABEL = new JLabel("OOO");
		MEMBER_ID_LABEL.setFont(new Font("�����ٸ����", Font.BOLD, 20));
		MEMBER_ID_LABEL.setBounds(26, 37, 57, 22);
		frmKakaobank.getContentPane().add(MEMBER_ID_LABEL);
		
		JLabel GREETING_LABEL = new JLabel("\uB2D8 \uBC18\uAC11\uC2B5\uB2C8\uB2E4.");
		GREETING_LABEL.setFont(new Font("�����ٸ����", Font.BOLD, 16));
		GREETING_LABEL.setBounds(78, 40, 102, 15);
		frmKakaobank.getContentPane().add(GREETING_LABEL);
		
		JButton MY_ACCOUNT_BTN = new JButton("\uB0B4 \uACC4\uC88C");
		MY_ACCOUNT_BTN.setBackground(new Color(255, 255, 255));
		MY_ACCOUNT_BTN.setFont(new Font("�����ٸ����", Font.BOLD, 12));
		MY_ACCOUNT_BTN.setBounds(180, 37, 79, 23);
		frmKakaobank.getContentPane().add(MY_ACCOUNT_BTN);
	}
}
