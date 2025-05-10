import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AttendeeListGUI extends JFrame {

    private JPanel contentPane;
    private JList<String> attendeeList;
    private JButton btnExit;
    private JLabel eventLabel;
    private Event event; 

    public AttendeeListGUI(Event event) {
        this.event = event;

        setTitle("Attendee List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 450);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Event title 
        eventLabel = new JLabel("Attendees for: " + event.getName());
        eventLabel.setBounds(50, 10, 300, 20);
        contentPane.add(eventLabel);

        attendeeList = new JList<>();
        attendeeList.setBounds(50, 50, 300, 200);
        contentPane.add(attendeeList);

        btnExit = new JButton("Exit");
        btnExit.setBounds(150, 300, 100, 40);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(btnExit);

        // load data from event object
        populateAttendeeList();

        setVisible(true);
    }

    // load attendees from the event object
    private void populateAttendeeList() {
        List<Student> attendees = event.getAttendeeList();

        //if no attendees are found
        if (attendees == null || attendees.isEmpty()) {
            attendeeList.setListData(new String[]{"No attendees registered."});
        } else {
            // if attendees exist, extract their names and display them
            String[] names = attendees.stream()
                    .map(Student::getName)
                    .toArray(String[]::new);

            attendeeList.setListData(names);
        }
    }
}
