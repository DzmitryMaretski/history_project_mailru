package by.maretsky.mailru.forms.realEstate;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import webdriver.BaseForm;
import webdriver.elements.BaseElement;
import webdriver.elements.Button;
import webdriver.elements.Label;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RealEstateApartmentPage extends BaseForm {


    public RealEstateApartmentPage() {
        super(By.xpath("//img[contains(@title,'Недвижимость')]"), "Купить однокмнатную");
    }

    private Label getTown = new Label(By.xpath("//div[contains(@class,'wrapper_relative')]//span[contains(@class,'inner')]"));
    private Label getSubway = new Label(By.xpath("//div[contains(@class,'cols__inner')]" +
            "//i[contains(@class,'subway')]/following-sibling::span"));
    private Label getApartmentType = new Label(By.xpath("//div[contains(@class,'inner')]" +
            "//span[contains(@class,'text')]//h1"));
    private boolean townIsRight = true;
    private boolean subwayIsRight = true;
    private boolean apartmentIsRight = true;

    private void checkTown(String setTown) {
        String obtTown = getTown.getText();
        String town = null;
        Pattern pattern = Pattern.compile("([\\w])+-([\\w])+", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(obtTown);

        if (matcher.find())
            town = matcher.group();
        if (!town.equalsIgnoreCase(setTown)) {
            townIsRight = false;
        }
        logger.info(String.format("Town: %s", town));
    }

    private void checkSubway(String setSubway) {
        String obtSubway = getSubway.getText();
        Pattern pattern = Pattern.compile(String.format("[%s]+",setSubway), Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(obtSubway);
        String subway = null;
        if(matcher.find())
            subway = matcher.group();

        if(subway.equalsIgnoreCase(setSubway)) {
            subwayIsRight = false;
        }
        logger.info(String.format("Subway: %s", subway));
    }

    private void checkApartmentType(String type) {
        String obtType = getApartmentType.getText();
        if (!obtType.contains(type))
            apartmentIsRight = false;
        logger.info(String.format("apartment type: %s", type));
    }

    String pathToPersonalPage = "//div[contains(@class,'search-results')]" +
            "//div[contains(@class,'js-track_click')][%s]//a[contains(@class,'link')]";
    public void checkSpecs(String town, String subway, String type) {
        int counter;
        int varList = 1;
        int variableForStep = 1;
        List<WebElement> listApartments;
        listApartments = BaseElement.findElements(By.xpath("//div[contains(@class,'search-results')]" +
                "//a[contains(@class,'title')]"));
        for (counter = 0; counter < listApartments.size(); counter++) {
            Button openPersonalPage = new Button(By.xpath(
                    String.format(pathToPersonalPage, variableForStep)),String.format("check apartment #%s", varList));
            String parentHandle = browser.getDriver().getWindowHandle();

            openPersonalPage.click();
            for(String childHandle : browser.getDriver().getWindowHandles()){
                if (!childHandle.equals(parentHandle)){
                    browser.getDriver().switchTo().window(childHandle);
                    checkTown(town);
                    checkSubway(subway);
                    checkApartmentType(type);
                    browser.getDriver().close();
                }
            }

            browser.getDriver().switchTo().window(parentHandle);
            varList++;
            if (variableForStep == 5) {
                variableForStep += 3;
            } else {
                variableForStep += 2;
            }
        }

    }
}
