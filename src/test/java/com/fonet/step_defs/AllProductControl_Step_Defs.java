package com.fonet.step_defs;

import com.fonet.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.fonet.utils.BrowserUtils.*;
import static com.fonet.utils.Driver.getDriver;

public class AllProductControl_Step_Defs {

    HomePage homePage = new HomePage();

    final static Logger logger = Logger.getLogger(FonetTask_Step_Defs.class);

    List<String[]> allProducts = new ArrayList<>();
    List<String[]> phones = new ArrayList<>();
    List<String[]> laptops = new ArrayList<>();
    List<String[]> monitors = new ArrayList<>();

    @When("Listelenen urunler loglanir")
    public void listelenen_urunler_loglanir() {
        waitTwoSeconds();
        while (true) {
            for (int i = 0; i < homePage.productNames.size(); i++) {
                String[] product = new String[2];
                product[0] = homePage.productNames.get(i).getText();
                product[1] = homePage.productPrices.get(i).getText();
                allProducts.add(product);
            }
            try {
                homePage.nextButton.click();
                waitTwoSeconds();
            } catch (Exception e) {
                logger.info("Son sayfa ulaşıldı, daha fazla ürün yok.");
                break;
            }
        }
    }

    @And("Phones kategorisi loglanir")
    public void phonesKategorisiLoglanir() {
        clickLinkByText("Phones");
        waitTwoSeconds();
        while (true) {
            for (int i = 0; i < homePage.productNames.size(); i++) {
                String[] product = new String[2];
                product[0] = homePage.productNames.get(i).getText();
                product[1] = homePage.productPrices.get(i).getText();
                phones.add(product);
            }
            try {
                homePage.nextButton.click();
                waitTwoSeconds();
            } catch (Exception e) {
                logger.info("Son sayfa ulaşıldı, daha fazla ürün yok.");
                break;
            }
        }
    }

    @And("Laptops kategorisi loglanir")
    public void laptopsKategorisiLoglanir() {
        clickLinkByText("Laptops");
        waitTwoSeconds();
        while (true) {
            for (int i = 0; i < homePage.productNames.size(); i++) {
                String[] product = new String[2];
                product[0] = homePage.productNames.get(i).getText();
                product[1] = homePage.productPrices.get(i).getText();
                laptops.add(product);
            }
            try {
                homePage.nextButton.click();
                waitTwoSeconds();
            } catch (Exception e) {
                logger.info("Son sayfa ulaşıldı, daha fazla ürün yok.");
                break;
            }
        }
    }

    @And("Monitors kategorisi loglanir")
    public void monitorsKategorisiLoglanir() {
        clickLinkByText("Monitors");
        waitTwoSeconds();
        while (true) {
            for (int i = 0; i < homePage.productNames.size(); i++) {
                String[] product = new String[2];
                product[0] = homePage.productNames.get(i).getText();
                product[1] = homePage.productPrices.get(i).getText();
                monitors.add(product);
            }
            try {
                homePage.nextButton.click();
                waitTwoSeconds();
            } catch (Exception e) {
                logger.info("Son sayfa ulaşıldı, daha fazla ürün yok.");
                break;
            }
        }
    }

    @Then("Toplam sayi kontrol edilir")
    public void toplam_sayi_kontrol_edilir() {

        logger.info("Toplam ürün adeti: " + allProducts.size());
        logger.info("Phones ürün adeti: " + phones.size());
        logger.info("Laptops ürün adeti: " + laptops.size());
        logger.info("Monitors ürün adeti: " + monitors.size());

        logger.info("All Products");
        for (String[] product : allProducts) {
            logger.info(String.format("Ürün Adı: %-20s | Fiyat: %s", product[0], product[1]));
        }
        logger.info("Phones");
        for (String[] product : phones) {
            logger.info(String.format("Ürün Adı: %-20s | Fiyat: %s", product[0], product[1]));
        }
        logger.info("Laptops");
        for (String[] product : laptops) {
            logger.info(String.format("Ürün Adı: %-20s | Fiyat: %s", product[0], product[1]));
        }
        logger.info("Monitors");
        for (String[] product : monitors) {
            logger.info(String.format("Ürün Adı: %-20s | Fiyat: %s", product[0], product[1]));
        }
    }
}

