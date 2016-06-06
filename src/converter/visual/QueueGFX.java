package converter.visual;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class QueueGFX extends GFXTemplate {

	public LowerStackGFX lowerStack;
	public LowerStackGFX tempStack;

	public QueueGFX(String name) {
		super(name);
		lowerStack = new LowerStackGFX("lowerStack implementation of Queue");
		tempStack = new LowerStackGFX("tempStack");
	}

	public void enqueue(Object name, String info) throws InterruptedException {
		status.setText("ENQUEUEING: " + info);
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

		signal.setBounds(buttons[visible - 1].getX() - WIDTH, Y, WIDTH, HEIGHT);
		popped.setBounds(signal.getX() - WIDTH, Y, WIDTH, HEIGHT);
		popped.setText(name.toString());
		signal.setVisible(true);
		popped.setVisible(true);
		buttons[buttonCtr].setText(name.toString());
		buttons[buttonCtr].setBackground(filled);

		Thread.sleep(SPEED);
		status.setText("Waiting for LowerStack");
		lowerStack.push(name);
		status.setText("");
		popped.setVisible(false);
		signal.setVisible(false);
		Thread.sleep(SPEED);
	}

	public Object dequeue(String info) throws InterruptedException {
		if (buttonCtr <= -1) {
			popped.setVisible(false);
			signal.setVisible(false);
			return null;
		}

		status.setText("DEQUEUEING: " + info);
		String word = buttons[0].getText();
		popped.setText(word);
		signal.setBounds(buttons[0].getX() + WIDTH, Y, WIDTH, HEIGHT);
		popped.setBounds(signal.getX() + WIDTH, Y, WIDTH, HEIGHT);
		signal.setVisible(true);
		popped.setVisible(true);

		int i;
		for (i = 0; i < buttonCtr; i++) {
			buttons[i].setText(buttons[i + 1].getText());
		}
		for (; i < visible; i++) {
			buttons[i].setText("");
			buttons[i].setBackground(empty);
		}
		buttonCtr--;

		this.repaint();
		Thread.sleep(SPEED);
		status.setText("Waiting for LowerStack");
		while (!lowerStack.isEmpty()) {
			tempStack.push(lowerStack.pop());
		}
		Object returnable = tempStack.pop();
		while (!tempStack.isEmpty()) {
			lowerStack.push(tempStack.pop());
		}
		status.setText("");
		signal.setVisible(false);
		popped.setVisible(false);
		Thread.sleep(SPEED);

		return returnable;
	}

	public boolean isEmpty() {
		return lowerStack.isEmpty();
	}
}
