package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> node = new QueueNode<>(e);
		if (last == null) {
			last = node;
			last.next = node;
		} else {
			QueueNode<E> prevLast = last;
			QueueNode<E> prevFirst = last.next;

			prevLast.next = node;
			last = node;
			last.next = prevFirst;
		}
		size++;

		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		return last != null ? last.next.element : null;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if (size == 0) {
			return null;
		} else if (size == 1) {
			E e = last.element;
			last = null;
			size = 0;
			return e;
		}

		QueueNode<E> first = last.next;
		last.next = first.next;

		size--;

		return first.element;
	}

	/**
	 * Appends the specified queue to this queue
	 * post: all elements from the specified queue are appended
	 * to this queue. The specified queue (q) is empty after the call.
	 * @param q the queue to append
	 * @throws IllegalArgumentException if this queue and q are identical
	 */
	public void append(FifoQueue<E> q) {
		if (q == this) {
			throw new IllegalArgumentException();
		}

		if (q.size() == 0) {
			return;
		}

		if (this.size() == 0) {
			this.duplicate(q);
			q.quickClear();
			return;
		}

		QueueNode<E> thisLast = this.last;
		QueueNode<E> thisFirst = this.last.next;
		QueueNode<E> otherLast = q.last;

		this.last = otherLast;
		thisLast.next = otherLast.next;
		otherLast.next = thisFirst;

		this.size = size + q.size;

		q.quickClear();
	}

	private void duplicate(FifoQueue<E> q) {
		last = q.last;
		size = q.size;
	}

	private void quickClear() {
		last = null;
		size = 0;
	}


	/**
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		private QueueIterator() {
			pos = last;
		}
		public boolean hasNext() {
			return pos != null;
		}
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			pos = pos.next;
			E e = pos.element;

			if (pos == last) {
				pos = null;
			}

			return e;
		}
	}


	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
