package webdriver.elements;

import org.openqa.selenium.By;

/**
 * Class, Describing element checkbox
 */
public class ComboBox extends BaseElement {
    /**
     *
     * @param locator
     * @param name
     */
    public ComboBox(final By locator, final String name) {
        super(locator, name);
    }

    public ComboBox(String string, String name) {
        super(string, name);
    }


    protected String getElementType() {
        return getLoc("loc.combobox");
    }

    public boolean isEnabled(){
        return this.getElement().isEnabled();
    }





    public ComboBox(By locator) {
        super(locator);
    }

}
