package converter.visual;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class StackGFX extends GFXTemplate {
	public QueueGFX queueGraphical, tempQG;

	public StackGFX(String name) {
		super(name);
		queueGraphical = new QueueGFX("Queue Implementation of Stack");
		tempQG = new QueueGFX("tempQueue");
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
		popped.setText(name.toString());
		signal.setVisible(true);
		popped.setVisible(true);

		buttons[buttonCtr].setText(name.toString());
		buttons[buttonCtr].setBackground(filled);

		Thread.sleep(SPEED);
		status.setText("Waiting for Queue");
		while (!queueGraphical.isEmpty()) {
			tempQG.enqueue(queueGraphical.dequeue("Transferring to tempQueue"), "items received from Queue");
		}
		queueGraphical.setStatus("");
		tempQG.setStatus("");

		queueGraphical.enqueue(name, "");
		queueGraphical.setStatus("");

		while (!tempQG.isEmpty()) {
			queueGraphical.enqueue(tempQG.dequeue("Returning items to Queue"), "rebuilding queue");
		}
		tempQG.setStatus("");
		queueGraphical.setStatus("");

		popped.setVisible(false);
		signal.setVisible(false);
		status.setText("");
		Thread.sleep(SPEED);
	}

	public String pop() throws InterruptedException {
		if (buttonCtr <= -1) {
			popped.setVisible(false);
			signal.setVisible(false);
			queueGraphical.setStatus("");
			tempQG.setStatus("");
			return null;
		}
		setStatus("POPPING ITEM");
		String word = buttons[buttonCtr].getText();
		signal.setText("◄");
		signal.setVisible(true);
		popped.setText(word);
		popped.setVisible(true);

		buttons[buttonCtr].setText("");
		buttons[buttonCtr].setBackground(empty);
		buttonCtr--;

		Thread.sleep(SPEED);
		status.setText("Waiting for Queue");
		queueGraphical.dequeue("");
		status.setText("");
		signal.setVisible(false);
		popped.setVisible(false);
		return word;
	}

	public boolean isEmpty() {
		if (buttonCtr == -1)
			return true;
		return false;
	}
}
