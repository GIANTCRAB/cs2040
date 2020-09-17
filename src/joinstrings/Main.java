package joinstrings;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int stringCount = Integer.parseInt(br.readLine());
            int stringOpCount = stringCount - 1;
            List<LinkedList<Integer>> linkedListArray = new ArrayList<>(stringCount);
            String[] stringArray = new String[stringCount];

            // read strings
            // start from index 0
            for (int i = 0; i < stringCount; i++) {
                stringArray[i] = br.readLine();
                linkedListArray.add(new LinkedList<>());
            }

            int finalOpId = 0;
            // read and carry out operations
            for (int i = 0; i < stringOpCount; i++) {
                // 0 based index
                StringTokenizer operationLine = new StringTokenizer(br.readLine());
                int operationA = Integer.parseInt(operationLine.nextToken(), 10) - 1;
                Integer operationB = Integer.parseInt(operationLine.nextToken(), 10) - 1;

                linkedListArray.get(operationA).add(operationB);

                finalOpId = operationA;
            }

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), StandardCharsets.US_ASCII), 512)) {
                // Begin retrieval
                retrieveRecursively(finalOpId, linkedListArray, stringArray, bw);
                bw.flush();
            }
        }
    }

    // Fetch itself recursively
    private static void retrieveRecursively(Integer id, List<LinkedList<Integer>> linkedListArray, String[] stringArray, BufferedWriter bw) throws IOException {
        LinkedList<Integer> currentLinkedList = linkedListArray.get(id);

        bw.write(stringArray[id]);
        currentLinkedList.forEach(i -> {
            try {
                retrieveRecursively(i, linkedListArray, stringArray, bw);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}