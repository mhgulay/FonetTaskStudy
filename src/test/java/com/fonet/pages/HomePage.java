package com.fonet.pages;

import com.fonet.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.fonet.utils.Driver.getDriver;

public class HomePage {

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "itemc")
    public List<WebElement> categories;

    @FindBy(css = ".card-title a")
    public List<WebElement> productNames;

    @FindBy(css = ".card-block h5")
    public List<WebElement> productPrices;

    @FindBy(id = "cartur")
    public WebElement cartMenu;

    @FindBy(id = "next2")
    public WebElement nextButton;

}
