package nlb_core;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import beans.AccountsBean;
import beans.HistoryBean;
import database.HistoryMgr;
import database.MemberMgr;
import beans.MemberBean;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.List;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import java.sql.*;
import javax.swing.JList;

public class HistoryFrame extends JFrame{

	private JPanel Account_Histroy;
	private JLabel Account_Balance;
	private String Member_ID;
	HistoryMgr th;
	Vector<HistoryBean> vlist;
	int doAccount = 0;
	List list;
	JTable historyTable;
	DefaultTableModel tableModel;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					HistoryFrame frame = new HistoryFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * 
	 * @param do_account
	 */
	
	public HistoryFrame(int do_account, MemberBean mBean, AccountsBean aBean) {
		this.doAccount = do_account;
		setBounds(100, 100, 500, 800);
		Account_Histroy = new JPanel();
		Account_Histroy.setBackground(new Color(255, 255, 255));
		Account_Histroy.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Account_Histroy);
		Account_Histroy.setLayout(null);

		JPanel History_Value = new JPanel();
		History_Value.setBackground(new Color(255, 228, 0));
		History_Value.setBounds(0, 0, 484, 254);
		Account_Histroy.add(History_Value);
		History_Value.setLayout(null);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainFrame mf = new MainFrame(mBean);
				mf.getFrame().setVisible(true);
			}
		}); 
		
		JButton transferbtn = new JButton("이체하기");
		transferbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransferFrame tf = new TransferFrame(do_account, aBean, mBean);
			}
		});
		transferbtn.setBackground(new Color(221, 199, 0));
		transferbtn.setBorderPainted(false);
		transferbtn.setBounds(44, 185, 400, 25);
		transferbtn.setFont(new Font("Dialog", Font.PLAIN, 18));
		History_Value.add(transferbtn);

		JButton btnNewButton_1 = new JButton("<");
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 12));
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(new Color(255, 228, 0));
		btnNewButton_1.setBounds(12, 10, 63, 42);
		History_Value.add(btnNewButton_1);

		ImageIcon icon = new ImageIcon("src/nlb_core/wheel.png");
		Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		icon.setImage(image); // 이미지 크기를 조정한 이미지로 설정

		JButton btnNewButton_2 = new JButton(icon);
		btnNewButton_2.setForeground(new Color(0, 0, 0));
		btnNewButton_2.setBackground(new Color(255, 228, 0));
		btnNewButton_2.setIcon(icon);
		btnNewButton_2.setBackground(new Color(255, 228, 0));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnNewButton_2.setBounds(407, 10, 52, 42);
		History_Value.add(btnNewButton_2);

		JLabel Account_Master = new JLabel();
		Account_Master.setBounds(195, 16, 102, 30);
		History_Value.add(Account_Master);
		MemberMgr memberDB = new MemberMgr();
		MemberBean member = memberDB.getMemberByID(Member_ID);
		Account_Master.setText(member.getMEMBER_Name());

		JLabel Account_Num = new JLabel("New label");
		Account_Num.setBounds(168, 66, 167, 25);
		History_Value.add(Account_Num);

		JLabel Account_Balance = new JLabel();
		Account_Balance.setFont(new Font("굴림", Font.BOLD, 50));
		Account_Balance.setBounds(70, 105, 300, 70);
		Account_Balance.setHorizontalAlignment(JLabel.CENTER);
		History_Value.add(Account_Balance);
		HistoryMgr transferHistory = new HistoryMgr();
		HistoryBean historybean = transferHistory.getHistoryList1(doAccount);
		Account_Balance.setText(String.valueOf(historybean.getAccount_Balance()) + "원"); // historybean 사용

		JPanel History_Value2 = new JPanel();
		History_Value2.setBackground(new Color(255, 255, 255));
		History_Value2.setBounds(0, 248, 484, 49);
		Account_Histroy.add(History_Value2);

		ImageIcon icon2 = new ImageIcon("src/nlb_core/find.png");
		Image image2 = icon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		icon2.setImage(image2); // 이미지 크기를 조정한 이미지로 설정

		JButton Find_History = new JButton("");
		Find_History.setBackground(new Color(255, 255, 255));
		Find_History.setBounds(12, 10, 45, 33);
		Find_History.setIcon(icon2);
		Find_History.setBorderPainted(false);
		Find_History.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		History_Value2.setLayout(null);
		History_Value2.add(Find_History);

		ImageIcon icon3 = new ImageIcon("src/nlb_core/etc.png");
		Image image3 = icon3.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		icon3.setImage(image3); // 이미지 크기를 조정한 이미지로 설정

		JButton History_Period = new JButton("1개월ㆍ전체ㆍ최신");
		History_Period.setBackground(new Color(255, 255, 255));
		History_Period.setFont(new Font("굴림", Font.PLAIN, 12));
		History_Period.setBounds(334, 10, 138, 23);
		History_Period.setBorderPainted(false);
		History_Period.setIconTextGap(-120); // 아이콘과 텍스트 사이의 간격 설정
		History_Period.setIcon(icon3); // 아이콘 설정
		History_Value2.add(History_Period);

		JPanel History_Value3 = new JPanel();
		History_Value3.setBackground(new Color(255, 255, 255));
		History_Value3.setBounds(0, 296, 484, 465);
		
		vlist = transferHistory.getHistoryList(doAccount);
		tableModel = new DefaultTableModel();
		historyTable = new JTable(tableModel);
		tableModel.addColumn("이체번호");
		tableModel.addColumn("이체날짜");
		tableModel.addColumn("메모");
		tableModel.addColumn("이체유형");
		tableModel.addColumn("계좌잔액");
		tableModel.addColumn("보내는곳");

		// 데이터를 테이블 모델에 추가
		for (int i = 0; i < vlist.size(); i++) {
		    HistoryBean bean = vlist.get(i);
		    String[] rowData = {
		        String.valueOf(i + 1),
		        bean.getTransfer_Date(),
		        bean.getTransfer_Memo(),
		        bean.getTransfer_Category(),
		        String.valueOf(bean.getTransfer_Balance()),
		        String.valueOf(bean.getTransfer_Take_Account())
		    };
		    tableModel.addRow(rowData);
		}
		JScrollPane tableScrollPane = new JScrollPane(historyTable);
		tableScrollPane.setBounds(0, 0, 484, 465);
		History_Value3.setLayout(new BorderLayout()); // 세로로 정렬되도록 설정
		History_Value3.add(tableScrollPane, BorderLayout.CENTER); // JScrollPane를 프레임에 추가
		Account_Histroy.add(History_Value3);
		setVisible(true);
}
}