package by.maretsky.mailru.forms.realEstate;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.TextBox;


public class RealEstateCatalogPage extends BaseForm {

    private Button btnGetRegionList = new Button(By.xpath("//a[contains(@class,'js-geo-field')]//i"), "get region list");
    private Button btnSetRegion;
    private String pathToSetRegion = "//a[text() = '%s']";
    private Button btnSetTown;
    private String pathToSetTown = "//input[contains(@data-text,'Санкт-Петербург')]/following-sibling::span//span[contains(text(),'Санкт-Петербург')]";
    private Button btnSubmit = new Button (By.xpath("//div[contains(text(),'Готово')]"), "submit town");
    private Button btnSubmitFilter = new Button(By.xpath("//div[contains(@class,'block_bg')]//button"));
    //setFilter
    private Button btnSubway = new Button(By.xpath("//span[contains(text(),'Станция метро')]"));
    private Button btnSetSubwayStation;
    private String pathToSetSubwayStation = "//input[contains(@data-text,'%s')]//..";
    //filter type of real estate
    private Button btnApartment;
    String pathToSetApartment = "//div[contains(@class,'inner')]" +
            "//span[contains(text(),'%s')]/../preceding-sibling::label";


    //set
    public RealEstateCatalogPage() {
        super(By.xpath("//img[contains(@title,'Недвижимость')]"), "Купить однокмнатную");
    }

    public void setRegion(String region, String town) {
        btnGetRegionList.click();
        btnSetRegion = new Button(By.xpath(String.format(pathToSetRegion, region)), "set russia");
        btnSetRegion.clickAndWait(By.xpath("//div[contains(@class,'item-cont')]//input[contains(@data-text,'Санкт-Петербург')]/.."));
        btnSetTown = new Button(By.xpath(String.format(pathToSetTown, town)), "saint-petserburg");
        btnSetTown.clickAndWait(By.xpath(".//*[@id='geo-popup-container']/div/div/div/div[3]/div/div[1]/div[3]/div[1]/div/label/span/span[contains(text(),'Бокситогорский')]"));
        btnSetTown.clickAndWait(By.xpath(".//*[@id='geo-popup-container']/div/div/div/div[3]/div/div[1]" +
                "/div[2]/div/div[1]/div/label/span/span[contains(text(),'Зеленогорск')]"));
        btnSubmit.clickAndWait(By.xpath("html/body/div[6]/div[5]/div/div/div[1]/div/div[2]" +
                "/div/form/div[1]/div/div[2]/a/span[contains(text(),'Санкт-Петербург')]"));
    }

    public void setFilter(String subway, String apartment) {
        btnSubway.click();
        btnSetSubwayStation = new Button(By.xpath(String.format(pathToSetSubwayStation, subway)), "submit subway station");
        btnSetSubwayStation.click();
        btnSubmit.click();
        btnApartment = new Button(By.xpath(String.format(pathToSetApartment, apartment)), "apartment choice");
        btnApartment.click();
        btnSubmitFilter.clickAndWait(By.xpath("//div[contains(@data-module,'Title')]//h1[contains(text(),'Черная речка')]"));
    }

}
