import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// OrganizerGUI represents the dashboard window shown to a logged-in organizer
public class OrganizerGUI extends JFrame {
    // GUI components
    private JPanel contentPane;
    private JButton btnCreateEvent;
    private JButton btnViewCreatedEvents;
    private JButton btnExit;
    private JLabel lblWelcome;

    // Organizer associated with this session
    private Organizer organizer;

    // Constructs the GUI using the provided Organizer object
    public OrganizerGUI(Organizer organizer) {
        this.organizer = organizer;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Welcome message personalized with organizer name
        lblWelcome = new JLabel("Welcome, " + organizer.getName() + "!");
        lblWelcome.setBounds(180, 20, 300, 30);
        contentPane.add(lblWelcome);

        // Button to create a new event
        btnCreateEvent = new JButton("Create Event");
        btnCreateEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // new CreateEventGUI(organizer);
            }
        });
        btnCreateEvent.setBounds(150, 80, 200, 40);
        contentPane.add(btnCreateEvent);

        // Button to view events the organizer has created
        btnViewCreatedEvents = new JButton("View Created Events");
        btnViewCreatedEvents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // new CreatedEventsGUI(organizer);
            }
        });
        btnViewCreatedEvents.setBounds(150, 140, 200, 40);
        contentPane.add(btnViewCreatedEvents);

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
