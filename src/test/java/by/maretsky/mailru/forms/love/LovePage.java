package by.maretsky.mailru.forms.love;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.ComboBox;
import webdriver.elements.TextBox;


public class LovePage extends BaseForm{

    private ComboBox btnOpenListParamWho = new ComboBox(By.xpath("//div[contains(@class,'param-who')]"),
            "open list who");
    private ComboBox cmbbSetGender;
    private ComboBox cmbbSetLookFor;
    private ComboBox cmbbOpenListParamLookFor = new ComboBox(By.xpath("//div[contains(@class,'look-for-button')]"),
            "open list look for");

    private Button click = new Button(By.xpath(".//*[@id='web-search-search_form__0']/table/tbody/tr/td[1]/div/div[3]/div[2]"));
    private TextBox txtbSetAgeFrom = new TextBox(By.xpath(".//*[@id='web-search-search_form__0']/table/tbody/tr/td[1]/div/div[3]/div[2]/input[1]"));

    private TextBox txtbSetAgeTo = new TextBox(By.xpath("//div[contains(@class,'form-param-age')]" +
            "//input[contains(@data-bind,'ageTo')]"),"set age to");
    private Button btnSubmit = new Button(By.xpath("//td[contains(@class,'form-submit')]//span"), "submit filter");

    public LovePage() {
        super(By.xpath("//img[contains(@title,'love')]"), "love mail ru");
    }

    private void setSex(String sex) {
        btnOpenListParamWho.click();
        cmbbSetGender = new ComboBox(By.xpath(String.format("//div[contains(@class,'param-who')]" +
                "//div[contains(@name,'who')]//span[contains(text(),'%s')]", sex)),"set gender");
        cmbbSetGender.click();
    }

    private void setLookForSex(String lookFor) {
        cmbbOpenListParamLookFor.click();
        cmbbSetLookFor = new ComboBox(By.xpath(String.format("//div[contains(@class,'look-for-button')]" +
                "/following-sibling::div//span[contains(text(),'%s')]", lookFor)),"set look for");
        cmbbSetLookFor.click();
    }

    private void setAgeRange(String ageFrom, String ageTo) {
        click.click();
        txtbSetAgeFrom.setText(ageFrom);
        txtbSetAgeTo.setText(ageTo);
    }

    public void setParams(String setSex, String lookFor, String setAgeFrom, String setAgeTo) {
        setSex(setSex);
        setLookForSex(lookFor);
        setAgeRange(setAgeFrom,setAgeTo);
        btnSubmit.click();
    }
}
