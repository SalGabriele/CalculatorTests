package calculatortests.overlays;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import calculatortests.utils.CalculatorUtils;

public class CalculatorOverlay {

	private WebDriver driver;
	private CalculatorUtils calculatorUtils;
	private static Pattern pattern;
	private static long timeout = 10;

	public CalculatorOverlay(WebDriver driver) {
		calculatorUtils = new CalculatorUtils();
		this.driver = driver;
		driver.get("https://greg73.github.io/angular-calculator/");
		pattern = Pattern.compile("^[^.]");
	}

	public void handleCalculation(String op1, String op2) {
		enterOperandValue(true, op1);
		enterOperandValue(false, op2);
		submitResult();
		waitForResult(timeout, pattern);
	}

	public CalculatorUtils getCalculatorUtils() {
		return calculatorUtils;
	}

	public void enterOperandValue(Boolean firstOperand, String value) {
		String id;
		if (firstOperand) {
			id = "first";
		} else {
			id = "second";
		}
		driver.findElement(By.id(id)).sendKeys(value);
	}

	public void selectOperator(String operator) {
		driver.findElement(By.id("operator")).click();
		List<WebElement> options = driver.findElements(By.xpath("//option"));
		for (WebElement option : options) {
			if ((option.getText()).equals(operator)) {
				option.click();
				break;
			}
		}
	}

	public void submitResult() {
		driver.findElement(By.id("gobutton")).click();
	}

	public void waitForResult(long timeout, Pattern pattern) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout))
				.until(ExpectedConditions.textMatches(By.id("latest"), pattern));
	}

	public void waitForResult(long timeout, String textToWait) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout))
				.until(ExpectedConditions.textToBe(By.id("latest"), textToWait));
	}

	public int getResultActualInt() {
		return Integer.parseInt(driver.findElement(By.id("latest")).getText());
	}

	public float getResultActualFloat() {
		return Float.parseFloat(driver.findElement(By.id("latest")).getText());
	}

	public String getResultActual() {
		return driver.findElement(By.id("latest")).getText();
	}
}
