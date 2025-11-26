import java.util.*;
import model.Student;
import exception.StudentNotFoundException;
import service.StudentManager;
import util.FileUtil;

public class MainApp {
    public static void main(String[] args) {

        String file = "students.txt";

        List<Student> loaded = FileUtil.loadFile(file);
        StudentManager manager = new StudentManager(loaded);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("===== Capstone Student Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search by Name");
            System.out.println("4. Delete by Name");
            System.out.println("5. Sort by Marks");
            System.out.println("6. Save and Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            try {
                switch (ch) {

                    case 1:
                        System.out.print("Enter Roll No: ");
                        int r = sc.nextInt(); sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();
                        System.out.print("Enter Course: ");
                        String course = sc.nextLine();
                        System.out.print("Enter Marks: ");
                        double marks = sc.nextDouble();

                        manager.addStudent(
                            new Student(r, name, email, course, marks)
                        );
                        break;

                    case 2:
                        manager.viewAllStudents();
                        break;

                    case 3:
                        System.out.print("Enter Name: ");
                        manager.searchStudent(sc.nextLine()).displayInfo();
                        break;

                    case 4:
                        System.out.print("Enter Name to Delete: ");
                        manager.deleteStudent(sc.nextLine());
                        break;

                    case 5:
                        manager.sortByMarks();
                        break;

                    case 6:
                        FileUtil.saveFile(file, manager.getStudents());
                        System.out.println("Saved and Exiting...");
                        return;

                    default:
                        System.out.println("Invalid Choice!");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
