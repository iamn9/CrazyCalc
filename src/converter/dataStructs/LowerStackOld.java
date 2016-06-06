package converter.dataStructs;

class LowerStackOld {

	private Object[] stackArray;
	private int top = -1;

	public LowerStackOld() {
		stackArray = new Object[150];
	}

	public void push(Object j) {
		stackArray[++top] = j;
	}

	public Object pop() {
		if (top == -1) {
			return null;
		}
		return stackArray[top--];
	}

	public Object peek() {
		return stackArray[top];
	}

	public boolean isEmpty() {
		return (top == -1);
	}
}