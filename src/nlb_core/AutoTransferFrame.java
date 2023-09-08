package nlb_core;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.AbstractDocument;

import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import beans.AccountsBean;
import beans.AutoTransferBean;
import beans.MemberBean;
import database.AutoTransferMgr;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class AutoTransferFrame {
	private DefaultListModel<String> model;
	private JList<String> AccountsList;
	private JFrame frmAutotransferframe;
	private JTextField transBalanceTF;
	private JTextField withdrawAccountTF;
	private int selectedAccountNum;
	private int memberAccountNum;
	private AccountsBean aBean;
	private int listIDX;
	private Vector<AutoTransferBean> accountlist = new Vector<AutoTransferBean>();
	
	private AutoTransferBean aTBean = new AutoTransferBean();
	private MemberBean mBean;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // â�� �߾� ��ǥ ���
	private AutoTransferMgr aTMgr = new AutoTransferMgr();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//AutoTransferFrame window = new AutoTransferFrame();
					//window.frmAutotransferframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public AutoTransferFrame(MemberBean mbean,AccountsBean abean,int selectnum) {
		this.mBean = mbean;
		this.aBean = abean;
		this.memberAccountNum = selectnum;
		initialize();
	}

	private void initialize() {
		MainFrame mf = new MainFrame(mBean);
		System.out.println("�ڵ���ü������"+memberAccountNum);
		frmAutotransferframe = new JFrame();
		frmAutotransferframe.setSize(500, 800); // ������ ������
		int centerX = (screenSize.width - frmAutotransferframe.getWidth()) / 2; // â �߾ӿ� frame
		int centerY = (screenSize.height - frmAutotransferframe.getHeight()) / 2;
		frmAutotransferframe.setLocation(centerX, centerY);
		frmAutotransferframe.getContentPane().setFont(new Font("�����ٸ����", Font.PLAIN, 13));
		frmAutotransferframe.setTitle("AUTO_TRANSFER_FRAME");
		frmAutotransferframe.getContentPane().setBackground(new Color(255, 255, 255));
		
		JPanel panel = new JPanel();
		frmAutotransferframe.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel titleLbl = new JLabel("�ڵ���ü");
		titleLbl.setFont(new Font("�����ٸ����", Font.BOLD, 20));
		titleLbl.setBounds(30, 20, 200, 40);
		panel.add(titleLbl);
		
		JScrollPane checkSP = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		checkSP.setBounds(30, 370, 430, 300);
		panel.add(checkSP);
		
		model = new DefaultListModel<>();
		AccountsList = new JList<>(model);
		AccountsList.setFont(new Font("�����ٸ����", Font.BOLD, 14));
		AccountsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // accountList �ϳ��� ���� �� �� �ֵ���
		AccountsList.setSelectionBackground(new Color(255, 225, 0));
		checkSP.setViewportView(AccountsList);
		
		JLabel registLbl = new JLabel("�ڵ���ü ���");
		registLbl.setFont(new Font("�����ٸ����", Font.BOLD, 15));
		registLbl.setBounds(30, 70, 200, 40);
		panel.add(registLbl);
		
		JLabel checkLabel = new JLabel("�ڵ���ü ��ȸ");
		checkLabel.setFont(new Font("�����ٸ����", Font.BOLD, 15));
		checkLabel.setBounds(30, 330, 200, 40);
		panel.add(checkLabel);
		
		JButton cancelBtn = new JButton("�ڵ���ü �����ϱ�");
		cancelBtn.setBackground(new Color(255, 225, 0));
		cancelBtn.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		cancelBtn.setBounds(30, 686, 430, 53);
		panel.add(cancelBtn);
		
		JLabel transAccountLbl = new JLabel("�ڵ���ü ��ü ���¹�ȣ : "+memberAccountNum);
		transAccountLbl.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		transAccountLbl.setBounds(40, 120, 396, 15);
		panel.add(transAccountLbl);
		
		JLabel withdrawAccountLbl = new JLabel("�ڵ���ü ��� ���¹�ȣ :");
		withdrawAccountLbl.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		withdrawAccountLbl.setBounds(40, 150, 396, 15);
		panel.add(withdrawAccountLbl);
		
		JLabel transBalaceLbl = new JLabel("�ڵ���ü �ݾ�");
		transBalaceLbl.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		transBalaceLbl.setBounds(40, 180, 138, 15);
		panel.add(transBalaceLbl);
		
		withdrawAccountTF = new JTextField();
		withdrawAccountTF.setBounds(210, 150, 250, 20);
		panel.add(withdrawAccountTF);
		withdrawAccountTF.setColumns(10);
		((AbstractDocument) withdrawAccountTF.getDocument()).setDocumentFilter(new NumericFilter());

		transBalanceTF = new JTextField();
		transBalanceTF.setBounds(210, 180, 250, 20);
		panel.add(transBalanceTF);
		transBalanceTF.setColumns(10);
		((AbstractDocument) transBalanceTF.getDocument()).setDocumentFilter(new NumericFilter());

		
		JLabel transDateLbl = new JLabel("��ü��");
		transDateLbl.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		transDateLbl.setBounds(40, 210, 138, 15);
		panel.add(transDateLbl);
		
		JComboBox transDateCmbx = new JComboBox();
		transDateCmbx.setModel(new DefaultComboBoxModel(new String[] {"1\uC77C", "2\uC77C", "3\uC77C", "4\uC77C", "5\uC77C", "6\uC77C", "7\uC77C", "8\uC77C", "9\uC77C", "10\uC77C", "11\uC77C", "12\uC77C", "13\uC77C", "14\uC77C", "15\uC77C", "16\uC77C", "17\uC77C", "18\uC77C", "19\uC77C", "20\uC77C", "21\uC77C", "22\uC77C", "23\uC77C", "24\uC77C", "25\uC77C", "26\uC77C", "27\uC77C", "28\uC77C"}));
		transDateCmbx.setBackground(new Color(255, 255, 255));
		transDateCmbx.setBounds(210, 210, 250, 20);
		panel.add(transDateCmbx);
		
		JButton RegistBtn = new JButton("�ڵ���ü ����ϱ�");
		RegistBtn.setBackground(new Color(255, 225, 0));
		RegistBtn.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
		RegistBtn.setBounds(30, 250, 430, 53);
		panel.add(RegistBtn);
		
		//�ڵ���ü ��� �׼Ǹ�����
		RegistBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int cmbxDateNum = 0;
				String cmbxSelectedValue = (String) transDateCmbx.getSelectedItem(); // �޺��ڽ����� ���õ� ���� ������
				// ���� �κи� ����
				String[] parts = cmbxSelectedValue.split("[^0-9]+");
				for (String part : parts) {
				    if (!part.isEmpty()) {
				    	cmbxDateNum = Integer.parseInt(part);
				        }
				}
				
				boolean flag = false;
				aTBean.setTakeccountNum(Integer.parseInt(withdrawAccountTF.getText()));
				aTBean.setDoccountNum(memberAccountNum);
				aTBean.setTransBalance(Integer.parseInt(transBalanceTF.getText()));
				aTBean.setTransferDate(cmbxDateNum);
				flag = aTMgr.inserAutoTransfer(aTBean);
				if(flag) {
					JOptionPane.showMessageDialog(frmAutotransferframe,"�ڵ���ü ��� �����Ͽ����ϴ�.","�ȳ�",JOptionPane.INFORMATION_MESSAGE);
					deleteAccountList();
					showAccountList();
				}
				else{
					JOptionPane.showMessageDialog(frmAutotransferframe,"�ڵ���ü ��� �����Ͽ����ϴ�.","���",JOptionPane.WARNING_MESSAGE);
				}
				

			}
		});
		
		//�ڵ���ü ���� ��ư �׼Ǹ�����
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println(selectedAccountNum());
				aTBean.setTakeccountNum(selectedAccountNum());
				aTBean.setDoccountNum(memberAccountNum);
				boolean flag = false;
				flag = aTMgr.deleteAutoTransfer(aTBean);
				if(flag) {
					JOptionPane.showMessageDialog(frmAutotransferframe, "�ڵ���ü ������ �Ϸ�Ǿ����ϴ�.","�ȳ�",JOptionPane.INFORMATION_MESSAGE);
					deleteAccountList();
					showAccountList();
				}else {
					JOptionPane.showMessageDialog(frmAutotransferframe, "�ڵ���ü ������ �����Ͽ����ϴ�.","���",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		showAccountList();
		
		frmAutotransferframe.setResizable(false);
		//frmAutotransferframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public int selectedAccountNum() {
		listIDX = AccountsList.getSelectedIndex();
		listIDX /=4;
		selectedAccountNum = accountlist.get(listIDX).getTakeccountNum();
		
		return selectedAccountNum;
	}
	public void showAccountList() {
		listIDX = 0;
		aTBean.setDoccountNum(memberAccountNum);
		accountlist = aTMgr.selectAutoTransfer(aTBean);
		for(AutoTransferBean autotransferbean : accountlist) {
			listIDX++;
			model.addElement("�ڵ���ü �Ա� ���� : "+autotransferbean.getTakeccountNum() + " �̸� :"+autotransferbean.getMEMBER_NAME());
			model.addElement("��� ���� : "+ autotransferbean.getDoccountNum());
			model.addElement("�ڵ���ü �ݾ� : "+autotransferbean.getTransBalance() +" �ڵ���ü�� : "+autotransferbean.getTransferDate());
			model.addElement(" ");
		}
	}	
	public void deleteAccountList() {
		model.clear();

	}

	public JFrame getFrame() {
		return frmAutotransferframe;
	}

   static class NumericFilter extends DocumentFilter { // textfield ���ڸ� �Է°����ϰ� �ϴ� ����
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            // �Է� ���ڿ��� �������� Ȯ��
            if (isNumeric(string)) {
                super.insertString(fb, offset, string, attr);
            }
        }
        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            // �Է� ���ڿ��� �������� Ȯ��
            if (isNumeric(text)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
        private boolean isNumeric(String text) {
            return text.matches("\\d*"); // ���� ǥ������ ����Ͽ� ���ڸ� ���
        }
    }
}
