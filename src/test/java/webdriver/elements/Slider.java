package webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;


public class Slider extends BaseElement{
    /**
     *
     * @param locator
     * @param name
     */
    public Slider(final By locator, final String name) {
        super(locator, name);
    }

    public Slider(String string, String name) {
        super(string, name);
    }

    protected String getElementType() {
        return getLoc("loc.slider");
    }

    public boolean isEnabled(){
        return this.getElement().isEnabled();
    }

    public void makeActionOnSlider(int xTo, int yTo) {
        Actions act = new Actions(browser.getDriver());
        act.dragAndDropBy(element, xTo, yTo).build().perform();
    }
    public Slider(By locator) {
        super(locator);
    }
}
