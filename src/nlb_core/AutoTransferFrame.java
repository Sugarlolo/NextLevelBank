package nlb_core;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.AbstractDocument;

import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import beans.AccountsBean;
import beans.MemberBean;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AutoTransferFrame {

	private JFrame frmAutotransferframe;
	private JTextField transBalanceTF;
	private JTextField withdrawAccountTF;
	private int selectedAccountNum;
	private AccountsBean aBean;
	private MemberBean mBean;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 창의 중앙 좌표 계산
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//AutoTransferFrame window = new AutoTransferFrame();
					//window.frmAutotransferframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public AutoTransferFrame(MemberBean mbean,AccountsBean abean) {
		this.mBean = mbean;
		this.aBean = abean;
		initialize();
	}

	private void initialize() {
		MainFrame mf = new MainFrame(mBean);
		selectedAccountNum = mf.selectedAccountNum();
		frmAutotransferframe = new JFrame();
		frmAutotransferframe.setSize(500, 800); // 프레임 사이즈
		int centerX = (screenSize.width - frmAutotransferframe.getWidth()) / 2; // 창 중앙에 frame
		int centerY = (screenSize.height - frmAutotransferframe.getHeight()) / 2;
		frmAutotransferframe.setLocation(centerX, centerY);
		frmAutotransferframe.getContentPane().setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
		frmAutotransferframe.setTitle("AUTO_TRANSFER_FRAME");
		frmAutotransferframe.getContentPane().setBackground(new Color(255, 255, 255));
		
		JPanel panel = new JPanel();
		frmAutotransferframe.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel titleLbl = new JLabel("자동이체");
		titleLbl.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		titleLbl.setBounds(30, 20, 200, 40);
		panel.add(titleLbl);
		
		JScrollPane checkSP = new JScrollPane();
		checkSP.setBounds(30, 370, 430, 300);
		panel.add(checkSP);
		
		JList AccountsList = new JList();
		AccountsList.setFont(new Font("나눔바른고딕", Font.BOLD, 12));
		checkSP.setViewportView(AccountsList);
		
		JLabel registLbl = new JLabel("자동이체 등록");
		registLbl.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		registLbl.setBounds(30, 70, 200, 40);
		panel.add(registLbl);
		
		JLabel checkLabel = new JLabel("자동이체 조회");
		checkLabel.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		checkLabel.setBounds(30, 330, 200, 40);
		panel.add(checkLabel);
		
		JButton cancelBtn = new JButton("자동이체 해지하기");
		cancelBtn.setBackground(new Color(255, 225, 0));
		cancelBtn.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		cancelBtn.setBounds(30, 686, 430, 53);
		panel.add(cancelBtn);
		
		JLabel transAccountLbl = new JLabel("자동이체 이체 계좌번호 : "+selectedAccountNum);
		transAccountLbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		transAccountLbl.setBounds(40, 120, 396, 15);
		panel.add(transAccountLbl);
		
		JLabel withdrawAccountLbl = new JLabel("자동이체 출금 계좌번호 :");
		withdrawAccountLbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		withdrawAccountLbl.setBounds(40, 150, 396, 15);
		panel.add(withdrawAccountLbl);
		
		JLabel transBalaceLbl = new JLabel("자동이체 금액");
		transBalaceLbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		transBalaceLbl.setBounds(40, 180, 138, 15);
		panel.add(transBalaceLbl);
		
		withdrawAccountTF = new JTextField();
		withdrawAccountTF.setBounds(210, 150, 250, 20);
		panel.add(withdrawAccountTF);
		withdrawAccountTF.setColumns(10);
		((AbstractDocument) withdrawAccountTF.getDocument()).setDocumentFilter(new NumericFilter());

		transBalanceTF = new JTextField();
		transBalanceTF.setBounds(210, 180, 250, 20);
		panel.add(transBalanceTF);
		transBalanceTF.setColumns(10);
		((AbstractDocument) transBalanceTF.getDocument()).setDocumentFilter(new NumericFilter());

		
		JLabel transDateLbl = new JLabel("이체일");
		transDateLbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		transDateLbl.setBounds(40, 210, 138, 15);
		panel.add(transDateLbl);
		
		JComboBox transDateCmbx = new JComboBox();
		transDateCmbx.setModel(new DefaultComboBoxModel(new String[] {"1\uC77C", "2\uC77C", "3\uC77C", "4\uC77C", "5\uC77C", "6\uC77C", "7\uC77C", "8\uC77C", "9\uC77C", "10\uC77C", "11\uC77C", "12\uC77C", "13\uC77C", "14\uC77C", "15\uC77C", "16\uC77C", "17\uC77C", "18\uC77C", "19\uC77C", "20\uC77C", "21\uC77C", "22\uC77C", "23\uC77C", "24\uC77C", "25\uC77C", "26\uC77C", "27\uC77C", "28\uC77C"}));
		transDateCmbx.setBackground(new Color(255, 255, 255));
		transDateCmbx.setBounds(210, 210, 250, 20);
		panel.add(transDateCmbx);
		
		JButton RegistBtn = new JButton("자동이체 등록하기");
		RegistBtn.setBackground(new Color(255, 225, 0));
		RegistBtn.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		RegistBtn.setBounds(30, 250, 430, 53);
		panel.add(RegistBtn);
		frmAutotransferframe.setResizable(false);
		//frmAutotransferframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JFrame getFrame() {
		return frmAutotransferframe;
	}

   static class NumericFilter extends DocumentFilter { // textfield 숫자만 입력가능하게 하는 필터
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
}
