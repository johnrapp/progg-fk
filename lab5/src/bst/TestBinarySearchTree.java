package bst;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestBinarySearchTree {
    private BinarySearchTree<Integer> emptyTree;
    private BinarySearchTree<Integer> filledTree;

    @Before
    public void setUp() throws Exception {
        emptyTree = new BinarySearchTree<>();
        filledTree = new BinarySearchTree<>();
        filledTree.add(2);
        filledTree.add(1);
        filledTree.add(3);
        filledTree.add(4);
    }

    @After
    public void tearDown() throws Exception {
        emptyTree = null;
        filledTree = null;
    }

    @Test
    public void heightEmptyTree() {
        assertEquals(0, emptyTree.height());
    }
    @Test
    public void heightSingleTree() {
        emptyTree.add(2);
        assertEquals(1, emptyTree.height());
    }
    @Test
    public void heightComplexTree() {
        assertEquals(3, filledTree.height());
    }

    @Test
    public void sizeEmptyTree() {
        assertEquals(0, emptyTree.size());
    }

    @Test
    public void sizeSingleTree() {
        emptyTree.add(2);
        assertEquals(1, emptyTree.size());
    }

    @Test
    public void cantAddDuplicates() {
        boolean added = filledTree.add(2);
        assertEquals(4, filledTree.size());
        assertFalse(added);
    }

    @Test
    public void toArrayLinearAdd() {
        Integer[] expected = new Integer[4];
        for (int i = 1; i <= 4; i++) {
            emptyTree.add(i);
            expected[i - 1] = i;
        }
        assertArrayEquals(expected, filledTree.toArray());
    }

    @Test
    public void toArrayComplexTree() {
        Integer[] expected = new Integer[4];
        for (int i = 1; i <= 4; i++) {
            expected[i - 1] = i;
        }
        assertArrayEquals(expected, filledTree.toArray());
    }
}