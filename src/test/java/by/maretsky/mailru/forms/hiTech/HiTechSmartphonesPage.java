package by.maretsky.mailru.forms.hiTech;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.CheckBox;
import webdriver.elements.TextBox;


public class HiTechSmartphonesPage extends BaseForm{

    private TextBox txtbPriceFrom = new TextBox(By.xpath("//input[@placeholder = 'от']"), "enter price from");
    private TextBox txtbPriceTo = new TextBox(By.xpath("//input[@placeholder = 'до']"), "enter price to");

    private CheckBox chkbBrand;
    private String btnBrandPath = "//div[contains(@class,'checkbox__inner')]" +
            "//span[contains(text(),'%s')]/../preceding-sibling::label";
    private Button btnSumbitFilter = new Button(By.xpath("//button[@type = 'submit']"), "search by filter");
    private Button btnSortByPrice = new Button(By.xpath("//div[contains(@class,'tbl')]//span[contains(text(),'по цене')]/.."),"sort by price from lower to higher");

    public HiTechSmartphonesPage() {
        super(By.xpath("//img[contains(@title,'Hi-Tech')]"), "Hi-tech page");
    }

    private void setBrand(String brand) {
        chkbBrand = new CheckBox(By.xpath(String.format(btnBrandPath, brand)), "set brand");
        chkbBrand.click();
    }

    private void setPriceFrom(String priceFrom) {
        txtbPriceFrom.setText(priceFrom);
    }

    private void setPriceTo(String priceTo) {
        txtbPriceTo.setText(priceTo);
    }

    private void submitFilter() {
        btnSumbitFilter.click();
    }
    private void setSortByPrice() {
        btnSortByPrice.click();
    }

    public void setSmartphoneFilter(String brand, String priceFrom, String priceTo) {
        setPriceFrom(priceFrom);
        setPriceTo(priceTo);
        setBrand(brand);
        setSortByPrice();
        submitFilter();

    }
}
