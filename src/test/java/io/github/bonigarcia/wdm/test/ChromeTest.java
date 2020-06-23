/*
 * (C) Copyright 2016 Boni Garcia (http://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.github.bonigarcia.wdm.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * Test with Chrome.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
public class ChromeTest {

	private WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@Before
	public void setupTest() {
		driver = new ChromeDriver();
	}

	@After
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void converterTestSuccess() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.get("https://www.unitconverters.net/length/m-to-cm.htm");
		By searchInput = By.id("ucfrom");
		wait.until(presenceOfElementLocated(searchInput));
		driver.findElement(searchInput).sendKeys("100");
		By convertButton = By.className("ucdcsubmit");
		driver.findElement(convertButton).click();
		assertEquals("Result: 100 meter = 10000 centimeter", driver.findElement(By.id("ucresult")).getText());
	}

	@Test
	public void googleTranslate() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.get("https://translate.google.com/");
		By sourceInput = By.id("source");
		wait.until(presenceOfElementLocated(sourceInput));
		driver.findElement(sourceInput).sendKeys("Olá, professor!");
		By targetInput = By.xpath("//span[contains(text(),'Hello teacher!')]");
		assertEquals("Hello teacher!", driver.findElement(targetInput).getText());
	}

}
