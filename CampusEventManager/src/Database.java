import java.io.*;
import java.util.*;

public class Database {

    // SINGLETON
    private static Database instance = null;

    private List<Event> events = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Organizer> organizers = new ArrayList<>();

    // data storage
    private static final String EVENTS_FILE = "events.csv";
    private static final String STUDENTS_FILE = "students.csv";
    private static final String ORGANIZERS_FILE = "organizers.csv";
    private static final String REGISTRATIONS_FILE = "registrations.csv";

    private Database() {}

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    // "GET" endpoints
    public List<Event> getAllEvents() {
        return events;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public List<Organizer> getAllOrganizers() {
        return organizers;
    }

    //"ADD" endpoints

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

    //"DELETE" endpoints

    public void removeEvent(Event event) {
        events.remove(event);
    }

    // file manipulation
    public void saveAll() {
        saveEvents();
        saveStudents();
        saveOrganizers();
        saveRegistrations();
    }

    public void loadAll() {
        loadOrganizers();
        loadStudents();
        loadEvents();
        loadRegistrations();
    }

    // "SAVE" methods
    private void saveEvents() {
        try (PrintWriter out = new PrintWriter(EVENTS_FILE)) {
            for (Event e : events) {
                String desc = e.getDescription().replace(",", "\\,");
                out.println(String.join(",", e.getName(), e.getDate(), e.getLocation(), e.getTime(), desc, e.getOrganizer().getUsername()));
            }
        } catch (IOException e) {
            System.err.println("Failed to save events.");
        }
    }

    private void saveStudents() {
        try (PrintWriter out = new PrintWriter(STUDENTS_FILE)) {
            for (Student s : students) {
                out.println(String.join(",", s.getName(), s.getUsername(), s.getPassword()));
            }
        } catch (IOException e) {
            System.err.println("Failed to save students.");
        }
    }

    private void saveOrganizers() {
        try (PrintWriter out = new PrintWriter(ORGANIZERS_FILE)) {
            for (Organizer o : organizers) {
                out.println(String.join(",", o.getName(), o.getUsername(), o.getPassword()));
            }
        } catch (IOException e) {
            System.err.println("Failed to save organizers.");
        }
    }

    private void saveRegistrations() {
        try (PrintWriter out = new PrintWriter(REGISTRATIONS_FILE)) {
            for (Student s : students) {
                for (Event e : s.getRegisteredEvents()) {
                    out.println(s.getUsername() + "," + e.getName());
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to save registrations.");
        }
    }

    
    private void loadEvents() {
        events.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(EVENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("(?<!\\\\),");
                if (parts.length >= 6) {
                    String name = parts[0];
                    String date = parts[1];
                    String location = parts[2];
                    String time = parts[3];
                    String description = parts[4].replace("\\,", ",");
                    String organizerUsername = parts[5];
                    Organizer organizer = findOrganizerByUsername(organizerUsername);
                    if (organizer != null) {
                        Event event = new Event(name, date, location, time, description, organizer);
                        events.add(event);
                        organizer.createEvent(event); // maintain organizer's list
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load events.");
        }
    }

    private void loadStudents() {
        students.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    students.add(new Student(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load students.");
        }
    }

    private void loadOrganizers() {
        organizers.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(ORGANIZERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    organizers.add(new Organizer(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load organizers.");
        }
    }

    private void loadRegistrations() {
        try (BufferedReader reader = new BufferedReader(new FileReader(REGISTRATIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String studentUsername = parts[0];
                    String eventName = parts[1];

                    Student student = findStudentByUsername(studentUsername);
                    Event event = findEventByName(eventName);
                    if (student != null && event != null) {
                        student.registerEvent(event);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("No existing registrations found.");
        }
    }

   
    private Organizer findOrganizerByUsername(String username) {
        for (Organizer o : organizers) {
            if (o.getUsername().equals(username)) return o;
        }
        return null;
    }

    private Student findStudentByUsername(String username) {
        for (Student s : students) {
            if (s.getUsername().equals(username)) return s;
        }
        return null;
    }

    private Event findEventByName(String name) {
        for (Event e : events) {
            if (e.getName().equals(name)) return e;
        }
        return null;
    }
}
