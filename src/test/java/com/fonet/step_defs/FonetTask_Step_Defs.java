package com.fonet.step_defs;

import com.fonet.pages.HomePage;
import com.fonet.pages.ProductDetailPage;
import com.fonet.utils.BrowserUtils;
import com.fonet.utils.ConfigurationReader;
import com.fonet.utils.Driver;
import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;
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

    String productName, productPrice, testUserFullName;

    final static Logger logger = Logger.getLogger(FonetTask_Step_Defs.class);

    Faker faker = new Faker();

    @Given("Kullanici demoblaze.com sitesini acar")
    public void kullanici_demoblaze_com_sitesini_acar() {
        getDriver().get(ConfigurationReader.getProperty("url"));
        logger.info("demoblaze.com sitesi açıldı");
    }

    @When("Kullanici site basligini {string} kontrol eder")
    public void kullaniciSiteBasliginiKontrolEder(String siteTitle) {
        String actualTitle = getDriver().getTitle();
        assertEquals(siteTitle, actualTitle);
        logger.info("Sayfa doğrulandı");
    }


    @When("Kullanici herhangi bir urun kategorisine tiklar")
    public void kullanici_herhangi_bir_urun_kategorisine_tiklar() {

        waitTwoSeconds();

        //List<WebElement> categories = getDriver().findElements(By.id("itemc"));

        Random random = new Random();
        int randomIndex = random.nextInt(homePage.categories.size());

        homePage.categories.get(randomIndex).click();

        String selectedCategory = homePage.categories.get(randomIndex).getText();
        logger.info("Seçilen kategori: " + selectedCategory);

    }

    @When("Kullanici herhangi bir urun kartina tiklar")
    public void kullanici_herhangi_bir_urun_kartina_tiklar() throws InterruptedException {
        waitTwoSeconds();

        Random random = new Random();
        int randomProductIndex = random.nextInt(homePage.productNames.size());

        this.productName = homePage.productNames.get(randomProductIndex).getText();
        this.productPrice = homePage.productPrices.get(randomProductIndex).getText();
        logger.info("Seçilen ürün: " + productName);
        logger.info("Seçilen ürün fiyatı: " + productPrice);
        homePage.productNames.get(randomProductIndex).click();
    }

    @When("Kullanici acilan urun adini ve fiyat bilgisini kontrol eder")
    public void kullanici_acilan_urun_adini_ve_fiyat_bilgisini_kontrol_eder() {

        String selectedProductName = productDetailPage.productTitle.getText();
        String selectedProductPrice = productDetailPage.productPrice.getText();

        assertEquals(productName, selectedProductName);
        assertTrue(selectedProductPrice.startsWith(productPrice));

        logger.info("Ürün adı ve fiyatı doğrulandı");
    }

    @And("Kullanici urun eklendi bilgi pop-up onaylar")
    public void kullaniciUrunEklendiBilgiPopUpOnaylar() {
        waitTwoSeconds();
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
        logger.info("Ürün sepete eklendi");
    }

    @When("Kullanici Cart menusune tiklar")
    public void kullaniciCartMenusuneTiklar() {
        getDriver().findElement(By.id("cartur")).click();
        logger.info("Sepet görüntülendi");
    }

    @When("Kullanici urun adini ve fiyatini kontrol eder")
    public void kullanici_urun_adini_ve_fiyatini_kontrol_eder() {
        waitTwoSeconds();

        WebElement table = getDriver().findElement(By.cssSelector(".table-striped"));

        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 2) {

                String addedProductName = cells.get(1).getText();
                String addedProductPrice = cells.get(2).getText();

                assertEquals(productName, addedProductName);
                assertTrue(productPrice.endsWith(addedProductPrice));
            }
        }
        logger.info("Sepetteki ürün adı ve fiyatı doğrulandı");
    }

    @When("Kullanici bilgi pop-up ta adini ve fiyati kontrol eder")
    public void kullanici_bilgi_pop_up_ta_adini_ve_fiyati_kontrol_eder() {
        waitTwoSeconds();

        String infoText = getDriver().findElement(By.cssSelector(".text-muted")).getText();

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

        logger.info("Ödeme yapıldı ve bilgiler kontrol edildi");
    }

    @When("Kullanici {string} butonuna tiklar")
    public void kullanici_butonuna_tiklar(String buttonText) {
        getDriver().findElement(By.xpath("//button[text()='" + buttonText + "']")).click();
        logger.info(buttonText + " butonuna tıklandı");
    }


    @And("Kullanici Add to cart butonuna tiklar")
    public void kullaniciAddToCartButonunaTiklar() {
        getDriver().findElement(By.xpath("//a[text()=\"Add to cart\"]")).click();
        logger.info("Add to cart butonuna tıklandı");
    }

    @And("Kullanici urun toplam fiyatini kontrol eder")
    public void kullaniciUrunToplamFiyatiniKontrolEder() {

        String selectedProductTotalPrice = getDriver().findElement(By.id("totalp")).getText();

        assertTrue(productPrice.endsWith(selectedProductTotalPrice));

        logger.info("Sepet toplamı kontrol edildi");
    }

    @And("Kullanici Place Order formunda {string} alanini doldurur")
    public void kullaniciPlaceOrderFormundaAlaniniDoldurur(String labelText) {
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
        logger.info(labelText + " bilgisi forma eklendi");
    }

    @And("Kullanici Place Order formunda fiyati kontrol eder")
    public void kullaniciPlaceOrderFormundaFiyatiKontrolEder() {

        waitTwoSeconds();

        String selectedProductTotalPrice = getDriver().findElement(By.id("totalm")).getText().replace("Total: ", "");

        assertTrue(productPrice.endsWith(selectedProductTotalPrice));

        logger.info("Sipariş formundaki fiyat kontrol edildi");
    }

    @And("Kullanici bilgi pop-up ta {string} mesajini görür")
    public void kullaniciBilgiPopUpTaMesajiniGörür(String msgText) {
        String infoText = getDriver().findElement(By.xpath("//div[10]/h2")).getText();
        assertEquals(msgText, infoText);
        logger.info("Sipariş tamamlandı");
    }
}

