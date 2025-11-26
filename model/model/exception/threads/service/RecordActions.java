package service;

import model.Student;
import exception.StudentNotFoundException;

public interface RecordActions {
    void addStudent(Student s);
    void deleteStudent(String name) throws StudentNotFoundException;
    void updateStudent(int rollNo, double newMarks) throws StudentNotFoundException;
    Student searchStudent(String name) throws StudentNotFoundException;
    void viewAllStudents();
    void sortByMarks();
}
