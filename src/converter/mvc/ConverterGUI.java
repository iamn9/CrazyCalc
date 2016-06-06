package converter.mvc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import converter.visual.GFXTemplate;
import converter.visual.TracePanel;

@SuppressWarnings("serial")
public class ConverterGUI extends JFrame {

	private JPanel contentPane;
	private JPanel visualPane;
	public JScrollPane visualPanel;
	private JTextField inputField;
	private JTextField postfixField;
	private JTextField valueField;
	private JButton convertBtn;
	public JTabbedPane tabbedPane;
	public JTextField textField;

	public ConverterGUI() {
		setResizable(false);
		setTitle("A Crazy Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(520, 730);
		setVisible(true);
		// setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblInfix = new JLabel("INFIX:");
		lblInfix.setBounds(10, 10, 52, 25);
		lblInfix.setForeground(UIManager.getColor("OptionPane.questionDialog.titlePane.foreground"));
		lblInfix.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfix.setFont(new Font("Segoe UI", Font.BOLD, 18));

		inputField = new JTextField();
		inputField.setBounds(108, 10, 275, 25);
		inputField.setBackground(Color.WHITE);
		inputField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		inputField.setColumns(10);

		JLabel lblPostfix = new JLabel("POSTFIX:");
		lblPostfix.setBounds(10, 40, 78, 25);
		lblPostfix.setForeground(UIManager.getColor("OptionPane.questionDialog.titlePane.foreground"));
		lblPostfix.setHorizontalAlignment(SwingConstants.LEFT);
		lblPostfix.setFont(new Font("Segoe UI", Font.BOLD, 18));

		postfixField = new JTextField();
		postfixField.setToolTipText("Postfix Equivalent");
		postfixField.setBounds(108, 40, 275, 25);
		postfixField.setEditable(false);
		postfixField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		postfixField.setColumns(10);

		JLabel lblValue = new JLabel("VALUE:");
		lblValue.setBounds(10, 70, 62, 25);
		lblValue.setForeground(UIManager.getColor("OptionPane.questionDialog.titlePane.foreground"));
		lblValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblValue.setFont(new Font("Segoe UI", Font.BOLD, 18));

		valueField = new JTextField();
		valueField.setToolTipText("Calculation Result");
		valueField.setBounds(108, 70, 275, 25);
		valueField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		valueField.setEditable(false);
		valueField.setColumns(10);

		convertBtn = new JButton("CALCULATE");
		convertBtn.setSize(112, 40);
		convertBtn.setBackground(new Color(255, 255, 102));
		convertBtn.setForeground(Color.BLUE);
		convertBtn.setFont(new Font("Tahoma", Font.BOLD, 17));
		convertBtn.setFocusable(false);
		convertBtn.setMargin(new Insets(0, 0, 0, 0));
		convertBtn.setLocation(valueField.getX() + valueField.getWidth() + 10, 12);
		contentPane.setLayout(null);

		contentPane.add(lblInfix);
		contentPane.add(inputField);
		contentPane.add(lblPostfix);
		contentPane.add(postfixField);
		contentPane.add(lblValue);
		contentPane.add(valueField);
		contentPane.add(convertBtn);

		JLabel lblSpeed = new JLabel("SPEED:");
		lblSpeed.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSpeed.setSize(55, 16);
		lblSpeed.setLocation(valueField.getX() + valueField.getWidth() + 10, 58);
		contentPane.add(lblSpeed);

		textField = new JTextField();
		textField.setText(Integer.toString(GFXTemplate.SPEED));
		textField.setToolTipText("Speed value for updating visualization");
		textField.setSize(62, 20);
		textField.setLocation(lblSpeed.getX() + 50, 58);
		contentPane.add(textField);
		textField.setColumns(10);

		visualPane = new JPanel();
		visualPane.setToolTipText("Visualization");
		visualPane.setLayout(null);
		visualPane.setBounds(10, 110, 670, this.getHeight());
		contentPane.add(visualPane);

		TracePanel output = new TracePanel();
		output.setToolTipText("Output Field");
		output.setBounds(10, 425, 672, 160);
		JFrame f1 = new JFrame("Output");
		f1.setSize(output.getWidth(), output.getHeight() * 2);
		f1.setResizable(false);
		f1.setAlwaysOnTop(true);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(HIDE_ON_CLOSE);
		f1.add(output);
		this.setLocation(150, 0);
		f1.setLocation(this.getX() + this.getWidth(), 0);

		JButton b1 = new JButton("TRACING");
		b1.setBackground(Color.CYAN);
		b1.setSize(112, 16);
		b1.setLocation(valueField.getX() + valueField.getWidth() + 10, 80);
		b1.setFocusable(false);
		b1.setMargin(new Insets(0, 0, 0, 0));
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f1.setVisible(!f1.isVisible());
			}
		});
		contentPane.add(b1);
	}

	public JTextField getInputField() {
		return inputField;
	}

	public void setPostfixField(String postfix) {
		postfixField.setText(postfix);
	}

	public void setValueField(String value) {
		valueField.setText(value);
	}

	public void addVisualization(JPanel panel, int x, int y) {
		panel.setBounds(x, y, panel.getWidth(), panel.getHeight());
		visualPane.add(panel);
	}

	public JButton getConvertBtn() {
		return convertBtn;
	}

	public void clear() {
		postfixField.setText("");
		valueField.setText("");
	}
}
