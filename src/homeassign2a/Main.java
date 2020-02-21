package homeassign2a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        final var stringMap = new HashMap<Integer, StringBuilder>();
        final var br = new BufferedReader(new InputStreamReader(System.in));

        final int stringCount = Integer.parseInt(br.readLine());
        final int stringOpCount = stringCount - 1;

        // read strings
        // start from index 1
        for (int i = 1; i <= stringCount; i++) {
            stringMap.put(i, new StringBuilder(br.readLine()));
        }

        // read and carry out operations
        for (int i = 0; i < stringOpCount; i++) {
            final StringTokenizer operationLine = new StringTokenizer(br.readLine());
            final int operationA = Integer.parseInt(operationLine.nextToken());
            final int operationB = Integer.parseInt(operationLine.nextToken());

            stringMap.get(operationA).append(stringMap.get(operationB));

            stringMap.replace(operationB, new StringBuilder(""));
        }

        stringMap.forEach((k, v) -> System.out.print(v));
    }
}