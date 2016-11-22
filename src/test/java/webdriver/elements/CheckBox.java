package webdriver.elements;

import org.openqa.selenium.By;

/**
 * Created by crz on 20.11.2016.
 */
public class CheckBox extends BaseElement{
    /**
     *
     * @param locator
     * @param name
     */
    public CheckBox(final By locator, final String name) {
        super(locator, name);
    }

    public CheckBox(String string, String name) {
        super(string, name);
    }

    protected String getElementType() {
        return getLoc("loc.checkbox");
    }

    public boolean isEnabled(){
        return this.getElement().isEnabled();
    }

    public CheckBox(By locator) {
        super(locator);
    }
}
