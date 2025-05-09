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
        lblEvents.setBounds(20, 20, 150, 20);
        contentPane.add(lblEvents);

        // List of registered events
        listModel = new DefaultListModel<>();
        eventList = new JList<>(listModel);
        eventList.setBounds(20, 50, 400, 200);
        contentPane.add(eventList);

        btnCancelRegistration = new JButton("Cancel Registration");
        btnCancelRegistration.setBounds(150, 270, 180, 30);
        btnCancelRegistration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Event selectedEvent = eventList.getSelectedValue();
                if (selectedEvent != null) {
                    student.cancelRegistration(selectedEvent);
                    listModel.removeElement(selectedEvent);
                    JOptionPane.showMessageDialog(null, "You have canceled your registration for: " + selectedEvent.getName());
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an event to cancel.");
                }
            }
        });
        contentPane.add(btnCancelRegistration);

        // Exit button
        btnExit = new JButton("Exit");
        btnExit.setBounds(350, 270, 80, 30);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();  
            }
        });
        contentPane.add(btnExit);

        displayRegisteredEvents(student.getRegisteredEvents());
        setVisible(true);
    }

    // Populates the list with registered events
    private void displayRegisteredEvents(List<Event> events) {
        listModel.clear();
        for (Event event : events) {
            listModel.addElement(event);
        }
    }
}
