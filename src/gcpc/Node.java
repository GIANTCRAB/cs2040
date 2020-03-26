package gcpc;

public class Node<T> {
    public Node<T> left;
    public Node<T> right;
    public Node<T> parent;
    public int key;
    public T value;
    private int height;
    public int size;

    Node(int key, T value) {
        this.key = key;
        this.value = value;
    }

    int getHeight() {
        return this.height;
    }

    void updateHeight() {
        this.height = this.calculateHeight(this);
    }

    int calculateHeight(Node<T> node) {
        if (node == null) {
            return -1;
        } else {
            return Math.max(calculateHeight(this.left), calculateHeight(this.right)) + 1;
        }
    }
}
