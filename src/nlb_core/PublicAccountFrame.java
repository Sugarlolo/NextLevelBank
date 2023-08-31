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
import beans.MemberBean;
import database.AccountsMgr;
import database.MemberMgr;

import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import database.AccountsMgr;

public class PublicAccountFrame {

	private JFrame frmAddfriend;
	private JTextField freindNameTF;
	private JTextField telNumberTF;
    private DefaultListModel<String> model;
	private final FrameManager frameMgr;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // â�� �߾� ��ǥ ���
	AccountsMgr mgr;
	AccountsBean bean;
	int memberAccountNum=0;
	int memberAccountBalance=0;
	String freindName =" ";
	String telNumber =" ";
    Vector<AccountsBean> friendAccountList1;
    
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
		memberAccountNum = frameMgr.getMainFrame().seletedAccountNum();
		bean.setACCOUNT_NUM(memberAccountNum);
		mgr.getAccount_balance(bean);
		memberAccountBalance = bean.getACCOUNT_BALANCE();
		JLabel memberAccountLabel = new JLabel("<html>���¹�ȣ(��������) : "+memberAccountNum+"<br>�ܾ� : "+memberAccountBalance+" </html>");
		memberAccountLabel.setFont(new Font("�����ٸ����", Font.BOLD, 15));
		memberAccountLabel.setBounds(30, 75, 430, 40);
		panel.add(memberAccountLabel);
	
	
		//ģ���̸� ��
		JLabel freindNameLabel = new JLabel("ģ���̸� : ");
		freindNameLabel.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		freindNameLabel.setBounds(30, 130, 110, 20);
		panel.add(freindNameLabel);
		
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
		freindName = freindNameTF.getText();
		
		//��ȭ��ȣ �Է��ʵ�
		telNumberTF = new JTextField();
		telNumberTF.setColumns(10);
		telNumberTF.setBounds(140, 160, 260, 20);
		panel.add(telNumberTF);
		telNumber = telNumberTF.getText();
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 199, 433, 491);
		panel.add(scrollPane);
		
		//���¹�ȣ ����Ʈ
        model = new DefaultListModel<>();  
		JList friendAccountList = new JList(model);
		friendAccountList.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		scrollPane.setViewportView(friendAccountList);
		
		//�߰��ϱ� ��ư
		JButton plusBtn = new JButton("�߰��ϱ�");
		plusBtn.setFont(new Font("�����ٸ����", Font.BOLD, 15));
		plusBtn.setBackground(new Color(255, 225, 0));
		plusBtn.setBounds(25, 700, 433, 51);
		panel.add(plusBtn);
		
        // �˻� ��ư
        ImageIcon searchIcon = new ImageIcon(MainFrame.class.getResource("/member2/searchIcon.png"));
        Image searchImg = searchIcon.getImage();
        Image changeSearchImg = searchImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon changeSearchIcon = new ImageIcon(changeSearchImg);
        JLabel searchIconLabel = new JLabel(changeSearchIcon); // �� ����
        searchIconLabel.setBounds(410, 130, 50, 50);
		panel.add(searchIconLabel);
		

		// �߰��ϱ� ��ư �׼� ������
		plusBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
 
			}
		});
	
	}
//	public void showAccountList() {   //�̸�, ��ȭ��ȣ�� ���¹�ȣ �ҷ�����
//        
//        AccountsMgr mgr = new AccountsMgr();
//        String category1 = "�Ϲ�";
//        String category2 = "��������";
//        MemberBean bean = new MemberBean();
//
//        
//        friendAccountList1 = mgr.getAccount_num(bean);
//        for(AccountsBean accountsBean1 : friendAccountList1) {
//        	model.addElement("���¹�ȣ(����� ����) : "+accountsBean1.getAccount_num());
//        	model.addElement("�ܾ� : "+accountsBean1.getAccount_balance());
//        	model.addElement(" ");    
//        }
//        //System.out.println(accountList1.get(0).getAccount_num()); //0��° ����Ʈ�� ���¹�ȣ ���
//         
//         //System.out.println(sumAccountIndex);
//        //System.out.println(accountList.get(1).getAccount_balance());
//        
//    }
}
