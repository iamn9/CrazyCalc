package converter.mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConverterController {
	public static void main(String[] args) {

		ConverterGUI frame = new ConverterGUI();
		ConverterModel model = new ConverterModel();

		frame.addVisualization(model.stackGFX, 5, 5);
		frame.addVisualization(model.stackGFX.queueGraphical, 5, 100);
		frame.addVisualization(model.stackGFX.tempQG, 5, 195);
		frame.addVisualization(model.stackGFX.queueGraphical.lowerStack, 5, 290);
		frame.addVisualization(model.stackGFX.queueGraphical.tempStack, 5, 385);
		frame.addVisualization(model.stackGFX.queueGraphical.lowerStack.llgfx, 5, 480);
		frame.repaint();

		Runnable conversion = new Runnable() {
			public void run() {
				frame.getConvertBtn().setEnabled(false);
				frame.getInputField().setEnabled(false);
				frame.clear();
				model.setUpdateSpeed(Integer.parseInt(frame.textField.getText()));
				try {
					model.convertToPostfix(frame.getInputField().getText());
					frame.setPostfixField(model.getPostfixNotation());
					frame.setValueField(Double.toString(model.computeValue()));
				} catch (InterruptedException e) {
				}
				frame.getInputField().setEnabled(true);
				frame.getConvertBtn().setEnabled(true);
			}
		};

		ActionListener ac1 = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(conversion).start();
			}
		};

		frame.getConvertBtn().addActionListener(ac1);
		frame.getInputField().addActionListener(ac1);
		frame.textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.setUpdateSpeed(Integer.parseInt(frame.textField.getText()));
			}
		});
	}
}
