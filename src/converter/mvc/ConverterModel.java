package converter.mvc;

import java.util.StringTokenizer;

import converter.dataStructs.Stack;
import converter.visual.GFXTemplate;
import converter.visual.StackGFX;

public class ConverterModel {

	private String postfix;
	private boolean verbose = true;
	public boolean convertDone = false;
	public Stack opStack;
	private String token, parsed = "";
	public StackGFX stackGFX = new StackGFX("Stack");

	private boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	private boolean isOperator(String str) {
		if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("^")) {
			return true;
		}
		return false;
	}

	private int checkPrecedence(String op1) {
		int check = 0;
		switch (op1) {
		case "+":
		case "-":
			check = 1;
			break;
		case "*":
		case "/":
			check = 2;
			break;
		case "^":
			check = 3;
			break;
		default:
			break;
		}

		return check;
	}

	public String convertToPostfix(String infix) throws InterruptedException {
		if (infix.equals("")) {
			return "";
		}
		convertDone = false;
		System.out.printf("\n%20s %60s %60s %n", "Token", "Parsed", "Postfix");
		System.out.println("---------------------------------------------------"
				+ "------------------------------------------------------------"
				+ "-------------------------------------------------");
		postfix = "";
		parsed = "";
		opStack = new Stack();

		// StringTokenizer(array,regex,delimiterAsToken)
		StringTokenizer parser = new StringTokenizer(infix, "+-*/^() ", true);

		while (parser.hasMoreTokens()) {
			token = parser.nextToken();
			if (token.length() == 0) {
				continue;
			} else if (isNumeric(token)) {
				updatePostfix(token);
			} else if (token.equals("(")) {
				opStack.push(token);
				stackGFX.push(token);

			} else if (token.equals(")")) {
				while (!opStack.isEmpty()) {
					String opToken = (String) opStack.pop();
					stackGFX.pop();

					if (!opToken.equals("(")) {
						updatePostfix(opToken);
					} else {
						break;
					}
				}
			} else if (isOperator(token)) {
				if (opStack.isEmpty()) {
					opStack.push(token);
					stackGFX.push(token);

				} else {
					while (!opStack.isEmpty()) {
						String lastOp = (String) opStack.pop();
						stackGFX.pop();

						if (lastOp.equals("(")) {
							opStack.push(lastOp);
							stackGFX.push(lastOp);

						} else if (isOperator(lastOp)) {
							if (checkPrecedence(lastOp) < checkPrecedence(token)) {
								opStack.push(lastOp);
								stackGFX.push(lastOp);

							} else if (checkPrecedence(lastOp) >= checkPrecedence(token))
								updatePostfix(lastOp);
						}
						if (checkPrecedence(lastOp) < checkPrecedence(token) || lastOp.equals("("))
							break;
					}
					opStack.push(token);
					stackGFX.push(token);

				}
			}

			traceMessage();
		}

		while (!opStack.isEmpty()) {
			updatePostfix(opStack.pop());
			stackGFX.pop();
		}
		stackGFX.queueGraphical.setStatus("");
		convertDone = true;
		token = "";
		traceMessage();
		return postfix;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	private void updatePostfix(Object concat) {
		postfix = postfix.concat(concat + " ");
	}

	private void traceMessage() {
		if (verbose == true) {
			parsed = parsed + token;
			System.out.printf("%20s %60s %60s %n", token, parsed, postfix);
		}
	}

	public String getPostfixNotation() {
		return postfix;
	}

	public double computeValue() {
		Stack stk = new Stack();
		String check;
		if (postfix == null || postfix.equals("")) {
			return 0;
		}
		StringTokenizer st = new StringTokenizer(postfix, " ", true);

		while (st.hasMoreTokens()) {
			check = st.nextToken();
			if (isNumeric(check))
				stk.push(check);
			if (isOperator(check)) {
				double tmp1 = Double.valueOf(stk.pop().toString());
				double tmp2 = Double.valueOf(stk.pop().toString());

				double result = evaluate(tmp2, tmp1, check);
				stk.push(result);
			}
		}
		return Double.valueOf(stk.pop().toString());
	}

	private double evaluate(double a, double b, String op) {
		double res = 0;
		switch (op) {
		case "+":
			res = (a + b);
			break;
		case "-":
			res = (a - b);
			break;
		case "*":
			res = (a * b);
			break;
		case "/":
			res = (a / b);
			break;
		case "^":
			res = Math.pow(a, b);
			break;
		}
		return res;
	}

	public void setUpdateSpeed(int value) {
		GFXTemplate.SPEED = value;
	}
}
