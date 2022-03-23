package kattis.conformity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {
    public static void main(String[] args) throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            final Map<String, Integer> coursePermutations = new HashMap<>();
            final int MAX_COURSES_PER_STUDENT = 5;
            final int studentCount = Integer.parseInt(br.readLine());

            for (int i = 0; i < studentCount; i++) {
                final var studentCourseList = new ArrayList<Integer>();

                var studentCourseTaking = new StringTokenizer(br.readLine());
                for (int j = 0; j < MAX_COURSES_PER_STUDENT; j++) {
                    final var courseNumber = Integer.parseInt(studentCourseTaking.nextToken());
                    studentCourseList.add(courseNumber);
                }

                Collections.sort(studentCourseList);

                final var sortedCourseName = new StringBuilder();
                for (Integer sortedCourseNumber : studentCourseList) {
                    sortedCourseName.append(sortedCourseNumber);
                }

                var value = coursePermutations.getOrDefault(sortedCourseName.toString(), 0);
                coursePermutations.put(sortedCourseName.toString(), value + 1);
            }

            var maxCourseCombination = 0;
            for (int courseCount : coursePermutations.values()) {
                maxCourseCombination = Math.max(maxCourseCombination, courseCount);
            }

            var total = 0;
            for (int courseCount : coursePermutations.values()) {
                if (courseCount == maxCourseCombination) {
                    total += courseCount;
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512)) {
                bw.write(String.valueOf(total));
                bw.flush();
            }
        }
    }
}