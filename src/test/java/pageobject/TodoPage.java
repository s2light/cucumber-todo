package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TodoPage {
    final String pageUrl = "https://todomvc.com/examples/react/#/active";
    String removeButton = "//ul[@class=\"todo-list\"]//label[text()=\"%s\"]/../button[@class=\"destroy\"]";
    String itemContainer = "//ul[@class=\"todo-list\"]//label[text()=\"%s\"]/..";
    @FindBy(css = "input.new-todo")
    public WebElement TaskInput;
    @FindAll({@FindBy(css="ul.todo-list li label")})
    public List<WebElement> TaskItems;
    @FindBy(css = "a[href=\"#/active\"]")
    public WebElement ActiveTab;
    @FindBy(css = "a[href=\"#/completed\"]")
    public WebElement CompleteTab;

    WebDriver driver;
    public TodoPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void Open()
    {
        this.driver.get(pageUrl);
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        PageFactory.initElements(this.driver, this);
    }

    public void reInitElement()
    {
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        PageFactory.initElements(this.driver, this);
    }

    public void RemoveTask(String task)
    {
        this.itemContainer = String.format(this.itemContainer, task);
        WebElement TaskItem = this.driver.findElement(By.xpath(this.itemContainer));
        Actions mouseAction = new Actions(this.driver);
        mouseAction.moveToElement(TaskItem).perform();
        WebElement remove = TaskItem.findElement(By.cssSelector("button.destroy"));
        remove.click();
    }

    public void CompleteTask(String item2Complete) {
        this.itemContainer = String.format(this.itemContainer, item2Complete);
        WebElement TaskItem = this.driver.findElement(By.xpath(this.itemContainer));
        Actions mouseAction = new Actions(this.driver);
        mouseAction.moveToElement(TaskItem).perform();
        WebElement complete = TaskItem.findElement(By.cssSelector("input.toggle"));
        complete.click();
    }
}
