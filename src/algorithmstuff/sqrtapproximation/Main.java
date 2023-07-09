package algorithmstuff.sqrtapproximation;

import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {
        newtonMethod(2);
    }

    public static void newtonMethod(int target) {
        double currentGuess = 1;
        double currentResult;
        boolean closeEnough = false;
        DecimalFormat df = new DecimalFormat("#.########");
        DecimalFormat resultDf = new DecimalFormat("#.#####");
        while (!closeEnough) {
            currentGuess = Double.parseDouble(df.format(((target / currentGuess) + currentGuess) / 2));
            currentResult = Double.parseDouble(resultDf.format(currentGuess * currentGuess));
            closeEnough = currentResult == target;
        }
        System.out.println(currentGuess);
    }
}
