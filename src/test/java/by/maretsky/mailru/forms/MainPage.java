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
        btnAllProjects.click();
        btnHiTech.click();
    }

    public void openRealEstatePage() {
        btnAllProjects.click();
        btnRealEstate.click();
    }

    public void openLovePage() {
        btnLove.click();
    }

    public void openFilmsPage() {
        btnAllProjects.click();
        btnFilms.click();
    }

    public void openCarsPage() {
        btnAllProjects.click();
        btnCars.click();
    }

}
