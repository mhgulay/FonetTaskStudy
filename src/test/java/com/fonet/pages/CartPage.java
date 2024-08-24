package com.fonet.pages;

import com.fonet.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    public CartPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = ".table-striped")
    public WebElement cartTable;

    @FindBy(css = ".text-muted")
    public WebElement infoText;

    @FindBy(id = "totalp")
    public WebElement totalPrice;

    @FindBy(id = "totalm")
    public WebElement totalPricePlaceOrder;

    @FindBy(xpath = "//div[10]/h2")
    public WebElement msgText;

}
