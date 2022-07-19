package calculatortests.utils;


public class CalculatorUtils {
	
	public int getResultExpectedInt(String operand1, String operand2, String operator) throws Exception {
		int op1 = Integer.parseInt(operand1);
		int op2 = Integer.parseInt(operand2);
		
		switch(operator) {
		case "+":
				return op1+op2;
		case "-":
				return op1-op2;
		case "*":
				return op1*op2;
		case "/":
				return op1/op2;
		case "%":
				return op1%op2;
		default:
				throw new Exception();
		}
	}
	
	public float getResultExpectedFloat(String operand1, String operand2, String operator) throws Exception {
		float op1 = Float.parseFloat(operand1);
		float op2 = Float.parseFloat(operand2);
		
		switch(operator) {
		case "+":
				return op1+op2;
		case "-":
				return op1-op2;
		case "*":
				return op1*op2;
		case "/":
				return op1/op2;
		case "%":
				return op1%op2;
		default:
				throw new Exception();
		}
	}
	
	public String getResultExpected(String number) {
		float n = Float.parseFloat(number);
		if(n<0) {
			return "-Infinity";
		}else if(n>0) {
			return "Infinity";
		}else {
			return "NaN";
		}
	}

}
