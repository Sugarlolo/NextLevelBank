package nlb_core;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class JOINFrame {

   private JFrame frmNextLevelBank2;
   private JLabel InputGuideLabel;
   private JLabel IdLabel;
   private JTextField IdTf;
   private JButton CHECK_BTN;
   private JLabel PwLabel;
   private JTextField PwTf;
   private JTextField PwCheckTf;
   private JTextField NameTf;
   private JLabel NameLabel;
   private JTextField TelTf;
   private JLabel TelLabel;
   private JTextField AddTf;
   private JLabel AddrLabel;
   private JTextField SocialNumberTf1;
   private JLabel SocialNumberLabel;
   private JButton JOIN_BTN;
   private JLabel SocialNumberLabel_1;
   private JTextField SocialNumberLabel2;
   private JLabel lblNewLabel_1;
   private JLabel NLB_LOGO;

   /**
    * Launch the application.
    */

   public class HintTextField extends JTextField {  //TF HINT
	   Font gainFont = new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 15);  
	   Font lostFont = new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 15);  
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
               JOINFrame window = new JOINFrame();
               window.frmNextLevelBank2.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public JOINFrame() {
      initialize();
      frmNextLevelBank2.setVisible(true);
   }

   /**
    * Initialize the contents of the frame.
    */

//   public class JDatePicker extends RegisterFrame{ 
//      public JDatePicker() { 
//         UtilDateModel model = new UtilDateModel(); 
//         JDatePanelImpl datePanel = new JDatePanelImpl(model); 
//         JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
//
//         panel.add(datePicker); 
//         panel.setBounds(20,30,40,50); 
//         panel.setVisible(true);
//      }
//   }
      

   private void initialize() {
      frmNextLevelBank2 = new JFrame();
      frmNextLevelBank2.setTitle("Next Level Bank JOIN");
      frmNextLevelBank2.getContentPane().setBackground(new Color(255, 255, 255));
      frmNextLevelBank2.getContentPane().setLayout(null);
      
      InputGuideLabel = new JLabel("NLB \uACC4\uC815 \uC815\uBCF4\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694.");
      InputGuideLabel.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 20));
      InputGuideLabel.setBounds(105, 135, 350, 30);
      frmNextLevelBank2.getContentPane().add(InputGuideLabel);
      
      IdLabel = new JLabel("\uC544\uC774\uB514");
      IdLabel.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 18));
      IdLabel.setBounds(29, 190, 80, 30);
      frmNextLevelBank2.getContentPane().add(IdLabel);
      
      IdTf = new HintTextField("  ¾ÆÀÌµð");
      IdTf.setBounds(119, 190, 240, 30);
      frmNextLevelBank2.getContentPane().add(IdTf);
      IdTf.setColumns(10);
      
      CHECK_BTN = new JButton("\uC911\uBCF5\uD655\uC778");
      CHECK_BTN.setBackground(new Color(255, 255, 255));
      CHECK_BTN.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 16));
      CHECK_BTN.setBounds(369, 190, 90, 30);
      frmNextLevelBank2.getContentPane().add(CHECK_BTN);
      
      PwLabel = new JLabel("\uBE44\uBC00\uBC88\uD638");
      PwLabel.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 18));
      PwLabel.setBounds(29, 240, 80, 30);
      frmNextLevelBank2.getContentPane().add(PwLabel);
      
      PwTf = new HintTextField("  ºñ¹Ð¹øÈ£");
      PwTf.setColumns(10);
      PwTf.setBounds(119, 240, 240, 30);
      frmNextLevelBank2.getContentPane().add(PwTf);
      
      PwCheckTf = new HintTextField("  ºñ¹Ð¹øÈ£ È®ÀÎ");
      PwCheckTf.setColumns(10);
      PwCheckTf.setBounds(119, 290, 240, 30);
      frmNextLevelBank2.getContentPane().add(PwCheckTf);
      
      NameTf = new HintTextField("  ÀÌ¸§");
      NameTf.setColumns(10);
      NameTf.setBounds(119, 340, 240, 30);
      frmNextLevelBank2.getContentPane().add(NameTf);
      
      NameLabel = new JLabel("\uC774\uB984");
      NameLabel.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 18));
      NameLabel.setBounds(29, 340, 80, 30);
      frmNextLevelBank2.getContentPane().add(NameLabel);
      
      TelTf = new HintTextField("  ÀüÈ­¹øÈ£");
      TelTf.setColumns(10);
      TelTf.setBounds(119, 390, 240, 30);
      frmNextLevelBank2.getContentPane().add(TelTf);
      
      TelLabel = new JLabel("\uC804\uD654\uBC88\uD638");
      TelLabel.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 18));
      TelLabel.setBounds(29, 390, 80, 30);
      frmNextLevelBank2.getContentPane().add(TelLabel);
      
      AddTf = new HintTextField("  ÁÖ¼Ò");
      AddTf.setColumns(10);
      AddTf.setBounds(119, 440, 240, 30);
      frmNextLevelBank2.getContentPane().add(AddTf);
      
      AddrLabel = new JLabel("\uC8FC\uC18C");
      AddrLabel.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 18));
      AddrLabel.setBounds(29, 440, 80, 30);
      frmNextLevelBank2.getContentPane().add(AddrLabel);
      
      SocialNumberTf1 = new HintTextField("  ¾Õ 6ÀÚ¸®");
      SocialNumberTf1.setColumns(10);
      SocialNumberTf1.setBounds(119, 490, 90, 30);
      frmNextLevelBank2.getContentPane().add(SocialNumberTf1);
      
      SocialNumberLabel = new JLabel("\uC8FC\uBBFC\uBC88\uD638");
      SocialNumberLabel.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 18));
      SocialNumberLabel.setBounds(29, 490, 80, 30);
      frmNextLevelBank2.getContentPane().add(SocialNumberLabel);
      
      JOIN_BTN = new JButton("\uAC00\uC785\uD558\uAE30");
      JOIN_BTN.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 20));
      JOIN_BTN.setBackground(new Color(255, 218, 0));
      JOIN_BTN.setBounds(29, 540, 430, 50);
      frmNextLevelBank2.getContentPane().add(JOIN_BTN);
      
      SocialNumberLabel_1 = new JLabel("-");
      SocialNumberLabel_1.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 18));
      SocialNumberLabel_1.setBounds(214, 488, 15, 30);
      frmNextLevelBank2.getContentPane().add(SocialNumberLabel_1);
      
      SocialNumberLabel2 = new HintTextField("  ¡Ü");
      SocialNumberLabel2.setColumns(10);
      SocialNumberLabel2.setBounds(229, 490, 30, 30);
      frmNextLevelBank2.getContentPane().add(SocialNumberLabel2);
      
      lblNewLabel_1 = new JLabel("____\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF");
      lblNewLabel_1.setBounds(229, 490, 130, 30);
      frmNextLevelBank2.getContentPane().add(lblNewLabel_1);
      
      
      
      //¾ÆÀÌÄÜ »ý¼º
      ImageIcon nlb_logo_icon = new ImageIcon(LoginFrame.class.getResource("/nlb_core/NLB_LOGO.png"));
      Image nlb_logo_img = nlb_logo_icon.getImage();
      Image change_nlb_logo_img = nlb_logo_img.getScaledInstance(70, 100, Image.SCALE_SMOOTH);
      ImageIcon change_nlb_logo_icon = new ImageIcon(change_nlb_logo_img);
      JLabel NLB_LOGO = new JLabel(change_nlb_logo_icon); //¶óº§ »ý¼º
       
      NLB_LOGO.setBounds(27, 70, 70, 100);
      frmNextLevelBank2.getContentPane().add(NLB_LOGO);
      frmNextLevelBank2.setBounds(100, 100, 500, 800);
      frmNextLevelBank2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
   }
}
