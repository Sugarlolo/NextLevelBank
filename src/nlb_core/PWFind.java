package nlb_core; 

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

public class PWFind {
// 기능1 : LoginFrame의 중간에 화면 띄우기
// 기능2 : ID 찾기 버튼 누루면 로그인 창의 아이디 입력칸에 자동 입력되도록 OR 아이디가 메시지 창에 뜨도록
   private JFrame FindRegist;
   private JTextField MEMBER_NAME_TF;
   private JTextField MEMBER_ID_TF;

   /**
    * Launch the application.
    */
   public class HintTextField extends JTextField {  //TF HINT
	   Font gainFont = new Font("나눔바른고딕", Font.PLAIN, 15);  
	   Font lostFont = new Font("나눔바른고딕", Font.PLAIN, 10);  
	   public HintTextField(final String hint) {  
	     setText(hint);  
	     setFont(lostFont);
	     setForeground(Color.GRAY);  
	     this.addFocusListener(new FocusAdapter() {  
	       @Override  
	       public void focusGained(FocusEvent e) {  
	         if (getText().equals(hint)) {  
	           setText("");  
	           setFont(gainFont);  
	         } else {  
	           setText(getText());  
	           setFont(gainFont);  
	         }  
	       }  

	       @Override  
	       public void focusLost(FocusEvent e) {  
	         if (getText().equals(hint)|| getText().length()==0) {  
	           setText(hint);  
	           setFont(lostFont);  
	           setForeground(Color.GRAY);  
	         } else {  
	           setText(getText());  
	           setFont(gainFont);  
	           setForeground(Color.BLACK);  
	         }  
	       }  
	     });   
	   }   
	 }  
   
   
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
            	PWFind window = new PWFind();
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
   public PWFind() {
      initialize();
      FindRegist.setVisible(true);
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      FindRegist = new JFrame();
      FindRegist.getContentPane().setBackground(new Color(255, 228, 0));
      FindRegist.setTitle("Next Level Bank PW Find");
      FindRegist.setBounds(100, 100, 350, 250);
      FindRegist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      FindRegist.getContentPane().setLayout(null);
      
      JLabel PW_FIND_LABEL = new JLabel("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
      PW_FIND_LABEL.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
      PW_FIND_LABEL.setBounds(125, 40, 90, 15);
      FindRegist.getContentPane().add(PW_FIND_LABEL);
      
      MEMBER_NAME_TF = new HintTextField("이름입력");
      MEMBER_NAME_TF.setBounds(55, 80, 220, 30);
      FindRegist.getContentPane().add(MEMBER_NAME_TF);
      MEMBER_NAME_TF.setColumns(10);
      
      JButton PW_FIND_BTN = new JButton("\uCC3E\uAE30");
      PW_FIND_BTN.setBackground(new Color(255, 218, 0));
      PW_FIND_BTN.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
      PW_FIND_BTN.setBounds(55, 140, 220, 30);
      FindRegist.getContentPane().add(PW_FIND_BTN);
      
      MEMBER_ID_TF = new HintTextField("아이디입력");
      MEMBER_ID_TF.setColumns(10);
      MEMBER_ID_TF.setBounds(55, 110, 220, 30);
      FindRegist.getContentPane().add(MEMBER_ID_TF);
      FindRegist.setResizable(false);
   }
}

