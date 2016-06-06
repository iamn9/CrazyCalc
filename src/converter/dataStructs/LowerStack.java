package converter.dataStructs;

import converter.dataStructs.LL.LinkedList;

public class LowerStack {

	private LinkedList linkList;

	public LowerStack() {
		linkList = new LinkedList();
	}

	public void push(Object j) {
		linkList.addFirst(j);
	}

	public Object pop() {
		return linkList.removeFirst().getValue();
	}

	public Object peek() {
		return linkList.getFirst().getValue();
	}

	public boolean isEmpty() {
		return (linkList.getFirst() == null);
	}
}