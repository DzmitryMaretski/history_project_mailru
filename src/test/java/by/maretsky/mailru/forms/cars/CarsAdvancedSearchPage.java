package by.maretsky.mailru.forms.cars;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import webdriver.BaseForm;
import webdriver.elements.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CarsAdvancedSearchPage extends BaseForm {

    //cars parameters
    private Button btnBrandList = new Button(By.xpath("//div[contains(text(),'Все марки')]/.."));

    String pathToBrand = "//div[contains(@class,'input__data')]" +
            "//div[contains(text(),'%s')]";
    private Button cmbbSetBrand;

    private Button btnModelList = new Button(By.xpath("//div[contains(text(),'Все модели')]//.."));
    String pathToModel = "//div[contains(@data-params,'Модель')]//div[contains(text(),'%s')]";
    private ComboBox cmbbSetModel;

    private Button btnYearsFromList = new Button(By.xpath("//div[contains(@data-params,'Год')]" +
            "//div[contains(text(),'Год от')]"));
    String pathToYear = "//div[contains(@data-params,'Год')]//div[contains(text(),'%s')]";
    private ComboBox cmbbYearsFromSet;

    private Button btnSubmitFilter = new Button(By.xpath("//div[contains(@data-params,'Расширенный')]/following-sibling::a"));

    private Button btnOpenGeoList = new Button(By.xpath("//div[contains(@class,'geo')]//span"));

    String pathToCountry = "//label[contains(@class,'tab__item')]//span[contains(text(),'%s')]/..";
    private Button btnSetCountry;
    private TextBox txtbTown = new TextBox(By.xpath("//div[contains(@class,'input__data')]" +
            "//input[contains(@placeholder,'название города')]"));
    String pathToTown = "//div[contains(@class,'input__data js-field')]" +
            "//div[contains(text(),'%s')]";
    private Button btnSetTown;

    private Button btnSubmit = new Button(By.xpath("//div[contains(@class,'js-geo_popup')]" +
            "//button[contains(@class,'submit')]"));

    //check specs
    private String carsList = "//div[contains(@class,'offer-card')]//a[contains(@class,'offer-card__link')]";
    private String cars = "//div[contains(@class,'offer-card')][%s]//a[contains(@class,'offer-card__link')]";
    private Label lblCarInfo = new Label(By.xpath("//div[contains(@class,'car__info')]//div[contains(@class,'title')]//h1"));

    private boolean brandIsRight = true;
    private boolean modelIsRight = true;
    private boolean releaseDateIsRight = true;

    public CarsAdvancedSearchPage() {
        super(By.xpath("//img[contains(@title,'Cars')]"), "cars page");
    }


    //car settings
    private void setCarBrand(String brand) {
        btnBrandList.click();
        cmbbSetBrand = new Button(By.xpath(String.format(pathToBrand, brand)), "set car brand");
        cmbbSetBrand.clickAndWait(By.xpath(String.format("//div[contains(text(),'%s')]", brand)));
    }

    private void setCarModel(String model) {
        if(btnModelList.isEnabled()) {
            btnModelList.click();
        }
        cmbbSetModel = new ComboBox(By.xpath(String.format(pathToModel, model)), "set car model");
        cmbbSetModel.click();
    }

    private void setYearFrom(String yearFrom) {
        btnYearsFromList.click();
        cmbbYearsFromSet = new ComboBox(By.xpath(String.format(pathToYear,yearFrom)),"set release date");
        cmbbYearsFromSet.click();
    }

    //geo settings
    public void openGeoList() {
        btnOpenGeoList.click();
    }

    public void setCountry(String region) {
        btnSetCountry = new Button(By.xpath(String.format(pathToCountry,region)), "set region");
        btnSetCountry.click();
    }

    public void setTown(String town) {
        btnSetTown = new Button(By.xpath(String.format(pathToTown, town)), "set town");
        btnSetTown.click();
    }

    public void setGeoParameters(String region, String town) {
        openGeoList();
        setCountry(region);
        txtbTown.setText(town);
        setTown(town);
        btnSubmit.click();
    }

    public void setCarParameters(String brand, String model, String yearFrom) {
        setCarBrand(brand);
        setCarModel(model);
        setYearFrom(yearFrom);
        btnSubmitFilter.click();
    }

    private void checkBrand(String[] info, String brand) {
        int counter = 0;

        for (int i = 0; i < info.length; i++) {
            if (info[i].equalsIgnoreCase(brand)) {
                counter++;
                logger.info(String.format("Brand is: %s", info[i]));
                break;
            }
        }
        if (counter == 0) {
            brandIsRight = false;
        }
    }

    private void checkModel(String[] info, String model) {
        int counter = 0;
        for (int i = 0; i < info.length; i++) {
            if (info[i].equalsIgnoreCase(model)) {
                counter++;
                logger.info(String.format("Model is: %s", info[i]));
                break;
            }
        }
        if (counter == 0) {
            brandIsRight = false;
        }
    }

    private void checkReleaseDate(String yearsFrom) {
        Pattern pattern = Pattern.compile("([2])+([\\d]+)");
        Matcher matchDate = pattern.matcher(lblCarInfo.getText());
        String regDate = null;
        if (matchDate.find()) {
            regDate = matchDate.group();
        }
        logger.info(String.format("Release date is: %s", regDate));
        if (Integer.valueOf(regDate) < Integer.valueOf(yearsFrom)) {
            releaseDateIsRight = false;
        }
    }

    public void checkCars(String brand, String model, String yearsFrom) {
        List<WebElement> listCars;
        listCars = BaseElement.findElements(By.xpath(carsList));
        int varList = 1;
        for (int count = 0; count < listCars.size(); count++) {
            Button btnOpenCar = new Button(By.xpath(String.format(cars,varList)),
                    String.format("check car #%s", varList));
            btnOpenCar.click();
            String[] info = lblCarInfo.getText().split(" ");
            checkBrand(info, brand);
            checkModel(info, model);
            checkReleaseDate(yearsFrom);
            assertBrand();
            assertModel();
            assertReleaseDate();
            varList++;
            browser.getDriver().navigate().back();
        }
    }

    private void assertBrand() {
        Assert.assertTrue(brandIsRight, "Brand is not right");
    }

    private void assertModel() {
        Assert.assertTrue(modelIsRight, "Model is not right");
    }

    private void assertReleaseDate() {
        Assert.assertTrue(releaseDateIsRight, "Date is not right");
    }

}