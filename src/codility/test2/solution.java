package codility.test2;

public class solution {
    public int solution(String S) {
        // write your code in Java SE 8
        final StringBuilder completeString = new StringBuilder(S);
        final int startIndex = completeString.indexOf("1");
        // is everything 0 and counts no 1?
        if (startIndex == -1) {
            return 0;
        }

        final StringBuilder fixedString = new StringBuilder(completeString.substring(startIndex, completeString.length()));
        int count = 0;
        while (fixedString.length() > 0) {
            final int lastIndex = fixedString.length() - 1;
            char lastBinary = fixedString.charAt(lastIndex);
            if (lastBinary == '1') {
                // odd
                fixedString.replace(lastIndex, fixedString.length(), "0");
            } else {
                fixedString.deleteCharAt(lastIndex);
            }

            count++;
        }

        return count - 1;
    }
}
