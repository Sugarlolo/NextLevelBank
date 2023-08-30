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
// ���1 : LoginFrame�� �߰��� ȭ�� ����
// ���2 : ID ã�� ��ư ����� �α��� â�� ���̵� �Է�ĭ�� �ڵ� �Էµǵ��� OR ���̵� �޽��� â�� �ߵ���
   private JFrame FindRegist;
   private JTextField MEMBER_NAME_TF;
   private JTextField MEMBER_ID_TF;

   /**
    * Launch the application.
    */
   public class HintTextField extends JTextField {  //TF HINT
	   Font gainFont = new Font("�����ٸ����", Font.PLAIN, 15);  
	   Font lostFont = new Font("�����ٸ����", Font.PLAIN, 10);  
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
      
      JLabel PW_FIND_LABEL = new JLabel("�н����� ã��");
      PW_FIND_LABEL.setBounds(125, 40, 90, 15);
      FindRegist.getContentPane().add(PW_FIND_LABEL);
      
      MEMBER_NAME_TF = new HintTextField("�̸��Է�");
      MEMBER_NAME_TF.setBounds(55, 80, 220, 30);
      FindRegist.getContentPane().add(MEMBER_NAME_TF);
      MEMBER_NAME_TF.setColumns(10);
      
      JButton PW_FIND_BTN = new JButton("ã��");
      PW_FIND_BTN.setBackground(new Color(255, 218, 0));
      PW_FIND_BTN.setFont(new Font("�����ٸ����", Font.PLAIN, 15));
      PW_FIND_BTN.setBounds(55, 140, 220, 30);
      FindRegist.getContentPane().add(PW_FIND_BTN);
      PW_FIND_BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // PW ã�� ��ư �������� ����
				String name = MEMBER_NAME_TF.getText().trim();
				String id = MEMBER_ID_TF.getText().trim();
				if (db.getFindPw(name, id) != null) { /* DB�� ����� �̸��� ID�� ���Ͽ� PW ��ȸ */
					String memberName = db.getFindPw(name, id).getMEMBER_Name();
					String memberID = db.getFindPw(name, id).getMEMBER_ID();
					if (memberName != null && db.getFindPw(name, id).getMEMBER_Name().equals(name)
							&& memberID != null && db.getFindPw(name, id).getMEMBER_ID().equals(id)) {
						JOptionPane.showMessageDialog(null, "�н������ " + db.getFindPw(name, id).getMEMBER_PW() + " �Դϴ�");
					} else {
						JOptionPane.showMessageDialog(null, "�Է��� ������ ��ġ�ϴ� PW�� �����ϴ�.", "PW ã�� ����",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "�ش� ����ڸ� ã�� �� �����ϴ�.", "ã�� ����", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
      
      MEMBER_ID_TF = new HintTextField("���̵��Է�");
      MEMBER_ID_TF.setColumns(10);
      MEMBER_ID_TF.setBounds(55, 110, 220, 30);
      FindRegist.getContentPane().add(MEMBER_ID_TF);
      FindRegist.setResizable(false);
   }
}

