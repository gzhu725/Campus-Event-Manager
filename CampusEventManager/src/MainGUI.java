import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {

    private JPanel contentPane;
    JLabel welcome;
    JLabel name;
    JLabel password;
    JLabel toSignUp;
    JButton signIn;
    JButton signUp;
    JButton btnExit;
    JTextField nameText;
    JPasswordField passwordText;

    public MainGUI() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 600, 600);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  
      setContentPane(contentPane);
      contentPane.setLayout(null);

      welcome = new JLabel("Welcome!");
      welcome.setBounds(160, 18, 106 ,17);
      contentPane.add(welcome);

      name = new JLabel("Username:");
      name.setBounds(160, 50, 106, 17);
      contentPane.add(name);

      nameText = new JTextField("");
      nameText.setBounds(160, 75, 130, 30);
      contentPane.add(nameText);

      password = new JLabel("Password:");
      password.setBounds(160, 110, 106, 17);
      contentPane.add(password);

      passwordText = new JPasswordField("");
      passwordText.setBounds(160, 135, 130, 30);
      contentPane.add(passwordText);
      
      signIn = new JButton("Sign In");
      signIn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          // convert_kg_lb();
        }
      });
      signIn.setBounds(160, 170, 100, 40);
      contentPane.add(signIn);

      toSignUp = new JLabel("Don't have an account?");
      toSignUp.setBounds(160, 190, 155, 60);
      contentPane.add(toSignUp);
      
      signUp = new JButton("Sign Up");
      signUp.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          signup();
          dispose();
        }
      });
      signUp.setBounds(320, 200, 100, 40);
      contentPane.add(signUp);
      
      btnExit = new JButton("Exit");
      btnExit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.exit(ABORT);
        }
      });
      btnExit.setBounds(159, 250, 100, 40);
      contentPane.add(btnExit);
    }
    
    void signup() {
      SignUpGUI s = new SignUpGUI();
      s.setVisible(true);
    	s.show();
    }
    // void convert_cm_inch() {
    // 	CmInchGUI cgu = new CmInchGUI();
    // 	cgu.show();
    // }
  }

