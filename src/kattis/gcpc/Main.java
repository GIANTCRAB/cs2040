package kattis.gcpc;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // Hashmap
        // key - scores
        // List - Team
        AvlTree<Integer> tree = new AvlTree<>();
        HashMap<Integer, List<Team>> teamScoreHashMap = new HashMap<>();

        final StringBuilder output = new StringBuilder();
        final TeamComparator teamComparator = new TeamComparator();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer teamAndEventInfo = new StringTokenizer(br.readLine());
            int teamCount = Integer.parseInt(teamAndEventInfo.nextToken(), 10);
            int eventCount = Integer.parseInt(teamAndEventInfo.nextToken(), 10);
            int teamsWithScore = 0;

            // Create the team data. Array is one-based index
            Team[] teamList = new Team[teamCount + 1];
            for (int x = 1; x <= teamCount; x++) {
                teamList[x] = new Team(x);
            }

            final Team teamOne = teamList[1];
            // Insert an initial ZERO score for ref purpose
            tree.insert(0, 0);

            for (int i = 0; i < eventCount; i++) {
                StringTokenizer teamAndPenaltyEventInfo = new StringTokenizer(br.readLine());
                int teamNumber = Integer.parseInt(teamAndPenaltyEventInfo.nextToken(), 10);
                int penalty = Integer.parseInt(teamAndPenaltyEventInfo.nextToken(), 10);

                final Team retrievedTeam = teamList[teamNumber];

                // Remove from tree if the score is not zero
                if (retrievedTeam.getScore() == 0) {
                    teamsWithScore++;
                    if (teamsWithScore == teamCount) {
                        // All teams have score now, delete the initial ZERO
                        tree.delete(0);
                    }
                } else {
                    tree.delete(retrievedTeam.getScore());
                    // Update record on hashtable
                    final var oldHashRecord = teamScoreHashMap.get(retrievedTeam.getScore());
                    oldHashRecord.remove(retrievedTeam);
                }

                retrievedTeam.incrementScore().addPenalty(penalty);
                // Re-insert into tree
                tree.insert(retrievedTeam.getScore(), 0);

                // Update hashtable
                final var newHashRecord = teamScoreHashMap.getOrDefault(retrievedTeam.getScore(), new ArrayList<>());
                newHashRecord.add(retrievedTeam);
                teamScoreHashMap.put(retrievedTeam.getScore(), newHashRecord);

                // TODO: Get reverse rank
                int teamOneRank = tree.rank(teamOne.getScore());
                // Get rank within hashtable
                final var teamOneRankRecord = teamScoreHashMap.get(teamOne.getScore());
                if (teamOneRankRecord != null) {
                    teamOneRankRecord.sort(teamComparator);
                    for (int j = 0; j < teamOneRankRecord.size(); j++) {
                        if (teamOneRankRecord.get(j).equals(teamOne)) {
                            teamOneRank += j;
                            break;
                        }
                    }
                }
                output.append(teamOneRank);
                output.append("\n");
            }

            tree.printTree(tree.rootNode, "", true);
        }


        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), StandardCharsets.US_ASCII), 512)) {
            bw.write(output.toString());
            bw.flush();
        }

    }

    private static void debug() {
        AvlTree<Integer> tree = new AvlTree<>();

        tree.insert(33, 5);
        tree.insert(13, 5);
        tree.insert(53, 5);
        tree.insert(9, 5);
        tree.insert(21, 5);
        tree.insert(61, 5);
        tree.insert(8, 5);
        tree.insert(11, 5);
        tree.insert(62, 5);
        tree.insert(65, 5);

        tree.printTree(tree.rootNode, "", true);

        tree.delete(13);
        tree.insert(7, 5);
        tree.insert(5, 5);
        tree.insert(6, 5);
        tree.delete(62);
        tree.insert(62, 5);

        System.out.println("After Deletion: ");
        tree.printTree(tree.rootNode, "", true);
        System.out.println("Rank of 65: ");
        System.out.println(tree.rank(65));
    }
}