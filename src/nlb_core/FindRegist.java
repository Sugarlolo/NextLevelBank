package nlb_core;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class FindRegist {

	private JFrame FindRegist;
	private JTextField FindID_NameText;
	private JTextField FindPW_IDText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindRegist window = new FindRegist();
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
	public FindRegist() {
		initialize();
		FindRegist.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		FindRegist = new JFrame();
		FindRegist.setTitle("Next Level Bank_Find");
		FindRegist.setBounds(100, 100, 410, 463);
		FindRegist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FindRegist.getContentPane().setLayout(null);
		
		JLabel IDLabel = new JLabel("아이디 찾기");
		IDLabel.setBounds(12, 10, 74, 15);
		FindRegist.getContentPane().add(IDLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 29, 370, 135);
		FindRegist.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton FindID_btn = new JButton("찾기");
		FindID_btn.setBounds(261, 36, 97, 59);
		panel.add(FindID_btn);
		
		JLabel FindID_Namelabel = new JLabel("이름");
		FindID_Namelabel.setBounds(48, 44, 57, 15);
		panel.add(FindID_Namelabel);
		
		FindID_NameText = new JTextField();
		FindID_NameText.setBounds(102, 41, 116, 21);
		panel.add(FindID_NameText);
		FindID_NameText.setColumns(10);
		
		JLabel PWLabel = new JLabel("비밀번호 찾기");
		PWLabel.setBounds(12, 174, 92, 15);
		FindRegist.getContentPane().add(PWLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(12, 194, 370, 135);
		FindRegist.getContentPane().add(panel_1);
		
		JButton FindPW_btn = new JButton("찾기");
		FindPW_btn.setBounds(261, 36, 97, 59);
		panel_1.add(FindPW_btn);
		
		JLabel FindPW_IDlabel = new JLabel("아이디");
		FindPW_IDlabel.setBounds(48, 44, 57, 15);
		panel_1.add(FindPW_IDlabel);
		
		FindPW_IDText = new JTextField();
		FindPW_IDText.setColumns(10);
		FindPW_IDText.setBounds(102, 41, 116, 21);
		panel_1.add(FindPW_IDText);
		
		JButton Back_LoginFrame_btn = new JButton("돌아가기");
		Back_LoginFrame_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindRegist.setVisible(false);
				new LoginFrame();
			}
		});
		Back_LoginFrame_btn.setBounds(150, 361, 97, 41);
		FindRegist.getContentPane().add(Back_LoginFrame_btn);
		FindRegist.setResizable(false);
	}
}
