import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AttendeeListGUI extends JFrame {
    // Components
    private JPanel contentPane;
    private JList<String> attendeeList;
    private JButton btnExit;
    private JLabel eventLabel;
    private String eventID;
    /*private String eventName; */
    private String clubName;    // Added Club Name

    // Constructor
    public AttendeeListGUI(String eventID, String clubName) {
        this.eventID = eventID;
        this.clubName = clubName;

        // Window settings
        setTitle("Attendee List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 450); 
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Club title
        eventLabel = new JLabel("Attendees for: " + clubName);
        eventLabel.setBounds(50, 10, 300, 20);
        contentPane.add(eventLabel);

        // List for Attendees
        attendeeList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(attendeeList);
        scrollPane.setBounds(50, 50, 300, 200);
        contentPane.add(scrollPane);

        // Exit button
        btnExit = new JButton("Exit");
        btnExit.setBounds(150, 300, 100, 40);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });
        contentPane.add(btnExit);

        // **Database call to populate list**
        // ***WAITING FOR DATABASE IMPLEMENTATION***
        // populateAttendeeList();

        // *** TEMPORARY MOCK DATA ***
        populateTemporaryList(); 

        setVisible(true);
    }

    // Fetches and displays the list of attendees from the database
    // ***WAITING FOR DATABASE IMPLEMENTATION***
    /*
    private void populateAttendeeList() {
        // Get attendee list from the database using the eventID
        List<String> attendees = Database.getAttendeeList(eventID);

        if (attendees == null) {
            JOptionPane.showMessageDialog(contentPane, "Failed to load attendees.");
        } else if (attendees.isEmpty()) {
            attendeeList.setListData(new String[]{"No Attendees Registered"});
        } else {
            attendeeList.setListData(attendees.toArray(new String[0]));
        }
    }
    */

    // Mock Data for Testing GUI
    private void populateTemporaryList() {
        List<String> attendees = new ArrayList<String>();
        attendees.add("John Doe");
        attendees.add("Jane Smith");
        attendees.add("Alice Johnson");
        attendees.add("Michael Brown");

        // Display the list
        attendeeList.setListData(attendees.toArray(new String[0]));
    }

    /*public static void main(String[] args) {
        new AttendeeListGUI("EVT123", "Computer Science Club");
    }*/
}
