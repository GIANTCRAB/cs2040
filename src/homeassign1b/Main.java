package homeassign1b;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {

    public static void main(String[] args) {
        // Read the inputs
        final Scanner sc = new Scanner(System.in);

        final int numberOfRunners = sc.nextInt();
        final int MAX_RUNNERS = 4;
        final List<RelayRunner> runnerList = new ArrayList<>();
        final List<RelayRunner> chosenRunnerList = new ArrayList<>();
        final Map<Float, RelayRunner> teamWithTime = new TreeMap<>();

        for (int i = 0; i < numberOfRunners; i++) {
            final String runnerName = sc.next();
            final float firstLegTime = sc.nextFloat();
            final float secondLegTime = sc.nextFloat();

            runnerList.add(new RelayRunner(runnerName, firstLegTime, secondLegTime));
        }

        // get runners by second leg
        float totalSecondLegTime = 0f;
        runnerList.sort(new RelayRunnerSecondLegComparator());
        for (int x = 0; x < MAX_RUNNERS; x++) {
            final RelayRunner runner = runnerList.get(x);
            chosenRunnerList.add(runner);

            totalSecondLegTime += runner.getSecondLegTime();
        }

        // Give all possible permutations
        for (final RelayRunner chosenFirstRunner : chosenRunnerList) {
            final float newTiming = chosenFirstRunner.getFirstLegTime() + (totalSecondLegTime - chosenFirstRunner.getSecondLegTime());
            teamWithTime.put(newTiming, chosenFirstRunner);
        }

        // Choose best possible runner
        final RelayRunner bestFirstRunner = teamWithTime.keySet().stream().findFirst().map(teamWithTime::get).orElse(null);

        // re-sort the final list
        if (bestFirstRunner != null) {
            chosenRunnerList.remove(bestFirstRunner);
            chosenRunnerList.sort(new RelayRunnerSecondLegComparator());

            // re-insert first runner to top of list
            chosenRunnerList.add(0, bestFirstRunner);
        }

        Float totalTime = 0f;
        for (RelayRunner chosenRunner : chosenRunnerList) {
            System.out.println(chosenRunner.getName());
        }
    }
}
