package com.example.demo.leetcode;

public class App16Deque {

	class MyCircularDeque {

		int[] queue;
		int size;
		int capicity;

		/** Initialize your data structure here. Set the size of the deque to be k. */
		public MyCircularDeque(int k) {
			this.queue = new int[k];
			this.capicity = 0;
			this.size = k;
		}

		/**
		 * Adds an item at the front of Deque. Return true if the operation is
		 * successful.
		 */
		public boolean insertFront(int value) {

			if (isFull())
				return false;

			for (int i = this.capicity; i > 0; i--) {
				this.queue[i] = this.queue[i - 1];
			}
			this.queue[0] = value;
			this.capicity++;
			return true;

		}

		/**
		 * Adds an item at the rear of Deque. Return true if the operation is
		 * successful.
		 */
		public boolean insertLast(int value) {

			if (isFull())
				return false;
			this.queue[this.capicity] = value;
			this.capicity++;
			return true;

		}

		/**
		 * Deletes an item from the front of Deque. Return true if the operation is
		 * successful.
		 */
		public boolean deleteFront() {
			if (isEmpty())
				return false;

			for (int i = 0; i < this.capicity-1; i++) {
				this.queue[i] = this.queue[i + 1];
			}
			this.queue[this.capicity-1] = 0;
			this.capicity--;
			return true;

		}

		/**
		 * Deletes an item from the rear of Deque. Return true if the operation is
		 * successful.
		 */
		public boolean deleteLast() {

			if (isEmpty())
				return false;

			this.queue[this.capicity-1] = 0;
			this.capicity--;
			return true;

		}

		/** Get the front item from the deque. */
		public int getFront() {
			if(isEmpty()) return -1;
			return this.queue[0];
		}

		/** Get the last item from the deque. */
		public int getRear() {
			if(isEmpty()) return -1;
			return this.queue[this.capicity - 1];
		}

		/** Checks whether the circular deque is empty or not. */
		public boolean isEmpty() {

			return this.capicity == 0;
		}

		/** Checks whether the circular deque is full or not. */
		public boolean isFull() {

			return this.size == this.capicity;
		}
	}

	/**
	 * Your MyCircularDeque object will be instantiated and called as such:
	 * MyCircularDeque obj = new MyCircularDeque(k); boolean param_1 =
	 * obj.insertFront(value); boolean param_2 = obj.insertLast(value); boolean
	 * param_3 = obj.deleteFront(); boolean param_4 = obj.deleteLast(); int param_5
	 * = obj.getFront(); int param_6 = obj.getRear(); boolean param_7 =
	 * obj.isEmpty(); boolean param_8 = obj.isFull();
	 */
}
