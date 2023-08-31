package nlb_core;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.print.attribute.AttributeSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.stream.Collector.Characteristics;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import beans.MemberBean;
import database.MemberMgr;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JCheckBox;

import nlb_core.LoginFrame;

public class JOINFrame {

	private JFrame frmNextLevelBank2;
	private JLabel InputGuideLabel;
	private JLabel IdLabel;
	private JTextField IdTf;
	private JButton CHECK_BTN;
	private JLabel PwLabel;
	private JTextField PwTf;
	private JTextField PwCheckTf;
	private JTextField NameTf;
	private JLabel NameLabel;
	private JTextField TelTf;
	private JLabel TelLabel;
	private JTextField AddTf;
	private JLabel AddrLabel;
	private JTextField SocialNumberTf1;
	private JLabel SocialNumberLabel;
	private JButton JOIN_BTN;
	private JLabel SocialNumberLabel_1;
	private JTextField SocialNumberLabel2;
	private JLabel lblNewLabel_1;
	private JLabel NLB_LOGO;
	private JTextField Paytf;

	/**
	 * Launch the application.
	 */


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JOINFrame window = new JOINFrame();
					window.frmNextLevelBank2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JOINFrame() {
		initialize();
		frmNextLevelBank2.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	public class JTextFieldLimit extends PlainDocument // JTextField 글자수 제한 클래스
	{
		private int limit; // 제한할 길이

		public JTextFieldLimit(int limit) // 생성자 : 제한할 길이를 인자로 받음
		{
			super();
			this.limit = limit;
		}

		// 텍스트 필드를 채우는 메소드 : 오버라이드
		public void insertString(int offset, String str, javax.swing.text.AttributeSet attr)
				throws BadLocationException {
			if (str == null)
				return;
			if (getLength() + str.length() <= limit)
				super.insertString(offset, str, attr);
		}
	}

	class IdKeyListener extends KeyAdapter {
		// JTextField에 영문소문자, 숫자만 입력가능할수 있게 하려고 만든 키이벤트

		@Override

		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();

			if (!(Character.isLowerCase(c) || Character.isDigit(c))) {
				e.consume();
			}
		}
	}

	class NameListener extends KeyAdapter {
		// JTextField에 영문소문자, 한글만 입력가능할수 있게 하려고 만든 키이벤트

		@Override

		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();

			if (!(Character.isLowerCase(c) || c >= 0xAC00 && c <= 0xD7AF || c == KeyEvent.VK_BACK_SPACE)) {
				e.consume();
			}
		}
	}
	
	class DigitListener extends KeyAdapter {
		// JTextField에 숫자만 입력가능할수 있게 하려고 만든 키이벤트

		@Override

		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();

			if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE)) {
				e.consume();
			}
		}
	}

	class SNListener extends KeyAdapter {
		// 주민등록번호 뒷자리의 첫번째자리는 1,2,3,4 만 입력가능 할수 있게 하려고 만든 키이벤트

		@Override

		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();

			if (c < '1' || c > '4') {
				e.consume();
			}
		}
	}

	private void initialize() {

		MemberMgr db = new MemberMgr();
		
		IdKeyListener idkey = new IdKeyListener();
		NameListener namekey = new NameListener();
		DigitListener digitkey = new DigitListener();
		SNListener snkey = new SNListener();

		frmNextLevelBank2 = new JFrame();
		frmNextLevelBank2.setTitle("Next Level Bank JOIN");
		frmNextLevelBank2.getContentPane().setBackground(new Color(255, 255, 255));
		frmNextLevelBank2.getContentPane().setLayout(null);
		frmNextLevelBank2.setResizable(false);

		InputGuideLabel = new JLabel("NLB \uACC4\uC815 \uC815\uBCF4\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694.");
		InputGuideLabel.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		InputGuideLabel.setBounds(105, 135, 350, 30);
		frmNextLevelBank2.getContentPane().add(InputGuideLabel);

		IdLabel = new JLabel("\uC544\uC774\uB514");
		IdLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		IdLabel.setBounds(29, 190, 80, 30);
		frmNextLevelBank2.getContentPane().add(IdLabel);

		IdTf = new JTextField();
		IdTf.setDocument(new JTextFieldLimit(20));
		IdTf.setBounds(119, 190, 240, 30);
		frmNextLevelBank2.getContentPane().add(IdTf);
		IdTf.setColumns(10);
		IdTf.addKeyListener(idkey);

		CHECK_BTN = new JButton("\uC911\uBCF5\uD655\uC778");
		CHECK_BTN.setBackground(new Color(255, 255, 255));
		CHECK_BTN.setFont(new Font("나눔바른고딕", Font.PLAIN, 16));
		CHECK_BTN.setBounds(369, 190, 90, 30);
		frmNextLevelBank2.getContentPane().add(CHECK_BTN);
		CHECK_BTN.addActionListener(new ActionListener() { // 아이디 중복체크
			public void actionPerformed(ActionEvent e) {
				String chkid = IdTf.getText().trim();
				if (chkid.equals("")) {
					JOptionPane.showMessageDialog(null, "사용하실 아이디를 입력하세요", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (db.idDuplicationCheck(chkid)) {
					JOptionPane.showMessageDialog(null, "이미 사용중인 아이디입니다.", "", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
					JOIN_BTN.setEnabled(true); // 중복체크를 통과하면 회원가입 버튼 활성화
				}
			}
		});

		PwLabel = new JLabel("\uBE44\uBC00\uBC88\uD638");
		PwLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		PwLabel.setBounds(29, 240, 80, 30);
		frmNextLevelBank2.getContentPane().add(PwLabel);

		PwTf = new JPasswordField();
		PwTf.setDocument(new JTextFieldLimit(25));
		PwTf.setColumns(10);
		PwTf.setBounds(119, 240, 240, 30);
		frmNextLevelBank2.getContentPane().add(PwTf);

		PwCheckTf = new JPasswordField();
		PwCheckTf.setDocument(new JTextFieldLimit(25));
		PwCheckTf.setColumns(10);
		PwCheckTf.setBounds(119, 290, 240, 30);
		frmNextLevelBank2.getContentPane().add(PwCheckTf);

		NameTf = new JTextField();
		NameTf.setDocument(new JTextFieldLimit(50));
		NameTf.setColumns(10);
		NameTf.setBounds(119, 340, 240, 30);
		frmNextLevelBank2.getContentPane().add(NameTf);
		NameTf.addKeyListener(namekey);

		NameLabel = new JLabel("\uC774\uB984");
		NameLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		NameLabel.setBounds(29, 340, 80, 30);
		frmNextLevelBank2.getContentPane().add(NameLabel);

		TelTf = new JTextField();
		TelTf.setDocument(new JTextFieldLimit(11));
		TelTf.setColumns(10);
		TelTf.setBounds(119, 390, 240, 30);
		frmNextLevelBank2.getContentPane().add(TelTf);
		TelTf.addKeyListener(digitkey);

		TelLabel = new JLabel("\uC804\uD654\uBC88\uD638");
		TelLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		TelLabel.setBounds(29, 390, 80, 30);
		frmNextLevelBank2.getContentPane().add(TelLabel);

		AddTf = new JTextField();
		AddTf.setDocument(new JTextFieldLimit(70));
		AddTf.setColumns(10);
		AddTf.setBounds(119, 440, 240, 30);
		frmNextLevelBank2.getContentPane().add(AddTf);

		AddrLabel = new JLabel("\uC8FC\uC18C");
		AddrLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		AddrLabel.setBounds(29, 440, 80, 30);
		frmNextLevelBank2.getContentPane().add(AddrLabel);

		SocialNumberTf1 = new JTextField();
		SocialNumberTf1.setDocument(new JTextFieldLimit(6));
		SocialNumberTf1.setColumns(10);
		SocialNumberTf1.setBounds(119, 490, 90, 30);
		frmNextLevelBank2.getContentPane().add(SocialNumberTf1);
		SocialNumberTf1.addKeyListener(digitkey);

		SocialNumberLabel = new JLabel("\uC8FC\uBBFC\uBC88\uD638");
		SocialNumberLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		SocialNumberLabel.setBounds(29, 490, 80, 30);
		frmNextLevelBank2.getContentPane().add(SocialNumberLabel);

		JOIN_BTN = new JButton("\uAC00\uC785\uD558\uAE30");
		JOIN_BTN.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		JOIN_BTN.setBackground(new Color(255, 218, 0));
		JOIN_BTN.setBounds(29, 617, 430, 50);
		frmNextLevelBank2.getContentPane().add(JOIN_BTN);
		JOIN_BTN.setEnabled(false); // 중복체크를 통과하지못했을 경우 가입버튼은 비활성화
		
		JOIN_BTN.addActionListener(new ActionListener() { // 가입 버튼 액션
			public void actionPerformed(ActionEvent e) {
				String jid = IdTf.getText().trim();
				String jpw = PwTf.getText().trim();
				String jpwchk = PwCheckTf.getText().trim();
				String jname = NameTf.getText().trim();
				String jtel = TelTf.getText().trim();
				String jadd = AddTf.getText().trim();
				String jsonum = SocialNumberTf1.getText().trim();
				String jsonuml2 = SocialNumberLabel2.getText().trim();
				String jpay = Paytf.getText().trim();
				
				int desiredNumLength = 6; /* 주민번호 앞자리 길이에 관한 변수 */
				int desiredPayLength = 6; /* 결제비밀번호 길이에 관한 변수 */

				if (jid.equals("") || jpw.equals("") || jpwchk.equals("") || jname.equals("") || jtel.equals("")
						|| jadd.equals("") || jsonum.equals("") || jsonuml2.equals("") || jpay.equals("")) { // 회원 정보가 모두 입력되어야함
					JOptionPane.showMessageDialog(null, "회원 정보를 모두 기입 해주세요.", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (db.idDuplicationCheck(jid)) { // 입력된 id가 DB에 이미 존재할 경우 중복
					JOptionPane.showMessageDialog(null, "ID 중복체크를 해주세요.", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
					return;
				} 
				if(jpw.equals(jpwchk)) { /* 비밀번호와 비밀번호 확인의 값이 같아야됨 */
					boolean numLength = jsonum.length() == desiredNumLength;
					boolean payLength = jpay.length() == desiredPayLength;
					
					if(numLength && payLength) { /* 전화번호와 주민등록번호의 길이는 각각 11자리, 6자리여야 함 && 결제비밀번호는 6자리여야 함 */
						if(db.joinCheck(jid, jpw, jname, jtel, jadd, jsonum, jpay) != null) {
							JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
							frmNextLevelBank2.dispose();
							new LoginFrame();
						}
					}else {
						if(!numLength) {
							JOptionPane.showMessageDialog(null, "주민등록번호 앞자리의 길이가 잘못되었습니다.\n\n주민등록번호 앞자리 = 6자리", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
						}else if(!payLength) {
							JOptionPane.showMessageDialog(null, "결제 비밀번호 길이가 잘못되었습니다.\n\n결제비밀번호 = 6자리", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
						}
					} // -- 전화번호, 주민등록번호 길이 체크
				} else { // 비밀번호 != 비밀번호확인 일경우 회원가입이 안됨
					JOptionPane.showMessageDialog(null, "비밀번호 확인오류", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		SocialNumberLabel_1 = new JLabel("-");
		SocialNumberLabel_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		SocialNumberLabel_1.setBounds(214, 488, 15, 30);
		frmNextLevelBank2.getContentPane().add(SocialNumberLabel_1);

		SocialNumberLabel2 = new JTextField();
		SocialNumberLabel2.setDocument(new JTextFieldLimit(1));
		SocialNumberLabel2.setColumns(10);
		SocialNumberLabel2.setBounds(229, 490, 30, 30);
		frmNextLevelBank2.getContentPane().add(SocialNumberLabel2);
		SocialNumberLabel2.addKeyListener(snkey);
		

		lblNewLabel_1 = new JLabel("____\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF");
		lblNewLabel_1.setBounds(229, 490, 130, 30);
		frmNextLevelBank2.getContentPane().add(lblNewLabel_1);

		// 아이콘 생성
		ImageIcon nlb_logo_icon = new ImageIcon(LoginFrame.class.getResource("/nlb_core/NLB_LOGO.png"));
		Image nlb_logo_img = nlb_logo_icon.getImage();
		Image change_nlb_logo_img = nlb_logo_img.getScaledInstance(70, 100, Image.SCALE_SMOOTH);
		ImageIcon change_nlb_logo_icon = new ImageIcon(change_nlb_logo_img);
		JLabel NLB_LOGO = new JLabel(change_nlb_logo_icon); // 라벨 생성

		NLB_LOGO.setBounds(27, 70, 70, 100);
		frmNextLevelBank2.getContentPane().add(NLB_LOGO);
		
		JLabel PayPwLabel = new JLabel("결제 비번");
		PayPwLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		PayPwLabel.setBounds(29, 545, 80, 30);
		frmNextLevelBank2.getContentPane().add(PayPwLabel);
		
		Paytf = new JPasswordField();
		Paytf.setDocument(new JTextFieldLimit(6));
		Paytf.setColumns(10);
		Paytf.setBounds(119, 545, 240, 30);
		frmNextLevelBank2.getContentPane().add(Paytf);
		Paytf.addKeyListener(digitkey);

		frmNextLevelBank2.setBounds(100, 100, 500, 800);
		Dimension frameSize = frmNextLevelBank2.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frmNextLevelBank2.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
//      frmNextLevelBank2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
