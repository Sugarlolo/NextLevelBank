package nlb_core; 
 
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import beans.AccountsBean;
import database.AccountsMgr;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import database.AccountsMgr;
import beans.AccountsBean;

public class AccountPlusFrame {
	private Font defaultFont; // 기본 폰트
	private JFrame frmAccountPlusFrame;
	private JPanel panel;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 창의 중앙 좌표 계산
    private CustomTextField pwField;
    AccountsMgr mgr;
    private FrameManager frameMgr;
    String member_id = ""; //회원아이디
    AccountsBean aBean;
    
 
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AccountPlusFrame window = new AccountPlusFrame(aBean);
//					window.frmAccountPlusFrame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
 
	public AccountPlusFrame(AccountsBean abean) {
		//frameMgr = FrameManager.getInstance();
		this.aBean = abean;
		initialize();
	}
 
	public JFrame getFrame() {
    	return frmAccountPlusFrame;
    }
	
	private void initialize() {
		member_id = aBean.getMEMBER_ID();
		
		frmAccountPlusFrame = new JFrame();
		frmAccountPlusFrame.getContentPane().setBackground(new Color(255, 255, 255));
		frmAccountPlusFrame.setTitle("Account Plus Frame");
		frmAccountPlusFrame.setSize(500, 800); //프레임 사이즈
	    int centerX = (screenSize.width - frmAccountPlusFrame.getWidth()) / 2; // 창 중앙에 frame
	    int centerY = (screenSize.height - frmAccountPlusFrame.getHeight()) / 2;
	    frmAccountPlusFrame.setLocation(centerX,centerY);
		frmAccountPlusFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAccountPlusFrame.getContentPane().setLayout(null);
		
		//title_label
		JLabel title_label = new JLabel("\uACC4\uC88C \uAC1C\uC124"); 
		title_label.setFont(new Font("나눔바른고딕", Font.BOLD, 30));
		title_label.setBounds(24, 58, 120, 47);
		frmAccountPlusFrame.getContentPane().add(title_label);
		
        // 로고
        ImageIcon nlb_logo_icon = new ImageIcon(MainFrame.class.getResource("NLB_LOGO.png"));
        Image nlb_logo_img = nlb_logo_icon.getImage();
        Image change_nlb_logo_img = nlb_logo_img.getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        ImageIcon change_nlb_logo_icon = new ImageIcon(change_nlb_logo_img);
        JLabel nlb_logo_label = new JLabel(change_nlb_logo_icon); // 라벨 생성
		nlb_logo_label.setBounds(380, 20, 80, 120);
		frmAccountPlusFrame.getContentPane().add(nlb_logo_label);
		
		//유형 ComboBox
		JComboBox<String> account_category_CBox = new JComboBox<>();
		account_category_CBox.setToolTipText("\uACC4\uC88C\uC720\uD615");
		account_category_CBox.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		account_category_CBox.addItem(" ");
		account_category_CBox.addItem("입출금 통장");
		account_category_CBox.addItem("모임 통장");
		account_category_CBox.setBounds(150, 165, 310, 60);
		frmAccountPlusFrame.getContentPane().add(account_category_CBox);
		
		//사용 용도 ComboBox
		JComboBox<String> account_purpose_CBox = new JComboBox<>();
		account_purpose_CBox.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		account_purpose_CBox.addItem(" ");
		account_purpose_CBox.addItem("급여 및 아르바이트");
		account_purpose_CBox.addItem("생활비 관리");  
		account_purpose_CBox.addItem("적금 자동이체");
		account_purpose_CBox.addItem("예금 가입");
		account_purpose_CBox.addItem("대출 신청"); 
		account_purpose_CBox.setBounds(150, 225, 310, 60);
		frmAccountPlusFrame.getContentPane().add(account_purpose_CBox);
		
		//계좌개설 버튼
		JButton opening_BTN = new JButton("\uAC1C\uC124\uD558\uAE30");
		opening_BTN.setBackground(new Color(255, 225, 0));
		opening_BTN.setFont(new Font("나눔바른고딕", Font.BOLD, 25));
		opening_BTN.setBounds(30, 670, 430, 60);
		frmAccountPlusFrame.getContentPane().add(opening_BTN);
		
		//비밀번호 입력 라벨
		JLabel pw_explan_label = new JLabel("\uD1B5\uC7A5 \uBE44\uBC00\uBC88\uD638");
		pw_explan_label.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		pw_explan_label.setBounds(30, 300, 120, 60);
		frmAccountPlusFrame.getContentPane().add(pw_explan_label);

		//계좌유형 선택 라벨
		JLabel account_category_label = new JLabel("\uACC4\uC88C \uC720\uD615");
		account_category_label.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		account_category_label.setBounds(30, 165, 120, 60);
		frmAccountPlusFrame.getContentPane().add(account_category_label);

		//계좌사용용도 선택 라벨
		JLabel account_purose_label = new JLabel("\uACC4\uC88C \uC0AC\uC6A9 \uC6A9\uB3C4");
		account_purose_label.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		account_purose_label.setBounds(30, 225, 120, 60);
		frmAccountPlusFrame.getContentPane().add(account_purose_label);
		
		//금융거래 한도계좌 이체한도 안내
	 
			JPanel account_constraint_panel = new JPanel();
			account_constraint_panel.setBackground(new Color(255, 255, 255));
			account_constraint_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			account_constraint_panel.setBounds(30, 385, 430, 260);
			frmAccountPlusFrame.getContentPane().add(account_constraint_panel);
			account_constraint_panel.setLayout(null);
			
			JPanel contstraint_content_panel = new JPanel();
			contstraint_content_panel.setBackground(new Color(255, 255, 255));
			contstraint_content_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			contstraint_content_panel.setBounds(1, 42, 428, 217);
			account_constraint_panel.add(contstraint_content_panel);
			contstraint_content_panel.setLayout(null);
			
			JLabel constraint_label2 = new JLabel("<html>금융사고 방지를 위해 넥스트레벨 뱅크 입출금통장은 '금융거래 한도계좌'로 "
					+ "개설됩니다. 이후 고객님의 넥스트레벨 뱅크 거래내역 등을 통해 금융거래 목적이 확인되면, 일반 계좌로 전환되어 한도가 상향됩니다.</html>");
			constraint_label2.setBackground(new Color(255, 255, 255));
			constraint_label2.setFont(new Font("나눔바른고딕", Font.PLAIN, 12));
			constraint_label2.setBounds(11, 10, 404, 47);
			contstraint_content_panel.add(constraint_label2);
			
			JPanel public_account_constraint = new JPanel();
			public_account_constraint.setBackground(new Color(255, 255, 255));
			public_account_constraint.setBorder(new LineBorder(new Color(0, 0, 0)));
			public_account_constraint.setBounds(10, 134, 404, 66);
			contstraint_content_panel.add(public_account_constraint);
			public_account_constraint.setLayout(new GridLayout(2, 1, 0, 0));
			
			panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			public_account_constraint.add(panel);
			panel.setLayout(null);
			
			JLabel constraint_label4 = new JLabel("\uACF5\uB3D9\uACC4\uC88C");
			constraint_label4.setBounds(12, 5, 107, 18);
			panel.add(constraint_label4);
			constraint_label4.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
			
			JLabel constraint_label4_1 = new JLabel("(1\uC77C \uCD5C\uB300\uD55C\uB3C4)");
			constraint_label4_1.setBounds(311, 5, 93, 17);
			panel.add(constraint_label4_1);
			constraint_label4_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 12));
			
			JPanel panel2 = new JPanel();
			panel2.setBackground(new Color(255, 255, 255));
			panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
			public_account_constraint.add(panel2);
			panel2.setLayout(null);
			
			JLabel constraint_label4_2 = new JLabel("\uB125\uC2A4\uD2B8\uB808\uBCA8\uBC45\uD06C \uC774\uCCB4");
			constraint_label4_2.setFont(new Font("나눔바른고딕", Font.PLAIN, 12));
			constraint_label4_2.setBounds(12, 10, 213, 15);
			panel2.add(constraint_label4_2);
			
			JLabel constraint_label4_3 = new JLabel("\uC774\uCCB4 200\uB9CC\uC6D0 / \uCD9C\uAE08 200\uB9CC\uC6D0");
			constraint_label4_3.setBounds(228, 10, 172, 15);
			panel2.add(constraint_label4_3);
			
			JPanel normal_account_constraint = new JPanel();
			normal_account_constraint.setBackground(new Color(255, 255, 255));
			normal_account_constraint.setBorder(new LineBorder(new Color(0, 0, 0)));
			normal_account_constraint.setBounds(10, 62, 404, 64);
			contstraint_content_panel.add(normal_account_constraint);
			normal_account_constraint.setLayout(new GridLayout(2, 1, 0, 0));
			
			JPanel panel1_1 = new JPanel();
			panel1_1.setBackground(new Color(255, 255, 255));
			panel1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel1_1.setLayout(null);
			normal_account_constraint.add(panel1_1);
			
			JLabel constraint_label3 = new JLabel("\uC77C\uBC18\uACC4\uC88C");
			constraint_label3.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
			constraint_label3.setBounds(12, 5, 107, 18);
			panel1_1.add(constraint_label3);
			
			JLabel constraint_label3_1 = new JLabel("(1\uC77C \uCD5C\uB300\uD55C\uB3C4)");
			constraint_label3_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 12));
			constraint_label3_1.setBounds(311, 5, 93, 17);
			panel1_1.add(constraint_label3_1);
			
			JPanel panel2_2 = new JPanel();
			panel2_2.setBackground(new Color(255, 255, 255));
			panel2_2.setBorder(new LineBorder(new Color(0, 0, 0)));
			normal_account_constraint.add(panel2_2);
			panel2_2.setLayout(null);
			
			JLabel constraint_label3_2 = new JLabel("\uB125\uC2A4\uD2B8\uB808\uBCA8\uBC45\uD06C \uC774\uCCB4");
			constraint_label3_2.setBackground(new Color(255, 255, 255));
			constraint_label3_2.setFont(new Font("나눔바른고딕", Font.PLAIN, 12));
			constraint_label3_2.setBounds(12, 10, 213, 15);
			panel2_2.add(constraint_label3_2);
			
			JLabel constraint_label3_3 = new JLabel("\uC774\uCCB4 100\uB9CC\uC6D0 / \uCD9C\uAE08 100\uB9CC\uC6D0");
			constraint_label3_3.setBackground(new Color(255, 255, 255));
			constraint_label3_3.setBounds(231, 10, 163, 15);
			panel2_2.add(constraint_label3_3);
			
			JPanel CONSTRAINT_TITLE_PANEL = new JPanel();
			CONSTRAINT_TITLE_PANEL.setBackground(new Color(255, 255, 255));
			CONSTRAINT_TITLE_PANEL.setBorder(new LineBorder(new Color(0, 0, 0)));
			CONSTRAINT_TITLE_PANEL.setBounds(1, 0, 428, 42);
			account_constraint_panel.add(CONSTRAINT_TITLE_PANEL);
			CONSTRAINT_TITLE_PANEL.setLayout(null);
			
			JLabel constraint_label1 = new JLabel("\uAE08\uC735\uAC70\uB798 \uD55C\uB3C4\uACC4\uC88C \uC774\uCCB4\uD55C\uB3C4 \uC548\uB0B4");
			constraint_label1.setBounds(12, 10, 191, 18);
			constraint_label1.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
			CONSTRAINT_TITLE_PANEL.add(constraint_label1);			
			
			CustomTextField pwField = new CustomTextField(4);
			pwField.setHorizontalAlignment(SwingConstants.CENTER);
			pwField.setFont(new Font("나눔바른고딕", Font.PLAIN, 70));
			pwField.setBounds(150, 300, 310, 60);
			//frmAccountPlusFrame.setVisible(true);
			frmAccountPlusFrame.getContentPane().add(pwField);
		 
		//~금융거래 한도계좌 이체한도 안내	
		opening_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	String account_category="";
                String account_purpose="";
                
            	//account_category_CBox
            	if(account_category_CBox.getSelectedItem().equals("입출금 통장")) {
            		account_category = "일반";
            	}else if(account_category_CBox.getSelectedItem().equals("모임 통장")){
            		account_category = "공동계좌";
            	}
            	 
            	//account_purpose_CBox
            	if(account_purpose_CBox.getSelectedItem().equals("급여 및 아르바이트")) {
            		account_purpose= "급여 및 아르바이트";
            	}else if(account_purpose_CBox.getSelectedItem().equals("생활비 관리")){
            		account_purpose= "생활비 관리";
            	}else if(account_purpose_CBox.getSelectedItem().equals("적금 자동이체")){
            		account_purpose= "적금 자동이체";
            	}else if(account_purpose_CBox.getSelectedItem().equals("예금 가입")){
            		account_purpose= "예금 가입";
            	}else if(account_purpose_CBox.getSelectedItem().equals("대출 신청")){
            		account_purpose= "대출 신청";
            	} 
            	String pw_TF_text = pwField.getText();
            	boolean flag = true;

            	if(account_category_CBox.getSelectedItem().equals(" ")) {
                    JOptionPane.showMessageDialog(frmAccountPlusFrame, "계좌유형을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);            		
            	}else if(account_purpose_CBox.getSelectedItem().equals(" ")) {
                  	JOptionPane.showMessageDialog(frmAccountPlusFrame, "계좌사용용도를 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
            	}else if(pw_TF_text.length()!=4) {
            		JOptionPane.showMessageDialog(frmAccountPlusFrame, "비밀번호는 4자리입니다.", "경고", JOptionPane.WARNING_MESSAGE);
            	}else {
            		for(int i =1;i<pw_TF_text.length();i++) {
            			if(pw_TF_text.charAt(i)!= pw_TF_text.charAt(0)) {
            				flag = false;
            			}
            		}
            		if(flag) {
            			JOptionPane.showMessageDialog(frmAccountPlusFrame, "4자리 동일한 비밀번호는 불가능합니다.", "경고", JOptionPane.WARNING_MESSAGE);
            		}else {
            			/* InsertAccount(String member_id,account_category, account_purpose)*/
            			mgr = new AccountsMgr();
            			AccountsBean bean = new AccountsBean();
            			bean.setMEMBER_ID(member_id);
            			bean.setACCOUNT_CATEGORY(account_category);
            			bean.setACCOUNT_PURPOSE(account_purpose);
            			Boolean isComplete = mgr.InsertAccount(bean);
            			if(isComplete == true)
            				JOptionPane.showMessageDialog(frmAccountPlusFrame, "통장 개설이 완료되었습니다.", "안내", JOptionPane.INFORMATION_MESSAGE);
            			else 
            				JOptionPane.showMessageDialog(frmAccountPlusFrame, "통장 개설을 실패하였습니다.", "경고", JOptionPane.WARNING_MESSAGE);
            			frameMgr.CustomSetVisible("main");       			 
            		}
            	}  
            	
            }
        });
	}
}

class CustomTextField extends JPasswordField{
	private static final int MAX_LENGTH = 4; // 최대 글자 수
	   
	public CustomTextField(int columns) {
		super(columns);
	}
  @Override
    protected Document createDefaultModel() {
        return new LimitDocument();
    }

    private class LimitDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }
            if (!str.matches("\\d+")) { // 숫자만 입력 가능하도록 검사
                return;
            }
            if (getLength() + str.length() <= MAX_LENGTH) { // 최대 글자 수를 초과하지 않도록 검사
                super.insertString(offs, str, a);
            }
        }
    }
    
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		String text = getText();
		
       if (text.isEmpty()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.GRAY);

            Font defaultFont = getFont();
            Font largeFont = new Font(defaultFont.getName(), defaultFont.getStyle(), 15); // 원하는 폰트 크기 설정

            g2d.setFont(largeFont);

            String drawingText = "●         ●         ●         ●";
            int textWidth = g2d.getFontMetrics().stringWidth(drawingText);
            int textHeight = g2d.getFontMetrics().getHeight();

            // 텍스트를 텍스트 필드의 가운데에 그립니다.
            int textX = (getWidth() - textWidth) / 2;
            int textY = (getHeight() + textHeight) / 2;
 
            g2d.drawString(drawingText, textX, textY);
        }
	}
}
