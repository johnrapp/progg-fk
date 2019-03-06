package bst;

public class Main {
    public static void main(String [] args) {
        BSTVisualizer visualizer = new BSTVisualizer("BST", 600, 600);

        BinarySearchTree<Integer> skewedTree = new BinarySearchTree<>();
        for (int i = 1; i <= 15; i++) {
            skewedTree.add(i);
        }
        //skewedTree.rebuild();
        //visualizer.drawTree(skewedTree);

        BinarySearchTree<Integer> skewedTree2 = new BinarySearchTree<>();
        for (int i = 1; i <= 5; i++) {
            skewedTree2.add(6 - i);
        }
        //visualizer.drawTree(skewedTree2);

        BinarySearchTree<Integer> balancedTree = new BinarySearchTree<>();
        balancedTree.add(4);
        balancedTree.add(2);
        balancedTree.add(1);
        balancedTree.add(3);
        balancedTree.add(6);
        balancedTree.add(7);
        balancedTree.add(5);
        //visualizer.drawTree(balancedTree);

        BinarySearchTree<Integer> balancedTree2 = new BinarySearchTree<>();
        for (int i = 0; i <= 15 * 2 - 1; i += 2) {
            balancedTree2.add(i);
        }
        balancedTree2.rebuild();
        visualizer.drawTree(balancedTree2);
    }

}
