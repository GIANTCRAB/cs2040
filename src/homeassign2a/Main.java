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
    public static void main(String[] args) throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        final int stringCount = Integer.parseInt(br.readLine());
        final int stringOpCount = stringCount - 1;
        final var linkedListArray = new ArrayList<LinkedList<Integer>>(stringCount);
        final var builderArrayList = new String[stringCount];

        // read strings
        // start from index 0
        for (int i = 0; i < stringCount; i++) {
            builderArrayList[i] = br.readLine();
            final var newLinkedList = new LinkedList<Integer>();
            linkedListArray.add(newLinkedList);
        }

        int finalOpId = 0;
        // read and carry out operations
        for (int i = 0; i < stringOpCount; i++) {
            // 0 based index
            final StringTokenizer operationLine = new StringTokenizer(br.readLine());
            final int operationA = Integer.parseInt(operationLine.nextToken()) - 1;
            final int operationB = Integer.parseInt(operationLine.nextToken()) - 1;

            linkedListArray.get(operationA).add(operationB);

            finalOpId = operationA;
        }

        br.close();

        // Begin retrieval
        retrieveRecursively(linkedListArray, builderArrayList, finalOpId);
    }

    // Fetch itself recursively
    private static void retrieveRecursively(ArrayList<LinkedList<Integer>> list, String[] stringList, int id) {
        final var currentLinkedList = list.get(id);

        System.out.print(stringList[id]);
        for (Integer i : currentLinkedList) {
            retrieveRecursively(list, stringList, i);
        }
    }
}