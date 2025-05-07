import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUpGUI extends JFrame {

    private JPanel contentPane;
    JLabel createAccount;
    JLabel identity;
    JLabel or;
    JButton btnStudent;
    JButton btnOrganizer;
    JLabel name;
    JLabel password;
    JLabel confirmPass;
    JTextField nameText;
    JPasswordField passwordText;
    JPasswordField confirmPassText;
    JButton btnSignUp;
    JButton btnBack;


    public SignUpGUI() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 600, 600);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      btnBack = new JButton("Back");
      btnBack.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          MainGUI m = new MainGUI();
          m.show();
          dispose();
        }
      });
      btnBack.setBounds(160, 10, 100, 40);
      contentPane.add(btnBack);

      createAccount = new JLabel("Create An Account");
      createAccount.setBounds(160, 55, 197, 17);
      contentPane.add(createAccount);

      identity = new JLabel("Are you a....");
      identity.setBounds(160, 100, 106, 17);
      contentPane.add(identity);

      btnStudent = new JButton("Student");
      btnStudent.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          showText();
          contentPane.revalidate();
          contentPane.repaint();
        }
      });
      btnStudent.setBounds(160, 130, 100, 40);
      contentPane.add(btnStudent);

      or = new JLabel("or");
      or.setBounds(270, 140, 106, 17);
      contentPane.add(or);

      btnOrganizer = new JButton("Organizer"); 
      btnOrganizer.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          showText();
          contentPane.revalidate();
          contentPane.repaint();
        }
      });
      btnOrganizer.setBounds(290, 130, 100, 40);
      contentPane.add(btnOrganizer);

    }
    
    void showText() {
      name = new JLabel("Username:");
      name.setBounds(160, 200, 106, 17);
      contentPane.add(name);

      nameText = new JTextField("");
      nameText.setBounds(160, 225, 130, 30);
      contentPane.add(nameText);

      password = new JLabel("Password:");
      password.setBounds(160, 260, 106, 17);
      contentPane.add(password);

      passwordText = new JPasswordField("");
      passwordText.setBounds(160, 285, 130, 30);
      contentPane.add(passwordText);

      confirmPass = new JLabel("Confirm Password:");
      confirmPass.setBounds(160, 320, 130, 17);
      contentPane.add(confirmPass);

      confirmPassText = new JPasswordField("");
      confirmPassText.setBounds(160, 345, 130, 30);
      contentPane.add(confirmPassText);

      btnSignUp = new JButton("Sign Up");
      btnSignUp.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          // convert_kg_lb();
        }
      });
      btnSignUp.setBounds(160, 380, 100, 40);
      contentPane.add(btnSignUp);

    }
  }

