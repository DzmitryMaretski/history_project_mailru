package by.maretsky.mailru.tests.carsTests;

import by.maretsky.mailru.forms.MainPage;
import by.maretsky.mailru.forms.cars.CarsAdvancedSearchPage;
import by.maretsky.mailru.forms.cars.CarsPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;

public class CarsTest extends BaseTest {

    private String region;
    private String town;
    private String brand;
    private String model;
    private String yearsFrom;


    @Parameters({"region", "town", "brand", "model", "yearFrom"})
    @BeforeClass
    public void getParameters(String region, String town, String brand, String model, String yearsFrom) {
        this.region = region;
        this.town = town;
        this.brand = brand;
        this.model = model;
        this.yearsFrom = yearsFrom;
    }

    public void runTest() {
        logStep(1);
        MainPage mainPage = new MainPage();
        mainPage.openCarsPage();

        logStep(2);
        CarsPage carsPage = new CarsPage();
        carsPage.openAdvancedSearchPage();

        logStep(3);
        CarsAdvancedSearchPage carsSearchPage = new CarsAdvancedSearchPage();
        carsSearchPage.setGeoParameters(region, town);

        logStep(4);
        carsSearchPage.setCarParameters(brand, model, yearsFrom);

        logStep(5);
        carsSearchPage.checkCars(brand, model, yearsFrom);
    }
}
