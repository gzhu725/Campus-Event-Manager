import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class RegisteredEventsGUI extends JFrame {

    private JPanel contentPane;
    private JList<Event> eventList;
    private JButton btnCancelRegistration;
    private JButton btnExit;
    private DefaultListModel<Event> listModel;
    private Student student;

    public RegisteredEventsGUI(Student student) {
        this.student = student;

        setTitle("My Registered Events");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblEvents = new JLabel("My Registered Events:");
        lblEvents.setBounds(20, 20, 300, 20);
        contentPane.add(lblEvents);

        // event list 
        listModel = new DefaultListModel<>();
        eventList = new JList<>(listModel);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // scroll pane to make the list scrollable
        JScrollPane scrollPane = new JScrollPane(eventList);
        scrollPane.setBounds(20, 50, 440, 200);
        contentPane.add(scrollPane);

        btnCancelRegistration = new JButton("Cancel Registration");
        btnCancelRegistration.setBounds(100, 270, 180, 30);

        // action listener to handle button click
        btnCancelRegistration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Event selectedEvent = eventList.getSelectedValue();

                if (selectedEvent != null) {
                    // cancel the registration for the event
                    student.cancelRegistration(selectedEvent);
                    // remove it from the list 
                    listModel.removeElement(selectedEvent);
                    Database.getInstance().saveAll(); 
                    JOptionPane.showMessageDialog(null, "You have canceled your registration for: " + selectedEvent.getName());
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an event to cancel.");
                }
            }
        });
        contentPane.add(btnCancelRegistration);

        // exit button
        btnExit = new JButton("Back");
        btnExit.setBounds(300, 270, 100, 30);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StudentGUI(student).setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnExit);

        displayRegisteredEvents(student.getRegisteredEvents());
        setVisible(true);
    }

    // method to load all the registered events
    private void displayRegisteredEvents(List<Event> events) {
        listModel.clear();
        for (Event event : events) {
            listModel.addElement(event);
        }
    }
}