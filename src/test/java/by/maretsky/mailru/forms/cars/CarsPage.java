package by.maretsky.mailru.forms.cars;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.TextBox;


public class CarsPage extends BaseForm{

    private Button btnAdvancedSearch = new Button(By.xpath("//div[contains(@class,'home-sale')]" +
            "//span[contains(@class,'button')]/.."), "Open advanced search page");

    public CarsPage() {
        super(By.xpath("//img[contains(@title,'Cars')]"), "cars page");
    }

    public void openAdvancedSearchPage() {
        btnAdvancedSearch.click();
    }



}
