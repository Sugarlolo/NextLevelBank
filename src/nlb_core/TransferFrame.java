package nlb_core;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.text.AbstractDocument;
import javax.net.ssl.SSLContext;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import beans.AccountsBean;
import beans.MemberBean;
import beans.TransferBean;
import database.TransferMgr;
import nlb_core.MainFrame.SharedData;
import nlb_core.MainFrame;

public class TransferFrame extends JFrame implements ActionListener {
	final String[] buttonLabels = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "00", "0", "지우기", };

	JFrame frame;
	JTextField textField_account;
	JTextField textField_money;
	String number = "";
	JButton buttons[];
	JButton admit, plus1, plus5, plus10, plusAll;
	boolean isNotEmpty=false;
	boolean isNotCounted=false;
	TransferMgr tMgr;
	TransferBean tBean;
	AccountsBean aBean;
	MemberBean mBean;
	String member_id;
	int my_account;
	int currentAmount;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public TransferFrame(int selectedAccount,AccountsBean abean, MemberBean mbean) {
		this.my_account = selectedAccount;
		this.aBean = abean;
		this.mBean = mbean;
		member_id=mBean.getMEMBER_ID();
		this.setResizable(false);

		
		System.out.println(abean.getACCOUNT_NUM()+" "+abean.getACCOUNT_BALANCE());
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 500, 800);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		int centerX = (screenSize.width -frame.getWidth()) / 2; // 창 중앙에 frame
	    int centerY = (screenSize.height - frame.getHeight()) / 2;
	    frame.setLocation(centerX,centerY);
		JLabel lblNewLabel = new JLabel("\uC774\uCCB4\uD558\uAE30");
		lblNewLabel.setFont(new Font("나눔바른고딕", Font.BOLD, 25));
		lblNewLabel.setBounds(30, 30, 147, 32);
		frame.getContentPane().add(lblNewLabel);

		textField_account = new JTextField();
		textField_account.setText("계좌번호");
		textField_account.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		textField_account.setBounds(30, 82, 425, 46);
		frame.getContentPane().add(textField_account);
		textField_account.setColumns(9);
		textField_account.addActionListener(this);
		((AbstractDocument) textField_account.getDocument()).setDocumentFilter(new NumericFilter());
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(SharedData.getFlag()!=1) {
					MainFrame mf = new MainFrame(mbean);
					mf.getFrame().setVisible(true);
					frame.dispose();
				}
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
			button.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
			panel.add(buttons[i] = button);
		}

		admit = new JButton("다음");
		admit.setBackground(new Color(255, 225, 0));
		admit.setFont(new Font("나눔바른고딕", Font.BOLD, 25));
		admit.setBounds(30, 709, 425, 32);
		frame.getContentPane().add(admit);
		admit.addActionListener(this);

		plus1 = new JButton("+1만");
		plus1.setBackground(new Color(255, 255, 255));
		plus1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		plus1.setBounds(30, 242, 97, 23);
		frame.getContentPane().add(plus1);
		plus1.addActionListener(this);
		
		plus5 = new JButton("+5만");
		plus5.setBackground(new Color(255, 255, 255));
		plus5.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		plus5.setBounds(139, 242, 97, 23);
		frame.getContentPane().add(plus5);
		plus5.addActionListener(this);
		
		plus10 = new JButton("+10만");
		plus10.setBackground(new Color(255, 255, 255));
		plus10.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		plus10.setBounds(248, 242, 97, 23);
		frame.getContentPane().add(plus10);
		plus10.addActionListener(this);
		
		plusAll = new JButton("전액");
		plusAll.setBackground(new Color(255, 255, 255));
		plusAll.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		plusAll.setBounds(358, 242, 97, 23);
		frame.getContentPane().add(plusAll);
		plusAll.addActionListener(this);

		textField_money = new JTextField();
		textField_money.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_money.setFont(new Font("나눔바른고딕", Font.PLAIN, 17));
		textField_money.setColumns(20);
		textField_money.setBounds(30, 154, 425, 46);
		frame.getContentPane().add(textField_money);
		((AbstractDocument) textField_money.getDocument()).setDocumentFilter(new NumericFilter());
		validate();
		frame.setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		TransferMgr tMgr = new TransferMgr();
//		System.out.println(e.getActionCommand());
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i] == obj) {
				String btnText = buttons[i].getText();
				if (obj == buttons[9] || obj == buttons[10]) {
					if (number.isEmpty())
						continue;
				}
				if (!(btnText.equals("지우기"))) {
					number += btnText;
					textField_money.setText(number);
				} else {
					number = "";
					textField_money.setText(number);
				}
			}
		}
		if (obj == plus1 || obj == plus5 || obj == plus10 || obj == plusAll) {
			System.out.println("눌렀어");
			currentAmount = 0;
			if (!textField_money.getText().isBlank()) {
				currentAmount = Integer.parseInt(textField_money.getText());
			}
			
			if (obj == plus1) {
				int after_amount = currentAmount + 10000;
				textField_money.setText(Integer.toString(after_amount));
			}
			if (obj == plus5) {
				int after_amount = currentAmount + 50000;
				textField_money.setText(Integer.toString(after_amount));
			}
			if (obj == plus10) {
				int after_amount = currentAmount + 100000;
				textField_money.setText(Integer.toString(after_amount));
			}
			if (obj == plusAll) {
				int after_amount = tMgr.getAccountBalance(my_account);
				textField_money.setText(Integer.toString(after_amount));
			}
		}
		
		if (obj == admit) {
			
			aBean.setACCOUNT_NUM(my_account);
			startTransferProcess();
		}
	}
	private void startTransferProcess() {
		tMgr = new TransferMgr();
		
		if (textField_account.getText().equals("계좌번호")) {
			JOptionPane.showMessageDialog(frame, "올바른 계좌번호가 아닙니다.");
			textField_account.setText("");
			return;
		}
		
		if (textField_account.getText().isBlank()) {
			JOptionPane.showMessageDialog(frame, "계좌를 입력하세요.");
			return;
		}
		int accountNum = Integer.parseInt(textField_account.getText());
		System.out.println("입력한 계좌번호: "+accountNum);
		
		int amount = getTransferAmount();
		if (amount<=0) {
			JOptionPane.showMessageDialog(frame, "유효한 금액을 입력해주세요.");
			return;
		}
		
		if (!tMgr.Transfer_CheckAccount(accountNum)) {
			JOptionPane.showMessageDialog(frame, "존재하지 않는 계좌입니다. 계좌 번호를 확인하세요.");
			return;
		}
		
		if (accountNum == my_account) {
			JOptionPane.showMessageDialog(frame, "해당 계좌는 본인 계좌입니다. 다른 계좌번호를 입력해주세요.");
			return;
		}
		
		if (!tMgr.Transfer_CheckBalance(my_account, amount)) {
			JOptionPane.showMessageDialog(frame, "이체하려는 금액이 해당 계좌 잔액을 초과합니다. 다시 시도해 주세요.");
			return;
		}
		
		if (!(tMgr.checkTransferLimits(aBean, amount))) {
			JOptionPane.showMessageDialog(
					frame, "이체 한도를 초과했습니다. 이체가 불가능합니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		boolean transferCompleted = performTransfer(tMgr, accountNum, amount);
		
		if (transferCompleted) {
			if(tMgr.Transfer_Transaction(tBean, my_account, accountNum, amount)) {
				JOptionPane.showMessageDialog(frame, "이체가 완료되었습니다.");
				frame.dispose();
				MainFrame mf = new MainFrame(mBean);
				mf.getFrame().setVisible(true);
			} else {
				JOptionPane.showMessageDialog(frame, "이체가 실패하였습니다. 다시 시도해주세요.");
			}
		}
	}
	
	
	private int getTransferAmount() {
		String amountStr = textField_money.getText();
        int amount = 0;

        if (!amountStr.isEmpty()) {
            try {
                amount = Integer.parseInt(amountStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "금액을 올바르게 입력해주세요.", "오류", JOptionPane.WARNING_MESSAGE);
            }
        }
        return amount;
	}
	
	private boolean performTransfer(TransferMgr tMgr, int accountNum, int amount) {
		//mBean = new MemberBean();
		tBean = new TransferBean();
		String memo = JOptionPane.showInputDialog(frame, "메모를 입력하세요. (입력하지 않으면 본인 이름이 표시됩니다. 최대 10자)");
		Object[] options = {"확인","취소"};
		
		if(memo==null) {
			JOptionPane.showMessageDialog(frame, "이체를 취소하셨습니다.", "취소", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (memo.length()>10) {
			JOptionPane.showMessageDialog(frame, "메모는 10자를 초과할 수 없습니다.","오류",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		tBean.setTransfer_Memo(memo.isBlank() ? mBean.getMEMBER_Name() : memo.trim());
		tMgr.iscountedPayPw(mBean);
		
		for (int i = mBean.getPAYPW_COUNT(); i<4; i++) {
			if (i==3) {
				JOptionPane.showMessageDialog(frame, "결제 비밀번호 입력 횟수를 초과하여 이체가 불가능합니다. 프로그램을 종료합니다.");
				tMgr.changeMemberStatus(mBean);
				System.out.println("트랜스퍼 프레임 회원 상태: "+mBean.getMEMBER_STATUS()); 
				frame.dispose();
				return false;
			}
			JPanel payPw_panel = new JPanel();
			payPw_panel.add(new JLabel("<html>"+mBean.getPAYPW_COUNT()+"회 시도하셨습니다...<br>3회 초과 시 이체가 제한됩니다.<br></html>"));
			JPasswordField payPw_Field = new JPasswordField(6);
			((AbstractDocument) payPw_Field.getDocument()).setDocumentFilter(new NumericFilter());
			payPw_panel.add(payPw_Field);
			
			int payPw = JOptionPane.showOptionDialog(
					frame,payPw_panel,"결제 비밀번호를 입력하세요...",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE,null,
					options,null);
			
			String strPayPw = new String(payPw_Field.getPassword());
			System.out.println("결제비밀번호: "+payPw);
			
			if (payPw == JOptionPane.NO_OPTION || payPw == -1) {
	            JOptionPane.showMessageDialog(frame, "이체를 취소하셨습니다.", "취소", JOptionPane.INFORMATION_MESSAGE);
	            return false;
	        }
			if (!(mBean.getPAYPW()).equals(strPayPw)) {
				tMgr.countPayPw(mBean, i+1);
				tMgr.iscountedPayPw(mBean);
				continue;
			} else {
				return true;
			}
		}
		return false;
	}

	private static class NumericFilter extends DocumentFilter { // textfield 숫자만 입력가능하게 하는 필터
		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException {
			// 입력 문자열이 숫자인지 확인
			if (isNumeric(string)) {
				super.insertString(fb, offset, string, attr);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			// 입력 문자열이 숫자인지 확인
			if (isNumeric(text)) {
				super.replace(fb, offset, length, text, attrs);
			}
		}

		private boolean isNumeric(String text) {
			return text.matches("\\d*"); // 정규 표현식을 사용하여 숫자만 허용
		}
	}
	

//	public static void main(String[] args) {
//		
//		new TransferFrame();
//	}
}