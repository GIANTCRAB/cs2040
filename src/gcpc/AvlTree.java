package gcpc;

import java.util.Comparator;

/**
 * Needs to be able to retrieve rank
 *
 * @param <T>
 */
public class AvlTree<T> {
    Node<T> rootNode;

    AvlTree() {
        this.rootNode = null;
    }

    public void insert(int key, T value) {
        final Node<T> newNode = new Node<T>(key, value);
        this.rootNode = insertRecursive(this.rootNode, newNode);
    }

    private Node<T> insertRecursive(Node<T> current, Node<T> newNode) {
        if (current == null) return newNode;          // insertion point is found

        if (current.key < newNode.key) {                                      // search to the right
            current.right = insertRecursive(current.right, newNode);
            current.right.parent = current;
        } else {                                                 // search to the left
            current.left = insertRecursive(current.left, newNode);
            current.left.parent = current;
        }

        // Correct the height and sizing
        current.height = height(current);
        current.size = size(current.left) + size(current.right) + 1;

        // Activate balancing
        this.balance(current);

        return current;                                          // return the updated BST
    }

    Node<T> rotateLeft(Node<T> T) {
        // must have RIGHT in order to rotate
        Node<T> newParentNode = T.right;

        newParentNode.parent = T.parent;
        T.parent = newParentNode;
        if (newParentNode.left != null) {
            newParentNode.left.parent = T;
        }
        newParentNode.left = T;
        System.out.println("rotate left");

        // Update height of T and then w
        T.height = height(T);
        newParentNode.height = height(newParentNode);
        return newParentNode;
    }

    Node<T> rotateRight(Node<T> T) {
        // must have LEFT in order to rotate
        Node<T> newParentNode = T.left;
        newParentNode.parent = T.parent;
        T.parent = newParentNode;
        if (newParentNode.right != null) {
            newParentNode.right.parent = T;
        }
        newParentNode.right = T;
        System.out.println("rotate right");

        // Update height of T and then w
        T.height = height(T);
        newParentNode.height = height(newParentNode);
        return newParentNode;
    }

    /**
     * Considered balanced if and only if the balance factor is:
     * --SMALLER THAN OR EQUALS TO 1
     * --AND GREATER THAN OR EQUALS TO -1
     * ----
     * NEGATIVE value means RIGHT is taller
     * POSITIVE value means LEFT is taller
     *
     * @return
     */
    int getBalanceFactor(Node<T> T) {
        if (T.left != null && T.right != null) {
            return T.left.height - T.right.height;
        } else {
            if (T.left != null) {
                return T.left.height;
            }
            if (T.right != null) {
                return -T.right.height;
            }
        }

        return 0;
    }

    void balance(Node<T> T) {
        final int selfBalanceFactor = this.getBalanceFactor(T);

        if (selfBalanceFactor == 2) {
            // If balance factor is 2, that means there MUST be something on the left
            final int leftBalanceFactor = this.getBalanceFactor(T.left);
            if (0 <= leftBalanceFactor && leftBalanceFactor <= 1) {
                this.rotateRight(T);
            }

            if (leftBalanceFactor == -1) {
                this.rotateLeft(T.left);
                this.rotateRight(T);
            }
        }

        if (selfBalanceFactor > 2) {
            // Request for left child to be balanced
            this.balance(T.left);
        }

        if (selfBalanceFactor == -2) {
            // If balance factor is -2, that means there MUST be something on the right
            final int rightBalanceFactor = this.getBalanceFactor(T.right);
            if (-1 <= rightBalanceFactor && rightBalanceFactor <= 0) {
                this.rotateRight(T);
            }

            if (rightBalanceFactor == 1) {
                this.rotateRight(T.right);
                this.rotateLeft(T);
            }
        }

        if (selfBalanceFactor < -2) {
            // Request for right child to be balanced
            this.balance(T.right);
        }
    }

    // public method called to search for a value v.
    // Return v if it is found in the BST otherwise return -1.
    // Here the assumption is that -1 is never a valid key value.
    public int search(int v) {
        Node<T> res = search(this.rootNode, v);
        return res == null ? -1 : res.key;
    }

    // helper method to perform search
    public Node<T> search(Node<T> T, int v) {
        if (T == null) return null;                     // not found
        else if (T.key == v) return T;                        // found
        else if (T.key < v) return search(T.right, v);       // search to the right
        else return search(T.left, v);        // search to the left
    }

    // public method called to find Minimum key value in BST
    public int findMin() {
        return findMin(this.rootNode);
    }

    // helper method to perform findMin
    // Question: What happens if BST is empty?
    public int findMin(Node<T> T) {
        if (T.left == null) return T.key;                    // this is the min
        else return findMin(T.left);           // go to the left
    }

    // public method called to find Maximum key value in BST
    public int findMax() {
        return findMax(this.rootNode);
    }

    // helper method to perform findMax
    // Question: Again, what happens if BST is empty?
    public int findMax(Node<T> T) {
        if (T.right == null) return T.key;                   // this is the max
        else return findMax(T.right);        // go to the right
    }

    // public method to find successor to given value v in BST.
    public int successor(int v) {
        Node<T> vPos = search(this.rootNode, v);
        return vPos == null ? -1 : successor(vPos);
    }

    // helper recursive method to find successor to for a given vertex T in BST
    public int successor(Node<T> T) {
        if (T.right != null)                       // this subtree has right subtree
            return findMin(T.right);  // the successor is the minimum of right subtree
        else {
            Node<T> par = T.parent;
            Node<T> cur = T;
            // if par(ent) is not root and cur(rent) is its right children
            while ((par != null) && (cur == par.right)) {
                cur = par;                                         // continue moving up
                par = cur.parent;
            }
            return par == null ? -1 : par.key;           // this is the successor of T
        }
    }

    // public method to find predecessor to given value v in BST
    public int predecessor(int v) {
        Node<T> vPos = search(this.rootNode, v);
        return vPos == null ? -1 : predecessor(vPos);
    }

    // helper recursive method to find predecessor to for a given vertex T in BST
    public int predecessor(Node<T> T) {
        if (T.left != null)                         // this subtree has left subtree
            return findMax(T.left);  // the predecessor is the maximum of left subtree
        else {
            Node<T> par = T.parent;
            Node<T> cur = T;
            // if par(ent) is not root and cur(rent) is its left children
            while ((par != null) && (cur == par.left)) {
                cur = par;                                         // continue moving up
                par = cur.parent;
            }
            return par == null ? -1 : par.key;           // this is the successor of T
        }
    }

    // public method called to perform inorder traversal
    public void inorder() {
        inorder(this.rootNode);
        System.out.println();
    }

    // helper method to perform inorder traversal
    public void inorder(Node<T> T) {
        if (T == null) return;
        inorder(T.left);                               // recursively go to the left
        System.out.printf(" %d", T.key);                      // visit this BST node
        inorder(T.right);                             // recursively go to the right
    }

    // public method to delete a vertex containing key with value v from BST
    public void delete(int v) {
        this.rootNode = delete(this.rootNode, v);
    }

    // helper recursive method to perform deletion
    public Node<T> delete(Node<T> T, int v) {
        if (T == null) return T;              // cannot find the item to be deleted

        if (T.key < v)                                    // search to the right
            T.right = delete(T.right, v);
        else if (T.key > v)                               // search to the left
            T.left = delete(T.left, v);
        else {                                            // this is the node to be deleted
            if (T.left == null && T.right == null)                   // this is a leaf
                T = null;                                      // simply erase this node
            else if (T.left == null && T.right != null) {   // only one child at right
                T.right.parent = T.parent;
                T = T.right;                                                 // bypass T
            } else if (T.left != null && T.right == null) {    // only one child at left
                T.left.parent = T.parent;
                T = T.left;                                                  // bypass T
            } else {                                 // has two children, find successor
                int successorV = successor(v);
                T.key = successorV;         // replace this key with the successor's key
                T.right = delete(T.right, successorV);      // delete the old successorV
            }
        }

        this.balance(this.rootNode);

        return T;                                          // return the updated BST
    }

    public int size() {
        return size(this.rootNode);
    }

    // Number of KV pairs in the Tree
    private int size(Node<T> tNode) {
        if (tNode == null) {
            return 0;
        } else {
            return tNode.size;
        }
    }

    public int height() {
        return height(this.rootNode);
    }

    private int height(Node<T> tNode) {
        if (tNode == null) {
            return -1;
        }
        return Math.max(height(tNode.left), height(tNode.right)) + 1;
    }

    public int rank(int key) {
        return rank(key, this.rootNode);
    }

    private int rank(int key, Node<T> tNode) {
        if (tNode == null) {
            return 0;
        }
        if (tNode.key < key) {
            return rank(key, tNode.left);
        } else if (tNode.key > key) {
            return size(tNode.left) + rank(key, tNode.right) + 1;
        } else {
            return size(tNode.left);
        }
    }

    public void printTree(Node<T> currPtr, String indent, boolean last) {
        if (currPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            System.out.println(currPtr.key);

            printTree(currPtr.left, indent, false);
            printTree(currPtr.right, indent, true);
        }
    }

}
