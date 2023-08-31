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

	public class JTextFieldLimit extends PlainDocument // JTextField ���ڼ� ���� Ŭ����
	{
		private int limit; // ������ ����

		public JTextFieldLimit(int limit) // ������ : ������ ���̸� ���ڷ� ����
		{
			super();
			this.limit = limit;
		}

		// �ؽ�Ʈ �ʵ带 ä��� �޼ҵ� : �������̵�
		public void insertString(int offset, String str, javax.swing.text.AttributeSet attr)
				throws BadLocationException {
			if (str == null)
				return;
			if (getLength() + str.length() <= limit)
				super.insertString(offset, str, attr);
		}
	}

	class IdKeyListener extends KeyAdapter {
		// JTextField�� �����ҹ���, ���ڸ� �Է°����Ҽ� �ְ� �Ϸ��� ���� Ű�̺�Ʈ

		@Override

		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();

			if (!(Character.isLowerCase(c) || Character.isDigit(c))) {
				e.consume();
			}
		}
	}

	class NameListener extends KeyAdapter {
		// JTextField�� �����ҹ���, �ѱ۸� �Է°����Ҽ� �ְ� �Ϸ��� ���� Ű�̺�Ʈ

		@Override

		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();

			if (!(Character.isLowerCase(c) || c >= 0xAC00 && c <= 0xD7AF || c == KeyEvent.VK_BACK_SPACE)) {
				e.consume();
			}
		}
	}
	
	class DigitListener extends KeyAdapter {
		// JTextField�� ���ڸ� �Է°����Ҽ� �ְ� �Ϸ��� ���� Ű�̺�Ʈ

		@Override

		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();

			if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE)) {
				e.consume();
			}
		}
	}

	class SNListener extends KeyAdapter {
		// �ֹε�Ϲ�ȣ ���ڸ��� ù��°�ڸ��� 1,2,3,4 �� �Է°��� �Ҽ� �ְ� �Ϸ��� ���� Ű�̺�Ʈ

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
		InputGuideLabel.setFont(new Font("�����ٸ����", Font.BOLD, 20));
		InputGuideLabel.setBounds(105, 135, 350, 30);
		frmNextLevelBank2.getContentPane().add(InputGuideLabel);

		IdLabel = new JLabel("\uC544\uC774\uB514");
		IdLabel.setFont(new Font("�����ٸ����", Font.PLAIN, 18));
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
		CHECK_BTN.setFont(new Font("�����ٸ����", Font.PLAIN, 16));
		CHECK_BTN.setBounds(369, 190, 90, 30);
		frmNextLevelBank2.getContentPane().add(CHECK_BTN);
		CHECK_BTN.addActionListener(new ActionListener() { // ���̵� �ߺ�üũ
			public void actionPerformed(ActionEvent e) {
				String chkid = IdTf.getText().trim();
				if (chkid.equals("")) {
					JOptionPane.showMessageDialog(null, "����Ͻ� ���̵� �Է��ϼ���", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (db.idDuplicationCheck(chkid)) {
					JOptionPane.showMessageDialog(null, "�̹� ������� ���̵��Դϴ�.", "", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "��� ������ ���̵��Դϴ�.");
					JOIN_BTN.setEnabled(true); // �ߺ�üũ�� ����ϸ� ȸ������ ��ư Ȱ��ȭ
				}
			}
		});

		PwLabel = new JLabel("\uBE44\uBC00\uBC88\uD638");
		PwLabel.setFont(new Font("�����ٸ����", Font.PLAIN, 18));
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
		NameLabel.setFont(new Font("�����ٸ����", Font.PLAIN, 18));
		NameLabel.setBounds(29, 340, 80, 30);
		frmNextLevelBank2.getContentPane().add(NameLabel);

		TelTf = new JTextField();
		TelTf.setDocument(new JTextFieldLimit(11));
		TelTf.setColumns(10);
		TelTf.setBounds(119, 390, 240, 30);
		frmNextLevelBank2.getContentPane().add(TelTf);
		TelTf.addKeyListener(digitkey);

		TelLabel = new JLabel("\uC804\uD654\uBC88\uD638");
		TelLabel.setFont(new Font("�����ٸ����", Font.PLAIN, 18));
		TelLabel.setBounds(29, 390, 80, 30);
		frmNextLevelBank2.getContentPane().add(TelLabel);

		AddTf = new JTextField();
		AddTf.setDocument(new JTextFieldLimit(70));
		AddTf.setColumns(10);
		AddTf.setBounds(119, 440, 240, 30);
		frmNextLevelBank2.getContentPane().add(AddTf);

		AddrLabel = new JLabel("\uC8FC\uC18C");
		AddrLabel.setFont(new Font("�����ٸ����", Font.PLAIN, 18));
		AddrLabel.setBounds(29, 440, 80, 30);
		frmNextLevelBank2.getContentPane().add(AddrLabel);

		SocialNumberTf1 = new JTextField();
		SocialNumberTf1.setDocument(new JTextFieldLimit(6));
		SocialNumberTf1.setColumns(10);
		SocialNumberTf1.setBounds(119, 490, 90, 30);
		frmNextLevelBank2.getContentPane().add(SocialNumberTf1);
		SocialNumberTf1.addKeyListener(digitkey);

		SocialNumberLabel = new JLabel("\uC8FC\uBBFC\uBC88\uD638");
		SocialNumberLabel.setFont(new Font("�����ٸ����", Font.PLAIN, 18));
		SocialNumberLabel.setBounds(29, 490, 80, 30);
		frmNextLevelBank2.getContentPane().add(SocialNumberLabel);

		JOIN_BTN = new JButton("\uAC00\uC785\uD558\uAE30");
		JOIN_BTN.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		JOIN_BTN.setBackground(new Color(255, 218, 0));
		JOIN_BTN.setBounds(29, 617, 430, 50);
		frmNextLevelBank2.getContentPane().add(JOIN_BTN);
		JOIN_BTN.setEnabled(false); // �ߺ�üũ�� ������������� ��� ���Թ�ư�� ��Ȱ��ȭ
		
		JOIN_BTN.addActionListener(new ActionListener() { // ���� ��ư �׼�
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
				
				int desiredNumLength = 6; /* �ֹι�ȣ ���ڸ� ���̿� ���� ���� */
				int desiredPayLength = 6; /* ������й�ȣ ���̿� ���� ���� */

				if (jid.equals("") || jpw.equals("") || jpwchk.equals("") || jname.equals("") || jtel.equals("")
						|| jadd.equals("") || jsonum.equals("") || jsonuml2.equals("") || jpay.equals("")) { // ȸ�� ������ ��� �ԷµǾ����
					JOptionPane.showMessageDialog(null, "ȸ�� ������ ��� ���� ���ּ���.", "ȸ������ ����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (db.idDuplicationCheck(jid)) { // �Էµ� id�� DB�� �̹� ������ ��� �ߺ�
					JOptionPane.showMessageDialog(null, "ID �ߺ�üũ�� ���ּ���.", "ȸ������ ����", JOptionPane.ERROR_MESSAGE);
					return;
				} 
				if(jpw.equals(jpwchk)) { /* ��й�ȣ�� ��й�ȣ Ȯ���� ���� ���ƾߵ� */
					boolean numLength = jsonum.length() == desiredNumLength;
					boolean payLength = jpay.length() == desiredPayLength;
					
					if(numLength && payLength) { /* ��ȭ��ȣ�� �ֹε�Ϲ�ȣ�� ���̴� ���� 11�ڸ�, 6�ڸ����� �� && ������й�ȣ�� 6�ڸ����� �� */
						if(db.joinCheck(jid, jpw, jname, jtel, jadd, jsonum, jpay) != null) {
							JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
							frmNextLevelBank2.dispose();
							new LoginFrame();
						}
					}else {
						if(!numLength) {
							JOptionPane.showMessageDialog(null, "�ֹε�Ϲ�ȣ ���ڸ��� ���̰� �߸��Ǿ����ϴ�.\n\n�ֹε�Ϲ�ȣ ���ڸ� = 6�ڸ�", "ȸ������ ����", JOptionPane.ERROR_MESSAGE);
						}else if(!payLength) {
							JOptionPane.showMessageDialog(null, "���� ��й�ȣ ���̰� �߸��Ǿ����ϴ�.\n\n������й�ȣ = 6�ڸ�", "ȸ������ ����", JOptionPane.ERROR_MESSAGE);
						}
					} // -- ��ȭ��ȣ, �ֹε�Ϲ�ȣ ���� üũ
				} else { // ��й�ȣ != ��й�ȣȮ�� �ϰ�� ȸ�������� �ȵ�
					JOptionPane.showMessageDialog(null, "��й�ȣ Ȯ�ο���", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		SocialNumberLabel_1 = new JLabel("-");
		SocialNumberLabel_1.setFont(new Font("�����ٸ����", Font.PLAIN, 18));
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

		// ������ ����
		ImageIcon nlb_logo_icon = new ImageIcon(LoginFrame.class.getResource("/nlb_core/NLB_LOGO.png"));
		Image nlb_logo_img = nlb_logo_icon.getImage();
		Image change_nlb_logo_img = nlb_logo_img.getScaledInstance(70, 100, Image.SCALE_SMOOTH);
		ImageIcon change_nlb_logo_icon = new ImageIcon(change_nlb_logo_img);
		JLabel NLB_LOGO = new JLabel(change_nlb_logo_icon); // �� ����

		NLB_LOGO.setBounds(27, 70, 70, 100);
		frmNextLevelBank2.getContentPane().add(NLB_LOGO);
		
		JLabel PayPwLabel = new JLabel("���� ���");
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
