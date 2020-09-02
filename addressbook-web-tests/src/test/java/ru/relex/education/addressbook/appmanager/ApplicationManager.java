package ru.relex.education.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {

  private final GroupsHelperr groupsHelperr = new GroupsHelperr();

  public void init() {
    System.setProperty("webdriver.chrome.driver", "D:/WORK/Tester/Java_Selenium/EducRepo2/chromedriver_win32/chromedriver.exe");
    groupsHelperr.driver = new ChromeDriver();
    login("admin", "secret");
  }

  private void login(String username, String password) {
    groupsHelperr.driver.get("http://localhost/addressbook/group.php");
    groupsHelperr.driver.manage().window().setSize(new Dimension(942, 576));
    groupsHelperr.driver.findElement(By.name("user")).click();
    groupsHelperr.driver.findElement(By.name("user")).sendKeys(username);
    groupsHelperr.driver.findElement(By.name("pass")).sendKeys(password);
    groupsHelperr.driver.findElement(By.cssSelector("input:nth-child(7)")).click();
  }

  public void gotoGroupPage() {

  }

  public void stop() {
    groupsHelperr.driver.quit();
  }

  public GroupsHelperr getGroupsHelperr() {
    return groupsHelperr;
  }
}
