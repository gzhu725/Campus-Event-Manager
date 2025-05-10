import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUpGUI extends JFrame {

    private JPanel contentPane;
    private JTextField usernameText, nameText;
    private JPasswordField passwordText, confirmPassText;
    private JButton btnSignUp, btnBack, btnStudent, btnOrganizer;
    private JLabel createAccount, identity, or, username, password, confirmPass;
    private String userType;
    private ButtonGroup userTypeGroup = new ButtonGroup();

    private Database db = Database.getInstance();

    public SignUpGUI() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        btnBack = new JButton("Back");
        btnBack.setBounds(160, 10, 100, 40);
        btnBack.addActionListener(e -> {
            new MainGUI().setVisible(true);
            dispose();
        });
        contentPane.add(btnBack);

        createAccount = new JLabel("Create An Account");
        createAccount.setBounds(160, 55, 197, 17);
        contentPane.add(createAccount);

        identity = new JLabel("Are you a...");
        identity.setBounds(160, 100, 106, 17);
        contentPane.add(identity);

        btnStudent = new JButton("Student");
        btnStudent.setBounds(160, 130, 100, 40);
        btnStudent.addActionListener(e -> {
            userType = "Student";
            clearFields();
            showTextFields();
        });
        contentPane.add(btnStudent);
        userTypeGroup.add(btnStudent);

        or = new JLabel("or");
        or.setBounds(270, 140, 106, 17);
        contentPane.add(or);

        btnOrganizer = new JButton("Organizer");
        btnOrganizer.setBounds(290, 130, 100, 40);
        btnOrganizer.addActionListener(e -> {
            userType = "Organizer";
            clearFields();
            showTextFields();
        });
        contentPane.add(btnOrganizer);
        userTypeGroup.add(btnOrganizer);

        nameText = new JTextField();
        nameText.setBounds(160, 225, 130, 30);
        contentPane.add(nameText);
        nameText.setVisible(false);

        nameText.setText("");

        usernameText = new JTextField();
        usernameText.setBounds(160, 285, 130, 30);
        contentPane.add(usernameText);
        usernameText.setVisible(false);

        passwordText = new JPasswordField();
        passwordText.setBounds(160, 350, 130, 30);
        contentPane.add(passwordText);
        passwordText.setVisible(false);

        confirmPassText = new JPasswordField();
        confirmPassText.setBounds(160, 425, 130, 30);
        contentPane.add(confirmPassText);
        confirmPassText.setVisible(false);

        btnSignUp = new JButton("Sign Up");
        btnSignUp.setBounds(160, 475, 100, 40);
        btnSignUp.addActionListener(e -> handleSignUp());
        contentPane.add(btnSignUp);
        btnSignUp.setVisible(false);
    }

    private void showTextFields() {
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(160, 200, 106, 17);
        contentPane.add(nameLabel);
        nameLabel.setVisible(true);

        JLabel username = new JLabel("Username:");
        username.setBounds(160, 260, 106, 17);
        contentPane.add(username);
        username.setVisible(true);

        JLabel password = new JLabel("Password:");
        password.setBounds(160, 325, 106, 17);
        contentPane.add(password);
        password.setVisible(true);

        JLabel confirmPass = new JLabel("Confirm Password:");
        confirmPass.setBounds(160, 400, 130, 17);
        contentPane.add(confirmPass);
        confirmPass.setVisible(true);

       
        nameText.setVisible(true);
        usernameText.setVisible(true);
        passwordText.setVisible(true);
        confirmPassText.setVisible(true);
        btnSignUp.setVisible(true);
    }

    private void clearFields() {
        if (nameText != null) nameText.setText("");
        if (usernameText != null) usernameText.setText("");
        if (passwordText != null) passwordText.setText("");
        if (confirmPassText != null) confirmPassText.setText("");
    }

    private void handleSignUp() {
        if (userType == null) {
            JOptionPane.showMessageDialog(contentPane, "You must select Student or Organizer.");
            return;
        }

        String name = nameText.getText().trim();
        String username = usernameText.getText().trim();
        String password = new String(passwordText.getPassword()).trim();
        String confirmPassword = new String(confirmPassText.getPassword()).trim();

        if (name.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(contentPane, "All fields must be filled out.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(contentPane, "Passwords do not match.");
            return;
        }

        if (userType.equals("Student")) {
            Student s = new Student(name, username, password);
            db.addStudent(s);
            db.saveAll();
            new StudentGUI(s).setVisible(true);
        } else {
            Organizer o = new Organizer(name, username, password);
            db.addOrganizer(o);
            db.saveAll();
            new OrganizerGUI(o).setVisible(true);
        }
        dispose();
    }
}
