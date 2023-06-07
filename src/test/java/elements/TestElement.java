package elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestElement {

    @FindBy(xpath = "")
    private WebElement dropDown;

    @Label(className = "")
    private WebElement dropDown1;


    public static void main(String[] args) {
        CheckBox checkBox = FindElement.elementFor(CheckBox.class);
        DropDown dropDown = FindElement.elementFor(DropDown.class);

        checkBox.isSelected();

        dropDown.selectValueByIndex(0);
    }

}
