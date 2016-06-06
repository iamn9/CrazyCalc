package converter.dataStructs.LL;

public class Link {
	private Object value;
	private Link next;
	private Link prev;

	public Link(Object x) {
		next = null;
		prev = null;
		value = x;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object newValue) {
		value = newValue;
	}

	public Link getNext() {
		return next;
	}

	public Link getPrevious() {
		return prev;
	}

	public void setNext(Link newLink) {
		next = newLink;
	}

	public void setPrevious(Link newLink) {
		prev = newLink;
	}
}
