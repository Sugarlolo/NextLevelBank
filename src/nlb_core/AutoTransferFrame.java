package nlb_core;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.AbstractDocument;

import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AutoTransferFrame {

	private JFrame frmAutotransferframe;
	private JTextField transBalanceTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutoTransferFrame window = new AutoTransferFrame();
					window.frmAutotransferframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AutoTransferFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAutotransferframe = new JFrame();
		frmAutotransferframe.getContentPane().setFont(new Font("�����ٸ�����", Font.PLAIN, 13));
		frmAutotransferframe.setTitle("AUTO_TRANSFER_FRAME");
		frmAutotransferframe.getContentPane().setBackground(new Color(255, 255, 255));
		
		JPanel panel = new JPanel();
		frmAutotransferframe.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel titleLbl = new JLabel("\uC790\uB3D9\uC774\uCCB4");
		titleLbl.setFont(new Font("�����ٸ�����", Font.BOLD, 20));
		titleLbl.setBounds(30, 20, 200, 40);
		panel.add(titleLbl);
		
		JScrollPane checkSP = new JScrollPane();
		checkSP.setBounds(30, 370, 430, 300);
		panel.add(checkSP);
		
		JList AccountsList = new JList();
		AccountsList.setFont(new Font("�����ٸ�����", Font.BOLD, 12));
		checkSP.setViewportView(AccountsList);
		
		JLabel registLbl = new JLabel("\uC790\uB3D9\uC774\uCCB4 \uB4F1\uB85D");
		registLbl.setFont(new Font("�����ٸ�����", Font.BOLD, 15));
		registLbl.setBounds(30, 70, 200, 40);
		panel.add(registLbl);
		
		JLabel checkLabel = new JLabel("\uC790\uB3D9\uC774\uCCB4 \uC870\uD68C");
		checkLabel.setFont(new Font("�����ٸ�����", Font.BOLD, 15));
		checkLabel.setBounds(30, 330, 200, 40);
		panel.add(checkLabel);
		
		JButton cancelBtn = new JButton("\uC790\uB3D9\uC774\uCCB4 \uD574\uC9C0\uD558\uAE30");
		cancelBtn.setBackground(new Color(255, 225, 0));
		cancelBtn.setFont(new Font("�����ٸ�����", Font.PLAIN, 15));
		cancelBtn.setBounds(30, 686, 430, 53);
		panel.add(cancelBtn);
		
		JLabel transAccountLbl = new JLabel("\uC790\uB3D9\uC774\uCCB4 \uC774\uCCB4 \uACC4\uC88C\uBC88\uD638 : ");
		transAccountLbl.setFont(new Font("�����ٸ�����", Font.PLAIN, 15));
		transAccountLbl.setBounds(40, 120, 396, 15);
		panel.add(transAccountLbl);
		
		JLabel withdrawAccountLbl = new JLabel("\uC790\uB3D9\uC774\uCCB4 \uCD9C\uAE08 \uACC4\uC88C\uBC88\uD638 : ");
		withdrawAccountLbl.setFont(new Font("�����ٸ�����", Font.PLAIN, 15));
		withdrawAccountLbl.setBounds(40, 150, 396, 15);
		panel.add(withdrawAccountLbl);
		
		JLabel transBalaceLbl = new JLabel("\uC790\uB3D9\uC774\uCCB4 \uAE08\uC561");
		transBalaceLbl.setFont(new Font("�����ٸ�����", Font.PLAIN, 15));
		transBalaceLbl.setBounds(40, 180, 138, 15);
		panel.add(transBalaceLbl);
		
		transBalanceTF = new JTextField();
		transBalanceTF.setBounds(190, 180, 270, 20);
		panel.add(transBalanceTF);
		transBalanceTF.setColumns(10);
		((AbstractDocument) transBalanceTF.getDocument()).setDocumentFilter(new NumericFilter());

		JLabel transDateLbl = new JLabel("\uC774\uCCB4\uC77C");
		transDateLbl.setFont(new Font("�����ٸ�����", Font.PLAIN, 15));
		transDateLbl.setBounds(40, 210, 138, 15);
		panel.add(transDateLbl);
		
		JComboBox transDateCmbx = new JComboBox();
		transDateCmbx.setModel(new DefaultComboBoxModel(new String[] {"1\uC77C", "2\uC77C", "3\uC77C", "4\uC77C", "5\uC77C", "6\uC77C", "7\uC77C", "8\uC77C", "9\uC77C", "10\uC77C", "11\uC77C", "12\uC77C", "13\uC77C", "14\uC77C", "15\uC77C", "16\uC77C", "17\uC77C", "18\uC77C", "19\uC77C", "20\uC77C", "21\uC77C", "22\uC77C", "23\uC77C", "24\uC77C", "25\uC77C", "26\uC77C", "27\uC77C", "28\uC77C"}));
		transDateCmbx.setBackground(new Color(255, 255, 255));
		transDateCmbx.setBounds(190, 210, 270, 20);
		panel.add(transDateCmbx);
		
		JButton RegistBtn = new JButton("\uC790\uB3D9\uC774\uCCB4 \uB4F1\uB85D\uD558\uAE30");
		RegistBtn.setBackground(new Color(255, 225, 0));
		RegistBtn.setFont(new Font("�����ٸ�����", Font.PLAIN, 15));
		RegistBtn.setBounds(30, 250, 430, 53);
		panel.add(RegistBtn);
		frmAutotransferframe.setBounds(250, 150, 500, 800);
		frmAutotransferframe.setResizable(false);
		//frmAutotransferframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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