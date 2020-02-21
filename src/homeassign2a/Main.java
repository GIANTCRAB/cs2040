package homeassign2a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {
    public static void main(String[] args) throws IOException {
        final var stringMap = new ArrayList<StringBuilder>();
        final var br = new BufferedReader(new InputStreamReader(System.in));

        final int stringCount = Integer.parseInt(br.readLine());
        final int stringOpCount = stringCount - 1;

        // read strings
        // start from index 0
        for (int i = 0; i < stringCount; i++) {
            stringMap.add(new StringBuilder(br.readLine()));
        }

        // read and carry out operations
        for (int i = 0; i < stringOpCount; i++) {
            // 0 based index
            final StringTokenizer operationLine = new StringTokenizer(br.readLine());
            final int operationA = Integer.parseInt(operationLine.nextToken()) - 1;
            final int operationB = Integer.parseInt(operationLine.nextToken()) - 1;

            final StringBuilder operationBString = stringMap.get(operationB);
            stringMap.get(operationA).append(operationBString);
            stringMap.get(operationB).delete(0, operationBString.length());
        }

        stringMap.forEach(v -> System.out.print(v.toString()));
    }
}