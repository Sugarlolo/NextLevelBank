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
   
   public class JTextFieldLimit extends PlainDocument // JTextField ���ڼ� ���� Ŭ����
   {
         private int limit;                             // ������ ����
         public JTextFieldLimit(int limit)        // ������ : ������ ���̸� ���ڷ� ����
         {
               super();
               this.limit = limit;
         }
         // �ؽ�Ʈ �ʵ带 ä��� �޼ҵ� : �������̵�
         public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException 
         {
               if (str == null)
                     return;
               if (getLength() + str.length() <= limit) 
                     super.insertString(offset, str, attr);
          }
   }
   
   
   class IdKeyListener extends KeyAdapter{ 
	   // JTextField�� �����ҹ���, ���ڸ� �Է°����Ҽ� �ְ� �Ϸ��� ���� Ű�̺�Ʈ
	   
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
	   
      frmNextLevelBank = new JFrame(); //������ ��ü ����
      frmNextLevelBank.getContentPane().setBackground(new Color(255, 228, 0));
      frmNextLevelBank.setSize(500,800);
      Dimension frameSize = frmNextLevelBank.getSize();      // ����� ũ��
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  //frame ũ�� 
      frmNextLevelBank.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);   // frame ��ġ ����
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
      
      JButton joinBtn = new JButton("�α���");
      joinBtn.setForeground(new Color(0, 0, 0));
      joinBtn.setFont(new Font("�����ٸ����", Font.BOLD, 18));
      joinBtn.setBackground(new Color(255, 228, 0));
      joinBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
        	 String uid = idField.getText().trim();
             String upw = pwField.getText().trim();
        	 if(uid.length()==0 && upw.length()==0) {
        		 JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� �Է����ּ���.", "�α��� ����", JOptionPane.ERROR_MESSAGE);
        		 return;
        	 }
        	 else if(uid.length()==0  && upw != null ){
        		 JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���.", "�α��� ����", JOptionPane.ERROR_MESSAGE);
        		 return;
        	 }
        	 else if(uid != null && upw.length()==0 ){
        		 JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.", "�α��� ����", JOptionPane.ERROR_MESSAGE);
        		 return;
        	 } 	 
        	 if(uid != null && upw != null){
        		 if(db.logincheck(uid, upw)){
            		 JOptionPane.showMessageDialog(null, "�α��ο� �����Ͽ����ϴ�.");
            		 frmNextLevelBank.dispose();
            	     new MainFrame();
            	 }else {
            		 JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� ���� �ʽ��ϴ�.");
            	 }
        	 }
         }
      });
      joinBtn.setBounds(120, 430, 260, 40);
      frmNextLevelBank.getContentPane().add(joinBtn);
      
      JLabel LOGO_LABEL = new JLabel("Next Level Bank");
      LOGO_LABEL.setFont(new Font("�����ٸ����", Font.BOLD, 15));
      
      LOGO_LABEL.setBounds(190, 280, 130, 30);
      frmNextLevelBank.getContentPane().add(LOGO_LABEL);
      
      JButton registerBtn = new JButton("ȸ������");
      registerBtn.setForeground(new Color(122, 122, 122));
      registerBtn.setFont(new Font("�����ٸ����", Font.PLAIN, 11));
      registerBtn.setBackground(new Color(255, 228, 0));
      registerBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new JOINFrame();
         }
      });
      registerBtn.setBounds(120, 480, 80, 20);
      frmNextLevelBank.getContentPane().add(registerBtn);
      
      JButton findIDBtn = new JButton("IDã��");
      findIDBtn.setForeground(new Color(122, 122, 122));
      findIDBtn.setFont(new Font("�����ٸ����", Font.PLAIN, 11));
      findIDBtn.setBackground(new Color(255, 228, 0));
      findIDBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new IdFindFrame();
         }
      });
      findIDBtn.setBounds(210, 480, 80, 20);
      frmNextLevelBank.getContentPane().add(findIDBtn);
        
      
      //������ ����
      ImageIcon nlb_logo_icon = new ImageIcon(LoginFrame.class.getResource("/nlb_core/NLB_LOGO.png"));
      Image nlb_logo_img = nlb_logo_icon.getImage();
      Image change_nlb_logo_img = nlb_logo_img.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
      ImageIcon change_nlb_logo_icon = new ImageIcon(change_nlb_logo_img);
      JLabel NLB_LOGO = new JLabel(change_nlb_logo_icon); //�� ����
      
      NLB_LOGO.setBounds(175, 120, 150, 150);
      frmNextLevelBank.getContentPane().add(NLB_LOGO);
      
      JButton findPwBtn = new JButton("PWã��");
      findPwBtn.setForeground(new Color(122, 122, 122));
      findPwBtn.setFont(new Font("�����ٸ����", Font.PLAIN, 11));
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
