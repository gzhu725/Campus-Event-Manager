import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class UpcomingEventsGUI extends JFrame {

    private JPanel contentPane;
    private JTable eventTable;
    private JScrollPane scrollPane;
    private JButton btnRegister;
    private JButton btnExit;
    private JTextField searchField;
    private JButton searchButton;

    private Student student;
    private List<Event> allEvents;

    public UpcomingEventsGUI(Student student) {
        this.student = student;
        this.allEvents = Database.getInstance().getAllEvents(); // Load from DB

        // Set up the main window
        setTitle("Upcoming Events");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Heading label
        JLabel lblTitle = new JLabel("All Upcoming Events:");
        lblTitle.setBounds(20, 20, 200, 20);
        contentPane.add(lblTitle);

        // Search field
        searchField = new JTextField();
        searchField.setBounds(350, 20, 200, 25);
        contentPane.add(searchField);

        // Search button
        searchButton = new JButton("Search");
        searchButton.setBounds(560, 20, 100, 25);
        contentPane.add(searchButton);

        eventTable = new JTable();
        // Only allow one row to be selected
        eventTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(eventTable);
        scrollPane.setBounds(20, 60, 640, 200);
        contentPane.add(scrollPane);

        // Populate the table initially
        updateTable(allEvents);

        // Search button logic
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText().trim().toLowerCase();
                List<Event> filtered = allEvents.stream().filter(event ->
                        event.getName().toLowerCase().contains(keyword) ||
                        event.getDate().toLowerCase().contains(keyword) ||
                        event.getTime().toLowerCase().contains(keyword) ||
                        event.getLocation().toLowerCase().contains(keyword) ||
                        event.getDescription().toLowerCase().contains(keyword)
                ).collect(Collectors.toList());

                updateTable(filtered);
            }
        });

        // Register Button
        btnRegister = new JButton("Register");
        btnRegister.setBounds(200, 280, 130, 30);
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = eventTable.getSelectedRow();
                if (selectedRow != -1) {
                    String eventName = eventTable.getValueAt(selectedRow, 0).toString();
                    Event selectedEvent = findEventByName(eventName);

                    if (selectedEvent != null) {
                        if (student.getRegisteredEvents().contains(selectedEvent)) {
                            JOptionPane.showMessageDialog(null, "You are already registered for this event.");
                        } 
                        else {
                            student.registerEvent(selectedEvent);
                            Database.getInstance().saveAll(); // Save registration
                            JOptionPane.showMessageDialog(null, "Successfully registered for: " + selectedEvent.getName());
                        }
                    }
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Please select an event to register.");
                }
            }
        });
        contentPane.add(btnRegister);

        // Exit Button
        btnExit = new JButton("Back");
        btnExit.setBounds(350, 280, 100, 30);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StudentGUI(student).setVisible(true); // Go back to dashboard
                dispose(); // Closes window
            }
        });
        contentPane.add(btnExit);

        setVisible(true);
    }

    private void updateTable(List<Event> eventList) {
        // Define column headers for the table
        String[] columnNames = {"Event Name", "Date", "Time", "Location", "Description"};
        // Convert list of Event objects into table data
        Object[][] rowData = new Object[eventList.size()][columnNames.length];
        for (int i = 0; i < eventList.size(); i++) {
            Event e = eventList.get(i);
            rowData[i][0] = e.getName();
            rowData[i][1] = e.getDate();
            rowData[i][2] = e.getTime();
            rowData[i][3] = e.getLocation();
            rowData[i][4] = e.getDescription();
        }
        eventTable.setModel(new DefaultTableModel(rowData, columnNames));
    }

    private Event findEventByName(String name) {
        for (Event e : allEvents) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
}
