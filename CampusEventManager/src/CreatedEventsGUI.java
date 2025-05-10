import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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
    private JButton btnViewAttendees;

    private Organizer organizer;
    private List<Event> events; // Holds reference to the actual Event objects
    private DefaultTableModel tableModel;

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
        tableModel = new DefaultTableModel(columnNames, 0);
        eventTable = new JTable(tableModel);
        // Only allow one row to be selected
        eventTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane = new JScrollPane(eventTable);
        scrollPane.setBounds(20, 50, 640, 200);
        contentPane.add(scrollPane);

        // Initial table data load
        refreshTable();

        // Edit Event Button
        btnEditEvent = new JButton("Edit Event");
        btnEditEvent.setBounds(50, 280, 130, 30);
        btnEditEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = eventTable.getSelectedRow();
                if (selectedRow != -1) {
                    Event selectedEvent = events.get(selectedRow);
                    // Opens EditEventGUI
                    new EditEventGUI(selectedEvent);
                    dispose(); // Closes the current CreatedEventsGUI
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Please select an event to edit.");
                }
            }
        });
        contentPane.add(btnEditEvent);

        // Delete Event Button
        btnDeleteEvent = new JButton("Delete Event");
        btnDeleteEvent.setBounds(190, 280, 130, 30);
        btnDeleteEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = eventTable.getSelectedRow();
                if (selectedRow != -1) {
                    Event selectedEvent = events.get(selectedRow);
                    organizer.getCreatedEvents().remove(selectedEvent);
                    Database.getInstance().removeEvent(selectedEvent);
                    Database.getInstance().saveAll();
                    refreshTable();
                    JOptionPane.showMessageDialog(null, "Deleted: " + selectedEvent.getName());
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an event to delete.");
                }
            }
        });
        contentPane.add(btnDeleteEvent);

        // View Attendee List Button
        btnViewAttendees = new JButton("View Attendees");
        btnViewAttendees.setBounds(330, 280, 150, 30);
        btnViewAttendees.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = eventTable.getSelectedRow();
                if (selectedRow != -1) {
                    Event selectedEvent = events.get(selectedRow);
                    new AttendeeListGUI(selectedEvent);
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Please select an event to view attendees.");
                }
            }
        });
        contentPane.add(btnViewAttendees);


        // Exit Button
        btnExit = new JButton("Exit");
        btnExit.setBounds(490, 280, 100, 30);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new OrganizerGUI(organizer).setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnExit);

        setVisible(true);
    }

    // Refresh table data after deleting or editing an event
    private void refreshTable() {
        this.events = organizer.getCreatedEvents();
        tableModel.setRowCount(0); // Clear current rows

        for (Event e : events) {
            Object[] row = { e.getName(), e.getDate(), e.getTime(), e.getLocation(), e.getDescription() };
            tableModel.addRow(row);
        }
    }
}
