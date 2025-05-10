import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditEventGUI extends JFrame {

    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtDate;
    private JTextField txtLocation;
    private JTextField txtTime;
    private JTextField txtDescription;
    private JButton btnSave;
    private JButton btnCancel;
    private Event event;
    private Organizer organizer;
    private Database db = Database.getInstance();

    public EditEventGUI(Event event) {
        this.event = event;
        this.organizer = event.getOrganizer();

        setTitle("Edit Event");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // event name field
        JLabel lblName = new JLabel("Event Name:");
        lblName.setBounds(20, 20, 100, 25);
        contentPane.add(lblName);
        txtName = new JTextField(event.getName());
        txtName.setBounds(130, 20, 400, 25);
        contentPane.add(txtName);

        // event date field
        JLabel lblDate = new JLabel("Date:");
        lblDate.setBounds(20, 60, 100, 25);
        contentPane.add(lblDate);
        txtDate = new JTextField(event.getDate());
        txtDate.setBounds(130, 60, 400, 25);
        contentPane.add(txtDate);

        // event location
        JLabel lblLocation = new JLabel("Location:");
        lblLocation.setBounds(20, 100, 100, 25);
        contentPane.add(lblLocation);
        txtLocation = new JTextField(event.getLocation());
        txtLocation.setBounds(130, 100, 400, 25);
        contentPane.add(txtLocation);

        // event time
        JLabel lblTime = new JLabel("Time:");
        lblTime.setBounds(20, 140, 100, 25);
        contentPane.add(lblTime);
        txtTime = new JTextField(event.getTime());
        txtTime.setBounds(130, 140, 400, 25);
        contentPane.add(txtTime);

        // description
        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setBounds(20, 180, 100, 25);
        contentPane.add(lblDescription);
        txtDescription = new JTextField(event.getDescription());
        txtDescription.setBounds(130, 180, 400, 25);
        contentPane.add(txtDescription);

        btnSave = new JButton("Save Changes");
        btnSave.setBounds(150, 240, 150, 30);

        // when "save changes" is clicked, update the event details
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText().trim();
                String date = txtDate.getText().trim();
                String location = txtLocation.getText().trim();
                String time = txtTime.getText().trim();
                String desc = txtDescription.getText().trim();

                if (name.isEmpty() || date.isEmpty() || location.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all required fields.");
                    return;
                }

                // update event object with new values
                event.setName(name);
                event.setDate(date);
                event.setLocation(location);
                event.setTime(time);
                event.setDescription(desc);

                db.saveAll(); 

                JOptionPane.showMessageDialog(null, "Event updated successfully.");
                new CreatedEventsGUI(organizer).setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnSave);

        // cancel button
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(320, 240, 100, 30);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreatedEventsGUI(organizer).setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnCancel);

        setVisible(true);
    }
}
