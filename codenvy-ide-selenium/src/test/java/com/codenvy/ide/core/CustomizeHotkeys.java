/*
 *
 * CODENVY CONFIDENTIAL
 * __________________
 *
 *  [2012] - [2013] Codenvy, S.A.
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.ide.core;

import com.codenvy.ide.MenuCommands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

/**
 * @author Evgen Vidolob
 *
 */
public class CustomizeHotkeys extends AbstractTestModule {
    /** @param ide */
    public CustomizeHotkeys(com.codenvy.ide.IDE ide) {
        super(ide);
    }

    private interface Locators {

        String FIRST_CELL_FOR_DELECT_ELEMENT =
                "//table[@id='ideCustomizeHotKeysListGrid']/tbody/tr//b/font[text()='File']";

        String ENABLED_BUTTON_PREFICS = "[button-enabled=true]";

        String DISABLED_BUTTON_PREFICS = "[button-enabled=false]";

        String CUSTOMIZE_HOTKEYS_FORM = "ideCustomizeHotKeysListGrid";

        String BIND_BUTTON_ID = "ideCustomizeHotKeysViewBindButton";

        String IS_BIND_ENABLED_SELECTOR = "div#" + BIND_BUTTON_ID + ENABLED_BUTTON_PREFICS;

        String IS_BIND_DISABLED_SELECTOR = "div#" + BIND_BUTTON_ID + DISABLED_BUTTON_PREFICS;

        String UNBIND_BUTTON_ID = "ideCustomizeHotKeysViewUnbindButton";

        String DEFAULTS_BUTTON_ID = "ideCustomizeHotKeysViewDefaultsButton";

        String IS_UNBIND_ENABLED_SELECTOR = "div#" + UNBIND_BUTTON_ID + ENABLED_BUTTON_PREFICS;

        String IS_UNBIND_DISABLED_SELECTOR = "div#" + UNBIND_BUTTON_ID + DISABLED_BUTTON_PREFICS;

        String OK_BUTTON_ID = "ideCustomizeHotKeysViewOkButton";

        String IS_OK_ENABLED_SELECTOR = "div#" + OK_BUTTON_ID + ENABLED_BUTTON_PREFICS;

        String IS_OK_DISABLED_SELECTOR = "div#" + OK_BUTTON_ID + DISABLED_BUTTON_PREFICS;

        String CANCEL_BUTTON_ID = "ideCustomizeHotKeysViewCancelButton";

        String IS_CANCEL_ENABLED_SELECTOR = "div#" + CANCEL_BUTTON_ID + ENABLED_BUTTON_PREFICS;

        String IS_CANCEL_DISABLED_SELECTOR = "div#" + CANCEL_BUTTON_ID + DISABLED_BUTTON_PREFICS;

        String LIST_GRID_FORM = "ideCustomizeHotKeysListGrid";

        String CUSTOMIZE_ROWS = "//table[@id='" + LIST_GRID_FORM + "']" + "/tbody//tr/td/div/span[contains(.,'%s')]";

        String BINDING_ROWS = "//table[@id='" + LIST_GRID_FORM + "']" + "/tbody//tr/td/div/span[contains(.,'%s')]";

        String MAXIMIZE_TITLE = "div#ideCustomizeHotKeysView-window img[title=Maximize]";

        String CLOSE_TITLE = "img[title=Close]";

        String INPUT_FIELD = "div[view-id=ideCustomizeHotKeysView] input[type=text]";

        String INPUT_FIELD_DISABLED = "div[view-id=ideCustomizeHotKeysView] input.gwt-TextBox[type='text'][disabled]";

        String INPUT_FIELD_ENABLED = "div[view-id=ideCustomizeHotKeysView] input.gwt-TextBox[type='text']";

        String LABEL_MESSAGE_HOTKEY_ID = "ideCustomizeHotKeysMessageLabel";

        String FIRST_KEY_MESSAGE = "First key should be Ctrl or Alt";

        String ALREDY_TO_ANORHER_COMM_MESSAGE = "Such hotkey already bound to another command";

        String ALREDY_TO_THIS_COMM_MESSAGE = "Such hotkey already bound to this command";

        String HOLT_MESSAGE = "Holt Ctrl or Alt, then press key";

        String HOKEY_USED_MESSAGE = "This hotkey is used by Code or WYSIWYG Editors";

    }

    // The basic webelements for customize
    @FindBy(id = Locators.CUSTOMIZE_HOTKEYS_FORM)
    private WebElement customizeHotkeyForm;

    @FindBy(id = Locators.BIND_BUTTON_ID)
    private WebElement bindButton;

    @FindBy(id = Locators.UNBIND_BUTTON_ID)
    private WebElement unbindButton;

    @FindBy(id = Locators.DEFAULTS_BUTTON_ID)
    private WebElement defaultsButton;

    @FindBy(id = Locators.OK_BUTTON_ID)
    private WebElement okButton;

    @FindBy(id = Locators.CANCEL_BUTTON_ID)
    private WebElement cancelButton;

    @FindBy(id = Locators.LABEL_MESSAGE_HOTKEY_ID)
    private WebElement hotkeyMessage;

    @FindBy(css = Locators.IS_BIND_ENABLED_SELECTOR)
    private WebElement isBindEnabled;

    @FindBy(css = Locators.IS_BIND_DISABLED_SELECTOR)
    private WebElement isBindDisabled;

    @FindBy(css = Locators.IS_UNBIND_DISABLED_SELECTOR)
    private WebElement isUnBindDisabled;

    @FindBy(css = Locators.IS_UNBIND_ENABLED_SELECTOR)
    private WebElement isUnBindEnabled;

    @FindBy(css = Locators.IS_OK_ENABLED_SELECTOR)
    private WebElement isOkEnabled;

    @FindBy(css = Locators.IS_OK_DISABLED_SELECTOR)
    private WebElement isOkDisabled;

    @FindBy(css = Locators.IS_CANCEL_DISABLED_SELECTOR)
    private WebElement isCancelDisabled;

    @FindBy(css = Locators.IS_CANCEL_ENABLED_SELECTOR)
    private WebElement isCancelEnabled;

    @FindBy(css = Locators.INPUT_FIELD)
    private WebElement keyField;

    @FindBy(css = Locators.MAXIMIZE_TITLE)
    private WebElement max;

    @FindBy(css = Locators.INPUT_FIELD_DISABLED)
    private WebElement isKeyFieldDisabled;

    @FindBy(css = Locators.INPUT_FIELD_ENABLED)
    private WebElement isKeyFieldEnabled;

    @FindBy(css = Locators.CLOSE_TITLE)
    private WebElement closeTitle;

    @FindBy(xpath = Locators.FIRST_CELL_FOR_DELECT_ELEMENT)
    private WebElement firstCell;

    /** Wait appearance Customize Hotkeys Form */
    public void waitOpened() throws InterruptedException {
        new WebDriverWait(driver(), 30).until(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver input) {
                return customizeHotkeyForm != null && customizeHotkeyForm.isDisplayed();
            }
        });
    }

    /** Waiting while button bind will enabled */
    public void waitBindEnabled() throws InterruptedException {
        new WebDriverWait(driver(), 30).until(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver input) {
                return isBindEnabled != null && isBindEnabled.isDisplayed();
            }
        });
    }

    /** Waiting while button unbind will enabled */
    public void waitUnBindEnabled() throws InterruptedException {
        new WebDriverWait(driver(), 30).until(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver input) {
                return isUnBindEnabled != null && isUnBindEnabled.isDisplayed();
            }
        });
    }

    /** Waiting while button ok will enabled */
    public void waitOkEnabled() throws InterruptedException {
        new WebDriverWait(driver(), 30).until(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver input) {
                return isOkEnabled != null && isOkEnabled.isDisplayed();
            }
        });
    }

    /**
     * Wait disappearance Customize Hotkeys Form
     *
     * @throws InterruptedException
     */
    public void waitClosed() throws InterruptedException {
        new WebDriverWait(driver(), 30).until(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver input) {
                try {
                    input.findElement(By.id(Locators.CUSTOMIZE_HOTKEYS_FORM));
                    return false;
                } catch (Exception e) {
                    return true;
                }
            }
        });
    }

    /** @return true if button enabled */
    public boolean isBindEnabled() {
        return isBindEnabled != null && isBindEnabled.isDisplayed();
    }

    /** @return true if button disabled */
    public boolean isBindDisabled() {
        return isBindDisabled != null && isBindDisabled.isDisplayed();
    }

    /** @return true if button disabled */
    public boolean isUnBindDisabled() {
        return isUnBindDisabled != null && isUnBindDisabled.isDisplayed();
    }

    /** @return true if button enabled */
    public boolean isUnBindEnabled() {
        return isUnBindEnabled != null && isUnBindEnabled.isDisplayed();
    }

    /** @return true if button enabled */
    public boolean isOkEnabled() {
        return isOkEnabled != null && isOkEnabled.isDisplayed();
    }

    /** @return true if button disabled */
    public boolean isOkDisabled() {
        return isOkDisabled != null && isOkDisabled.isDisplayed();
    }

    /** @return true if cancel button enabled */
    public boolean isCancelEnabled() {
        return isCancelEnabled != null && isCancelEnabled.isDisplayed();
    }

    /** @return true if cancel button disabled */
    public boolean isCancelDisabled() {
        return isCancelDisabled != null && isCancelDisabled.isDisplayed();
    }

    /** click cancel button */
    public void cancelButtonClick() {
        cancelButton.click();
    }

    /** click bind button */
    public void bindButtonClick() {
        bindButton.click();
    }

    /** click unbind button */
    public void unbindButtonClick() {
        unbindButton.click();
    }

    /** click on defaults button */
    public void clickDefaultsButton() {
        defaultsButton.click();
    }

    /** click ok button */
    public void okButtonClick() {
        okButton.click();
    }

    public void maximizeClick() {
        max.click();
    }

    /**
     * @return true if button disabled
     * @throws InterruptedException
     */
    public void typeKeys(String text) throws InterruptedException {
        IDE().INPUT.typeToElement(keyField, text);
    }

    /**
     * Return current text from textfield
     *
     * @return
     */
    public String getTextTypeKeys() {
        return IDE().INPUT.getValue(keyField);
    }

    /**
     * Selects an item on the commandlist by name
     *
     * @param name
     */
    public void selectElementOnCommandlistbarByName(String name) {
        WebElement rowByName = driver().findElement(By.xpath(String.format(Locators.CUSTOMIZE_ROWS, name)));
        rowByName.click();
    }

    /** Close form using the method of click on close label in form */
    public void closeClick() {
        closeTitle.click();
    }

    /** get message from Hot Keys Message Label */
    public String getMessage() {
        return hotkeyMessage.getText();
    }

    /**
     * Checking  active key field
     *
     * @param isActive
     */
    public boolean isKeyFieldActive(boolean isActive) {
        if (isActive) {
            return isKeyFieldEnabled != null && isKeyFieldEnabled.isDisplayed();
        } else {
            return isKeyFieldDisabled != null && isKeyFieldDisabled.isDisplayed();
        }
    }

    /** Checking label with message is empty */

    public void isAlredyNotView() {
        assertEquals("", getMessage());
    }

    /** Check present already message */
    public void isAlreadyMessageView() {
        assertEquals(Locators.ALREDY_TO_ANORHER_COMM_MESSAGE, getMessage());
    }

    /** Check present already to this command message */
    public void isAlreadyToThisCommandMessView() {
        assertEquals(Locators.ALREDY_TO_THIS_COMM_MESSAGE, getMessage());
    }

    /** Checking label with "First key should be Ctrl or Alt" is present */

    public void isFirstKeyMessageView() {
        assertEquals(Locators.FIRST_KEY_MESSAGE, getMessage());
    }

    /** Checking Holt Message is view */

    public void isHoltMessageView() {
        assertEquals(Locators.HOLT_MESSAGE, getMessage());
    }

    /** Checking "This hotkey is used by Code or WYSIWYG Editors" Message is view */

    public void isHotKeyUsedMessageView() {
        assertEquals(Locators.HOKEY_USED_MESSAGE, getMessage());
    }

    /** click on first cell in Customize hotkey form and deselect elements from list */

    public void deselctElemenftWithFirstCellClick() {
        firstCell.click();
    }


    /**
     * Reset hot keys
     *
     * @throws Exception
     */
    public void resetToDefaults() throws Exception {
        IDE().MENU.runCommand(MenuCommands.Window.WINDOW, MenuCommands.Window.PREFERNCESS);
        IDE().PREFERENCES.waitPreferencesOpen();
        IDE().PREFERENCES.selectCustomizeMenu(MenuCommands.Preferences.CUSTOMIZE_HOTKEYS);
        IDE().CUSTOMIZE_HOTKEYS.waitOpened();
        clickDefaultsButton();
        IDE().LOADER.waitClosed();
        okButtonClick();
        IDE().LOADER.waitClosed();
    }
}
