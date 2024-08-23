package com.fonet.pages;

import com.fonet.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductDetailPage {

    public ProductDetailPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(css = ".product-deatil h2")
    public WebElement productTitle;

    @FindBy(css = ".product-deatil h3")
    public WebElement productPrice;
}
