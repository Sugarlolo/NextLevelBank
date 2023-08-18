package nlb_core;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class RegisterFrame {

   private JFrame frmNextLevelBank2;
   private JTextField NameField;
   private JTextField PWField;
   private JLabel NameLabel;
   private JTextField IDField;
   private JLabel PNLabel;
   private JTextField PNField;
   private JTextField ADField;
   private JLabel Regident;
   private JTextField Registration1;
   private JPanel panel;
   private JPanel panel_1;
   private JTextField PWcheck;
   private JTextField Registration2;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               RegisterFrame window = new RegisterFrame();
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
   public RegisterFrame() {
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
      frmNextLevelBank2.setTitle("Next Level Bank_Register");
      frmNextLevelBank2.getContentPane().setBackground(Color.YELLOW);
      frmNextLevelBank2.setBounds(100, 100, 450, 500);
      frmNextLevelBank2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frmNextLevelBank2.getContentPane().setLayout(null);
      
      
      JButton Register_Button = new JButton("회원등록");
      Register_Button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            frmNextLevelBank2.dispose();
            new LoginFrame();
         }
      });
      JButton RegiCancel_Button = new JButton("등록취소");
      RegiCancel_Button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            frmNextLevelBank2.dispose();
            new LoginFrame();
         }
      });
      
      RegiCancel_Button.setBounds(240, 398, 100, 40);
      frmNextLevelBank2.getContentPane().add(RegiCancel_Button);
      
      Register_Button.setBounds(121, 398, 100, 40);
      frmNextLevelBank2.getContentPane().add(Register_Button);
      
   
      panel = new JPanel();
      panel.setBackground(new Color(255, 250, 205));
      panel.setBorder(new LineBorder(new Color(0, 0, 0)));
      panel.setBounds(12, 201, 410, 166);
      frmNextLevelBank2.getContentPane().add(panel);
      panel.setLayout(null);
      
      NameLabel = new JLabel("이름");
      NameLabel.setBounds(86, 30, 57, 15);
      panel.add(NameLabel);
      NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
      
      Regident = new JLabel("주민번호");
      Regident.setBounds(49, 123, 94, 15);
      panel.add(Regident);
      Regident.setHorizontalAlignment(SwingConstants.CENTER);
      
      NameField = new JTextField();
      NameField.setBounds(155, 27, 147, 21);
      panel.add(NameField);
      NameField.setColumns(10);
      Registration1 = new JTextField();
      Registration1.setBounds(155, 120, 100, 21);
      panel.add(Registration1);
      Registration1.setColumns(10);
      
      PNField = new JTextField();
      PNField.setBounds(155, 58, 147, 21);
      panel.add(PNField);
      PNField.setColumns(10);
      
      PNLabel = new JLabel("전화번호");
      PNLabel.setBounds(86, 61, 57, 15);
      panel.add(PNLabel);
      PNLabel.setHorizontalAlignment(SwingConstants.CENTER);
      
      JLabel ADLabel = new JLabel("주소");
      ADLabel.setBounds(86, 92, 57, 15);
      panel.add(ADLabel);
      ADLabel.setHorizontalAlignment(SwingConstants.CENTER);
      
      ADField = new JTextField();
      ADField.setBounds(155, 89, 147, 21);
      panel.add(ADField);
      ADField.setColumns(10);
      
      Registration2 = new JTextField();
      Registration2.setColumns(10);
      Registration2.setBounds(277, 120, 25, 21);
      panel.add(Registration2);
      
      panel_1 = new JPanel();
      panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
      panel_1.setBackground(new Color(255, 250, 205));
      panel_1.setBounds(12, 10, 410, 181);
      frmNextLevelBank2.getContentPane().add(panel_1);
      panel_1.setLayout(null);
      
      IDField = new JTextField();
      IDField.setBounds(155, 46, 147, 21);
      panel_1.add(IDField);
      IDField.setColumns(10);
      
      PWField = new JTextField();
      PWField.setBounds(155, 90, 147, 21);
      panel_1.add(PWField);
      PWField.setColumns(10);
      
      JLabel IDLabel = new JLabel("아이디");
      IDLabel.setBounds(86, 49, 57, 15);
      panel_1.add(IDLabel);
      IDLabel.setHorizontalAlignment(SwingConstants.CENTER);
      
      JLabel PWLabel = new JLabel("비밀번호");
      PWLabel.setBounds(86, 93, 57, 15);
      panel_1.add(PWLabel);
      PWLabel.setHorizontalAlignment(SwingConstants.CENTER);
      
      PWcheck = new JTextField();
      PWcheck.setColumns(10);
      PWcheck.setBounds(155, 131, 147, 21);
      panel_1.add(PWcheck);
      
   }
}
