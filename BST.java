// importing main utils

import java.util.List;
import java.util.ArrayList;

public class BST<K extends Comparable<K>, V> {
    private Node root;

    // Node class representing a node in the binary search tree
    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    // Inserts a key-value pair into the binary search tree
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    // Recursive helper method to insert a key-value pair into the binary search tree
    private Node put(Node node, K key, V val) {
        if (node == null) {
            return new Node(key, val);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.val = val; // Update value if key already exists
        }
        return node;
    }

    // Retrieves the value associated with a given key in the binary search tree
    public V get(K key) {
        Node node = get(root, key);
        return node != null ? node.val : null;
    }

    // Recursive helper method to search for a key in the binary search tree
    private Node get(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    // Deletes a key-value pair from the binary search tree
    public void delete(K key) {
        root = delete(root, key);
    }

    // Recursive helper method to delete a key-value pair from the binary search tree
    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            // Node to delete found
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                // Node to delete has both left and right child
                Node minRightNode = findMin(node.right);
                node.key = minRightNode.key;
                node.val = minRightNode.val;
                node.right = delete(node.right, minRightNode.key);
            }
        }
        return node;
    }

    // Finds the minimum key in the binary search tree rooted at the given node
    public Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Returns an iterator for the binary search tree that iterates through the keys in ascending order
    public Iterable<K> iterator() {
        List<K> keys = new ArrayList<>();
        inorderTraversal(root, keys);
        return keys;
    }
    // Performs an inorder traversal of the binary search tree and adds the keys to the list

    private void inorderTraversal(Node node, List<K> keys) {
        if (node == null)
            return;
        inorderTraversal(node.left, keys); // Traverse left subtree
        keys.add(node.key); // Add current node's key to the list
        inorderTraversal(node.right, keys); // Traverse right subtree
    }

    // Returns the size (number of nodes) of the binary search tree
    public int size() {
        return size(root);
    }

    // Recursive helper method to calculate the size of the binary search tree
    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + size(node.left) + size(node.right);
        }
    }
    public int maxHeight(){
        return maxHeight(root);
    }
    private int maxHeight(Node node){
        if(node == null){
            return 0;
        }
        int left = maxHeight(node.left);
        int right = maxHeight(node.right);
        if(left > right){
            return left+1;
        }
        return right+1;
    }
}