package by.maretsky.mailru.forms.realEstate;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;


public class RealEstatePage extends BaseForm {

    private Button btnSubmit = new Button(By.xpath("//button[@type='submit']"), "get page with extended filter");

    public RealEstatePage() {
        super(By.xpath("//img[contains(@title,'Недвижимость')]"), "Real estate");
    }

    public void getExtendedPage() {
        btnSubmit.click();
    }
}
