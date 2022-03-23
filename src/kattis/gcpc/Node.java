package kattis.gcpc;

public class Node<T> {
    public Node<T> left = null;
    public Node<T> right = null;
    public Node<T> parent = null;
    public int key;
    public T value;
    public int height;
    public int size;

    Node(int key, T value) {
        this.key = key;
        this.value = value;
    }
}
