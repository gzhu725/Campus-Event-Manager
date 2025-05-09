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
import java.util.*;

public class MainGUI extends JFrame {

    private JPanel contentPane;
    JLabel welcome;
    JLabel username;
    JLabel password;
    JLabel toSignUp;
    JButton signIn;
    JButton signUp;
    JButton btnExit;
    JTextField usernameText;
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

      username = new JLabel("Username:");
      username.setBounds(160, 50, 106, 17);
      contentPane.add(username);

      usernameText = new JTextField("");
      usernameText.setBounds(160, 75, 130, 30);
      contentPane.add(usernameText);

      password = new JLabel("Password:");
      password.setBounds(160, 110, 106, 17);
      contentPane.add(password);

      passwordText = new JPasswordField("");
      passwordText.setBounds(160, 135, 130, 30);
      contentPane.add(passwordText);
      
      signIn = new JButton("Sign In");
      signIn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          Database db = Database.getInstance();
          String username = usernameText.getText();
          String password = new String(passwordText.getPassword());

          List<Student> allStudents = db.getAllStudents();
          List<Organizer> allOrganizers = db.getAllOrganizers();

          if (allStudents == null || allOrganizers == null) {
            System.out.println("Database returned null for user lists.");
            return;
          }

          if (allStudents.isEmpty() && allOrganizers.isEmpty()) {
            System.out.println("No users found in the database.");
            return;
          }

          for(Student s : allStudents) {
            if(s.getUsername().equals(username) && s.getPassword().equals(password)) {
              StudentGUI studentGUI = new StudentGUI(s);
              studentGUI.setVisible(true);
              dispose();
            }
          }

          for(Organizer o : allOrganizers) {
            if(o.getUsername().equals(username) && o.getPassword().equals(password)) {
              OrganizerGUI organizerGUI = new OrganizerGUI(o);
              organizerGUI.setVisible(true);
              dispose();
            }
          }


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
    
  }

