package testbinarysearchtree;
import java.io.*;
import java.util.*;
public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {
    protected ArrayList<E> order = new ArrayList<>();
    protected TreeNode<E> root;
    protected int size = 0;
    /** Create a default binary tree */
    public BinarySearchTree() {
    }//binarySearchTree constructor
    /** Create a binary tree from an array of objects */
    public BinarySearchTree(E[] objects) {
        for (int i = 0; i < objects.length; i++)
        insert(objects[i]);
    }//binarySearchTree contructor w/parameters
    public void output() {
        System.out.println("==================================");
        System.out.println("Tree Output:");
        System.out.print("inorder():\t");
        inorder();
        System.out.print("\npreorder():\t");
        preorder();
        System.out.print("\npostorder():\t");
        postorder();
        System.out.println("\ngetNumberOfLeaves(getRoot()): " + getNumberOfLeaves(getRoot()));
        System.out.println("==================================");
    }//output
    /** Returns true if the element is in the tree */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }//if
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }//elseif
            else // element matches current.element
                return true; // Element is found
        }//while
        return false;
    }//search
    /** Insert element o into the binary tree
    *  Return true if the element is inserted successfully.
    *  Uses an iterative algorithm
    */
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
        // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }//if
                else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                }//else if
                else
                    return false; // Duplicate node not inserted
                // Create the new node and attach it to the parent node
                if (e.compareTo(parent.element) < 0)
                    parent.left = createNewNode(e);
                else
                    parent.right = createNewNode(e);
        }//else
        size++;
        return true; // Element inserted
    }//insert
    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }//createNewNode
    /** Inorder traversal from the root*/
    public void inorder() {
        inorder(root);
    }//inorder
    /** Inorder traversal from a subtree */
    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
        order.add(root.element);
    }//inorder w/parameters
    /** Postorder traversal from the root */
    public void postorder() {
        postorder(root);
    }//postorder
    /** Postorder traversal from a subtree */
    protected void postorder(TreeNode<E> root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }//postorder w/parameters
    /** Preorder traversal from the root */
    public void preorder() {
        preorder(root);
    }//preorder
    /** Preorder traversal from a subtree */
    protected void preorder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }//preorder w/parameters
    /** Inner class tree node */
    public static class TreeNode<E extends Comparable<E>> {
        E element;
        TreeNode<E> left;
        TreeNode<E> right;
        public TreeNode(E e) {
            element = e;
        }//TreeNode w/parameters
    }//TreeNode
    /** Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }//getSize
    /** Returns the root of the tree */
    public TreeNode getRoot() {
        return root;
    }//getRoot
    /** Returns a path from the root leading to the specified element */
    public ArrayList<E> path(E e){
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root
        //implement the code here as in search method.
        return list; // Return an array of elements
    }//path
    /** Delete an element from the binary tree.
    *  Return true if the element is deleted successfully
    *  Return false if the element is not in the tree */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            }//if
            else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            }//else if
            else
                break; // Element is in the tree pointed by current
        }//while
        if (current == null)
            return false; // Element is not in the tree
        // Case 1: current has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            }//if
            else {
                if (e.compareTo(parent.element) < 0)
                parent.left = current.right;
                else
                    parent.right = current.right;
            }//else
        }//if
        else {
            // Case 2 & 3: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;
            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }//while
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;
            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
        }//else
        size--;
        return true; // Element inserted
    }//delete
    /** Obtain an iterator. Use inorder. */
    public java.util.Iterator iterator() {
        return inorderIterator();
    }//iterator
    /** Obtain an inorder iterator */
    public java.util.Iterator inorderIterator() {
        return new InorderIterator();
    }//inorderIterator
    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {
        // Store the elements in a list
        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
        private int current = 0; // Point to the current element in list
        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }//inOrderIterator
        /** Inorder traversal from the root*/
        private void inorder() {
            inorder(root);
        }//inOrder
        /** Inorder traversal from a subtree */
        private void inorder(TreeNode<E> root) {
            if (root == null)return;
                inorder(root.left);
                list.add(root.element);
                inorder(root.right);
        }//inOrder w/parameters
        /** Next element for traversing? */
        public boolean hasNext() {
        if (current < list.size())
            return true;
        return false;
        }//hasNext
        /** Get the current element and move cursor to the next */
        public Object next() {
            return list.get(current++);
        }//next
        /** Remove the current element and refresh the list */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }//remove
    }//InorderIterator
    /** Remove all elements from the tree */
    public void clear() {
        root = null;
        size = 0;
    }//clear

    public int getNumberOfLeaves(TreeNode<E> root){
        if(root == null) {
            return 0;
        }//if
        else if ((root.left == null) && (root.right == null)) {
            return 1;
        }//elseif
        else {
            return getNumberOfLeaves(root.left) + getNumberOfLeaves(root.right);
        }//else
    }//getNumberOfLeaves
    public E inOrder2(E e){
        E ans;
        int num = order.indexOf(e);
        ans = order.get(num-1);
        return ans;
    }// inorder predecessor
}//BST