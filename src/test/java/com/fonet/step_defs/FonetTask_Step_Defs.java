package com.fonet.step_defs;

import com.fonet.pages.CartPage;
import com.fonet.pages.HomePage;
import com.fonet.pages.ProductDetailPage;
import com.fonet.utils.ConfigurationReader;
import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static com.fonet.utils.BrowserUtils.*;
import static com.fonet.utils.Driver.getDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FonetTask_Step_Defs {

    HomePage homePage = new HomePage();
    ProductDetailPage productDetailPage = new ProductDetailPage();
    CartPage cartPage = new CartPage();

    String productName, productPrice, testUserFullName;

    final static Logger logger = Logger.getLogger(FonetTask_Step_Defs.class);

    Faker faker = new Faker();


    @Given("The user opens the demoblaze.com site")
    public void theUserOpensTheDemoblazeComSite() {
        getDriver().get(ConfigurationReader.getProperty("url"));
        logger.info("demoblaze.com site opened");
    }

    @When("The user checks the site title is {string}")
    public void theUserChecksTheSiteTitleIs(String siteTitle) {
        String actualTitle = getDriver().getTitle();
        assertEquals(siteTitle, actualTitle);
        logger.info("Page title verified");
    }

    @And("User clicks on any product category")
    public void userClicksOnAnyProductCategory() {
        waitTwoSeconds();

        Random random = new Random();
        int randomIndex = random.nextInt(homePage.categories.size());
        homePage.categories.get(randomIndex).click();

        String selectedCategory = homePage.categories.get(randomIndex).getText();
        logger.info("Selected category: " + selectedCategory);
    }

    @And("User clicks on any product card")
    public void userClicksOnAnyProductCard() {
        waitTwoSeconds();

        Random random = new Random();
        int randomProductIndex = random.nextInt(homePage.productNames.size());

        this.productName = homePage.productNames.get(randomProductIndex).getText();
        this.productPrice = homePage.productPrices.get(randomProductIndex).getText();
        logger.info("Selected product: " + productName);
        logger.info("Selected product price: " + productPrice);
        homePage.productNames.get(randomProductIndex).click();
    }

    @And("User verifies the product name and price in the product detail page")
    public void userVerifiesTheProductNameAndPriceInTheProductDetailPage() {
        String selectedProductName = productDetailPage.productTitle.getText();
        String selectedProductPrice = productDetailPage.productPrice.getText();

        assertEquals(productName, selectedProductName);
        assertTrue(selectedProductPrice.startsWith(productPrice));

        logger.info("Product name and price verified in the product detail page");
    }

    @And("User clicks the Add to cart button")
    public void userClicksTheAddToCartButton() {
        productDetailPage.addToCart.click();
        logger.info("Clicked the Add to cart button");
    }

    @And("User clicks the {string} button")
    public void userClicksTheButton(String buttonText) {
        clickButtonByText(buttonText);
        logger.info("Clicked the " + buttonText + " button");
    }

    @And("User confirms the product added pop-up message")
    public void userConfirmsTheProductAddedPopUpMessage() {
        waitTwoSeconds();
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
        logger.info("Product added to cart");
    }

    @And("User clicks on the Cart menu")
    public void userClicksOnTheCartMenu() {
        homePage.cartMenu.click();
        logger.info("Cart menu viewed");
    }

    @And("User verifies the product name and price on the cart menu")
    public void userVerifiesTheProductNameAndPriceOnTheCartMenu() {
        waitTwoSeconds();

        List<WebElement> rows = cartPage.cartTable.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 2) {

                String addedProductName = cells.get(1).getText();
                String addedProductPrice = cells.get(2).getText();
                assertEquals(productName, addedProductName);
                assertTrue(productPrice.endsWith(addedProductPrice));
            }
        }
        logger.info("Product name and price verified on the cart menu");
    }

    @And("User verifies the total product price on the cart menu")
    public void userVerifiesTheTotalProductPriceOnTheCartMenu() {
        String selectedProductTotalPrice = cartPage.totalPrice.getText();
        assertTrue(productPrice.endsWith(selectedProductTotalPrice));
        logger.info("Cart menu total price verified");
    }

    @And("User verifies the price in the Place Order form")
    public void userVerifiesThePriceInThePlaceOrderForm() {
        waitTwoSeconds();
        String selectedProductTotalPrice = cartPage.totalPricePlaceOrder.getText().replace("Total: ", "");
        assertTrue(productPrice.endsWith(selectedProductTotalPrice));
        logger.info("Price in the Place Order form verified");
    }

    @And("User fills in the {string} field in the Place Order form")
    public void userFillsInTheFieldInThePlaceOrderForm(String labelText) {
        WebElement inputBox = getDriver().findElement(By.id(labelText));
        waitTwoSeconds();
        switch (labelText) {
            case "name":
                this.testUserFullName = faker.name().fullName();
                inputBox.sendKeys(testUserFullName);
                break;
            case "country":
                inputBox.sendKeys(faker.address().country());
                break;
            case "city":
                inputBox.sendKeys(faker.address().city());
                break;
            case "card":
                inputBox.sendKeys(faker.finance().creditCard(CreditCardType.VISA));
                break;
            case "month":
                inputBox.sendKeys("11");
                break;
            case "year":
                inputBox.sendKeys("2032");
                break;
        }
        logger.info(labelText + " information added to the purchase form");
    }

    @And("User verifies the name and the price in the confirmation pop-up")
    public void userVerifiesTheNameAndThePriceInTheConfirmationPopUp() {
        waitTwoSeconds();

        String infoText = cartPage.infoText.getText();

        String prefix = "Name: ";
        int startIndex = infoText.indexOf(prefix) + prefix.length();
        int endIndex = infoText.indexOf("\n", startIndex);

        String Name = infoText.substring(startIndex, endIndex).trim();
        assertEquals(testUserFullName, Name);

        prefix = "Amount: ";
        startIndex = infoText.indexOf(prefix) + prefix.length();
        endIndex = infoText.indexOf("\n", startIndex);

        String Amount = infoText.substring(startIndex, endIndex).trim();
        assertTrue(Amount.startsWith(productPrice.replace("$", "")));

        logger.info("Payment made and details verified");
    }

    @And("User sees the {string} message in the confirmation pop-up")
    public void userSeesTheMessageInTheConfirmationPopUp(String msgText) {
        String infoText = cartPage.msgText.getText();
        assertEquals(msgText, infoText);
        logger.info("Order completed");
    }

}
