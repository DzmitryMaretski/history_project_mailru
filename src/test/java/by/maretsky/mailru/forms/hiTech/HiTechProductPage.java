package by.maretsky.mailru.forms.hiTech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import webdriver.BaseForm;
import webdriver.elements.BaseElement;
import webdriver.elements.Button;
import webdriver.elements.Label;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HiTechProductPage extends BaseForm{
    private int counter;
    private String openSmartphoneProductPage = "//div[@data-index = '%s']//a[contains(@class,'title')]";
    private Label lblBrand = new Label(By.xpath("//div[contains(@class,'cols__inner')]//h1[contains(@class,'hdr__inner')]"), "check brand name");
    private Label lblPrice = new Label(By.xpath("//div[contains(@class,'wrapper')]" +
            "//span[contains(@class,'price__number')]"), "check price");

    Pattern patternBrand = Pattern.compile("^[\\w]+");
    Pattern patternPrice = Pattern.compile("[\\w\\s]+");

    private String regBrand;
    private String regPrice;
    private int previousPrice = 0;
    private int price;

    private boolean brandIsRight = true;
    private boolean priceIsRight = true;
    private boolean previousPriceIsRight = true;

    public HiTechProductPage() {
        super(By.xpath("//img[contains(@title,'Hi-Tech')]"), "Hi-tech page");
    }

    private void checkBrand(String brand) {
        String obtBrand = lblBrand.getText();
        Matcher matchBrand = patternBrand.matcher(obtBrand);
        if (matchBrand.find())
            regBrand = matchBrand.group();

        if (!regBrand.equalsIgnoreCase(brand)) {
            logger.error(String.format("Brand %s is not right", regBrand));
            brandIsRight = false;
        }
    }

    private void checkPrice(String priceFrom, String priceTo) {

        String obtPrice = lblPrice.getText();
        Matcher matchPrice = patternPrice.matcher(obtPrice);
        if (matchPrice.find())
            regPrice = matchPrice.group();

        regPrice = regPrice.replaceAll("\\s+","");
        price = Integer.valueOf(regPrice);
        if (Integer.valueOf(regPrice) < Integer.valueOf(priceFrom) || Integer.valueOf(regPrice) > Integer.valueOf(priceTo)) {
            logger.error("error price");
            priceIsRight = false;
        }

        if(previousPrice > price) {
            previousPriceIsRight = false;
        }
    }

    public void checkSmartphoneSpecs(String brand, String priceFrom, String priceTo) {
        List<WebElement> listSmartphones;
        listSmartphones = BaseElement.findElements(By.xpath("//div[contains(@class,'card_block')]//a[contains(@class,'title')]"));
        logger.info("SMARTPHONES SPECS ON PAGE");
        for (counter = 0; counter < listSmartphones.size(); counter++) {
            Button openMobile = new Button(By.xpath(String.format(openSmartphoneProductPage, counter)),"check smartphone");
            openMobile.click();
            checkBrand(brand);
            checkPrice(priceFrom, priceTo);
            assertSortByPrice();
            assertBrand(brand);
            assertPrice(price);
            previousPrice = price;
            browser.getDriver().navigate().back();
        }
    }
    public void assertBrand(String brand) {
        Assert.assertTrue(brandIsRight, String.format(brand + ": %s", "it's not right brand"));
    }

    public void assertPrice(int price) {
        Assert.assertTrue(priceIsRight, String.format(price + ": %s", "Price is not right"));
    }
    public void assertSortByPrice() {
        Assert.assertTrue(previousPriceIsRight, "Previous price is not right");
    }

}
