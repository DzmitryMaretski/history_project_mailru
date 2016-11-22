package by.maretsky.mailru.forms.hiTech;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

public class HiTechPage extends BaseForm {

    private Button btnOpenCatalog = new Button(By.xpath("//a[contains(@href,'catalog')]"), "open catalog page");
    private Button btnCloseNotification = new Button(By.xpath("//button[contains(@class,'js-push-notifications')]"),"close news notification");
    public HiTechPage() {
        super(By.xpath("//img[contains(@title,'Hi-Tech')]"), "Hi-tech page");
    }

    public void openCatalog() {
        btnCloseNotification.click();
        btnOpenCatalog.click();
    }


}
