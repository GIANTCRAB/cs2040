package assignment3;

import java.util.Comparator;

public class StudentNameComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        // Check first character
        final Character o1FirstChar = o1.charAt(0);
        final Character o2FirstChar = o2.charAt(0);
        final int firstSortResult = o1FirstChar.compareTo(o2FirstChar);

        if (firstSortResult == 0) {
            // Is same, check second char
            final Character o1SecondChar = o1.charAt(1);
            final Character o2SecondChar = o2.charAt(1);

            return o1SecondChar.compareTo(o2SecondChar);
        } else {
            return firstSortResult;
        }
    }
}
