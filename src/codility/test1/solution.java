package codility.test1;

public class solution {
    public String solution(String S, int[] A) {
        // write your code in Java SE 8
        final StringBuilder fullMessage = new StringBuilder();
        int currentIndex = 0;
        do {
            final String value = S.substring(currentIndex, currentIndex + 1);
            fullMessage.append(value);
            currentIndex = A[currentIndex];
        } while (currentIndex != 0);

        return fullMessage.toString();
    }
}
