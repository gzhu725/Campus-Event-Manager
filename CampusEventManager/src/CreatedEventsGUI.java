import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CreatedEventsGUI extends JFrame {

    // GUI components
    private JPanel contentPane;
    private JTable eventTable;
    private JScrollPane scrollPane;
    private JButton btnEditEvent;
    private JButton btnDeleteEvent;
    private JButton btnExit;

    private Organizer organizer;
    private List<Event> events; // Holds reference to the actual Event objects

    // Builds the GUI using the given organizer's created events
    public CreatedEventsGUI(Organizer organizer) {
        this.organizer = organizer;
        this.events = organizer.getCreatedEvents();

        // Set up the main window
        setTitle("My Created Events");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Heading label
        JLabel lblEvents = new JLabel("Events You've Created:");
        lblEvents.setBounds(20, 20, 200, 20);
        contentPane.add(lblEvents);

        // Define column headers for the table
        String[] columnNames = {"Event Name", "Date", "Time", "Location", "Description"};

        // Convert list of Event objects into table data
        Object[][] rowData = new Object[events.size()][columnNames.length];
        for (int i = 0; i < events.size(); i++) {
            Event e = events.get(i);
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

        // Edit Event Button
        btnEditEvent = new JButton("Edit Event");
        btnEditEvent.setBounds(80, 280, 130, 30);
        btnEditEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = eventTable.getSelectedRow();
                if (selectedRow != -1) {
                    Event selectedEvent = events.get(selectedRow);
                    // Open EditEventGUI
                    EditEventGUI ee = new EditEventGUI(selectedEvent);
                    ee.setVisible(true);
                    dispose(); // Close the current CreatedEventsGUI
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Please select an event to edit.");
                }
            }
        });
        contentPane.add(btnEditEvent);

        // Delete Event Button
        btnDeleteEvent = new JButton("Delete Event");
        btnDeleteEvent.setBounds(230, 280, 130, 30);
        btnDeleteEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = eventTable.getSelectedRow();
                if (selectedRow != -1) {
                    Event selectedEvent = events.get(selectedRow);
                    organizer.getCreatedEvents().remove(selectedEvent);
                    // Update the table view
                    refreshTable();
                    JOptionPane.showMessageDialog(null, "You have canceled your event for: " + selectedEvent.getName());
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Please select an event to delete.");
                }
            }
        });
        contentPane.add(btnDeleteEvent);

        // Exit Button
        btnExit = new JButton("Exit");
        btnExit.setBounds(380, 280, 100, 30);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(btnExit);

        setVisible(true);
    }

    // Refresh table data after deleting or editing an event
    private void refreshTable() {
        String[] columnNames = {"Event Name", "Date", "Time", "Location", "Description"};
        Object[][] rowData = new Object[events.size()][columnNames.length];
        for (int i = 0; i < events.size(); i++) {
            Event e = events.get(i);
            rowData[i][0] = e.getName();
            rowData[i][1] = e.getDate();
            rowData[i][2] = e.getTime();
            rowData[i][3] = e.getLocation();
            rowData[i][4] = e.getDescription();
        }

        eventTable.setModel(new javax.swing.table.DefaultTableModel(rowData, columnNames));
    }
}
