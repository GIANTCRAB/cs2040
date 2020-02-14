package assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {

    public static void main(String[] args) {
        final List<List<String>> studentList = new ArrayList<>();

        // Read the inputs
        final Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        int caseNumber = sc.nextInt();

        while (caseNumber != 0) {
            final List<String> classStudentList = new ArrayList<>();
            for (int i = 0; i < caseNumber; i++) {
                classStudentList.add(sc.next());
            }

            // Do sorting
            classStudentList.sort(new StudentNameComparator());
            // Add to main student List
            studentList.add(classStudentList);

            caseNumber = sc.nextInt();
        }

        for (List<String> sortedClassStudentList : studentList) {
            for (String sortedStudent : sortedClassStudentList) {
                System.out.println(sortedStudent);
            }
            System.out.print("\n");
        }
    }
}
