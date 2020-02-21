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
        retrieveRecursively(linkedListArray, builderArrayList, finalOpId);
    }

    // Fetch itself recursively
    private static void retrieveRecursively(ArrayList<LinkedList<Integer>> list, String[] stringList, int id) {
        final var currentLinkedList = list.get(id);

        System.out.print(stringList[id]);
        while (currentLinkedList.size() > 0) {
            final var i = currentLinkedList.poll();
            retrieveRecursively(list, stringList, i);
        }
    }
}