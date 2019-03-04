package bst;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBinarySearchTree {
    private BinarySearchTree<Integer> tree;

    @Before
    public void setUp() throws Exception {
        tree = new BinarySearchTree<>();
    }

    @After
    public void tearDown() throws Exception {
        tree = null;
    }

    @Test
    public void heightEmptyTree() {
        assertEquals(0, tree.height());
    }
    @Test
    public void heightSingleTree() {
        tree.add(2);
        assertEquals(1, tree.height());
    }
    @Test
    public void heightComplexTree() {
        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.add(4);
        assertEquals(3, tree.height());
    }

    @Test
    public void sizeEmptyTree() {
        assertEquals(0, tree.size());
    }

    @Test
    public void sizeSingleTree() {
        tree.add(2);
        assertEquals(1, tree.size());
    }
}