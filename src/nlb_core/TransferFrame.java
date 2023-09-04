package nlb_core;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import beans.AccountsBean;
import beans.MemberBean;
import beans.TransferBean;
import database.TransferMgr;

public class TransferFrame extends JFrame implements ActionListener {
	final String[] buttonLabels = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "00", "0", "�����", };

	JFrame frame;
	JTextField textField_account;
	JTextField textField_money;
	String number = "";
	JButton buttons[];
	JButton admit, plus1, plus5, plus10, plusAll;
	boolean isNotEmpty=false;
	
	TransferBean tBean;
	AccountsBean aBean;
	MemberBean mBean;
	int my_account;
	public TransferFrame(int selectedAccount,AccountsBean abean, MemberBean mbean) {
		this.my_account = selectedAccount;
		this.aBean = abean;
		this.mBean = mbean;
		this.setResizable(false);
		System.out.println(abean.getACCOUNT_NUM()+" "+abean.getACCOUNT_BALANCE());
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 500, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC774\uCCB4\uD558\uAE30");
		lblNewLabel.setFont(new Font("�����ٸ����", Font.BOLD, 25));
		lblNewLabel.setBounds(30, 30, 147, 32);
		frame.getContentPane().add(lblNewLabel);

		textField_account = new JTextField();
		textField_account.setText("���¹�ȣ");
		textField_account.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		textField_account.setBounds(30, 82, 425, 46);
		frame.getContentPane().add(textField_account);
		textField_account.setColumns(9);
		textField_account.addActionListener(this);

		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainFrame mf = new MainFrame(mbean);
				mf.getFrame().setVisible(true);
				frame.dispose();
			}
		}); 
			
		
		textField_account.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isNotEmpty==false) {
					textField_account.setText("");
					isNotEmpty=true;
				}
			}
		});
		
		textField_account.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				if (textField_account.getText().length() >=9) {
					ke.consume();
				}
			}
		});
		

		JPanel panel = new JPanel();
		panel.setBounds(30, 290, 425, 394);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(4, 3, 10, 10));

		buttons = new JButton[12];

		for (int i = 0; i < 12; i++) {
			JButton button = new JButton(buttonLabels[i]);
			button.addActionListener(this);
			button.setBackground(Color.white);
			button.setFont(new Font("�����ٸ����", Font.BOLD, 20));
			panel.add(buttons[i] = button);
		}

		admit = new JButton("����");
		admit.setBackground(new Color(255, 225, 0));
		admit.setFont(new Font("�����ٸ����", Font.BOLD, 25));
		admit.setBounds(30, 709, 425, 32);
		frame.getContentPane().add(admit);
		admit.addActionListener(this);

		JButton btnNewButton_1 = new JButton("+1��");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		btnNewButton_1.setBounds(30, 242, 97, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("+5��");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		btnNewButton_2.setBounds(139, 242, 97, 23);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("+10��");
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		btnNewButton_3.setBounds(248, 242, 97, 23);
		frame.getContentPane().add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("����");
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		btnNewButton_4.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		btnNewButton_4.setBounds(358, 242, 97, 23);
		frame.getContentPane().add(btnNewButton_4);

		textField_money = new JTextField();
		textField_money.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_money.setFont(new Font("�����ٸ����", Font.PLAIN, 17));
		textField_money.setColumns(20);
		textField_money.setBounds(30, 154, 425, 46);
		frame.getContentPane().add(textField_money);

		validate();
		frame.setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
//		System.out.println(e.getActionCommand());
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i] == obj) {
				String btnText = buttons[i].getText();
				if (obj == buttons[9] || obj == buttons[10]) {
					if (number.isEmpty())
						continue;
				}
				if (!(btnText.equals("�����"))) {
					number += btnText;
					textField_money.setText(number);
				} else {
					number = "";
					textField_money.setText(number);
				}
			}
		}
		if (obj == admit) {
			int account_num = Integer.parseInt(textField_account.getText());
			System.out.println("�Է��� ���¹�ȣ:"+account_num);
			TransferMgr tMgr = new TransferMgr();
			boolean flag = false;
			boolean check = false;
			number = textField_money.getText();
			int amount = 0;
			int count = 0;
			tBean = new TransferBean();
			if (!(number.isEmpty())) {
				amount = Integer.parseInt(number);
				//��ü ����� ���¹�ȣ�� �Է��� ���¹�ȣ�� ��ġ�ϴ��� �˻�.
				flag = tMgr.Transfer_CheckAccount(account_num);
				if (flag != false) {
					System.out.println("�´� �����Դϴ�.");
					// �׽�Ʈ ����
					System.out.println("�� ���¹�ȣ "+my_account);
					if (tMgr.Transfer_CheckBalance(my_account, amount) != false) {
						System.out.println("�۱��� �����մϴ�.");
						JOptionPane.showMessageDialog(frame, "�۱��� �����մϴ�.");
						
						String memo = JOptionPane.showInputDialog(frame,"�޸� �Է��ϼ���. (�Է����� ������ ���� �̸��� ǥ�õ˴ϴ�. �ִ� 10��)");
						
						if (memo.length()>10) {
							JOptionPane.showMessageDialog(frame, "�޸�� 10�ڸ� �ʰ��� �� �����ϴ�.");
						} else if (memo.isBlank() || memo.length()<=10) {
							if (memo.isBlank()) {
								tBean.setTransfer_Memo(mBean.getMEMBER_Name());
								System.out.println("�Էµ� �̸�: "+tBean.getTransfer_Memo());
							} else
								tBean.setTransfer_Memo(memo.trim());
							for (int i = 0; i<4; i++) {
								if (count==3) {
									JOptionPane.showMessageDialog(frame, "�Է� Ƚ���� �ʰ��߽��ϴ�.");
									break;
								}
								
								String payPw = JOptionPane.showInputDialog(frame, "���� ��й�ȣ�� �Է����ּ���. "+count+"ȸ �Է��ϼ̽��ϴ�.");
								if (tMgr.PayPassword_check(mBean.getMEMBER_ID(), payPw) == true) {
									check = tMgr.Transfer_Transaction(tBean, my_account, account_num, amount);

									if (check == true) {
										JOptionPane.showMessageDialog(frame, "��ü�� �Ϸ�Ǿ����ϴ�.");
										frame.dispose();
										MainFrame mf = new MainFrame(mBean);
										mf.getFrame().setVisible(true);
									} else
										JOptionPane.showMessageDialog(frame, "��ü�� �����Ͽ����ϴ�.");
									break;
								} else {
									count++;
									continue;
								}
							}
						}
						
					} else {
						System.out.println("�۱��� �Ұ����մϴ�.");
						JOptionPane.showMessageDialog(frame, "�۱��� �Ұ����մϴ�.");
					}
				} else {
					System.out.println("Ʋ�� �����Դϴ�.");
					JOptionPane.showMessageDialog(frame, "�������� �ʴ� �����Դϴ�. ���¹�ȣ�� Ȯ���ϼ���.");
				}
			} else
				JOptionPane.showMessageDialog(frame, "�ݾ��� �Է��ؾ� �մϴ�.");
		}
	}
	

//	public static void main(String[] args) {
//		
//		new TransferFrame();
//	}
}