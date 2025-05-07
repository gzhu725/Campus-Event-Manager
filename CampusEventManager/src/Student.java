import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String username;
    private String password;
    // Waiting for Event class to be created
    private List<Event> registeredEvents;

    // Constructs a new Student with the given details
    public Student(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.registeredEvents = new ArrayList<>();
    }

    // Registers the student for a given event
    public void registerEvent(Event event) {
        if (!registeredEvents.contains(event)) {
            registeredEvents.add(event);
            event.addAttendee(this);
        }
    }

    // Cancels the student's registration for a given event
    public void cancelRegistration(Event event) {
        if (registeredEvents.remove(event)) {
            event.removeAttendee(this);
        }
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Event> getRegisteredEvents() { 
        return registeredEvents;
    }
    
    public void setRegisteredEvents(List<Event> registeredEvents) {
        this.registeredEvents = registeredEvents;
    }
}
