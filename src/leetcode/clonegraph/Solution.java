package leetcode.clonegraph;

import java.util.*;

// My initial approach was to use a HashMap
// This visited HashMap with its key as the node.val and its value as Node
// We initialise an empty node with no neighbour first
// Thereafter, we can put it into the visited hash map
// If a node value already exists in the map, we can return immediately and not loop
// For my loop, i went with new ArrayList<>(node.neighbors.stream().map().toList())
// This solved the problem but it was sub-optimal
// Changing to use a fixed index array and not having to initialise 2 arrays
// and instead using a for(Node neighbor: node.neighbors) would lead to a major improvement in performance

class Solution {
    private Node[] visited = new Node[101];

    public Node cloneGraph(Node node) {
        if(node == null) {
            return null;
        }
        if(visited[node.val] != null) {
            return visited[node.val];
        }
        Node newNode = new Node(node.val);
        visited[node.val] = newNode;
        ArrayList<Node> newList = new ArrayList<>();
        for(Node neighbor: node.neighbors) {
            newList.add(cloneGraph(neighbor));
        }
        newNode.neighbors = newList;
        return newNode;
    }
}