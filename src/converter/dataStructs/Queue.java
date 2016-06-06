package converter.dataStructs;

public class Queue {

	private LowerStack lowerStack, tempStack;

	public Queue() {
		lowerStack = new LowerStack();
		tempStack = new LowerStack();
	}

	public void enqueue(Object x) {
		lowerStack.push(x.toString());
	}

	public Object dequeue() {
		Object returnValue;
		while (!lowerStack.isEmpty()) {
			tempStack.push(lowerStack.pop());
		}
		returnValue = tempStack.pop();
		while (!tempStack.isEmpty()) {
			lowerStack.push(tempStack.pop());
		}
		return returnValue;
	}

	public boolean isEmpty() {
		return lowerStack.isEmpty();
	}
}
