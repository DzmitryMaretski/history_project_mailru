package webdriver.elements;

import org.openqa.selenium.By;


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

    public Slider(By locator) {
        super(locator);
    }
}
