package homeassign2a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        final Map<Integer, String> stringMap = new HashMap<>();
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int stringCount = Integer.parseInt(br.readLine());
        final int stringOpCount = stringCount - 1;

        // read strings
        // start from index 1
        for (int i = 1; i <= stringCount; i++) {
            stringMap.put(i, br.readLine());
        }

        // read and carry out operations
        for (int i = 0; i < stringOpCount; i++) {
            final StringTokenizer operationLine = new StringTokenizer(br.readLine());
            final int operationA = Integer.parseInt(operationLine.nextToken());
            final int operationB = Integer.parseInt(operationLine.nextToken());

            final String opAString = stringMap.get(operationA);
            final String opBString = stringMap.get(operationB);
            final String combinedString = opAString.concat(opBString);

            stringMap.replace(operationA, combinedString);
            stringMap.replace(operationB, "");
        }

        stringMap.forEach((k, v) -> System.out.print(v));
    }
}