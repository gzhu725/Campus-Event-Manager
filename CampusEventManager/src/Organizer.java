import java.util.List;
import java.util.ArrayList;

// Organizer class represents a user who can create and manage events
public class Organizer {
    private String name; // Full name of the organizer
    private String username; // Unique login username
    private String password; // Account password

    // List to store events created by this organizer
    private List<Event> createdEvents;

    // Constructor to initialize Organizer fields
    public Organizer(String name, String username, String password) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.createdEvents = new ArrayList<>(); // Initialize the event list
    }

    // Creates a new event and add it to the organizer's list
    public void createEvent(Event event) {
        createdEvents.add(event);
    }

    // // Edits an event's details by finding it by its name
    // public void editEvent(String currentEventName, String newEventName, String newDate, String newLocation) {
    //     for (Event event : createdEvents) {
    //         if (event.getName().equals(currentEventName)) {
    //             event.setName(newEventName);
    //             event.setDate(newDate);
    //             event.setLocation(newLocation);
    //             return;
    //         }
    //     }
    // }

    // Getters and setters for all attributes
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
    
    public List<Event> getCreatedEvents() {
        return createdEvents;
    }
    
    public void setCreatedEvents(List<Event> createdEvents) {
        this.createdEvents = createdEvents;
    }    
}
