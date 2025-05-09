import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

// StudentGUI represents the main dashboard window shown to a logged-in student
public class StudentGUI extends JFrame {
    // GUI components
    private JPanel contentPane;
    private JButton btnViewEvents;
    private JButton btnViewRegistered;
    private JButton btnExit;
    private JLabel lblWelcome;

    // Student associated with this session
    private Student student;
    private List<Event> allEvents;

    // Builds the GUI using the provided Student object
    public StudentGUI(Student student, List<Event> allEvents) {
        this.student = student;
        this.allEvents = allEvents;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Welcome message personalized with student name
        lblWelcome = new JLabel("Welcome, " + student.getName() + "!");
        lblWelcome.setBounds(180, 20, 300, 30);
        contentPane.add(lblWelcome);

        // Button to view upcoming events
        btnViewEvents = new JButton("View Upcoming Events");
        btnViewEvents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // new UpcomingEventsGUI(student, allEvents);
            }
        });
        btnViewEvents.setBounds(150, 80, 200, 40);
        contentPane.add(btnViewEvents);

        // Button to view events the student has registered for
        btnViewRegistered = new JButton("View Registered Events");
        btnViewRegistered.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisteredEventsGUI re = new RegisteredEventsGUI(student);
                re.setVisible(true);
                dispose(); // Closes the current StudentGUI
            }
        });
        btnViewRegistered.setBounds(150, 140, 200, 40);
        contentPane.add(btnViewRegistered);

        // Button to exit the application
        btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close this window
            }
        });
        btnExit.setBounds(200, 200, 100, 40);
        contentPane.add(btnExit);

        setVisible(true);
    }
}
