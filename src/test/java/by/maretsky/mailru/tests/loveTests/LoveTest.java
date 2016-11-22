package by.maretsky.mailru.tests.loveTests;

import by.maretsky.mailru.forms.MainPage;
import by.maretsky.mailru.forms.love.LoveManPage;
import by.maretsky.mailru.forms.love.LovePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;


public class LoveTest extends BaseTest{

    private String sex;
    private String lookFor;
    private String ageFrom;
    private String ageTo;

    @Parameters({"MySex", "lookFor", "ageFrom", "ageTo"})
    @BeforeClass
    public void getParameters(String sex, String lookFor, String ageFrom, String ageTo) {
        this.sex = sex;
        this.lookFor = lookFor;
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
    }

    public void runTest() {

        logStep(1);
        MainPage mainPage = new MainPage();
        mainPage.openLovePage();

        logStep(2);
        LovePage lovePage = new LovePage();
        lovePage.setParams(sex, lookFor, ageFrom, ageTo);

        logStep(3);
        LoveManPage loveManPage = new LoveManPage();
        loveManPage.checkParams(ageFrom, ageTo);
    }
}
