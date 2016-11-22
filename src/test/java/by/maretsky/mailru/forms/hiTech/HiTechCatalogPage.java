package by.maretsky.mailru.forms.hiTech;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;


public class HiTechCatalogPage extends BaseForm {


    private Button btnOpenSmartphonesPage = new Button(By.xpath("//a[contains(text(),'Смартфоны')]"),
            "open smartphones");

    public HiTechCatalogPage() {
        super(By.xpath("//img[contains(@title,'Hi-Tech')]"), "Hi-tech page");
    }

    public void OpenSmartphonesPage() {
        btnOpenSmartphonesPage.click();
    }

}
