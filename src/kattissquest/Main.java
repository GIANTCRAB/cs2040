package kattissquest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {
    public static void main(String[] args) throws IOException {
        final TreeMap<Integer, Queue<Integer>> quests = new TreeMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int commandCount = Integer.parseInt(br.readLine());

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512)) {
                // read strings
                // start from index 0
                for (int i = 0; i < commandCount; i++) {
                    StringTokenizer operationLine = new StringTokenizer(br.readLine());
                    String operationCommand = operationLine.nextToken();

                    switch (operationCommand) {
                        case "add":
                            final int energy = Integer.parseInt(operationLine.nextToken());
                            final int gold = Integer.parseInt(operationLine.nextToken());
                            final var questEntry = quests.get(energy);
                            if (questEntry != null) {
                                // There's already a quest with the same amount of energy requirements
                                questEntry.add(gold);
                            } else {
                                // Create a new quest entry
                                final Queue<Integer> questPq = new PriorityQueue<>(Comparator.reverseOrder());
                                questPq.add(gold);
                                quests.put(energy, questPq);
                            }
                            break;
                        case "query":
                            int maxEnergy = Integer.parseInt(operationLine.nextToken());

                            int totalGoldEarned = calculateTotalGold(quests, maxEnergy);
                            bw.write(totalGoldEarned + "\n");

                            break;
                    }
                }

                bw.flush();
            }
        }
    }

    public static int calculateTotalGold(TreeMap<Integer, Queue<Integer>> quests, int startingEnergy) {
        boolean allEnergyExpended = false;
        int energyLeft = startingEnergy;
        int totalGoldEarned = 0;
        while (!allEnergyExpended) {
            final var foundQuest = quests.floorEntry(energyLeft);

            if (foundQuest != null) {
                final var questPq = foundQuest.getValue();
                final Integer specificQuestEnergy = foundQuest.getKey();
                final Integer specificQuestGold = questPq.poll();

                energyLeft -= specificQuestEnergy;
                totalGoldEarned += specificQuestGold;
                if (questPq.isEmpty()) {
                    quests.remove(foundQuest.getKey());
                }
            } else {
                allEnergyExpended = true;
            }
        }

        return totalGoldEarned;
    }
}