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
      frmNextLevelBank.getContentPane().setBackground(new Color(255, 211, 0));
      frmNextLevelBank.setSize(500,800);
      Dimension frameSize = frmNextLevelBank.getSize();      // 모니터 크기
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  //frame 크기 
      frmNextLevelBank.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);   // frame 위치 설정
      frmNextLevelBank.setTitle("Next Level Bank_Login"); 
      frmNextLevelBank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frmNextLevelBank.getContentPane().setLayout(null);
      frmNextLevelBank.setResizable(false);
      
      idField = new JTextField();
      idField.setBounds(132, 332, 226, 21);
      frmNextLevelBank.getContentPane().add(idField);
      idField.setColumns(10);
      
      pwField = new JTextField();
      pwField.setBounds(132, 363, 226, 21);
      frmNextLevelBank.getContentPane().add(pwField);
      pwField.setColumns(10);
      
      JButton joinBtn = new JButton("로그인");
      joinBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            frmNextLevelBank.dispose();
            new MainFrame();
         }
      });
      joinBtn.setBounds(133, 394, 225, 27);
      frmNextLevelBank.getContentPane().add(joinBtn);
      
      JLabel LOGO_LABEL = new JLabel("Next Level Bank");
      
      LOGO_LABEL.setBounds(190, 273, 97, 27);
      frmNextLevelBank.getContentPane().add(LOGO_LABEL);
      
      JButton registerBtn = new JButton("회원 가입");
      registerBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new RegisterFrame();
         }
      });
      registerBtn.setBounds(133, 431, 97, 23);
      frmNextLevelBank.getContentPane().add(registerBtn);
      
      JButton findPwBtn = new JButton("비밀번호 찾기");
      findPwBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new FindRegist();
         }
      });
      findPwBtn.setBounds(242, 431, 116, 23);
      frmNextLevelBank.getContentPane().add(findPwBtn);
       
      
      
      //NLB_LOGO
//      ImageIcon nlb_logo_icon = new ImageIcon("NLB_LOGO.png");
//      Image nlb_logo_img = nlb_logo_icon.getImage();
//      Image change_nlb_logo_img = nlb_logo_img.getScaledInstance(150,150,Image.SCALE_SMOOTH);
//      ImageIcon change_nlb_logo_icon = new ImageIcon(change_nlb_logo_img);
//      
//      
      
     
      //아이콘 생성
      ImageIcon nlb_logo_icon = new ImageIcon(LoginFrame.class.getResource("/nlb_core/NLB_LOGO.png"));
      Image nlb_logo_img = nlb_logo_icon.getImage();
      Image change_nlb_logo_img = nlb_logo_img.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
      ImageIcon change_nlb_logo_icon = new ImageIcon(change_nlb_logo_img);
      JLabel NLB_LOGO = new JLabel(change_nlb_logo_icon); //라벨 생성
      
      NLB_LOGO.setBounds(175, 144, 129, 119);
      frmNextLevelBank.getContentPane().add(NLB_LOGO);
   }
}
