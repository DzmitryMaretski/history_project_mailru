package by.maretsky.mailru.tests.filmsTests;

import by.maretsky.mailru.forms.MainPage;
import by.maretsky.mailru.forms.films.FilmPage;
import by.maretsky.mailru.forms.films.FilmsCatalogPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;


public class FilmsTest extends BaseTest {

    private String yearsFrom;
    private String countries;
    private String genres;

    @Parameters({"yearFrom", "countries", "genres"})
    @BeforeClass
    public void getParameters(String yearsFrom, String countries, String genres) {
        this.yearsFrom = yearsFrom;
        this.countries = countries;
        this.genres = genres;
    }

    public void runTest() {
        logStep(1);
        MainPage mainPage = new MainPage();
        mainPage.openFilmsPage();

        logStep(2);
        FilmsCatalogPage filmsPage = new FilmsCatalogPage();
        filmsPage.setParameters(yearsFrom, genres, countries);

        logStep(3);
        FilmPage filmPage = new FilmPage();
        filmPage.checkFilmInfo(yearsFrom, genres, countries);
    }
}
