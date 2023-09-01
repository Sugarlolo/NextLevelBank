package nlb_core;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Member;
import java.nio.channels.SelectableChannel;
import java.security.PublicKey;
import javax.swing.*;
import java.util.Vector;
import beans.AccountsBean;
import beans.MemberBean;
import database.AccountsMgr;

public class MainFrame {

	private JFrame frame;
	private DefaultListModel<String> model;
	private JList<String> accountList;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // â�� �߾� ��ǥ ���

	int nomalAccountIndex = 0;
	int publicAccountIndex = 0;
	int sumAccountIndex = 0;
	int listSeletedIndex = 0;
	int seletedAccountNum = 0;
	AccountsBean abean;
	MemberBean mbean;
	String memberId = "";

	Vector<AccountsBean> accountList1;
	Vector<AccountsBean> accountList2;

	public final FrameManager frameMgr;

	int i = 1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// AccountPlusFrame frame2 = new AccountPlusFrame(AccountsBean abean);
					// PublicAccountFrame frame3 = new PublicAccountFrame();
					// frame2.getFrame().setVisible(false);
					// frame3.getFrame().setVisible(false);

					// frameMgr.setAccountPlusFrame(frame2);
					// frameMgr.setPublicAccountFrame(frame3);

					// PublicAccountFrame frame3 = new PublicAccountFrame();
					// frame3.getFrame().setVisible(false);
					// frameMgr.setPublicAccountFrame(frame3);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public MainFrame(MemberBean bean) {
		// AccountPlusFrame frame2 = new AccountPlusFrame(AccountsBean abean);
		frameMgr = FrameManager.getInstance();

		frameMgr.setMainFrame(this);
		// frameMgr.setAccountPlusFrame(frame2);

		this.mbean = bean;
		initialize();
	}

	private void initialize() {
		memberId = mbean.getMEMBER_ID(); // ȸ�����̵�
		System.out.println(mbean.getMEMBER_ID());
		// mainframe - frame
		frame = new JFrame();
		frame.setSize(500, 800); // ������ ������
		int centerX = (screenSize.width - frame.getWidth()) / 2; // â �߾ӿ� frame
		int centerY = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(centerX, centerY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		// main panel
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		// scrollPane
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(30, 200, 430, 430);
		panel.add(scrollPane);

		// account_list
		model = new DefaultListModel<>();
		accountList = new JList<>(model);
		accountList.setFont(new Font("�����ٸ����", Font.BOLD, 18));
		scrollPane.setViewportView(accountList);
		accountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // accountList �ϳ��� ���� �� �� �ֵ���
		accountList.setSelectionBackground(new Color(255, 225, 0));

		// ���� ���� ��ư
		JButton accountPlusBTN = new JButton("+");
		accountPlusBTN.setBackground(new Color(255, 255, 255));
		accountPlusBTN.setFont(new Font("�����ٸ����", Font.BOLD, 20));
		accountPlusBTN.setBounds(34, 648, 419, 55);
		panel.add(accountPlusBTN);

		// ��ü�ϱ� ��ư
		JButton transferBTN = new JButton("��ü�ϱ�");
		transferBTN.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		transferBTN.setBackground(new Color(255, 228, 0));
		transferBTN.setBounds(30, 150, 140, 40);
		panel.add(transferBTN);

		// ��ü���� ��ư
		JButton transferHistoryBTN = new JButton("��ü����");
		transferHistoryBTN.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		transferHistoryBTN.setBackground(new Color(255, 228, 0));
		transferHistoryBTN.setBounds(175, 150, 140, 40);
		panel.add(transferHistoryBTN);

		// ȸ�����̵� ��
		JLabel memberIDLabel = new JLabel(mbean.getMEMBER_Name());
		memberIDLabel.setFont(new Font("�����ٸ����", Font.BOLD, 20));
		memberIDLabel.setBounds(115, 80, 60, 40);
		panel.add(memberIDLabel);

		// �λ� ��
		JLabel greetingLabel = new JLabel("�� �ݰ����ϴ�.");
		greetingLabel.setFont(new Font("�����ٸ����", Font.BOLD, 15));
		greetingLabel.setBounds(175, 82, 100, 40);
		panel.add(greetingLabel);

		// �ΰ�
		ImageIcon nlb_logo_icon = new ImageIcon(MainFrame.class.getResource("NLB_LOGO.png"));
		Image nlb_logo_img = nlb_logo_icon.getImage();
		Image change_nlb_logo_img = nlb_logo_img.getScaledInstance(70, 100, Image.SCALE_SMOOTH);
		ImageIcon change_nlb_logo_icon = new ImageIcon(change_nlb_logo_img);
		JLabel nlbLogoLabel = new JLabel(change_nlb_logo_icon); // �� ����
		nlbLogoLabel.setBounds(30, 20, 70, 100);
		panel.add(nlbLogoLabel);

		// ������ form ��
		ImageIcon profile_top_icon = new ImageIcon(MainFrame.class.getResource("profile_top.png"));
		Image profile_top_img = profile_top_icon.getImage();
		Image change_profile_top_img = profile_top_img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon change_profile_top_icon = new ImageIcon(change_profile_top_img);
		JLabel profileTopLabel = new JLabel(change_profile_top_icon); // �� ����
		profileTopLabel.setBounds(380, 45, 80, 80);
		panel.add(profileTopLabel);

		// ������ ��
		ImageIcon profileIcon = new ImageIcon(MainFrame.class.getResource("profile1.png"));
		Image profileImg = profileIcon.getImage();
		Image changeProfileImg = profileImg.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon changeProfileIcon = new ImageIcon(changeProfileImg);
		JLabel profileImageLabel = new JLabel(changeProfileIcon);
		profileImageLabel.setBounds(380, 45, 80, 80);
		panel.add(profileImageLabel);

		// �������� ģ���߰� ��ư
		JButton publicMemberPlusBTN = new JButton("<html>��������<br>ģ���߰�</html>");
		publicMemberPlusBTN.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		publicMemberPlusBTN.setBackground(new Color(255, 228, 0));
		publicMemberPlusBTN.setBounds(320, 150, 140, 40);
		panel.add(publicMemberPlusBTN);

		showAccountList(); // DB���� ���� list�� �Ѹ���

		// accountList.getSelectedIndex();

		// ��ü�ϱ� ��ư �׼� ������
		transferBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("mainframe ��ü��ư ����: " + seletedAccountNum());
				System.out.println("mainframe ��ü ���� �ܰ�: " + abean.getACCOUNT_BALANCE());
				TransferFrame tf = new TransferFrame(seletedAccountNum, abean, mbean);
				frame.dispose();
			}
		});

		// ��ü���� ��ư �׼Ǹ�����
		transferHistoryBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(seletedAccountNum());
				HistoryFrame hf = new HistoryFrame(seletedAccountNum(), mbean, abean);
				hf.setVisible(true);
				frame.dispose();
			}
		});

		// �������� ģ���߰� ��ư
		publicMemberPlusBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (seletedAccountNum() > accountList1.size()) { // �������� ������������

					PublicAccountFrame frame3 = new PublicAccountFrame();
					frame3.getFrame().setVisible(true);
//	                     frameMgr.setPublicAccountFrame(frame3);
//					 frameMgr.CustomSetVisible("publicAccountFrame");
				}
			}
		});

		// ���°��� ��ư �׼Ǹ�����
		accountPlusBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// frameMgr.CustomSetVisible("account");
				abean.setMEMBER_ID(memberId);
				AccountPlusFrame accountPlusFrame = new AccountPlusFrame(abean);
				accountPlusFrame.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

//    public int getMemberAccountNum() {
//		return seletedAccountNum;
//	}
	public JFrame getFrame() {
		return frame;
	}

	public void deleteAccountList() {
		model.clear();

	}

	public int seletedAccountNum() { // ���õ� ����Ʈ ���¹�ȣ

		listSeletedIndex = accountList.getSelectedIndex();
		listSeletedIndex /= 3;

		Vector<AccountsBean> tmpAccountsBeans = accountList1;
		tmpAccountsBeans.addAll(accountList2);

		seletedAccountNum = tmpAccountsBeans.get(listSeletedIndex).getACCOUNT_NUM(); // transfer frame ���� �ѱ� ���¹�ȣ
		System.out.println("������ ���¹�ȣ: " + seletedAccountNum);
		return seletedAccountNum;

	}

	public void showAccountList() { // ���� ���� ��񿡼� �о �Ѹ���

		AccountsMgr mgr = new AccountsMgr();
		String category1 = "�Ϲ�";
		String category2 = "��������";
		abean = new AccountsBean();

		abean.setMEMBER_ID(memberId);
		abean.setACCOUNT_CATEGORY("�Ϲ�");
		accountList1 = mgr.getAccount_num(abean);
		for (AccountsBean accountsBean1 : accountList1) {
			model.addElement("���¹�ȣ(����� ����) : " + accountsBean1.getACCOUNT_NUM());
			model.addElement("�ܾ� : " + accountsBean1.getACCOUNT_BALANCE());
			model.addElement(" ");
			nomalAccountIndex++;
		}
//        System.out.println(accountList1.get(0).getACCOUNT_NUM()); //0��° ����Ʈ�� ���¹�ȣ ���
//        System.out.println(accountList1.get(1).getACCOUNT_NUM());
//        System.out.println(accountList1.get(2).getACCOUNT_NUM());

		abean.setMEMBER_ID(memberId);
		abean.setACCOUNT_CATEGORY("��������");
		accountList2 = mgr.getAccount_num(abean);
		for (AccountsBean accountsBean2 : accountList2) {
			model.addElement("���¹�ȣ(���� ����) : " + accountsBean2.getACCOUNT_NUM());
			model.addElement("�ܾ� : " + accountsBean2.getACCOUNT_BALANCE());
			model.addElement(" ");
			publicAccountIndex++;
		}

		sumAccountIndex = nomalAccountIndex + publicAccountIndex;
//        System.out.println(sumAccountIndex);
//        System.out.println(accountList1.get(0).getACCOUNT_NUM());

	}
}
