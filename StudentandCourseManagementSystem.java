package management;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Random;
import java.util.List; 
import java.util.Map;
import java.util.HashMap;

public class StudentandCourseManagementSystem {
	
	
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        // Initialize systems
	        SMS studentManagementSystem = new SMS();
	        CMS courseManagementSystem = new CMS();

	        // Welcome message
	        System.out.println("Welcome to Student and Course Management System!\n");

	        // Prompt for number of students on the same line
	        System.out.print("This system will allow you to manage students and courses. Let's start with the number of students this system will have: ");
	        scanner.nextInt();

	        // Main loop
	        while (true) {
	            System.out.println("\n*** Welcome to Student and Course Management System ***");
	            System.out.println("Press '1' for Student Management System (SMS)");
	            System.out.println("Press '2' for Course Management System (CMS)");
	            System.out.println("Press '0' to exit");
	            System.out.print("");
	            int systemChoice = scanner.nextInt(); // Reading the user's choice

	         // Switch case to handle the user's choice
	            switch (systemChoice) {
	                case 1: // Option 1: Run the Student Management System
	                    studentManagementSystem.runSMS();
	                    break;
	                case 2: // Option 2: Run the Course Management System
	                    courseManagementSystem.runCMS();
	                    break;
	                case 0:// Option 0: Exit the application
	                    System.out.println("Good Bye!!!");
	                    scanner.close(); // Closing the scanner before exiting
	                    System.exit(0); // Exiting the application
	                    break;
	                default: // Default case for handling invalid inputs
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
	    }
	}


class StudentSMS {
	
    private static HashSet<Integer> usedIDs = new HashSet<>();  // Stores unique IDs to ensure no duplicates
    private static Random random = new Random(); // Random object for generating IDs
    
    private int studentID;
    private String firstName;
    private String lastName;
    private String level; // Academic level (e.g., freshman, sophomore)
    private boolean isActive; // Current active status of the student
    private String job; // Job title if assigned
    private String jobType;  // Type of job (e.g., part-time, internship)

 // Constructor to initialize a new student with basic information
    public StudentSMS(String firstName, String lastName, String level) {
        this.studentID = generateUniqueID(); // Assigns a unique ID
        this.firstName = firstName;
        this.lastName = lastName;
        this.level = level;
        this.isActive = true; // New students are active by default
        this.job = "";
        this.jobType = "";
    }

 // Generates a unique student ID
    private static int generateUniqueID() {
        int id;
        do {
            id = random.nextInt(100);
        } while (usedIDs.contains(id));
        usedIDs.add(id);
        return id; 
    }

 // Getter for student ID
    public int getStudentID() {
        return studentID;
    }

 // Returns the active status of the student
    public boolean isActive() {
        return isActive;
    }

 // Sets the active status of the student
    public void setActive(boolean active) {
        isActive = active;
    }

 // Assigns a job to the student
    public void assignJob(String job, String jobType) {
        this.job = job;
        this.jobType = jobType;
    }

 // Returns job information or a message if no job is assigned
    public String getJobInfo() {
        return job.isEmpty() ? "No job assigned" : jobType + " " + job;
    }

 // Overrides the default toString method to display student information
    @Override
    public String toString() {
        return firstName + " " + lastName +
               "\nID: " + studentID +
               "\nLevel: " + level +
               "\nStatus: " + (isActive ? "Active" : "Inactive") +
               "\nJob: " + getJobInfo() + "\n";
    }

 // Additional methods with placeholders for future implementation
	public int getLastName() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String displayInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		
	}
}

	class SMS {
    private List<StudentSMS> students; // List of students in the system
    private Scanner scanner; // Scanner object for input operations

 // Constructor initializes the students list and scanner
    public SMS() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

 // Main loop for running the student management system
    public void runSMS() {
        while (true) {
        	
        	// Display the main menu
            System.out.println("\n***Welcome to SMS***");
            System.out.println("Press '1' to add a student");
            System.out.println("Press '2' to deactivate a student");
            System.out.println("Press '3' to display all students");
            System.out.println("Press '4' to search for a student by ID");
            System.out.println("Press '5' to assign on-campus job");
            System.out.println("Press '6' to display all students with on-campus jobs");
            System.out.println("Press '0' to exit SMS");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Fix for scanner bug

         // Switch-case to handle different user choices
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    deactivateStudent();
                    break;
                case 3:
                    displayStudents();
                    break;
                case 4:
                    findStudent();
                    break;
                case 5:
                    assignJob();
                    break;
                case 6:
                    displayStudentsWithJobs();
                    break;
                case 0:
                    return; // Exit the SMS loop and go back to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

 // Handles adding a new student to the system
    private void addStudent() {
        System.out.print("\nEnter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter level of the student: ");
        String level = scanner.nextLine();

        StudentSMS newStudent = new StudentSMS(firstName, lastName, level);
        students.add(newStudent);
        System.out.println("\n" + firstName + " " + lastName + " has been added as a " + level + " with ID " + newStudent.getStudentID());
    }

 // Deactivates a student based on ID
    private void deactivateStudent() {
        System.out.print("\nEnter the ID for the student you want to deactivate: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline

        for (StudentSMS student : students) {
            if (student.getStudentID() == id && student.isActive()) {
                student.setActive(false);
                System.out.println("\n" + student.getFirstName() + " " + student.getLastName() + " has been deactivated");
                return;
            }
        }
        System.out.println("Student not found or already inactive.");
    }

 // Displays all students in the system
    private void displayStudents() {
        System.out.println();
        for (StudentSMS student : students) {
            System.out.println(student);
        }
    }

 // Finds and displays a student by ID
    private void findStudent() {
        System.out.print("\nEnter the student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (StudentSMS student : students) {
            if (student.getStudentID() == id) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student not found.");
    }

 // Assigns a job to a student
    private void assignJob() {
        System.out.print("\nEnter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline

        System.out.print("Enter job: ");
        String job = scanner.nextLine();

        System.out.print("Enter job type: ");
        String jobType = scanner.nextLine();

        for (StudentSMS student : students) {
            if (student.getStudentID() == id && student.isActive()) {
                student.assignJob(job, jobType);
                System.out.println("" + student.getFirstName() + " " + student.getLastName() + " has been assigned " + jobType + " " + job + " job");
                return;
            }
        }
       
        System.out.println("Student not found or is inactive.");
    }

 // Displays students who have been assigned jobs
    private void displayStudentsWithJobs() {
        System.out.println();
        boolean found = false;
        for (StudentSMS student : students) {
            if (!student.getJobInfo().equals("No job assigned")) {
                System.out.println(student);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No students currently have on-campus jobs.");
        }
    }
}
class Course {
	private String courseId; // Unique identifier for the course
    private String courseName;  // Name of the course

 // Constructor to initialize a course with its ID and name
    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

 // Getter for course ID
    public String getCourseId() {
        return courseId;
    }

 // Getter for course name
    public String getCourseName() {
        return courseName;
    }
}


// Ensure that this Student class incorporates all fields and methods from both SMS and CMS versions.
class StudentCMS {
	private String studentId; // Unique identifier for the student
    private String studentName; // Name of the student
    private List<Course> courses; // List of courses the student is enrolled in

 // Constructor to initialize a new student with ID and name
    public StudentCMS(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courses = new ArrayList<>(); // Initializes an empty course list
    }

 // Adds a course to the student's course list
    public void addCourse(Course course) {
        courses.add(course);
    }

 // Compiles and returns the student's information including enrolled courses
    public String displayInfo() {
        StringBuilder info = new StringBuilder();
        info.append(studentName).append("\nID - ").append(studentId).append("\nCourses: ");
        for (Course c : courses) {
            info.append(c.getCourseId()).append(" ");
        }
        return info.toString().trim(); // Trims any extra whitespace from the string
    }
}


class CMS {
    private Map<String, Course> courses; // Stores courses using their ID as key
    private Map<String, StudentSMS> students; // Stores students using their ID as key
    private Scanner scanner; // Scanner object for input operations

 // Constructor initializes the maps and scanner for CMS operations
    public CMS() {
        courses = new HashMap<>();
        students = new HashMap<>();
        scanner = new Scanner(System.in);
    }

 // Main loop for running the CMS
    public void runCMS() {
        while (true) {
            System.out.println("***Welcome to CMS***\n");
            System.out.println("Press '1' to add a new course");
            System.out.println("Press '2' to assign student a new course");
            System.out.println("Press '3' to display student with assigned courses");
            System.out.println("Press '0' to exit CMS\n");

            System.out.print("");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addNewCourse();
                    break;
                case 2:
                    assignCourseToStudent();
                    break;
                case 3:
                    displayStudentsWithCourses();
                    break;
                case 0:
                    return; // Exit CMS loop
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

 // Adds a new course to the CMS
    private void addNewCourse() {
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        courses.put(courseId, new Course(courseId, courseName));
        System.out.println("Confirmation: New course " + courseId + " has been added\n");
    }

 // Assigns a course to a student in the CMS
    private void assignCourseToStudent() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        // Assuming that the student name is being handled elsewhere or is not required for CMS
        students.computeIfAbsent(studentId, k -> new StudentSMS(studentId, "Unnamed", k));
        students.get(studentId).addCourse(courses.get(courseId));
        System.out.println("Confirmation: Student has been assigned course " + courseId + "\n");
    }

 // Displays students and their assigned courses
    private void displayStudentsWithCourses() {
        students.values().forEach(student -> System.out.println(student.displayInfo() + "\n"));
    }
}