package by.maretsky.mailru.forms.love;

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


public class LoveManPage extends BaseForm {

    private int counter;
    private Pattern patternAge = Pattern.compile("[\\d]+");
    public Label lblAge = new Label(By.id("self-age"), "get person age");
    private String getPages = "//div[contains(@class,'tiles-list')]//ul//li//a";
    private String goToUserPage = "//div[contains(@class,'tiles-list')]//ul//li[%s]//a";
    private String regAge;
    private boolean ageIsRight = true;
    private Button btnBack = new Button(By.xpath("//div[@id='Anketa']//a[contains(text(),'поиск')]"), "page is not available");
    public LoveManPage() {
        super(By.xpath("//img[contains(@title,'love')]"), "love mail ru");
    }





    private void checkAge(String ageFrom, String ageTo) {
        String forRegular = lblAge.getText();
        Matcher matchAge = patternAge.matcher(forRegular);
        if (matchAge.find())
            regAge = matchAge.group();
        logger.info(String.format("%s years old", regAge));
        if (Integer.valueOf(regAge) < Integer.valueOf(ageFrom) ||
                Integer.valueOf(regAge) > Integer.valueOf(ageTo)) {
            ageIsRight = false;
        }
    }

    public void checkParams(String ageFrom, String ageTo) {
        int varList = 1;
        List<WebElement> listSmartphones;
        listSmartphones = BaseElement.findElements(By.xpath(getPages));
        for (counter = 0; counter < listSmartphones.size(); counter++) {
            Button openPersonalPage = new Button(By.xpath(String.format(goToUserPage, varList)),String.format("check person #%s", varList));
            openPersonalPage.click();

            if (btnBack.isPresent()) {
                browser.getDriver().navigate().back();
                break;
            }
            checkAge(ageFrom, ageTo);
            assertAge();
            browser.getDriver().navigate().back();
            varList++;
        }
    }

    private void assertAge() {
        Assert.assertTrue(ageIsRight, "age is not right");
    }

}
