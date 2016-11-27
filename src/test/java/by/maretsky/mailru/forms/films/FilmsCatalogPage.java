package by.maretsky.mailru.forms.films;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import webdriver.BaseForm;
import webdriver.elements.*;

import java.util.List;
import java.util.StringTokenizer;


public class FilmsCatalogPage extends BaseForm{

    public FilmsCatalogPage() {
        super(By.xpath("//img[contains(@src,'kino')]"), "open page with films");
    }

    private Button btnOpenListYears = new Button(By.xpath("//div[contains(@class,'js-range')]" +
            "/following-sibling::i"),"open");

    private WebElement slider = browser.getDriver().findElement
            (By.xpath("//div[contains(@class,'pc-form-line')]//div[contains(@class,'slider_from')]"));

    private Label lblYearsStartFrom = new Label(By.xpath("//div[contains(@class,'line__steps')]" +
            "//div[contains(@class,'line__step')]"),"get text");
    private Slider sliderFrom = new Slider(By.xpath("//div[contains(@class,'pc-form-line')]" +
            "//div[contains(@class,'slider_from')]"), "move \"from\" year slider");


    private Button btnListGanre = new Button(By.xpath("//div[contains(text(),'Все жанры')]/.."),"genre");

    String findGenresElements = "//div[contains(@class,'js-genre__control')]" +
            "//span[contains(@class,'text')]";
    String findCountriesElements = "//div[contains(@class,'js-genre__control')]" +
            "//span[contains(@class,'text')]";
    String pathCheck = "//div[contains(@class,'js-genre__control')][%s]" +
            "//span[contains(@class,'text')]";

    String pathToCheck = "//div[contains(@class,'js-genre__control')][%s]" +
            "//span[contains(@class,'text')]/../preceding-sibling::label";

    private Button btnListCountries = new Button(By.xpath("//div[contains(text(),'Все страны')]/.."),"countries");
    private Button btnSearch = new Button(By.xpath("//div[contains(@class,'button')]//a"), "search by filter");



    private void setYearsRangeFrom(String yearFrom) {
        btnOpenListYears.click();
        String startValue = lblYearsStartFrom.getText();
        int distinctionYears = Integer.valueOf(yearFrom) - Integer.valueOf(startValue);
        int odd;
        int even;
        int movSliderTo;
        if (Integer.valueOf(distinctionYears)%2 != 0) {
            even = (Integer.valueOf(distinctionYears)/2) * 3;
            odd = (Integer.valueOf(distinctionYears)/2 + 1) * 4;
            movSliderTo = even+odd+12;
        } else {
            even = (Integer.valueOf(distinctionYears)/2) * 3;
            odd = (Integer.valueOf(distinctionYears)/2) * 4;
            movSliderTo = even+odd+12;
        }


        int sliderValue = Integer.parseInt(slider.getCssValue("left").replaceAll("px", "")) + slider.getSize().width / 2;
        Actions act = new Actions(browser.getDriver());
        //act.dragAndDropBy(slider, -sliderValue, 0).build().perform();
        //act.dragAndDropBy(slider, movSliderTo, 0).build().perform();
        sliderFrom.makeActionOnSlider(-sliderValue, 0);
        sliderFrom.makeActionOnSlider(movSliderTo, 0);
    }


    private void setFilmGenre(String genreQuery) {
        btnListGanre.click();
        String[] listGenreQuery = genreQuery.split("\\s+");
        List<WebElement> listGenres;
        listGenres = BaseElement.findElements(By.xpath(findGenresElements));
        int counter = 1;
        for (int i = 0; i < listGenreQuery.length; i++) {
            for (int j = 0; j < listGenres.size(); j++) {
                Label lblGenre = new Label(By.xpath(String.format(pathCheck,counter)));
                String countryText = lblGenre.getText();
                if (countryText.equalsIgnoreCase(listGenreQuery[i])) {
                    Button btnCheckGenre = new Button(By.xpath(String.format(pathToCheck, j+1)));
                    btnCheckGenre.click();
                    break;
                }
                counter++;
            }
            counter = 1;
        }
    }

    private void setCountries(String countries) {

        btnListCountries.click();
        String[] listCountryQuery = countries.split("\\s+");

        List<WebElement> listGenres;
        listGenres = BaseElement.findElements(By.xpath(findCountriesElements));
        int counter = 1;
        for (int i = 0; i < listCountryQuery.length; i++) {
            for (int j = 0; j < listGenres.size(); j++) {
                Label lblCountry = new Label(By.xpath(String.format(pathCheck, counter)));
                String countryText = lblCountry.getText();
                if (countryText.equalsIgnoreCase(listCountryQuery[i])) {
                    Button btnCheck = new Button(By.xpath(String.format(pathToCheck, j+1)));
                    btnCheck.click();
                    break;
                }
                counter++;
            }
            counter = 1;
        }
    }

    public void setParameters(String yearFrom, String genres, String countries) {
        setFilmGenre(genres);
        setCountries(countries);
        setYearsRangeFrom(yearFrom);
        btnSearch.click();
    }

}
