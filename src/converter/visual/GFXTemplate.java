package converter.visual;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class GFXTemplate extends JPanel {

	public static int SPEED = 1500;
	int X = 25, Y = 25, WIDTH = 30, HEIGHT = 30;

	Color filled = new Color(100200100);
	Color empty = Color.LIGHT_GRAY;

	int buttonCtr = -1, visible = 10;
	JButton buttons[] = new JButton[40];
	JButton popped, signal;
	JLabel status;

	public GFXTemplate(String name) {
		this.setLayout(null);
		this.setBorder(new TitledBorder(name));
		this.setSize(X + WIDTH * (visible + 4) + WIDTH, Y + HEIGHT * 2 + 10);

		for (int i = 0; i < visible; i++) {
			buttons[i] = new JButton("");
			buttons[i].setEnabled(false);
			buttons[i].setMargin(new Insets(0, 0, 0, 0));
			buttons[i].setBackground(empty);
			buttons[i].setBounds(X + WIDTH + (WIDTH * visible) - i * WIDTH, Y, WIDTH, HEIGHT);
			this.add(buttons[i]);
		}

		popped = new JButton("");
		popped.setEnabled(false);
		popped.setMargin(new Insets(0, 0, 0, 0));
		popped.setForeground(Color.black);
		popped.setBounds(buttons[visible - 1].getX() - WIDTH * 2, Y, WIDTH, HEIGHT);
		popped.setVisible(false);
		this.add(popped);

		signal = new JButton("►");
		signal.setMargin(new Insets(0, 0, 0, 0));
		signal.setEnabled(false);
		signal.setBackground(Color.black);
		signal.setBorderPainted(false);
		signal.setBounds(buttons[visible - 1].getX() - WIDTH, Y, WIDTH, HEIGHT);
		signal.setVisible(false);
		this.add(signal);

		status = new JLabel("");
		status.setBounds(X, HEIGHT * 2 + 10, this.getWidth() - 10, 20);
		status.setVisible(true);
		this.add(status);
	}

	public void setStatus(String name) {
		status.setText(name);
	}
}
