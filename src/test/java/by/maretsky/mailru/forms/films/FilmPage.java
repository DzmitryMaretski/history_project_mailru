package by.maretsky.mailru.forms.films;

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


public class FilmPage extends BaseForm {

    public FilmPage() {
        super(By.xpath("//img[contains(@src,'kino')]"), "open page with films");
    }

    String getInfo = "//div[contains(@class,'cols__column')][%s]//div[contains(@class,'margin_top_5')]";
    public Label lblYear;

    private boolean yearIsRight = true;
    private boolean genreIsRight = true;
    private boolean countryIsRight = true;

    public void checkYear(String year, int value) {
        lblYear = new Label(By.xpath(String.format(getInfo, value)));
        String obtYear = lblYear.getText();
        String regYear = null;
        Pattern pattern = Pattern.compile("[\\d]+");
        Matcher matcher = pattern.matcher(obtYear);
        if (matcher.find())
            regYear = matcher.group();

        if (Integer.valueOf(regYear) < Integer.valueOf(year)) {
            yearIsRight = false;
        }
        logger.info(String.format("Release date: %s", regYear));
    }

    public void checkFilmInfo(String yearsFrom, String genres, String countries) {
        List<WebElement> listFilms;
        listFilms = BaseElement.findElements(By.xpath("//div[contains(@class,'cols__column')]" +
                "//div[contains(@class,'margin_top_5')]"));
        int varList = 1;
        String[] arrCountries = countries.split(" ");
        String[] arrGenres = genres.split(" ");
        logger.info("check films specs");
        for (int counter = 0; counter < listFilms.size(); counter++) {
            Label lblInfo = new Label(By.xpath(String.format(getInfo, varList)), String.format("Check film#%s",varList));
            String getText = lblInfo.getText();
            String[] arrValueFilm = getText.split(", ");
            int countCountries = 0;
            int countGenres = 0;

            for (int i = 0; i < arrCountries.length; i++) {
                for (int j = 0; j < arrValueFilm.length; j++){
                    if (arrValueFilm[j].equalsIgnoreCase(arrCountries[i])){
                        countCountries++;
                        logger.info(String.format("film from: %s", arrValueFilm[j]));
                        break;
                    }
                }
            }

            for (int i = 0; i < arrGenres.length; i++) {
                for (int j = 0; j < arrValueFilm.length; j++){
                    if (arrValueFilm[j].equalsIgnoreCase(arrGenres[i])) {
                        countGenres++;
                        logger.info(String.format("film genre: %s", arrValueFilm[j]));
                        break;
                    }
                }
            }
            if (countCountries == 0) {
                countryIsRight = false;
            }
            if (countGenres == 0) {
                genreIsRight = false;
            }
            checkYear(yearsFrom,varList);

            assertCountry();
            assertGenre();
            assertYear();

            varList++;
        }
    }

    private void assertCountry() {
        Assert.assertTrue(countryIsRight, "it's not right country");
    }
    private void assertGenre() {
        Assert.assertTrue(genreIsRight, "it's not right genre");
    }
    private void assertYear() {
        Assert.assertTrue(yearIsRight, "it's not right year");
    }
}
