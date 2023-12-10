import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
}

class Student {
    String studentID;
    String name;
    List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (course.capacity > 0) {
            registeredCourses.add(course);
            course.capacity--;
            System.out.println("Course registration successful.");
        } else {
            System.out.println("Course is full. Registration failed.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.capacity++;
            System.out.println("Course dropped successfully.");
        } else {
            System.out.println("You are not registered for this course.");
        }
    }
}

public class StudentCourseReg {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CSE101", "Introduction to Programming", "Basic programming concepts", 30, "MWF 10:00 AM"));
        courses.add(new Course("MAT201", "Calculus I", "Fundamental calculus topics", 25, "TTH 2:00 PM"));
        courses.add(new Course("CSE3002","Artificial Intelligence","Artificial Intelligence",30,"MWF 3:00 PM"));
        courses.add(new Course("CSE2001","Data Structures and Algorithms","DSA",30,"MWS 11:00 AM"));
        courses.add(new Course("MAT1003","Discrete Mathematical Structures","Mathematics",35,"TTF 3:00 PM"));

        List<Student> students = new ArrayList<>();
        students.add(new Student("S001", "Satya Pranay"));
        students.add(new Student("S002", "Rakesh"));
        students.add(new Student("S003", "Pavan"));
        students.add(new Student("S004", "Sai Kiran"));
        students.add(new Student("S005", "Dinesh"));


        displayCourseListing(courses);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Register for a course");
            System.out.println("2. Drop a course");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            if (choice == 1) {
                registerCourse(scanner, students, courses);
            } else if (choice == 2) {
                dropCourse(scanner, students, courses);
            } else if (choice == 3) {
                System.out.println("Exiting. Thank you!");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void displayCourseListing(List<Course> courses) {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course.code + " - " + course.title + " (" + course.schedule + ")");
            System.out.println("   Description: " + course.description);
            System.out.println("   Capacity: " + course.capacity + " / " + course.capacity);
            System.out.println();
        }
    }

    private static void registerCourse(Scanner scanner, List<Student> students, List<Course> courses) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.next();

        Student student = findStudentByID(studentID, students);
        if (student != null) {
            System.out.print("Enter course code to register: ");
            String courseCode = scanner.next();

            Course course = findCourseByCode(courseCode, courses);
            if (course != null) {
                student.registerCourse(course);
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void dropCourse(Scanner scanner, List<Student> students, List<Course> courses) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.next();

        Student student = findStudentByID(studentID, students);
        if (student != null) {
            System.out.print("Enter course code to drop: ");
            String courseCode = scanner.next();

            Course course = findCourseByCode(courseCode, student.registeredCourses);
            if (course != null) {
                student.dropCourse(course);
            } else {
                System.out.println("You are not registered for this course.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static Student findStudentByID(String studentID, List<Student> students) {
        for (Student student : students) {
            if (student.studentID.equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String courseCode, List<Course> courses) {
        for (Course course : courses) {
            if (course.code.equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}

