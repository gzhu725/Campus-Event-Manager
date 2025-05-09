import java.util.*;

public class Database {

  // SINGLETON
  private static Database instance = null;
  private Database() {
    // private constructor to prevent instantiation
  }
  public static Database getInstance() {
    if (instance == null) {
      instance = new Database();
    }
    return instance;
  }

  private List<Event> events = new ArrayList<Event>();
  private List<Student> students = new ArrayList<Student>();
  private List<Organizer> organizers = new ArrayList<Organizer>();

  // "GET" events
  public List<Object> getAllUsers() {
    List<Object> allUsers = new ArrayList<>();
    allUsers.addAll(students);
    allUsers.addAll(organizers);
    System.out.println(allUsers.toString());
    return allUsers;
  }

  public List<Student> getAllStudents() {
    System.out.println(students.toString());
    return students;
  }

  public List<Organizer> getAllOrganizers() {
    System.out.println(organizers.toString());
    return organizers;
  }

  public List<Event> getAllEvents() {
    System.out.println(events.toString());
    return events;
  }

  public List<Event> getRegisteredEvents(Student student) {
    List<Event> registeredEvents = new ArrayList<>();
    for (Event event : events) {
      if (event.getAttendees().contains(student)) {
        registeredEvents.add(event);
      }
    }
    return registeredEvents;
  }

  public List<Event> getCreatedEvents(Organizer organizer) {
    List<Event> organizedEvents = new ArrayList<>();
    for (Event event : events) {
      if (event.getOrganizer().equals(organizer)) {
        organizedEvents.add(event);
      }
    }
    return organizedEvents;
  }

  // "ADD" events
  public void addEvent(Event event) {
    events.add(event);
  }
  public void addStudent(Student student) {
    students.add(student);
  }
  public void addOrganizer(Organizer organizer) {
    organizers.add(organizer);
  }

  public void addAttendee(Student student, Event event) {
    event.addAttendee(student);
  }

  // "REMOVE" events
  public void removeEvent(Event event) {
    events.remove(event);
  }
  public void removeStudent(Student student) {
    students.remove(student);
  }
  public void removeOrganizer(Organizer organizer) {
    organizers.remove(organizer);
  }

  public void removeAttendee(Student student, Event event) {
    event.removeAttendee(student);
  }

  
}
