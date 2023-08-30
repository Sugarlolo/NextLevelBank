package nlb_core;

import beans.MemberBean;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class IDFind {
// 기능1 : LoginFrame의 중간에 화면 띄우기
// 기능2 : ID 찾기 버튼 누루면 로그인 창의 아이디 입력칸에 자동 입력되도록 OR 아이디가 메시지 창에 뜨도록
	private JFrame FindRegist;
	private JTextField MEMBER_NAME_TF;
	private JTextField MEMBER_TEL_TF;

	/**
	 * Launch the application.
	 */
	public class HintTextField extends JTextField { // TF HINT
		Font gainFont = new Font("나눔바른고딕", Font.PLAIN, 15);
		Font lostFont = new Font("나눔바른고딕", Font.PLAIN, 10);

		public HintTextField(final String hint) {
			setText(hint);
			setFont(lostFont);
			setForeground(Color.GRAY);
			this.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if (getText().equals(hint)) {
						setText("");
						setFont(gainFont);
					} else {
						setText(getText());
						setFont(gainFont);
					}
				}

				@Override
				public void focusLost(FocusEvent e) {
					if (getText().equals(hint) || getText().length() == 0) {
						setText(hint);
						setFont(lostFont);
						setForeground(Color.GRAY);
					} else {
						setText(getText());
						setFont(gainFont);
						setForeground(Color.BLACK);
					}
				}
			});
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IDFind window = new IDFind();
					window.FindRegist.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IDFind() {
		initialize();
		FindRegist.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		MemberDB db = new MemberDB();
		MemberBean bean = new MemberBean();
		FindRegist = new JFrame();
		FindRegist.getContentPane().setBackground(new Color(255, 228, 0));
		FindRegist.setTitle("Next Level Bank ID Find");
		FindRegist.setBounds(100, 100, 350, 250);
		Dimension frameSize = FindRegist.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		FindRegist.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		//FindRegist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FindRegist.getContentPane().setLayout(null);

		JLabel ID_FIND_LABEL = new JLabel("아이디 찾기");
		ID_FIND_LABEL.setBounds(130, 40, 70, 15);
		FindRegist.getContentPane().add(ID_FIND_LABEL);

		MEMBER_NAME_TF = new HintTextField("이름 입력");
		MEMBER_NAME_TF.setBounds(55, 80, 220, 30);
		FindRegist.getContentPane().add(MEMBER_NAME_TF);
		MEMBER_NAME_TF.setColumns(10);

		JButton ID_FIND_BTN = new JButton("찾기");
		ID_FIND_BTN.setBackground(new Color(255, 218, 0));
		ID_FIND_BTN.setBounds(55, 140, 220, 30);
		FindRegist.getContentPane().add(ID_FIND_BTN);
		ID_FIND_BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // id 찾기 버튼 눌렸을때 동작
				String name = MEMBER_NAME_TF.getText().trim();
				String phone = MEMBER_TEL_TF.getText().trim();
				if (db.getFindId(name, phone) != null) { /* DB에 저장된 이름과 전화번호를 비교하여 ID 조회 */
					String memberName = db.getFindId(name, phone).getMEMBER_Name();
					String memberPhoneNum = db.getFindId(name, phone).getTEL_Num();
					if (memberName != null && db.getFindId(name, phone).getMEMBER_Name().equals(name)
							&& memberPhoneNum != null && db.getFindId(name, phone).getTEL_Num().equals(phone)) {
						JOptionPane.showMessageDialog(null, "아이디는 " + db.getFindId(name, phone).getMEMBER_ID() + " 입니다");
					} else {
						JOptionPane.showMessageDialog(null, "입력한 정보와 일치하는 ID가 없습니다.", "ID 찾기 실패",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "해당 사용자를 찾을 수 없습니다.", "찾기 실패", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		MEMBER_TEL_TF = new HintTextField("전화번호 입력('-' 를 제외한 숫자 11자리만 입력)");
		MEMBER_TEL_TF.setColumns(10);
		MEMBER_TEL_TF.setBounds(55, 110, 220, 30);
		FindRegist.getContentPane().add(MEMBER_TEL_TF);
		FindRegist.setResizable(false);
	}
}
