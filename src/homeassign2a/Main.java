package homeassign2a;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

            // Begin retrieval
            retrieveRecursively(finalOpId, linkedListArray, stringArray);
        }
    }

    // Fetch itself recursively
    private static void retrieveRecursively(Integer id, List<LinkedList<Integer>> linkedListArray, String[] stringArray) {
        LinkedList<Integer> currentLinkedList = linkedListArray.get(id);

        System.out.print(stringArray[id]);
        currentLinkedList.forEach(i -> retrieveRecursively(i, linkedListArray, stringArray));
    }
}