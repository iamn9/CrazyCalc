package converter.visual;

import java.awt.Color;
import java.util.ArrayList;

import converter.dataStructs.LL.Link;

@SuppressWarnings("serial")
public class LinkedListGFX extends GFXTemplate {
	private Link first;
	private Link last;

	public LinkedListGFX(String name) {
		super(name);
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

		updateGFX();
		try {
			setStatus("adding First Node");
			Thread.sleep(SPEED);
			setStatus("");
			Thread.sleep(SPEED / 2);
		} catch (Exception e) {
		}
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
			updateGFX();
			try {
				setStatus("adding Last Node");
				Thread.sleep(SPEED);
				setStatus("");
				Thread.sleep(SPEED / 2);
			} catch (Exception e) {
			}
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
		updateGFX();
		try {
			setStatus("removing First Node");
			Thread.sleep(SPEED);
			setStatus("");
			Thread.sleep(SPEED / 2);
		} catch (Exception e) {
		}
		return node;
	}

	public Link removeLast() {
		Link node = last;
		last = last.getPrevious();
		updateGFX();
		try {
			setStatus("removing Last Node");
			Thread.sleep(SPEED);
			setStatus("");
			Thread.sleep(SPEED / 2);
		} catch (Exception e) {
		}
		return node;
	}

	private void updateGFX() {
		Link node = first;
		ArrayList<Link> ll = new ArrayList<Link>();
		while (node != null) {
			ll.add(node);
			node = node.getNext();
		}

		int i;
		for (i = 1; i <= ll.size(); i++) {
			buttons[visible - i].setText(ll.get(i - 1).getValue().toString());
			buttons[visible - i].setBackground(Color.WHITE);
		}
		for (; i <= visible; i++) {
			buttons[visible - i].setBackground(Color.LIGHT_GRAY);
			buttons[visible - i].setText("");
		}
		try {
			buttons[visible - ll.size()].setBackground(filled);
			buttons[visible - 1].setBackground(filled);
		} catch (Exception e) {
		}
		this.repaint();
	}
}
