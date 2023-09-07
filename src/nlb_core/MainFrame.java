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
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 창의 중앙 좌표 계산
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
                    // MainFrame 초기화
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
		
		 // ScheduledExecutorService 초기화
        scheduler = Executors.newScheduledThreadPool(1); 
        // 5초마다 showAlert() 함수 실행
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {showAlert();}}, 0, 5, TimeUnit.SECONDS);
	}

	private void initialize() {
		
		memberId = mbean.getMEMBER_ID(); // 회원아이디
		System.out.println(mbean.getMEMBER_ID());
		// mainframe - frame
		frame = new JFrame();
		frame.setSize(500, 800); // 프레임 사이즈
		int centerX = (screenSize.width - frame.getWidth()) / 2; // 창 중앙에 frame
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
		accountList.setFont(new Font("나눔바른고딕", Font.BOLD, 18));
		scrollPane.setViewportView(accountList);
		accountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // accountList 하나만 선택 될 수 있도록
		accountList.setSelectionBackground(new Color(255, 225, 0));

		// 계좌 개설 버튼
		JButton accountPlusBTN = new JButton("+");
		accountPlusBTN.setBackground(new Color(255, 255, 255));
		accountPlusBTN.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		accountPlusBTN.setBounds(34, 648, 419, 55);
		panel.add(accountPlusBTN);

		// 이체하기 버튼
		JButton transferBTN = new JButton("이체하기");
		transferBTN.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		transferBTN.setBackground(new Color(255, 228, 0));
		transferBTN.setBounds(30, 150, 100, 40);
		panel.add(transferBTN);

		// 이체내역 버튼
		JButton transferHistoryBTN = new JButton("이체내역");
		transferHistoryBTN.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		transferHistoryBTN.setBackground(new Color(255, 228, 0));
		transferHistoryBTN.setBounds(135, 150, 105, 40);
		panel.add(transferHistoryBTN);

		// 회원아이디 라벨
		JLabel memberIDLabel = new JLabel(mbean.getMEMBER_Name());
		memberIDLabel.setFont(new Font("나눔바른고딕", Font.BOLD, 18));
		memberIDLabel.setBounds(130, 80, 60, 40);
		panel.add(memberIDLabel);

		// 인사 라벨
		JLabel greetingLabel = new JLabel("님 반갑습니다.");
		greetingLabel.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		greetingLabel.setBounds(184, 81, 300, 40);
		panel.add(greetingLabel);

		// 로고
		ImageIcon nlb_logo_icon = new ImageIcon(MainFrame.class.getResource("NLB_LOGO.png"));
		Image nlb_logo_img = nlb_logo_icon.getImage();
		Image change_nlb_logo_img = nlb_logo_img.getScaledInstance(70, 100, Image.SCALE_SMOOTH);
		ImageIcon change_nlb_logo_icon = new ImageIcon(change_nlb_logo_img);
		JLabel nlbLogoLabel = new JLabel(change_nlb_logo_icon); // 라벨 생성
		nlbLogoLabel.setBounds(30, 20, 70, 100);
		panel.add(nlbLogoLabel);

		// 프로필 form 라벨
		ImageIcon profile_top_icon = new ImageIcon(MainFrame.class.getResource("profile_top.png"));
		Image profile_top_img = profile_top_icon.getImage();
		Image change_profile_top_img = profile_top_img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon change_profile_top_icon = new ImageIcon(change_profile_top_img);
		JLabel profileTopLabel = new JLabel(change_profile_top_icon); // 라벨 생성
		profileTopLabel.setBounds(380, 45, 80, 80);
		panel.add(profileTopLabel);

		// 프로필 라벨
		ImageIcon profileIcon = new ImageIcon(MainFrame.class.getResource("profile1.png"));
		Image profileImg = profileIcon.getImage();
		Image changeProfileImg = profileImg.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon changeProfileIcon = new ImageIcon(changeProfileImg);
		JLabel profileImageLabel = new JLabel(changeProfileIcon);
		profileImageLabel.setBounds(380, 45, 80, 80);
		panel.add(profileImageLabel);

		// 모임통장 친구추가 버튼
		JButton publicMemberPlusBTN = new JButton("<html>모임통장<br>친구추가</html>");
		publicMemberPlusBTN.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		publicMemberPlusBTN.setBackground(new Color(255, 228, 0));
		publicMemberPlusBTN.setBounds(245, 150, 105, 40);
		panel.add(publicMemberPlusBTN);
		
		JButton autoTransBtn = new JButton("\uC790\uB3D9\uC774\uCCB4");
		autoTransBtn.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		autoTransBtn.setBackground(new Color(255, 228, 0));
		autoTransBtn.setBounds(355, 150, 105, 40);
		panel.add(autoTransBtn);

		showAccountList(); // DB계좌 정보 list에 뿌리기

		// accountList.getSelectedIndex();

		// 이체하기 버튼 액션 리스너
		transferBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("mainframe 이체버튼 계좌: " + selectedAccountNum());
				System.out.println("mainframe 이체 계좌 잔고: " + abean.getACCOUNT_BALANCE());
				TransferFrame tf = new TransferFrame(seletedAccountNum, abean, mbean);
			}
		});

		// 이체내역 버튼 액션리스너
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

		// 모임통장 친구추가 버튼
		publicMemberPlusBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedAccountNum();

				if (listSeletedIndex+1 > nomalAccountIndex && listSeletedIndex <=   nomalAccountIndex + pAccountIndex) { // 모임통장 선택했을때만

					PublicAccountFrame frame3 = new PublicAccountFrame();
					frame3.getFrame().setVisible(true);
//	                     frameMgr.setPublicAccountFrame(frame3);
//					 frameMgr.CustomSetVisible("publicAccountFrame");
				}else {
					JOptionPane.showMessageDialog(frame,"모임통장이 아닙니다.","경고",JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		// 계좌개설 버튼 액션리스너
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
		
		//자동이체 버튼 액션 리스너
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

	public int selectedAccountNum() { // 선택된 리스트 계좌번호

		listSeletedIndex = accountList.getSelectedIndex();
		listSeletedIndex /= 3;

		Vector<AccountsBean> tmpAccountsBeans = accountList1;
		tmpAccountsBeans.addAll(accountList2);
		Vector<AccountsPublicBean> accountsPublicBeans = accountList3;
		
		if(listSeletedIndex > nomalAccountIndex+pAccountIndex) {
			seletedAccountNum = accountsPublicBeans.get(listSeletedIndex).getACCOUNT_NUM();					
		}else {
			seletedAccountNum = tmpAccountsBeans.get(listSeletedIndex).getACCOUNT_NUM(); // transfer frame 으로 넘길 계좌번호	
		}System.out.println("선택한 계좌번호: " + seletedAccountNum);
		return seletedAccountNum;

	}

	public void showAccountList() { // 계좌 정보 디비에서 읽어서 뿌리기

		AccountsMgr mgr = new AccountsMgr();
		abean = new AccountsBean();

		//본인 일반 계좌
		abean.setMEMBER_ID(memberId);
		abean.setACCOUNT_CATEGORY("일반");
		accountList1 = mgr.getAccount_num(abean);
		for (AccountsBean accountsBean1 : accountList1) {
			nomalAccountIndex++;
			model.addElement("입출금 통장"+nomalAccountIndex +" : " + accountsBean1.getACCOUNT_NUM());
			model.addElement("잔액 : " + accountsBean1.getACCOUNT_BALANCE());
			model.addElement(" ");
		}
//        System.out.println(accountList1.get(0).getACCOUNT_NUM()); //0번째 리스트의 계좌번호 출력
//        System.out.println(accountList1.get(1).getACCOUNT_NUM());
//        System.out.println(accountList1.get(2).getACCOUNT_NUM());

		//본인 공동계좌
		abean.setMEMBER_ID(memberId);
		abean.setACCOUNT_CATEGORY("공동계좌");
		accountList2 = mgr.getAccount_num(abean);
		for (AccountsBean accountsBean2 : accountList2) {
			pAccountIndex++;
			model.addElement("모임 통장"+pAccountIndex +" : " + accountsBean2.getACCOUNT_NUM());
			model.addElement("잔액 : " + accountsBean2.getACCOUNT_BALANCE());
			model.addElement(" ");
			
		}
		
		// 참여중인 공동계좌
		mbean.setMEMBER_ID(memberId);
		accountList3 = apMgr.getPublicAccountNum(mbean);
		for(int j =0;j<accountList3.size();j++) {
			attendPAccountIndex++;
			model.addElement(accountList3.get(j).getMEMBER_ID()+"님의 "+"모임 통장"+attendPAccountIndex+" : " + accountList3.get(j).getACCOUNT_NUM());
			model.addElement("잔액 : " + accountList3.get(j).getACCOUNT_BALANCE());
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
			//1분 60
			//1시간 3600
			//1일 86400
			//1개월 2,592,000
			// 1년 31,104,000
			if(subSecond < 60) {
				JOptionPane.showMessageDialog(frame,subSecond+"초 전  "+ content,"이체알림",JOptionPane.INFORMATION_MESSAGE);
			}else if(subSecond < 3600) {
				JOptionPane.showMessageDialog(frame,subSecond/60+"분 전  "+ content,"이체알림",JOptionPane.INFORMATION_MESSAGE);
			}else if(subSecond < 86400) {
				JOptionPane.showMessageDialog(frame,subSecond/3600+"시간 전  "+ content,"이체알림",JOptionPane.INFORMATION_MESSAGE);
			}else if(subSecond < 2592000) {
				JOptionPane.showMessageDialog(frame,subSecond/86400+"일 전  "+ content,"이체알림",JOptionPane.INFORMATION_MESSAGE);
			}else if(subSecond < 31104000) {
				JOptionPane.showMessageDialog(frame,subSecond/2592000+"개월 전  "+ content,"이체알림",JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(frame,taBean.getALERT_DATE()+"  "+ content,"이체알림",JOptionPane.INFORMATION_MESSAGE);
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
