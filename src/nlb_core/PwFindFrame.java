package nlb_core; 

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import database.MemberDB;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class PwFindFrame {
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
            	PwFindFrame window = new PwFindFrame();
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
   public PwFindFrame() {
      initialize();
      FindRegist.setVisible(true);
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
	  MemberDB db = new MemberDB();
      FindRegist = new JFrame();
      FindRegist.getContentPane().setBackground(new Color(255, 228, 0));
      FindRegist.setTitle("Next Level Bank PW Find");
      FindRegist.setBounds(100, 100, 350, 250);
      Dimension frameSize = FindRegist.getSize();
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      FindRegist.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
      //FindRegist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      FindRegist.getContentPane().setLayout(null);
      
      JLabel PW_FIND_LABEL = new JLabel("패스워드 찾기");
      PW_FIND_LABEL.setBounds(125, 40, 90, 15);
      FindRegist.getContentPane().add(PW_FIND_LABEL);
      
      MEMBER_NAME_TF = new HintTextField("이름입력");
      MEMBER_NAME_TF.setBounds(55, 80, 220, 30);
      FindRegist.getContentPane().add(MEMBER_NAME_TF);
      MEMBER_NAME_TF.setColumns(10);
      
      JButton PW_FIND_BTN = new JButton("찾기");
      PW_FIND_BTN.setBackground(new Color(255, 218, 0));
      PW_FIND_BTN.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
      PW_FIND_BTN.setBounds(55, 140, 220, 30);
      FindRegist.getContentPane().add(PW_FIND_BTN);
      PW_FIND_BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // PW 찾기 버튼 눌렸을때 동작
				String name = MEMBER_NAME_TF.getText().trim();
				String id = MEMBER_ID_TF.getText().trim();
				if (db.getFindPw(name, id) != null) { /* DB에 저장된 이름과 ID를 비교하여 PW 조회 */
					String memberName = db.getFindPw(name, id).getMEMBER_Name();
					String memberID = db.getFindPw(name, id).getMEMBER_ID();
					if (memberName != null && db.getFindPw(name, id).getMEMBER_Name().equals(name)
							&& memberID != null && db.getFindPw(name, id).getMEMBER_ID().equals(id)) {
						JOptionPane.showMessageDialog(null, "패스워드는 " + db.getFindPw(name, id).getMEMBER_PW() + " 입니다");
					} else {
						JOptionPane.showMessageDialog(null, "입력한 정보와 일치하는 PW가 없습니다.", "PW 찾기 실패",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "해당 사용자를 찾을 수 없습니다.", "찾기 실패", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
      
      MEMBER_ID_TF = new HintTextField("아이디입력");
      MEMBER_ID_TF.setColumns(10);
      MEMBER_ID_TF.setBounds(55, 110, 220, 30);
      FindRegist.getContentPane().add(MEMBER_ID_TF);
      FindRegist.setResizable(false);
   }
}

