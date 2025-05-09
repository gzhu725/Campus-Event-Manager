import java.util.*;

public class Event {
  private String name;
  private String date;
  private String location;
  private String time;
  private String description;
  private Organizer organizer;
  private List<Student> attendees;

  public Event(String name, String date, String location, String time, String description, Organizer organizer) {
    this.name = name;
    this.date = date;
    this.location = location;
    this.time = time;
    this.description = description;
    this.organizer = organizer;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }
  public String getTime() {
    return time;
  }
  public void setTime(String time) {
    this.time = time;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  public void addAttendee(Student student) {
    if (attendees == null) {
      attendees = new ArrayList<>();
    }
    attendees.add(student);
  }

  public void removeAttendee(Student student) {
    if (attendees != null) {
      attendees.remove(student);
    }
  }

  public List<Student> getAttendeeList() {
    return attendees;
  }

  
  
}
