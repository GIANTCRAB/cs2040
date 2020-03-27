package gcpc;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {
    public static void main(String[] args) {
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

        System.out.println("After Deletion: ");
        tree.printTree(tree.rootNode, "", true);
    }
}