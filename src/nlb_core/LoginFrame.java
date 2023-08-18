package nlb_core;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
      frmNextLevelBank = new JFrame();
      frmNextLevelBank.setTitle("Next Level Bank_Login");
      frmNextLevelBank.setBounds(100, 100, 410, 351);
      frmNextLevelBank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frmNextLevelBank.getContentPane().setLayout(null);
      frmNextLevelBank.setResizable(false);
      
      idField = new JTextField();
      idField.setBounds(86, 149, 116, 21);
      frmNextLevelBank.getContentPane().add(idField);
      idField.setColumns(10);
      
      pwField = new JTextField();
      pwField.setBounds(86, 180, 116, 21);
      frmNextLevelBank.getContentPane().add(pwField);
      pwField.setColumns(10);
      
      JButton joinBtn = new JButton("로그인");
      joinBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            frmNextLevelBank.dispose();
            new MainFrame();
         }
      });
      joinBtn.setBounds(214, 149, 97, 52);
      frmNextLevelBank.getContentPane().add(joinBtn);
      
      JLabel mainLogo = new JLabel("Next Level Bank");
      mainLogo.setBounds(156, 85, 97, 27);
      frmNextLevelBank.getContentPane().add(mainLogo);
      
      JButton registerBtn = new JButton("회원 가입");
      registerBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new RegisterFrame();
         }
      });
      registerBtn.setBounds(86, 211, 97, 23);
      frmNextLevelBank.getContentPane().add(registerBtn);
      
      JButton findPwBtn = new JButton("비밀번호 찾기");
      findPwBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new FindRegist();
         }
      });
      findPwBtn.setBounds(195, 211, 116, 23);
      frmNextLevelBank.getContentPane().add(findPwBtn);
   }
}
