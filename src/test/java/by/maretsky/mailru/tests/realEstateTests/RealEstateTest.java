package by.maretsky.mailru.tests.realEstateTests;

import by.maretsky.mailru.forms.MainPage;
import by.maretsky.mailru.forms.realEstate.RealEstateApartmentPage;
import by.maretsky.mailru.forms.realEstate.RealEstateCatalogPage;
import by.maretsky.mailru.forms.realEstate.RealEstatePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;


public class RealEstateTest extends BaseTest {

    private String region;
    private String subway;
    private String town;
    private String apartment;
    @Parameters({"region", "town", "subway", "apartment"})
    @BeforeClass
    public void getParameters(String region, String town, String subway, String apartment) {
        this.region = region;
        this.town = town;
        this.subway = subway;
        this.apartment = apartment;
    }

    public void runTest() {
        logStep(1);
        MainPage mainPage = new MainPage();
        mainPage.openRealEstatePage();

        logStep(2);
        RealEstatePage realEstatePage = new RealEstatePage();
        realEstatePage.getExtendedPage();

        logStep(3);
        RealEstateCatalogPage realEstateCatalogPage = new RealEstateCatalogPage();
        realEstateCatalogPage.setRegion(region, town);
        realEstateCatalogPage.setFilter(subway, apartment);
        RealEstateApartmentPage apPage = new RealEstateApartmentPage();
        apPage.checkSpecs(town, subway, apartment);
    }
}
