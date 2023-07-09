package algorithmstuff.sqrtapproximation;

public class Main {

    public static void main(String[] args) {
        newtonMethod(9);
    }

    public static void newtonMethod(int target) {
        double currentGuess = 1;
        double currentResult;
        boolean closeEnough = false;
        while (!closeEnough) {
            currentGuess = ((target / currentGuess) + currentGuess) / 2;
            currentResult = currentGuess * currentGuess;
            closeEnough = Math.floor(currentResult) == target || Math.ceil(currentResult) == target;
        }
        System.out.println(currentGuess);
    }
}
