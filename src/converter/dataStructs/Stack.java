package converter.dataStructs;

public class Stack {
	public Queue queueDS, tempQueue;

	public Stack() {
		queueDS = new Queue();
		tempQueue = new Queue();
	}

	public void push(Object insert) {
		while (!queueDS.isEmpty()) {
			tempQueue.enqueue(queueDS.dequeue());
		}
		queueDS.enqueue(insert);
		while (!tempQueue.isEmpty()) {
			queueDS.enqueue(tempQueue.dequeue());
		}
	}

	public Object pop() {
		return queueDS.dequeue();
	}

	public Object peek() {
		Object peekValue = queueDS.dequeue();
		queueDS.enqueue(peekValue);
		return peekValue;
	}

	public boolean isEmpty() {
		return queueDS.isEmpty();
	}
}