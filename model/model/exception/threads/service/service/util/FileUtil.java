package util;

import java.io.*;
import java.util.*;
import model.Student;

public class FileUtil {

    public static List<Student> loadFile(String fileName) {
        List<Student> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                int roll = Integer.parseInt(p[0]);
                String name = p[1];
                String email = p[2];
                String course = p[3];
                double marks = Double.parseDouble(p[4]);

                list.add(new Student(roll, name, email, course, marks));
            }

        } catch (Exception e) {
            System.out.println("Error Reading File: " + e.getMessage());
        }

        return list;
    }

    public static void saveFile(String fileName, List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            for (Student s : students) {
                bw.write(s.toFile());
                bw.newLine();
            }

        } catch (Exception e) {
            System.out.println("Error Saving File: " + e.getMessage());
        }
    }
}
