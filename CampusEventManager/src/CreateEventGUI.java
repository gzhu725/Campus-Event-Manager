import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateEventGUI extends JFrame {

    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtDate;
    private JTextField txtLocation;
    private JTextField timeText;
    private JTextArea descriptionText;
    private JButton btnSave;
    private JButton btnExit;
    private Organizer organizer;
    private Database db = Database.getInstance();

    public CreateEventGUI(Organizer organizer) {
        this.organizer = organizer;

        setTitle("Create Event");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Event Name
        JLabel lblName = new JLabel("Event Name:");
        lblName.setBounds(20, 20, 100, 20);
        contentPane.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(130, 20, 300, 25);
        contentPane.add(txtName);

        // Event Date
        JLabel lblDate = new JLabel("Date:");
        lblDate.setBounds(20, 60, 100, 20);
        contentPane.add(lblDate);

        txtDate = new JTextField();
        txtDate.setBounds(130, 60, 300, 25);
        contentPane.add(txtDate);

        // Event Location
        JLabel lblLocation = new JLabel("Location:");
        lblLocation.setBounds(20, 100, 100, 20);
        contentPane.add(lblLocation);

        txtLocation = new JTextField();
        txtLocation.setBounds(130, 100, 300, 25);
        contentPane.add(txtLocation);

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setBounds(20, 140, 100, 20);
        contentPane.add(timeLabel);

        timeText = new JTextField();
        timeText.setBounds(130, 140, 300, 25);
        contentPane.add(timeText);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(20, 180, 100, 20);
        contentPane.add(descriptionLabel);

        descriptionText = new JTextArea();
        descriptionText.setBounds(130, 180, 300, 80);
        descriptionText.setLineWrap(true);
        descriptionText.setWrapStyleWord(true);
        contentPane.add(descriptionText);

        btnSave = new JButton("Save Event");
        btnSave.setBounds(150, 300, 150, 30);
        // action listener for saving the event
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // get the input values 
                String name = txtName.getText().trim();
                String date = txtDate.getText().trim();
                String location = txtLocation.getText().trim();
                String time = timeText.getText().trim();
                String description = descriptionText.getText().trim();

                if (!name.isEmpty() && !date.isEmpty() && !location.isEmpty()) {
                    // create new event object
                    Event event = new Event(name, date, location, time, description, organizer);
                    db.addEvent(event);
                    organizer.createEvent(event);
                    db.saveAll(); 

                    JOptionPane.showMessageDialog(null, "Event created successfully!");

                    new OrganizerGUI(organizer).setVisible(true);
                    // close the window 
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in all required fields!");
                }
            }
        });
        contentPane.add(btnSave);

        // button to go back to the previous window
        btnExit = new JButton("Cancel");
        btnExit.setBounds(310, 300, 100, 30);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new OrganizerGUI(organizer).setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnExit);

        setVisible(true);
    }
}
