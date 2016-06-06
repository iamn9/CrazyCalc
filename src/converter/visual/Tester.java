package converter.visual;

import javax.swing.JFrame;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedListGFX gfx = new LinkedListGFX("");

		JFrame f1 = new JFrame();
		f1.add(gfx);
		f1.setVisible(true);
		f1.setSize(600, 800);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			Thread.sleep(1000);
			gfx.addFirst(1);
			Thread.sleep(1000);
			gfx.addFirst(2);
			Thread.sleep(1000);
			gfx.addFirst(3);
			Thread.sleep(1000);
			gfx.removeFirst();
			Thread.sleep(1000);
			gfx.removeFirst();
			Thread.sleep(1000);
			gfx.removeFirst();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
