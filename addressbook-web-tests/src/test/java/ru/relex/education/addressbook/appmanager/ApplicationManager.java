package ru.relex.education.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupsHelperr groupHelperr;
  private ContactsHelperr contactHelperr;
  WebDriver wd;

  public GroupsHelperr getGroupsHelperr() {
    return groupHelperr;
  }

  public ContactsHelperr getContactsHelperr() {
    return contactHelperr;
  }

  public void init() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "D:/WORK/Tester/Java_Selenium/EducRepo2/chromedriver_win32/chromedriver.exe");
    wd = new ChromeDriver();
    wd.get("http://localhost/addressbook/group.php");
    wd.manage().window().setSize(new Dimension(942, 576));
    groupHelperr = new GroupsHelperr(wd);
    contactHelperr = new ContactsHelperr(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    TimeUnit.SECONDS.sleep(5);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
