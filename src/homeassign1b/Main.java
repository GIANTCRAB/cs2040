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
        final List<RelayRunner> runnerList = new ArrayList<>();
        final Map<Float, RelayRunner> runnerMap = new TreeMap<>();

        for (int i = 0; i < numberOfRunners; i++) {
            final String runnerName = sc.next();
            final float firstLegTime = sc.nextFloat();
            final float secondLegTime = sc.nextFloat();

            runnerList.add(new RelayRunner(runnerName, firstLegTime, secondLegTime));
        }

        // Apply sorting through comparator
        runnerList.sort(new RunnerSecComparator());

        final List<RelayRunner> topSecondLapRunnerList = runnerList
                .stream()
                .limit(MAX_SECOND_LEG_RUNNERS + 1)
                .collect(Collectors.toList());

        for (final RelayRunner iRunner : runnerList) {
            Float totalTime = iRunner.getFirstLegTime();
            // Choose 3 runners to be second lap runners
            final List<RelayRunner> chosenRunnerList = topSecondLapRunnerList
                    .stream()
                    .filter(rn -> !rn.equals(iRunner))
                    .limit(MAX_SECOND_LEG_RUNNERS)
                    .collect(Collectors.toList());
            for (final RelayRunner jRunner : chosenRunnerList) {
                totalTime += jRunner.getSecondLegTime();
            }

            runnerMap.put(totalTime, iRunner);
        }

        // Choose best possible runner
        final Optional<Float> bestTiming = runnerMap.keySet().stream().findFirst();
        final RelayRunner bestFirstRunner = bestTiming.map(runnerMap::get).orElse(null);

        final List<RelayRunner> finalRunnerList = topSecondLapRunnerList
                .stream()
                .filter(rn -> !rn.equals(bestFirstRunner))
                .limit(MAX_SECOND_LEG_RUNNERS)
                .collect(Collectors.toList());
        finalRunnerList.add(0, bestFirstRunner);

        System.out.printf("%.2f\n", bestTiming.get());
        for (RelayRunner chosenRunner : finalRunnerList) {
            System.out.println(chosenRunner.getName());
        }
    }
}
