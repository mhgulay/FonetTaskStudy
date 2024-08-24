package com.fonet.step_defs;

import com.fonet.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.fonet.utils.BrowserUtils.*;

public class AllProductControl_Step_Defs {

    HomePage homePage = new HomePage();

    final static Logger logger = Logger.getLogger(FonetTask_Step_Defs.class);

    List<String[]> allProducts = new ArrayList<>();
    List<String[]> phones = new ArrayList<>();
    List<String[]> laptops = new ArrayList<>();
    List<String[]> monitors = new ArrayList<>();

    @And("All listed products are fetched")
    public void allListedProductsAreFetched() {
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
                logger.info("All products fetched");
                break;
            }
        }
    }

    @And("Products in the Phones category are fetched")
    public void productsInThePhonesCategoryAreFetched() {
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
                logger.info("Phones category fetched.");
                break;
            }
        }
    }

    @And("Products in the Laptops category are fetched")
    public void productsInTheLaptopsCategoryAreFetched() {
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
                logger.info("Laptops category fetched.");
                break;
            }
        }
    }

    @And("Products in the Monitors category are fetched")
    public void productsInTheMonitorsCategoryAreFetched() {
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
                logger.info("Monitors category fetched");
                break;
            }
        }
    }

    @Then("Product details and quantities are logged")
    public void productDetailsAndQuantitiesAreLogged() {
        logger.info("Total product quantity: " + allProducts.size());
        logger.info("Quantity of Phones: " + phones.size());
        logger.info("Quantity of Laptops: " + laptops.size());
        logger.info("Quantity of Monitors: " + monitors.size());

        logger.info("All Products");
        for (String[] product : allProducts) {
            logger.info(String.format("Product Name: %-20s | Price: %s", product[0], product[1]));
        }
        logger.info("Phones");
        for (String[] product : phones) {
            logger.info(String.format("Product Name: %-20s | Price: %s", product[0], product[1]));
        }
        logger.info("Laptops");
        for (String[] product : laptops) {
            logger.info(String.format("Product Name: %-20s | Price: %s", product[0], product[1]));
        }
        logger.info("Monitors");
        for (String[] product : monitors) {
            logger.info(String.format("Product Name: %-20s | Price: %s", product[0], product[1]));
        }

    }
}

