package nlb_core;


import java.awt.*;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.ImageIcon;

public class LoginFrame {

   private JFrame frmNextLevelBank;
   private JTextField idField;
   private JTextField pwField;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               LoginFrame window = new LoginFrame();
               window.frmNextLevelBank.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public LoginFrame() {
      initialize();
      frmNextLevelBank.setVisible(true);
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      frmNextLevelBank = new JFrame(); //프레임 객체 생성
      frmNextLevelBank.getContentPane().setBackground(new Color(255, 228, 0));
      frmNextLevelBank.setSize(500,800);
      Dimension frameSize = frmNextLevelBank.getSize();      // 모니터 크기
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  //frame 크기 
      frmNextLevelBank.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);   // frame 위치 설정
      frmNextLevelBank.setTitle("Next Level Bank Login"); 
      frmNextLevelBank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frmNextLevelBank.getContentPane().setLayout(null);
      frmNextLevelBank.setResizable(false);
      
      idField = new JTextField();
      idField.setBounds(120, 340, 260, 40);
      frmNextLevelBank.getContentPane().add(idField);
      idField.setColumns(10);
      
      pwField = new JTextField();
      pwField.setBounds(120, 380, 260, 40);
      frmNextLevelBank.getContentPane().add(pwField);
      pwField.setColumns(10);
      
      JButton joinBtn = new JButton("로그인");
      joinBtn.setForeground(new Color(0, 0, 0));
      joinBtn.setFont(new Font("나눔바른고딕", Font.BOLD, 18));
      joinBtn.setBackground(new Color(255, 228, 0));
      joinBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            frmNextLevelBank.dispose();
            new MainFrame();
         }
      });
      joinBtn.setBounds(120, 430, 260, 40);
      frmNextLevelBank.getContentPane().add(joinBtn);
      
      JLabel LOGO_LABEL = new JLabel("Next Level Bank");
      LOGO_LABEL.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
      
      LOGO_LABEL.setBounds(195, 280, 110, 30);
      frmNextLevelBank.getContentPane().add(LOGO_LABEL);
      
      JButton registerBtn = new JButton("\uD68C\uC6D0\uAC00\uC785");
      registerBtn.setForeground(new Color(122, 122, 122));
      registerBtn.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
      registerBtn.setBackground(new Color(255, 228, 0));
      registerBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new JOINFrame();
         }
      });
      registerBtn.setBounds(120, 480, 80, 20);
      frmNextLevelBank.getContentPane().add(registerBtn);
      
      JButton findPwBtn = new JButton("ID\uCC3E\uAE30");
      findPwBtn.setForeground(new Color(122, 122, 122));
      findPwBtn.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
      findPwBtn.setBackground(new Color(255, 228, 0));
      findPwBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new IDFind();
         }
      });
      findPwBtn.setBounds(210, 480, 80, 20);
      frmNextLevelBank.getContentPane().add(findPwBtn);
        
      
      //아이콘 생성
      ImageIcon nlb_logo_icon = new ImageIcon(LoginFrame.class.getResource("/nlb_core/NLB_LOGO.png"));
      Image nlb_logo_img = nlb_logo_icon.getImage();
      Image change_nlb_logo_img = nlb_logo_img.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
      ImageIcon change_nlb_logo_icon = new ImageIcon(change_nlb_logo_img);
      JLabel NLB_LOGO = new JLabel(change_nlb_logo_icon); //라벨 생성
      
      NLB_LOGO.setBounds(175, 120, 150, 150);
      frmNextLevelBank.getContentPane().add(NLB_LOGO);
      
      JButton btnPw = new JButton("PW\uCC3E\uAE30");
      btnPw.setForeground(new Color(122, 122, 122));
      btnPw.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
      btnPw.setBackground(new Color(255, 228, 0));
      btnPw.setBounds(300, 480, 80, 20);
      frmNextLevelBank.getContentPane().add(btnPw);
   }
}
