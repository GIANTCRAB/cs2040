package homeassign2a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        final Map<Integer, String> stringMap = new LinkedHashMap<>();
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final StringTokenizer st = new StringTokenizer(br.readLine());
        final int stringCount = Integer.parseInt(st.nextToken());
        final int stringOpCount = stringCount - 1;

        // read strings
        // start from index 1
        for (int i = 1; i <= stringCount; i++) {
            stringMap.put(i, br.readLine());
        }

        // final output string
        String outputString = "";

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

            if (i + 1 == stringOpCount) {
                outputString = combinedString;
            }
        }

        System.out.println(outputString);
    }
}