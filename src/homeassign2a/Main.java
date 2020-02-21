package homeassign2a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {
    private static String[] stringArray;
    private static ArrayList<LinkedList<Integer>> linkedListArray;

    public static void main(String[] args) throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        final int stringCount = Integer.parseInt(br.readLine());
        final int stringOpCount = stringCount - 1;
        linkedListArray = new ArrayList<>(stringCount);
        stringArray = new String[stringCount];


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
            final var operationLine = new StringTokenizer(br.readLine());
            final var operationA = Integer.parseInt(operationLine.nextToken()) - 1;
            final var operationB = Integer.parseInt(operationLine.nextToken()) - 1;

            linkedListArray.get(operationA).add(operationB);

            finalOpId = operationA;
        }

        br.close();

        // Begin retrieval
        retrieveRecursively(finalOpId);
    }

    // Fetch itself recursively
    private static void retrieveRecursively(Integer id) {
        final var currentLinkedList = linkedListArray.get(id);

        System.out.print(stringArray[id]);
        for (Integer i : currentLinkedList) {
            retrieveRecursively(i);
        }
    }
}