package calculatortests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import calculatortests.overlays.CalculatorOverlay;
import calculatortests.utils.CalculatorUtils;

public class CalculatorOperationTestHelper {

	protected WebDriver driver;
	protected CalculatorOverlay calculatorOverlay;
	protected CalculatorUtils calculatorUtils;
	protected String textNaN = "NaN";
	protected String operator;

	public CalculatorOperationTestHelper(String operator) {
		this.operator = operator;
	}

	@BeforeSuite
	public void suiteSetup() {
		System.setProperty("webdriver.chrome.driver",
				"src/lib/chromedriver.exe");
	}

	@BeforeClass
	public void classSetup() {
		driver = new ChromeDriver();
		calculatorOverlay = new CalculatorOverlay(driver);
		calculatorUtils = calculatorOverlay.getCalculatorUtils();
		calculatorOverlay.selectOperator(operator);
	}

	@AfterClass
	public void classTeardown() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}

	@DataProvider(name = "IntegerPairs")
	public Object[][] getIntegerPairs() {
		return new Object[][] { { "0", "5" }, { "10", "1" }, { "5", "3" }, { "-5", "74" }, { "22", "-45" },
				{ "85", "85" }, { "-10", "-3" } };
	}

	@DataProvider(name = "FloatPairs")
	public Object[][] getFloatPairs() {
		return new Object[][] { { "1.8", "2" }, { "10.758", "28" }, { "0", "5.7896" }, { "-5", "74.7412" },
				{ "22.13", "-45.77" }, { "5.2", "1.2" } };
	}

	@DataProvider(name = "StringPairs")
	public Object[][] getStringPairs() {
		return new Object[][] { { "abc", "2" }, { "10ag", "28" }, { "0a", "5.7896" }, { "-5", "74x.7412" },
				{ "22s.13", "-45*.77" } };
	}

	@DataProvider(name = "VariousInputs")
	public Object[][] getVariousInputs() {
		return new Object[][] { { "123" }, { "abc" }, { "22.5" }, { "45f" }, { "-5220" }, { "-hdn5" }, { "c56" },
				{ "2+6" }, { "a8" } };
	}

	@Test(dataProvider = "IntegerPairs")
	public void testIntegersOperation(String op1, String op2) throws Exception {
		calculatorOverlay.handleCalculation(op1, op2);
		int resultExpected = calculatorUtils.getResultExpectedInt(op1, op2, operator);
		int resultActual = calculatorOverlay.getResultActualInt();
		Assert.assertEquals(resultActual, resultExpected);
	}

	@Test(dataProvider = "FloatPairs")
	public void testFloatsOperation(String op1, String op2) throws Exception {
		calculatorOverlay.handleCalculation(op1, op2);
		float resultExpected = calculatorUtils.getResultExpectedFloat(op1, op2, operator);
		float resultActual = calculatorOverlay.getResultActualFloat();
		Assert.assertEquals(resultActual, resultExpected, 0.000001);
	}

	@Test(dataProvider = "StringPairs")
	public void testStringsOperation(String op1, String op2) {
		calculatorOverlay.handleCalculation(op1, op2);
		String resultExpected = textNaN;
		String resultActual = calculatorOverlay.getResultActual();
		Assert.assertEquals(resultActual, resultExpected);
	}

	@Test(dataProvider = "VariousInputs")
	public void testFirstOperandEmpty(String op2) {
		calculatorOverlay.handleCalculation("", op2);
		String resultExpected = textNaN;
		String resultActual = calculatorOverlay.getResultActual();
		Assert.assertEquals(resultActual, resultExpected);
	}

	@Test(dataProvider = "VariousInputs")
	public void testSecondOperandEmpty(String op1) {
		calculatorOverlay.handleCalculation(op1, "");
		String resultExpected = textNaN;
		String resultActual = calculatorOverlay.getResultActual();
		Assert.assertEquals(resultActual, resultExpected);
	}

	public void testBothOperandsEmpty() {
		calculatorOverlay.handleCalculation("", "");
		String resultExpected = textNaN;
		String resultActual = calculatorOverlay.getResultActual();
		Assert.assertEquals(resultActual, resultExpected);
	}

}
