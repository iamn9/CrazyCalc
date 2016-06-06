package converter.visual;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class LowerStackGFX extends GFXTemplate {

	public QueueGFX queueGraphical, tempQG;
	public LinkedListGFX llgfx;

	public LowerStackGFX(String name) {
		super(name);
		llgfx = new LinkedListGFX("LinkedList implementation of LowerStack");
	}

	public void push(Object name) throws InterruptedException {
		status.setText("PUSHING ITEM");
		buttonCtr++;
		if (buttonCtr > 9) {
			visible++;
			buttons[visible - 1] = new JButton(name.toString());
			for (int i = 0; i < visible; i++) {
				buttons[i].setBackground(filled);
				buttons[i].setForeground(Color.BLACK);
				buttons[i].setBounds(X + WIDTH + (WIDTH * visible) - i * WIDTH, Y, WIDTH, HEIGHT);
				buttons[i].setMargin(new Insets(0, 0, 0, 0));
			}
			buttons[visible - 1].setEnabled(false);
			this.add(buttons[visible - 1]);
			this.setSize(X + WIDTH * (visible + 3) + WIDTH, Y + HEIGHT * 2 + 10);
			this.repaint();
		}

		signal.setText("►");
		signal.setVisible(true);
		popped.setText(name.toString());
		popped.setVisible(true);

		buttons[buttonCtr].setText(name.toString());
		buttons[buttonCtr].setBackground(filled);

		Thread.sleep(SPEED);
		status.setText("Waiting for LinkedList");
		llgfx.addFirst(name);
		popped.setVisible(false);
		signal.setVisible(false);
		status.setText("");
		Thread.sleep(SPEED);
	}

	public Object pop() throws InterruptedException {
		if (buttonCtr <= -1) {
			popped.setVisible(false);
			signal.setVisible(false);
			queueGraphical.setStatus("");
			tempQG.setStatus("");
			return null;
		}

		String word = buttons[buttonCtr].getText();
		signal.setText("◄");
		signal.setVisible(true);
		popped.setText(word);
		popped.setVisible(true);

		buttons[buttonCtr].setText("");
		buttons[buttonCtr].setBackground(empty);
		buttonCtr--;

		status.setText("POPPING ITEM");
		Thread.sleep(SPEED);
		status.setText("Waiting for LinkedList");
		Object giveBack = llgfx.removeFirst().getValue();
		signal.setVisible(false);
		popped.setVisible(false);
		status.setText("");
		Thread.sleep(SPEED);

		return giveBack;
	}

	public boolean isEmpty() {
		return (llgfx.getFirst() == null);
	}
}
