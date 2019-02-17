package testqueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import queue_singlelinkedlist.FifoQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestAppendFifoQueue {
    private FifoQueue<Integer> q1;
    private FifoQueue<Integer> q2;

    @Before
    public void setUp() throws Exception {
        q1 = new FifoQueue<Integer>();
        q2 = new FifoQueue<Integer>();
    }

    @After
    public void tearDown() throws Exception {
        q1 = null;
        q2 = null;
    }

    @Test
    public final void testAppendTwoEmpty() {
        q1.append(q2);
        assertTrue("q1 still empty", q1.size() == 0);
        assertTrue("q2 still empty", q2.size() == 0);
    }

    @Test
    public final void testAppendEmpty() {
        q1.offer(1);
        q1.offer(2);

        q1.append(q2);
        assertTrue("q1 the same", q1.size() == 2);
        assertTrue("q2 still empty", q2.size() == 0);
    }

    @Test
    public final void testAppendNonEmpty() {
        q2.offer(1);
        q2.offer(2);

        q1.append(q2);
        assertTrue("q1 contains new elements", q1.size() == 2);
        assertTrue("q2 empty", q2.size() == 0);
    }

    @Test
    public final void testAppendQs() {
        for (int i = 0; i < 10; i++) {
            q1.offer(i);
        }

        for (int i = 10; i < 20; i++) {
            q2.offer(i);
        }

        q1.append(q2);

        assertTrue("Size is updated", q1.size() == 20);
        assertTrue("Size is updated", q2.size() == 0);

        for (int i = 0; i < 20; i++) {
            assertTrue("Gives elements in right order", q1.poll() == i);
        }
        assertTrue("q2 is cleared", q2.size() == 0);
    }

    @Test
    public final void testAppendSameQ() {
        try {
            q1.append(q1);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Passed
        }
    }



    /** Test a single offer followed by a single peek. */
    /*
    @Test
    public final void testPeek() {
        myIntQueue.offer(1);
        int i = myIntQueue.peek();
        assertEquals("peek on queue of size 1", 1, i);
        assertTrue(myIntQueue.size() == 1);
    }*/


}
