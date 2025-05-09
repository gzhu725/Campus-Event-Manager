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
