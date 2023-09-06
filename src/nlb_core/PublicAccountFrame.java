package nlb_core; 
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import beans.AccountsBean;
import beans.AccountsPublicBean;
import beans.MemberBean;
import database.AccountsMgr;
import database.AccountsPublicMgr;
import database.MemberMgr;

import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import database.AccountsMgr;

public class PublicAccountFrame {

	private JFrame frmAddfriend;
	private JTextField freindNameTF;
	private JTextField telNumberTF;
    private DefaultListModel<String> model;
    private JList friendAccountList;
    
	private final FrameManager frameMgr;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // â�� �߾� ��ǥ ���
	AccountsMgr mgr;
	AccountsBean bean;
	int memberAccountNum=0;
	int memberAccountBalance=0;
	String friendName =" "; //search friend Name
	String telNumber =" ";
    Vector<AccountsBean> friendAccountList1;
	int listSeletedIdx = 0;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PublicAccountFrame window = new PublicAccountFrame();
					window.frmAddfriend.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    public JFrame getFrame() {
    	return frmAddfriend;
    }
    
	public PublicAccountFrame() {
		frameMgr = FrameManager.getInstance();
		initialize();
	}

	private void initialize() {
		frmAddfriend = new JFrame();
		frmAddfriend.setTitle("AddFriend");
		frmAddfriend.setSize(500, 800); //������ ������
	    int centerX = (screenSize.width - frmAddfriend.getWidth()) / 2; // â �߾ӿ� frame
	    int centerY = (screenSize.height - frmAddfriend.getHeight()) / 2;
	    frmAddfriend.setLocation(centerX,centerY);
		frmAddfriend.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		frmAddfriend.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//���� ��
		JLabel lblNewLabel = new JLabel("�������� ģ���߰�");
		lblNewLabel.setFont(new Font("�����ٸ����", Font.BOLD, 25));
		lblNewLabel.setBounds(30, 30, 200, 30);
		panel.add(lblNewLabel);
		
		//ȸ�� ���¹�ȣ, �ܾ� ��
		mgr = new AccountsMgr();

		AccountsBean bean = new AccountsBean();
		memberAccountNum = frameMgr.getMainFrame().selectedAccountNum();
		bean.setACCOUNT_NUM(memberAccountNum);
		mgr.getAccount_balance(bean);
		memberAccountBalance = bean.getACCOUNT_BALANCE();
		JLabel memberAccountLabel = new JLabel("<html>���¹�ȣ(��������) : "+memberAccountNum+"<br>�ܾ� : "+memberAccountBalance+" </html>");
		memberAccountLabel.setFont(new Font("�����ٸ����", Font.BOLD, 15));
		memberAccountLabel.setBounds(30, 75, 430, 40);
		panel.add(memberAccountLabel);
	
	
		//ģ���̸� ��
		JLabel friendNameLabel = new JLabel("ģ���̸� : ");
		friendNameLabel.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		friendNameLabel.setBounds(30, 130, 110, 20);
		panel.add(friendNameLabel);
		
		//�ڵ�����ȣ ��
		JLabel telNumberLabel = new JLabel("�ڵ��� ��ȣ : ");
		telNumberLabel.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		telNumberLabel.setBounds(30, 160, 110, 20);
		panel.add(telNumberLabel);
		
		//ģ���̸� �Է��ʵ�
		freindNameTF = new JTextField();
		freindNameTF.setBounds(140, 130, 260, 20);
		panel.add(freindNameTF);
		freindNameTF.setColumns(10);
		
		//��ȭ��ȣ �Է��ʵ�
		telNumberTF = new JTextField();
		telNumberTF.setColumns(10);
		telNumberTF.setBounds(140, 160, 260, 20);
		panel.add(telNumberTF);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 199, 433, 491);
		panel.add(scrollPane);
		
		//���¹�ȣ ����Ʈ
		model = new DefaultListModel<>();  
		friendAccountList = new JList(model);
		friendAccountList.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		friendAccountList.setSelectionBackground(new Color(255,225,0));
		model.addElement(" ");
		scrollPane.setViewportView(friendAccountList);
		
		//�߰��ϱ� ��ư
		JButton plusBtn = new JButton("�߰��ϱ�");
		plusBtn.setFont(new Font("�����ٸ����", Font.BOLD, 15));
		plusBtn.setBackground(new Color(255, 225, 0));
		plusBtn.setBounds(25, 700, 433, 51);
		panel.add(plusBtn);
		
        // �˻� ��ư
        ImageIcon searchIcon = new ImageIcon(MainFrame.class.getResource("searchIcon.png"));
        Image searchImg = searchIcon.getImage();
        Image changeSearchImg = searchImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon changeSearchIcon = new ImageIcon(changeSearchImg);
        JButton searchIconBtn = new JButton(changeSearchIcon); // �� ����
        searchIconBtn.setBackground(new Color(255, 255, 255));
        searchIconBtn.setBounds(410, 130, 50, 50);
		panel.add(searchIconBtn);
		
		// �˻��ϱ� ��ư �׼� ������
		searchIconBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.clear();
				showAccountList();
				friendAccountList.repaint();
			}
		});
		
		//�߰��ϱ� ��ư �׼� ������
		plusBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AccountsPublicMgr mgr = new AccountsPublicMgr();
				AccountsPublicBean aPBean = new AccountsPublicBean();
				
				aPBean.setACCOUNT_NUM(memberAccountNum);
				System.out.println("memberAccountNum : "+memberAccountNum);
				
				int seletedNUM = seletedAccountNum();
				System.out.println("seletedNUM : "+seletedNUM);
				
				int memberNum = Integer.parseInt(model.getElementAt(seletedNUM));
				System.out.println("memberNum :"+memberNum);
				aPBean.setACCOUNT_PUBLIC_MEMBER_NUM(memberNum);
					
				Boolean flag = mgr.InsertAccountPublic(aPBean);
				System.out.println(flag);
				
				if(flag) {
					JOptionPane.showMessageDialog(frmAddfriend,"�������� ģ���߰��� �Ϸ�Ǿ����ϴ�.","�ȳ�",JOptionPane.INFORMATION_MESSAGE);
					frmAddfriend.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(frmAddfriend,"�������� ģ���߰��� �����Ͽ����ϴ�.","���",JOptionPane.WARNING_MESSAGE);
					
				}
			}
		});
	
	}
	public void showAccountList() {   //�̸�, ��ȭ��ȣ�� ���¹�ȣ �ҷ�����
        
        AccountsMgr mgr = new AccountsMgr();
        MemberBean mbean = new MemberBean();
        Vector<AccountsBean> vAbean = new Vector<AccountsBean>();


		friendName = freindNameTF.getText();
		telNumber = telNumberTF.getText();
		
        mbean.setMEMBER_Name(friendName);
        mbean.setTEL_Num(telNumber);
        vAbean = mgr.getAccount_num2(mbean);
 
        for(int i =0;i<vAbean.size();i++) {
        	model.addElement(""+vAbean.get(i).getACCOUNT_NUM());
        }
    }
	public int seletedAccountNum() {
		
		listSeletedIdx = friendAccountList.getSelectedIndex();
		return listSeletedIdx;
	}
}
