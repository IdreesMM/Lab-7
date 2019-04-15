package testbinarysearchtree;
import java.util.*;
import java.io.*;
public class TestBinarySearchTree {
    public static void main(String[] args) {
        Integer[] num = {67, 87, 55, 43, 48, 73, 91, 39, 59, 92, 34, 95, 81, 66, 40, 53, 84, 77};
        // Create an empty BinaryTree
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(num);
        Scanner input = new Scanner(System.in);
        System.out.println("Binary Search Tree:");
        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i] + " ");
        }//fori
        System.out.println();
        tree.output();
        System.out.print("\n\nEnter an element to search: ");
        Integer key = input.nextInt();
        System.out.println("tree.search(key)=\t\t" + tree.search(key));
        System.out.print("\nEnter an element to delete: ");
        key = input.nextInt();
        tree.delete(key);
        System.out.println("The deletion of number\t\t" + key);
        tree.output();
        System.out.print("\nEnter an element to find it's predecessor: ");
        key = input.nextInt();
        System.out.println("The predecessor of " + key + " is: " + tree.inOrder2(key));
        //complete the code as suggested in the Lab document.
    }//Main
}//testBinarySearchTree