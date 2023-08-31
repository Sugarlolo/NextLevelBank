package nlb_core;

import java.awt.*;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import database.MemberMgr;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

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
    * 
    */


   public LoginFrame() {
	   initialize();
	   frmNextLevelBank.setVisible(true);
   }
   
   public class JTextFieldLimit extends PlainDocument // JTextField 글자수 제한 클래스
   {
         private int limit;                             // 제한할 길이
         public JTextFieldLimit(int limit)        // 생성자 : 제한할 길이를 인자로 받음
         {
               super();
               this.limit = limit;
         }
         // 텍스트 필드를 채우는 메소드 : 오버라이드
         public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException 
         {
               if (str == null)
                     return;
               if (getLength() + str.length() <= limit) 
                     super.insertString(offset, str, attr);
          }
   }
   
   
   class IdKeyListener extends KeyAdapter{ 
	   // JTextField에 영문소문자, 숫자만 입력가능할수 있게 하려고 만든 키이벤트
	   
	   @Override
	   
	   public void keyTyped(KeyEvent e) {
		   char c = e.getKeyChar();
		   
		   if(!(Character.isLowerCase(c) || Character.isDigit(c))) {
			   e.consume();
		   } 
	   }
   }

/**
    * Initialize the contents of the frame.
    */
   
   private void initialize() {
	   
	  IdKeyListener idkey = new IdKeyListener();
	   
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
      idField.setDocument(new JTextFieldLimit(20));
      idField.setBounds(120, 340, 260, 40);
      frmNextLevelBank.getContentPane().add(idField);
      idField.setColumns(10);
      idField.addKeyListener(idkey);
      
      
      pwField = new JPasswordField();
      pwField.setDocument(new JTextFieldLimit(25));
      pwField.setBounds(120, 380, 260, 40);
      frmNextLevelBank.getContentPane().add(pwField);
      pwField.setColumns(10);
      
      MemberMgr db = new MemberMgr();
      
      JButton joinBtn = new JButton("로그인");
      joinBtn.setForeground(new Color(0, 0, 0));
      joinBtn.setFont(new Font("나눔바른고딕", Font.BOLD, 18));
      joinBtn.setBackground(new Color(255, 228, 0));
      joinBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
        	 String uid = idField.getText().trim();
             String upw = pwField.getText().trim();
        	 if(uid.length()==0 && upw.length()==0) {
        		 JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
        		 return;
        	 }
        	 else if(uid.length()==0  && upw != null ){
        		 JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
        		 return;
        	 }
        	 else if(uid != null && upw.length()==0 ){
        		 JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
        		 return;
        	 } 	 
        	 if(uid != null && upw != null){
        		 if(db.logincheck(uid, upw)){
            		 JOptionPane.showMessageDialog(null, "로그인에 성공하였습니다.");
            		 frmNextLevelBank.dispose();
            	     new MainFrame();
            	 }else {
            		 JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 맞지 않습니다.");
            	 }
        	 }
         }
      });
      joinBtn.setBounds(120, 430, 260, 40);
      frmNextLevelBank.getContentPane().add(joinBtn);
      
      JLabel LOGO_LABEL = new JLabel("Next Level Bank");
      LOGO_LABEL.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
      
      LOGO_LABEL.setBounds(190, 280, 130, 30);
      frmNextLevelBank.getContentPane().add(LOGO_LABEL);
      
      JButton registerBtn = new JButton("회원가입");
      registerBtn.setForeground(new Color(122, 122, 122));
      registerBtn.setFont(new Font("나눔바른고딕", Font.PLAIN, 11));
      registerBtn.setBackground(new Color(255, 228, 0));
      registerBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new JOINFrame();
         }
      });
      registerBtn.setBounds(120, 480, 80, 20);
      frmNextLevelBank.getContentPane().add(registerBtn);
      
      JButton findIDBtn = new JButton("ID찾기");
      findIDBtn.setForeground(new Color(122, 122, 122));
      findIDBtn.setFont(new Font("나눔바른고딕", Font.PLAIN, 11));
      findIDBtn.setBackground(new Color(255, 228, 0));
      findIDBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new IdFindFrame();
         }
      });
      findIDBtn.setBounds(210, 480, 80, 20);
      frmNextLevelBank.getContentPane().add(findIDBtn);
        
      
      //아이콘 생성
      ImageIcon nlb_logo_icon = new ImageIcon(LoginFrame.class.getResource("/nlb_core/NLB_LOGO.png"));
      Image nlb_logo_img = nlb_logo_icon.getImage();
      Image change_nlb_logo_img = nlb_logo_img.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
      ImageIcon change_nlb_logo_icon = new ImageIcon(change_nlb_logo_img);
      JLabel NLB_LOGO = new JLabel(change_nlb_logo_icon); //라벨 생성
      
      NLB_LOGO.setBounds(175, 120, 150, 150);
      frmNextLevelBank.getContentPane().add(NLB_LOGO);
      
      JButton findPwBtn = new JButton("PW찾기");
      findPwBtn.setForeground(new Color(122, 122, 122));
      findPwBtn.setFont(new Font("나눔바른고딕", Font.PLAIN, 11));
      findPwBtn.setBackground(new Color(255, 228, 0));
      findPwBtn.setBounds(300, 480, 80, 20);
      frmNextLevelBank.getContentPane().add(findPwBtn);
      findPwBtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
             new PwFindFrame();
          }
       });
   }
}
