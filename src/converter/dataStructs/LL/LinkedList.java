package converter.dataStructs.LL;

public class LinkedList {
	private Link first;
	private Link last;

	public LinkedList() {
		first = null;
		last = null;
	}

	public void addFirst(Object x) {
		Link node = new Link(x);
		node.setNext(first);
		node.setPrevious(null);
		if (first != null)
			first.setPrevious(node);
		else
			first = node;
		first = node;
		if (last == null)
			last = first;
	}

	public void addLast(Object x) {
		if (first == null) {
			addFirst(x);
			last = first;
		} else {
			Link node = new Link(x);
			node.setNext(null);
			node.setPrevious(last);
			last.setNext(node);
			last = node;
		}
	}

	public Link getFirst() {
		return first;
	}

	public Link getLast() {
		return last;
	}

	public Link removeFirst() {
		Link node = first;
		first = first.getNext();
		return node;
	}

	public Link removeLast() {
		Link node = last;
		last = last.getPrevious();
		return node;
	}
}
