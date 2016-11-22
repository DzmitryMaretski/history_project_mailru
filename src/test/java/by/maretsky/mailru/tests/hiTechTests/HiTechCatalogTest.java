package by.maretsky.mailru.tests.hiTechTests;

import by.maretsky.mailru.forms.hiTech.HiTechCatalogPage;
import by.maretsky.mailru.forms.hiTech.HiTechPage;
import by.maretsky.mailru.forms.hiTech.HiTechProductPage;
import by.maretsky.mailru.forms.hiTech.HiTechSmartphonesPage;
import by.maretsky.mailru.forms.MainPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;


public class HiTechCatalogTest extends BaseTest {

    private String brand;
    private String priceFrom;
    private String priceTo;

    @Parameters({"brand", "priceFrom", "priceTo"})
    @BeforeClass
    public void getParameters(String brand, String priceFrom, String priceTo) {
        this.brand = brand;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
    }

    public void runTest() {
        logStep(1);
        MainPage mainPage = new MainPage();
        mainPage.openHiTechPage();

        logStep(2);
        HiTechPage hiTechPage = new HiTechPage();
        hiTechPage.openCatalog();

        logStep(3);
        HiTechCatalogPage hiTechCatalogPage = new HiTechCatalogPage();
        hiTechCatalogPage.OpenSmartphonesPage();

        logStep(4);
        HiTechSmartphonesPage hiTechSmartphonesPage = new HiTechSmartphonesPage();
        hiTechSmartphonesPage.setSmartphoneFilter(brand, priceFrom, priceTo);

        logStep(5);
        HiTechProductPage hiTechProductPage = new HiTechProductPage();
        hiTechProductPage.checkSmartphoneSpecs(brand, priceFrom, priceTo);

    }
}
//button[contains(@class,'js-push-notifications')]