package kattisquest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {
    public static void main(String[] args) throws IOException {
        final TreeSet<Quest> quests = new TreeSet<>(new QuestComparator());
        int highestGold = 0;

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

                            final Quest newQuest = new Quest(energy, gold);

                            if (gold > highestGold) {
                                highestGold = gold;
                            }
                            quests.add(newQuest);
                            break;
                        case "query":
                            int maxEnergy = Integer.parseInt(operationLine.nextToken());

                            int totalGoldEarned = calculateTotalGold(quests, maxEnergy, highestGold);
                            bw.write(totalGoldEarned + "\n");

                            break;
                    }
                }

                bw.flush();
            }
        }
    }

    public static int calculateTotalGold(TreeSet<Quest> quests, int startingEnergy, int highestGold) {
        boolean allEnergyExpended = false;
        int energyLeft = startingEnergy;
        int totalGoldEarned = 0;
        while (!allEnergyExpended) {
            final Quest searchQuest = new Quest(energyLeft, highestGold);

            final Quest foundQuest = quests.floor(searchQuest);
            if (foundQuest != null) {
                energyLeft -= foundQuest.getEnergy();
                totalGoldEarned += foundQuest.getGold();
                quests.remove(foundQuest);
            } else {
                allEnergyExpended = true;
            }
        }

        return totalGoldEarned;
    }
}