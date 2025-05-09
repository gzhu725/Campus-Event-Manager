import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class SignUpGUI extends JFrame {

  private JPanel contentPane;
  JLabel createAccount;
  JLabel identity;
  JLabel or;
  JButton btnStudent;
  JButton btnOrganizer;
  JLabel username;
  JLabel password;
  JLabel confirmPass;
  JTextField usernameText;
  JPasswordField passwordText;
  JPasswordField confirmPassText;
  JTextField nameText;
  JButton btnSignUp;
  JButton btnBack;
  String userType; // has to be student or organizer
  ButtonGroup userTypeGroup = new ButtonGroup(); // student and organizer buttons

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
        userType = "Student";
        showText();
        contentPane.revalidate();
        contentPane.repaint();
      }
    });
    btnStudent.setBounds(160, 130, 100, 40);
    contentPane.add(btnStudent);
    userTypeGroup.add(btnStudent);

    or = new JLabel("or");
    or.setBounds(270, 140, 106, 17);
    contentPane.add(or);

    btnOrganizer = new JButton("Organizer");
    btnOrganizer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        userType = "Organizer";
        showText();
        contentPane.revalidate();
        contentPane.repaint();
      }
    });
    btnOrganizer.setBounds(290, 130, 100, 40);
    contentPane.add(btnOrganizer);
    userTypeGroup.add(btnOrganizer);
  }

  void showText() {
    JLabel firstName = new JLabel("Name:");
    firstName.setBounds(160, 200, 106, 17);
    contentPane.add(firstName);

    nameText = new JTextField("");
    nameText.setBounds(160, 225, 130, 30);
    contentPane.add(nameText);

    username = new JLabel("Username:");
    username.setBounds(160, 260, 106, 17);
    contentPane.add(username);

    usernameText = new JTextField("");
    usernameText.setBounds(160, 285, 130, 30);
    contentPane.add(usernameText);

    password = new JLabel("Password:");
    password.setBounds(160, 325, 106, 17);
    contentPane.add(password);

    passwordText = new JPasswordField("");
    passwordText.setBounds(160, 350, 130, 30);
    contentPane.add(passwordText);

    confirmPass = new JLabel("Confirm Password:");
    confirmPass.setBounds(160, 400, 130, 17);
    contentPane.add(confirmPass);

    confirmPassText = new JPasswordField("");
    confirmPassText.setBounds(160, 425, 130, 30);
    contentPane.add(confirmPassText);

    btnSignUp = new JButton("Sign Up");
    btnSignUp.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        if (userType == null) {
          JOptionPane.showMessageDialog(contentPane, "You must select either Student or Organizer.");
          return;
        }
        if (nameText.getText().trim().isEmpty()) {
          System.out.println(nameText.getText());
          JOptionPane.showMessageDialog(contentPane, "You must enter a name.");
          return;
        }

        if (usernameText.getText().trim().isEmpty()) {
          JOptionPane.showMessageDialog(contentPane, "You must enter a username.");
          return;
        }

        if (new String(passwordText.getPassword()).trim().isEmpty()) {
          JOptionPane.showMessageDialog(contentPane, "You must enter a password.");
          return;
        }

        if (new String(confirmPassText.getPassword()).trim().isEmpty()) {
          JOptionPane.showMessageDialog(contentPane, "You must confirm your password.");
          return;
        }

        // Check if passwordText and confirmPassText are the same
        if (!new String(passwordText.getPassword()).equals(new String(confirmPassText.getPassword()))) {
          JOptionPane.showMessageDialog(contentPane, "Passwords do not match.");
          return;
        }
        if (userType.equals("Student")) {
          StudentGUI st = new StudentGUI(new Student(nameText.getText(), usernameText.getText(), passwordText.getText()));
          st.setVisible(true);
          st.show();
          dispose();
        } else {
          System.out.println("organizer");
          OrganizerGUI org = new OrganizerGUI(new Organizer(nameText.getText(),
          usernameText.getText(), "lol", passwordText.getText()));
          org.setVisible(true);
          org.show();
          dispose();
        }
      }
    });
    btnSignUp.setBounds(160, 475, 100, 40);
    contentPane.add(btnSignUp);
  }
}
