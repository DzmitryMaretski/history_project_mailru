package by.maretsky.mailru.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;


public class MainPage extends BaseForm {

    private Button btnAllProjects = new Button(By.xpath("//span[@class = 'x-ph__menu']//a"), "Open list of projects");
    private Button btnHiTech = new Button (By.xpath("//span[contains(@id, 'Menu_dropdown')]" +
            "//a[contains(text(),'Hi-Tech')]"), "open Hi-Tech page");
    private Button btnRealEstate = new Button(By.xpath("//span[contains(@id, 'Menu_dropdown')]" +
            "//a[contains(text(),'Недвижимость')]"), "Open real estate page");
    private Button btnLove = new Button (By.xpath("//a[contains(@class,'x-ph')]" +
            "//span[contains(text(),'Знакомства')]/.."), "open love page");
    private Button btnFilms = new Button (By.xpath("//span[contains(@id, 'Menu_dropdown')]" +
            "//a[contains(text(),'Кино')]"), "Open films page");
    private Button btnCars = new Button (By.xpath("//span[contains(@id, 'Menu_dropdown')]" +
            "//a[contains(text(),'Cars')]"), "Open cars page");

    public MainPage() {
        super(By.xpath("//a[@id = 'logo']"), "mail ru");
    }

    public void openHiTechPage() {
        browser.maximize();
        btnAllProjects.click();
        btnHiTech.click();
    }

    public void openRealEstatePage() {
        browser.maximize();
        btnAllProjects.click();
        btnRealEstate.click();
    }

    public void openLovePage() {
        browser.maximize();
        btnLove.click();
    }

    public void openFilmsPage() {
        browser.maximize();
        btnAllProjects.click();
        btnFilms.click();
    }

    public void openCarsPage() {
        browser.maximize();
        btnAllProjects.click();
        btnCars.click();
    }

}
