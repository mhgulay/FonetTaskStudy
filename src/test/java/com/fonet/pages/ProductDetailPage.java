package com.fonet.pages;

import com.fonet.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage {

    public ProductDetailPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = ".product-deatil h2")
    public WebElement productTitle;

    @FindBy(css = ".product-deatil h3")
    public WebElement productPrice;

    @FindBy(xpath = "//a[text()=\"Add to cart\"]")
    public WebElement addToCart;
}
