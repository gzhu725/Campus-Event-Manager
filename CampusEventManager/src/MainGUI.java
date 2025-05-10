import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

public class MainGUI extends JFrame {

    private JPanel contentPane;
    JLabel welcome, username, password, toSignUp;
    JButton signIn, signUp, btnExit;
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
        signIn.setBounds(160, 170, 100, 40);
        signIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Database db = Database.getInstance();
                db.loadAll();

                String username = usernameText.getText();
                String password = new String(passwordText.getPassword());

                boolean loginSuccessful = false;

                //check for valid login credentials
                for(Student s : db.getAllStudents()) {
                    if(s.getUsername().equals(username) && s.getPassword().equals(password)) {
                        new StudentGUI(s).setVisible(true);
                        loginSuccessful = true;
                        dispose();
                        return;
                    }
                }

                for(Organizer o : db.getAllOrganizers()) {
                    if(o.getUsername().equals(username) && o.getPassword().equals(password)) {
                        new OrganizerGUI(o).setVisible(true);
                        loginSuccessful = true;
                        dispose();
                        return;
                    }
                }

                if (!loginSuccessful) {
                    JOptionPane.showMessageDialog(null, "Incorrect username or password.");
                }
            }
        });
        contentPane.add(signIn);

        toSignUp = new JLabel("Don't have an account?");
        toSignUp.setBounds(160, 190, 155, 60);
        contentPane.add(toSignUp);

        signUp = new JButton("Sign Up");
        signUp.setBounds(320, 200, 100, 40);
        signUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SignUpGUI().setVisible(true);
                dispose();
            }
        });
        contentPane.add(signUp);

        btnExit = new JButton("Exit");
        btnExit.setBounds(159, 250, 100, 40);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        contentPane.add(btnExit);
    }
}


