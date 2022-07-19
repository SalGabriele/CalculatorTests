package calculatortests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DivisionTest extends CalculatorOperationTestHelper{
	public DivisionTest() {
		super("/");
	}
	
	
	@DataProvider(name = "Numbers")
	public Object[][] getNumbers() {
		return new Object[][] { { "1"}, { "10"}, { "3"}, {"74" }, {"-45" }, {"0"} };
	}
	
	@Override
	@Test(dataProvider="IntegerPairs")
	public void testIntegersOperation(String op1, String op2) throws Exception {
		calculatorOverlay.handleCalculation(op1, op2);
		float resultExpected = calculatorUtils.getResultExpectedFloat(op1, op2, operator);
		float resultActual = calculatorOverlay.getResultActualFloat();
		Assert.assertEquals(resultActual, resultExpected, 0.01);
	}
	
	@Test(dataProvider="Numbers")
	public void testDivisionByZero(String op1) throws Exception {
		calculatorOverlay.handleCalculation(op1, "0");
		String resultExpected = calculatorUtils.getResultExpected(op1);
		String resultActual = calculatorOverlay.getResultActual();
		Assert.assertEquals(resultActual, resultExpected);
	}

}
