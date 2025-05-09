import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UpcomingEventsGUI extends JFrame {

    private JPanel contentPane;
    private JTable eventTable;
    private JScrollPane scrollPane;
    private JButton btnRegister;
    private JButton btnExit;

    private Student student;
    private List<Event> allEvents;

    public UpcomingEventsGUI(Student student) {
        this.student = student;
        // this.allEvents = Database.getEvents();

        // Set up the main window
        setTitle("Upcoming Events");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Heading label
        JLabel lblTitle = new JLabel("All Upcoming Events:");
        lblTitle.setBounds(20, 20, 300, 20);
        contentPane.add(lblTitle);

        // Define column headers for the table
        String[] columnNames = {"Event Name", "Date", "Time", "Location", "Description"};

        // Convert list of Event objects into table data
        Object[][] rowData = new Object[allEvents.size()][columnNames.length];
        for (int i = 0; i < allEvents.size(); i++) {
            Event e = allEvents.get(i);
            rowData[i][0] = e.getName();
            rowData[i][1] = e.getDate();
            rowData[i][2] = e.getTime();
            rowData[i][3] = e.getLocation();
            rowData[i][4] = e.getDescription();
        }

        // Create the table and wrap it in a scroll pane
        eventTable = new JTable(rowData, columnNames);
        // Only allow one row to be selected
        eventTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(eventTable);
        scrollPane.setBounds(20, 50, 640, 200);
        contentPane.add(scrollPane);

        // Register Button
        btnRegister = new JButton("Register");
        btnRegister.setBounds(200, 280, 130, 30);
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = eventTable.getSelectedRow();
                if (selectedRow != -1) {
                    Event selectedEvent = allEvents.get(selectedRow);
                    if (student.getRegisteredEvents().contains(selectedEvent)) {
                        JOptionPane.showMessageDialog(null, "You are already registered for this event.");
                    } 
                    else {
                        student.registerEvent(selectedEvent);
                        JOptionPane.showMessageDialog(null, "Successfully registered for: " + selectedEvent.getName());
                    }
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Please select an event to register.");
                }
            }
        });
        contentPane.add(btnRegister);

        // Exit Button
        btnExit = new JButton("Exit");
        btnExit.setBounds(350, 280, 100, 30);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close window
            }
        });
        contentPane.add(btnExit);

        setVisible(true);
    }
}
