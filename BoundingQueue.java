import java.util.Scanner;

/**
 * Problem: Using only primitive types, implement a bounded queue to store integers.
 * The data structure should be optimized for algorithmic runtime, memory usage,
 * and memory throughput. No external libraries should be imported and/or used.
 * @author Igor Pogorelskiy
 *
 */
public class BoundingQueue {
	
	private int[] bq;
	private int size; // size of queue at given moment
	private int head; // track head
	private int tail; // track tail
	
	/**
	 * Constructor
	 * @param inputLength
	 */
	public BoundingQueue(int length) {
		
		this.bq = new int[length]; // all values init at 0 in java array
		this.size = 0;
		this.head = 0; // pointer to head of queue (both pointers start at 0
		this.tail = 0; // pointer to tail of queue  since queue is empty initially)
	}
	
	/**
	 * enqueues integer to end of queue
	 * If queue is full, does nothing and prints a warning 
	 * @param n integer to enqueue
	 */
	public void enqueue(int n) {
		if (isFull()) throw new IllegalStateException("Queue is full!");
		size++;
		bq[tail++] = n;
		// efficient memory storage w/ wraparound
		if (tail == bq.length) tail = 0;
	}
	
	/**
	 * dequeues integer in beginning of queue 
	 * Throws exception if dequeueing from an empty queue
	 * @return dequeued int value
	 */
	public int dequeue() {
		if (isEmpty()) throw new IllegalStateException("\nQueue is empty!\n");
		int removedItem = bq[head];
		if (head == bq.length - 1) head = 0; // wraparound
		else head++;
		size--;
		return removedItem;	
	}
	
	/**
	 * checks if queue is empty
	 * @return true if queue is empty, false otherwise
	 */
	private boolean isEmpty() {
		return (size == 0);
	}
	
	/**
	 * checks if queue is full
	 * @return true if queue is full, false otherwise
	 */
	private boolean isFull() {
		return (size == bq.length);
	}
	
	/**
	 * print queue for unit tests
	 */
	private void print() {
		// reorganize a wraparound queue and print
		for (int i = 0; i < size; i++) {
			System.out.print(bq[(head + i) % bq.length] + "  ");
		}
		System.out.println("\n");
	}
	
	/**
	 * Main method of program
	 * Used for testing the queue
	 * @param args
	 */
	public static void main(String[] args) {
		BoundingQueue b = new BoundingQueue(Integer.parseInt(args[0]));
		Scanner sc = new Scanner(System.in);
		System.out.println("Bounded queue of size " + args[0] + " has been created.\n");

		while (true) {
			System.out.println("What would you like to do?\n 1) Print current queue: Press P\n"
					+ " 2) Enqueue an integer: Enter any valid integer\n 3) Dequeue: Press D\n 4) exit: Press E\n");
			String in = sc.next();
			if (in.equalsIgnoreCase("d")) {
				try {
					b.dequeue();
					System.out.println("\nYour queue is now... \n");
					b.print();
				} catch (IllegalStateException e) {
					System.out.println("\nQueue is empty!\n");
				}
			}
			else if (in.equalsIgnoreCase("p")) {
				System.out.println("\nYour queue is now... \n");
				b.print();
			}
			else if (in.equalsIgnoreCase("e")) {
				System.out.println("\nGoodbye!");
				sc.close();
				System.exit(0);
			} 
			else {
				try {
					int input = Integer.parseInt(in);
					b.enqueue(input);
					System.out.println("\n" + input + " has been enqueued. Your queue is now... \n");
					b.print();
				} catch (NumberFormatException e) {
					System.out.println("\nNot an integer or valid input! Try again.\n");
				} catch (IllegalStateException i) {
					System.out.println("\nQueue is full!\n");
				}
			}
		}
		
	}
}


