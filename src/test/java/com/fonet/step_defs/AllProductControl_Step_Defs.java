package com.fonet.step_defs;
import com.fonet.utils.ConfigurationReader;
import com.fonet.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.fonet.utils.Driver.getDriver;

public class AllProductControl_Step_Defs {
    public static void main(String[] args) {

        getDriver().get(ConfigurationReader.getProperty("url"));

        List<String[]> allProducts = new ArrayList<>();

        while (true) {
            List<WebElement> productNames = getDriver().findElements(By.cssSelector(".card-title a"));
            List<WebElement> productPrices = getDriver().findElements(By.cssSelector(".card-block h5"));

            for (int i = 0; i < productNames.size(); i++) {
                String[] product = new String[2];
                product[0] = productNames.get(i).getText();
                product[1] = productPrices.get(i).getText();
                allProducts.add(product);
                System.out.println("Ürün bilgileri: " + Arrays.toString(allProducts.get(i)));
            }

            try {
                WebElement nextButton = getDriver().findElement(By.id("next2"));
                nextButton.click();

                Thread.sleep(2000);
            } catch (Exception e) {
                break;
            }
        }
        System.out.println("Toplam ürün adeti: " + allProducts.size());

        getDriver().quit();
    }
}

