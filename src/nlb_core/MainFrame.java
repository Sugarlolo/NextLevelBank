package nlb_core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Member;
import java.nio.channels.SelectableChannel;
import java.security.PublicKey;
import javax.swing.*;
import java.util.Vector;
import beans.AccountsBean;
import beans.AccountsPublicBean;
import beans.MemberBean;
import beans.TransferAlertBean;
import database.AccountsMgr;
import database.AccountsPublicMgr;
import database.TransferAlertMgr;
import nlb_core.MainFrame.SharedData;

public class MainFrame {

	private JFrame frame;
	private DefaultListModel<String> model;
	private JList<String> accountList;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // â�� �߾� ��ǥ ���
	private ScheduledExecutorService scheduler;
	
	int nomalAccountIndex = 0;
	int pAccountIndex = 0;
	int attendPAccountIndex = 0;
	int sumAccountIndex = 0;
	int listSeletedIndex = 0;
	int seletedAccountNum = 0;
	String memberId = "";
	int subSecond = 0;
	int alertNo=0;
	int alertIndex;
	
	public final FrameManager frameMgr;
	AccountsBean abean;
	MemberBean mbean;
	Vector<AccountsBean> accountList1;
	Vector<AccountsBean> accountList2;
	Vector<AccountsPublicBean> accountList3;
	AccountsPublicBean apBean = new AccountsPublicBean();
	AccountsPublicMgr apMgr = new AccountsPublicMgr();
	TransferAlertMgr taMgr = new TransferAlertMgr();
	TransferAlertBean taBean = new TransferAlertBean();
	
	
	   
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
                    // MainFrame �ʱ�ȭ
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public MainFrame(MemberBean bean) {
		// AccountPlusFrame frame2 = new AccountPlusFrame(AccountsBean abean);
		frameMgr = FrameManager.getInstance();

		frameMgr.setMainFrame(this);
		// frameMgr.setAccountPlusFrame(frame2);

		this.mbean = bean;
		initialize();
		
		 // ScheduledExecutorService �ʱ�ȭ
        scheduler = Executors.newScheduledThreadPool(1); 
        // 5�ʸ��� showAlert() �Լ� ����
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {showAlert();}}, 0, 5, TimeUnit.SECONDS);
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
		transferBTN.setBounds(30, 150, 100, 40);
		panel.add(transferBTN);

		// ��ü���� ��ư
		JButton transferHistoryBTN = new JButton("��ü����");
		transferHistoryBTN.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		transferHistoryBTN.setBackground(new Color(255, 228, 0));
		transferHistoryBTN.setBounds(135, 150, 105, 40);
		panel.add(transferHistoryBTN);

		// ȸ�����̵� ��
		JLabel memberIDLabel = new JLabel(mbean.getMEMBER_Name());
		memberIDLabel.setFont(new Font("�����ٸ����", Font.BOLD, 18));
		memberIDLabel.setBounds(130, 80, 60, 40);
		panel.add(memberIDLabel);

		// �λ� ��
		JLabel greetingLabel = new JLabel("�� �ݰ����ϴ�.");
		greetingLabel.setFont(new Font("�����ٸ����", Font.BOLD, 15));
		greetingLabel.setBounds(184, 81, 300, 40);
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
		publicMemberPlusBTN.setBounds(245, 150, 105, 40);
		panel.add(publicMemberPlusBTN);
		
		JButton autoTransBtn = new JButton("\uC790\uB3D9\uC774\uCCB4");
		autoTransBtn.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		autoTransBtn.setBackground(new Color(255, 228, 0));
		autoTransBtn.setBounds(355, 150, 105, 40);
		panel.add(autoTransBtn);

		showAccountList(); // DB���� ���� list�� �Ѹ���

		// accountList.getSelectedIndex();

		// ��ü�ϱ� ��ư �׼� ������
		transferBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("mainframe ��ü��ư ����: " + selectedAccountNum());
				System.out.println("mainframe ��ü ���� �ܰ�: " + abean.getACCOUNT_BALANCE());
				TransferFrame tf = new TransferFrame(seletedAccountNum, abean, mbean);
			}
		});

		// ��ü���� ��ư �׼Ǹ�����
		transferHistoryBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(selectedAccountNum());
				HistoryFrame hf = new HistoryFrame(seletedAccountNum, mbean, abean);
				hf.setVisible(true);
				SharedData.setFlag(1);
				frame.dispose();
			}
		});

		// �������� ģ���߰� ��ư
		publicMemberPlusBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedAccountNum();

				if (listSeletedIndex+1 > nomalAccountIndex && listSeletedIndex <=   nomalAccountIndex + pAccountIndex) { // �������� ������������

					PublicAccountFrame frame3 = new PublicAccountFrame();
					frame3.getFrame().setVisible(true);
//	                     frameMgr.setPublicAccountFrame(frame3);
//					 frameMgr.CustomSetVisible("publicAccountFrame");
				}else {
					JOptionPane.showMessageDialog(frame,"���������� �ƴմϴ�.","���",JOptionPane.WARNING_MESSAGE);
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
		
		//�ڵ���ü ��ư �׼� ������
		autoTransBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AutoTransferFrame atFrame = new AutoTransferFrame(); 
				atFrame.getFrame().setVisible(true);
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

	public int selectedAccountNum() { // ���õ� ����Ʈ ���¹�ȣ

		listSeletedIndex = accountList.getSelectedIndex();
		listSeletedIndex /= 3;

		Vector<AccountsBean> tmpAccountsBeans = accountList1;
		tmpAccountsBeans.addAll(accountList2);
		Vector<AccountsPublicBean> accountsPublicBeans = accountList3;
		
		if(listSeletedIndex > nomalAccountIndex+pAccountIndex) {
			seletedAccountNum = accountsPublicBeans.get(listSeletedIndex).getACCOUNT_NUM();					
		}else {
			seletedAccountNum = tmpAccountsBeans.get(listSeletedIndex).getACCOUNT_NUM(); // transfer frame ���� �ѱ� ���¹�ȣ	
		}System.out.println("������ ���¹�ȣ: " + seletedAccountNum);
		return seletedAccountNum;

	}

	public void showAccountList() { // ���� ���� ��񿡼� �о �Ѹ���

		AccountsMgr mgr = new AccountsMgr();
		abean = new AccountsBean();

		//���� �Ϲ� ����
		abean.setMEMBER_ID(memberId);
		abean.setACCOUNT_CATEGORY("�Ϲ�");
		accountList1 = mgr.getAccount_num(abean);
		for (AccountsBean accountsBean1 : accountList1) {
			nomalAccountIndex++;
			model.addElement("����� ����"+nomalAccountIndex +" : " + accountsBean1.getACCOUNT_NUM());
			model.addElement("�ܾ� : " + accountsBean1.getACCOUNT_BALANCE());
			model.addElement(" ");
		}
//        System.out.println(accountList1.get(0).getACCOUNT_NUM()); //0��° ����Ʈ�� ���¹�ȣ ���
//        System.out.println(accountList1.get(1).getACCOUNT_NUM());
//        System.out.println(accountList1.get(2).getACCOUNT_NUM());

		//���� ��������
		abean.setMEMBER_ID(memberId);
		abean.setACCOUNT_CATEGORY("��������");
		accountList2 = mgr.getAccount_num(abean);
		for (AccountsBean accountsBean2 : accountList2) {
			pAccountIndex++;
			model.addElement("���� ����"+pAccountIndex +" : " + accountsBean2.getACCOUNT_NUM());
			model.addElement("�ܾ� : " + accountsBean2.getACCOUNT_BALANCE());
			model.addElement(" ");
			
		}
		
		// �������� ��������
		mbean.setMEMBER_ID(memberId);
		accountList3 = apMgr.getPublicAccountNum(mbean);
		for(int j =0;j<accountList3.size();j++) {
			attendPAccountIndex++;
			model.addElement(accountList3.get(j).getMEMBER_ID()+"���� "+"���� ����"+attendPAccountIndex+" : " + accountList3.get(j).getACCOUNT_NUM());
			model.addElement("�ܾ� : " + accountList3.get(j).getACCOUNT_BALANCE());
			model.addElement(" ");
			
		}
		
		sumAccountIndex = nomalAccountIndex + pAccountIndex + attendPAccountIndex;
//        System.out.println(sumAccountIndex);
//        System.out.println(accountList1.get(0).getACCOUNT_NUM());

	}

	public void showAlert() {
		taBean.setALERT_MEMBER_ID(memberId);
		subSecond = taMgr.getSubSeconds(taBean);
		
		taBean.setALERT_MEMBER_ID(memberId);
		taMgr.getAlert(taBean);
		String content = taBean.getALERT_CONTENT(); 
		alertNo = taBean.getALERT_NO();

		if (alertNo>alertIndex) {
			//1�� 60
			//1�ð� 3600
			//1�� 86400
			//1���� 2,592,000
			// 1�� 31,104,000
			if(subSecond < 60) {
				JOptionPane.showMessageDialog(frame,subSecond+"�� ��  "+ content,"��ü�˸�",JOptionPane.INFORMATION_MESSAGE);
			}else if(subSecond < 3600) {
				JOptionPane.showMessageDialog(frame,subSecond/60+"�� ��  "+ content,"��ü�˸�",JOptionPane.INFORMATION_MESSAGE);
			}else if(subSecond < 86400) {
				JOptionPane.showMessageDialog(frame,subSecond/3600+"�ð� ��  "+ content,"��ü�˸�",JOptionPane.INFORMATION_MESSAGE);
			}else if(subSecond < 2592000) {
				JOptionPane.showMessageDialog(frame,subSecond/86400+"�� ��  "+ content,"��ü�˸�",JOptionPane.INFORMATION_MESSAGE);
			}else if(subSecond < 31104000) {
				JOptionPane.showMessageDialog(frame,subSecond/2592000+"���� ��  "+ content,"��ü�˸�",JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(frame,taBean.getALERT_DATE()+"  "+ content,"��ü�˸�",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		alertIndex = alertNo;
	}
	public class SharedData {
	    private static int flag = 0;

	    public static int getFlag() {
	        return flag;
	    }

	    public static void setFlag(int value) {
	        flag = value;
	    }
	}

}
