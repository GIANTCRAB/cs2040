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
        final int MAX_SECOND_LEG_RUNNERS = 3;
        final int MAX_RUNNERS = 4;
        final List<RelayRunner> runnerList = new ArrayList<>();
        List<RelayRunner> chosenRunnerList = new ArrayList<>();
        final Map<Float, RelayRunner> teamWithTime = new TreeMap<>();

        for (int i = 0; i < numberOfRunners; i++) {
            final String runnerName = sc.next();
            final float firstLegTime = sc.nextFloat();
            final float secondLegTime = sc.nextFloat();

            runnerList.add(new RelayRunner(runnerName, firstLegTime, secondLegTime));
        }

        /**runnerList.sort(new RelayRunnerSecondLegComparator());

        int index = 0;
        Float maxTiming = null;
        for (int i = 0; i < runnerList.size(); i++) {
            float totalTiming = runnerList.get(i).getFirstLegTime();
            for (int j = 0; j < MAX_RUNNERS; j++) {
                if (i != j) {
                    totalTiming += runnerList.get(j).getSecondLegTime();
                }
            }
            if (maxTiming == null || maxTiming > totalTiming) {
                maxTiming = totalTiming;
                index = i;
            }
        }*/

        // get runners by second leg
        float totalSecondLegTime = 0f;
        runnerList.sort(new RelayRunnerSecondLegComparator());
        for (final RelayRunner runner : runnerList) {
            totalSecondLegTime += runner.getSecondLegTime();
        }

        // Give all possible permutations
        for (final RelayRunner firstRunner : runnerList) {
            final float newTiming = firstRunner.getFirstLegTime() + (totalSecondLegTime - firstRunner.getSecondLegTime());
            teamWithTime.put(newTiming, firstRunner);
        }

        // Choose best possible runner
        final RelayRunner bestFirstRunner = teamWithTime.keySet().stream().findFirst().map(teamWithTime::get).orElse(null);

        Float totalTime = 0f;

        // re-sort the final list
        if (bestFirstRunner != null) {
            totalTime += bestFirstRunner.getFirstLegTime();
            runnerList.remove(bestFirstRunner);
            chosenRunnerList = runnerList.stream()
                    .sorted(new RelayRunnerSecondLegComparator())
                    .limit(MAX_SECOND_LEG_RUNNERS)
                    .collect(Collectors.toList());
            for (RelayRunner chosenRunner : chosenRunnerList) {
                totalTime += chosenRunner.getSecondLegTime();
            }

            // re-insert first runner to top of list
            chosenRunnerList.add(0, bestFirstRunner);
        }

        System.out.println(totalTime);
        for (RelayRunner chosenRunner : chosenRunnerList) {
            System.out.println(chosenRunner.getName());
        }

        for (RelayRunner chosenRunner : teamWithTime.values()) {
            //System.out.println(chosenRunner.getName());
            //System.out.println(chosenRunner.getFirstLegTime());
        }
    }
}
