import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditEventGUI extends JFrame {

    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtDate;
    private JTextField txtLocation;
    private JTextField txtTime;
    private JTextField txtDescription;
    private JButton btnSave;
    private JButton btnCancel;
    private Event event;  // pass this directly 

    public EditEventGUI(Event event) {
        this.event = event;
        
        setTitle("Edit Event");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Form fields
        JLabel lblName = new JLabel("Event Name:");
        lblName.setBounds(20, 20, 100, 25);
        contentPane.add(lblName);

        txtName = new JTextField(event.getName());
        txtName.setBounds(130, 20, 400, 25);
        contentPane.add(txtName);

        JLabel lblDate = new JLabel("Date:");
        lblDate.setBounds(20, 60, 100, 25);
        contentPane.add(lblDate);

        txtDate = new JTextField(event.getDate());
        txtDate.setBounds(130, 60, 400, 25);
        contentPane.add(txtDate);

        JLabel lblLocation = new JLabel("Location:");
        lblLocation.setBounds(20, 100, 100, 25);
        contentPane.add(lblLocation);

        txtLocation = new JTextField(event.getLocation());
        txtLocation.setBounds(130, 100, 400, 25);
        contentPane.add(txtLocation);

        JLabel lblTime = new JLabel("Time:");
        lblTime.setBounds(20, 140, 100, 25);
        contentPane.add(lblTime);

        txtTime = new JTextField(event.getTime());
        txtTime.setBounds(130, 140, 400, 25);
        contentPane.add(txtTime);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setBounds(20, 180, 100, 25);
        contentPane.add(lblDescription);

        txtDescription = new JTextField(event.getDescription());
        txtDescription.setBounds(130, 180, 400, 25);
        contentPane.add(txtDescription);

        // Save Button
        btnSave = new JButton("Save Changes");
        btnSave.setBounds(150, 240, 150, 30);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Directly update the event object
                event.setName(txtName.getText());
                event.setDate(txtDate.getText());
                event.setLocation(txtLocation.getText());
                event.setTime(txtTime.getText());
                event.setDescription(txtDescription.getText());

                JOptionPane.showMessageDialog(null, "Event updated successfully!");
                dispose();
            }
        });
        contentPane.add(btnSave);

        // Cancel Button
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(320, 240, 100, 30);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(btnCancel);

        setVisible(true);
    }
}
