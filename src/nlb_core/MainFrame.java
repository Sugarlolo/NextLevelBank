package nlb_core;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;

public class MainFrame {

	private JFrame frmKakaobank;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmKakaobank.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		frmKakaobank.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKakaobank = new JFrame();
		frmKakaobank.getContentPane().setBackground(new Color(255, 255, 255));
		frmKakaobank.setTitle("Next Level Bank MAIN");
		frmKakaobank.setBounds(100, 100, 450, 700);
		frmKakaobank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKakaobank.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 115, 410, 516);
		frmKakaobank.getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setColumnHeaderView(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 211, 0));
		panel_1.setBounds(40, 34, 328, 175);
		panel.add(panel_1);
	}
}
