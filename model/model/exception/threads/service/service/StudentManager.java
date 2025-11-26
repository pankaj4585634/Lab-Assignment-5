package service;

import model.Student;
import exception.StudentNotFoundException;
import threads.Loader;
import util.FileUtil;

import java.util.*;

public class StudentManager implements RecordActions {

    private List<Student> studentList;

    public StudentManager(List<Student> list) {
        studentList = list;
    }

    @Override
    public void addStudent(Student s) {
        Thread loader = new Thread(new Loader());
        loader.start();

        try { loader.join(); } catch (Exception e) {}

        studentList.add(s);
        System.out.println("Student Added Successfully!");
    }

    @Override
    public void deleteStudent(String name) throws StudentNotFoundException {
        Iterator<Student> it = studentList.iterator();
        while (it.hasNext()) {
            if (it.next().getName().equalsIgnoreCase(name)) {
                it.remove();
                System.out.println("Student Deleted Successfully!");
                return;
            }
        }
        throw new StudentNotFoundException("Student Not Found!");
    }

    @Override
    public void updateStudent(int rollNo, double newMarks) throws StudentNotFoundException {
        for (Student s : studentList) {
            if (s.getRollNo() == rollNo) {
                studentList.remove(s);
                studentList.add(
                    new Student(rollNo, s.getName(), s.email, s.course, newMarks)
                );
                System.out.println("Student Updated");
                return;
            }
        }
        throw new StudentNotFoundException("Roll No Not Found!");
    }

    @Override
    public Student searchStudent(String name) throws StudentNotFoundException {
        for (Student s : studentList) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        throw new StudentNotFoundException("Student Not Found!");
    }

    @Override
    public void viewAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No Records Available!");
            return;
        }
        for (Student s : studentList) {
            s.displayInfo();
        }
    }

    @Override
    public void sortByMarks() {
        studentList.sort(Comparator.comparingDouble(Student::getMarks).reversed());
        System.out.println("Sorted by Marks:");
        viewAllStudents();
    }

    public List<Student> getStudents() {
        return studentList;
    }
}
