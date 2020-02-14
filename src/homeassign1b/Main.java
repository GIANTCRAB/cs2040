package homeassign1b;

import java.util.*;
import java.util.stream.Collectors;

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

        for (int i = 0; i < numberOfRunners; i++) {
            final String runnerName = sc.next();
            final float firstLegTime = sc.nextFloat();
            final float secondLegTime = sc.nextFloat();

            runnerList.add(new RelayRunner(runnerName, firstLegTime, secondLegTime));
        }

        // Apply sorting through comparator
        runnerList.sort(new RunnerSecComparator());

        RelayRunner selectedRunner = null;
        Float maxTiming = null;
        for (final RelayRunner iRunner : runnerList) {
            int counter = 0;
            float totalTiming = iRunner.getFirstLegTime();

            for (final RelayRunner jRunner : runnerList) {
                if (!iRunner.equals(jRunner)) {
                    totalTiming += jRunner.getSecondLegTime();
                    counter++;
                }

                if (counter + 1 == MAX_RUNNERS) {
                    break;
                }
            }
            if (maxTiming == null || maxTiming > totalTiming) {
                // Select the fastest runner
                maxTiming = totalTiming;
                selectedRunner = iRunner;
            }
        }

        // Choose 3 runners to be second lap runners
        final RelayRunner finalSelectedRunner = selectedRunner;
        final List<RelayRunner> chosenRunnerList = runnerList.stream()
                .filter(rn -> !rn.equals(finalSelectedRunner))
                .limit(MAX_SECOND_LEG_RUNNERS)
                .collect(Collectors.toList());
        // Re-insert first lap runner to top
        chosenRunnerList.add(0, selectedRunner);

        System.out.println(maxTiming);
        for (RelayRunner chosenRunner : chosenRunnerList) {
            System.out.println(chosenRunner.getName());
        }
    }
}
