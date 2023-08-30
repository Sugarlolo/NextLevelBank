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
// ���1 : LoginFrame�� �߰��� ȭ�� ����
// ���2 : ID ã�� ��ư ����� �α��� â�� ���̵� �Է�ĭ�� �ڵ� �Էµǵ��� OR ���̵� �޽��� â�� �ߵ���
	private JFrame FindRegist;
	private JTextField MEMBER_NAME_TF;
	private JTextField MEMBER_TEL_TF;

	/**
	 * Launch the application.
	 */
	public class HintTextField extends JTextField { // TF HINT
		Font gainFont = new Font("�����ٸ����", Font.PLAIN, 15);
		Font lostFont = new Font("�����ٸ����", Font.PLAIN, 10);

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

		JLabel ID_FIND_LABEL = new JLabel("���̵� ã��");
		ID_FIND_LABEL.setBounds(130, 40, 70, 15);
		FindRegist.getContentPane().add(ID_FIND_LABEL);

		MEMBER_NAME_TF = new HintTextField("�̸� �Է�");
		MEMBER_NAME_TF.setBounds(55, 80, 220, 30);
		FindRegist.getContentPane().add(MEMBER_NAME_TF);
		MEMBER_NAME_TF.setColumns(10);

		JButton ID_FIND_BTN = new JButton("ã��");
		ID_FIND_BTN.setBackground(new Color(255, 218, 0));
		ID_FIND_BTN.setBounds(55, 140, 220, 30);
		FindRegist.getContentPane().add(ID_FIND_BTN);
		ID_FIND_BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // id ã�� ��ư �������� ����
				String name = MEMBER_NAME_TF.getText().trim();
				String phone = MEMBER_TEL_TF.getText().trim();
				if (db.getFindId(name, phone) != null) { /* DB�� ����� �̸��� ��ȭ��ȣ�� ���Ͽ� ID ��ȸ */
					String memberName = db.getFindId(name, phone).getMEMBER_Name();
					String memberPhoneNum = db.getFindId(name, phone).getTEL_Num();
					if (memberName != null && db.getFindId(name, phone).getMEMBER_Name().equals(name)
							&& memberPhoneNum != null && db.getFindId(name, phone).getTEL_Num().equals(phone)) {
						JOptionPane.showMessageDialog(null, "���̵�� " + db.getFindId(name, phone).getMEMBER_ID() + " �Դϴ�");
					} else {
						JOptionPane.showMessageDialog(null, "�Է��� ������ ��ġ�ϴ� ID�� �����ϴ�.", "ID ã�� ����",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "�ش� ����ڸ� ã�� �� �����ϴ�.", "ã�� ����", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		MEMBER_TEL_TF = new HintTextField("��ȭ��ȣ �Է�('-' �� ������ ���� 11�ڸ��� �Է�)");
		MEMBER_TEL_TF.setColumns(10);
		MEMBER_TEL_TF.setBounds(55, 110, 220, 30);
		FindRegist.getContentPane().add(MEMBER_TEL_TF);
		FindRegist.setResizable(false);
	}
}
