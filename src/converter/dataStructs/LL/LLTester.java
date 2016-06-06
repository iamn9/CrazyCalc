package converter.dataStructs.LL;

import converter.dataStructs.LowerStack;

public class LLTester {

	public static void main(String[] args) {
		LinkedList ll = new LinkedList();

		System.out.println("linkList addFirst(1),...,addFirst(5)");
		ll.addFirst(1);
		ll.addFirst(2);
		ll.addFirst(3);
		ll.addFirst(4);
		ll.addFirst(5);

		Link scan = ll.getFirst();
		System.out.println("Traversing LinkList from first to last...");
		while (scan != null) {
			System.out.println(scan.getValue());
			scan = scan.getNext();
		}
		System.out.println("Remove Last Value (and print it)....");
		while (ll.getLast() != null) {
			System.out.println(ll.removeLast().getValue());
		}

		System.out.println("Pushing 1 to 5 to LowerStack (linkList implemented)...");
		LowerStack lsTest = new LowerStack();
		lsTest.push(1);
		lsTest.push(2);
		lsTest.push(3);
		lsTest.push(4);
		lsTest.push(5);

		System.out.println("Peek lowerStack: " + lsTest.peek());
		System.out.println("Pop lowerStack values...");
		while (!lsTest.isEmpty()) {
			System.out.println(lsTest.pop());
		}
	}

}
