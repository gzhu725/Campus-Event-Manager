import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateEventGUI extends JFrame {

    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtDate;
    private JTextField txtLocation;
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
        JTextField timeText = new JTextField();
        timeText.setBounds(130, 140, 300, 25);
        contentPane.add(timeText);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(20, 180, 100, 20);
        contentPane.add(descriptionLabel);

        JTextArea descriptionText = new JTextArea();
        descriptionText.setBounds(130, 180, 300, 100);
        contentPane.add(descriptionText);

        // Save Button
        btnSave = new JButton("Save Event");
        btnSave.setBounds(150, 300, 150, 30);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add Event to Organizer's list
                String name = txtName.getText();
                String date = txtDate.getText();
                String location = txtLocation.getText();
                String time = timeText.getText();
                String description = descriptionText.getText();
                
                if (!name.isEmpty() && !date.isEmpty() && !location.isEmpty()) {
                    /* asuming Event class has a constructor like Event(String name, String date, String location) */
                    Event event = new Event(name, date, location, time, description, organizer);
                    db.addEvent(event);
                    // organizer.createEvent(event);

                    JOptionPane.showMessageDialog(null, "Event created successfully!");
                    dispose();  
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                }
            }
        });
        contentPane.add(btnSave);

        // Exit Button
        btnExit = new JButton("Exit");
        btnExit.setBounds(310, 300, 80, 30);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(btnExit);

        setVisible(true);
    }
}
