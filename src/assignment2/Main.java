package assignment2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {

    public static void main(String[] args) {
        final List<String> caseItems = new ArrayList<>();
        // Map of the data
        final Map<Character, Integer> caseData = Map.ofEntries(
                Map.entry('a', 2),
                Map.entry('b', 22),
                Map.entry('c', 222),
                Map.entry('d', 3),
                Map.entry('e', 33),
                Map.entry('f', 333),
                Map.entry('g', 4),
                Map.entry('h', 44),
                Map.entry('i', 444),
                Map.entry('j', 5),
                Map.entry('k', 55),
                Map.entry('l', 555),
                Map.entry('m', 6),
                Map.entry('n', 66),
                Map.entry('o', 666),
                Map.entry('p', 7),
                Map.entry('q', 77),
                Map.entry('r', 777),
                Map.entry('s', 7777),
                Map.entry('t', 8),
                Map.entry('u', 88),
                Map.entry('v', 888),
                Map.entry('w', 9),
                Map.entry('x', 99),
                Map.entry('y', 999),
                Map.entry('z', 9999),
                Map.entry(' ', 0)
        );

        // Read the inputs
        final Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        final int cases = sc.nextInt();

        for (int i = 0; i < cases; i++) {
            caseItems.add(sc.next());
        }

        sc.close();

        final List<String> caseDecimals = new ArrayList<>();

        // Analyze collected data
        for (String caseItem : caseItems) {
            final var caseItemDecimal = new StringBuilder();

            final Collection<Character> caseChars = caseItem.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            Character previousDecimalGroup = null;
            // Read through individual characters
            for (Character caseChar : caseChars) {
                final var caseDecimal = caseData.get(caseChar);
                final var decimalGroup = caseDecimal.toString().charAt(caseDecimal.toString().length() - 1);
                // Check if it is the same as the previous decimal group, if so, append a space
                if (previousDecimalGroup != null && previousDecimalGroup == decimalGroup) {
                    caseItemDecimal.append(" ");
                }
                caseItemDecimal.append(caseDecimal);

                previousDecimalGroup = decimalGroup;
            }

            caseDecimals.add(caseItemDecimal.toString());
        }

        // Output the results
        for (int i = 0; i < caseDecimals.size(); i++) {
            final var printCaseDecimal = caseDecimals.get(i);
            System.out.printf("Case #%d: " + printCaseDecimal + "\n", i + 1);
        }

    }
}
