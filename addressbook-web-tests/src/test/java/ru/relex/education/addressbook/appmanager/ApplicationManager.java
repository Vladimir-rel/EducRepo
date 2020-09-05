package ru.relex.education.addressbook.appmanager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupsHelperr groupHelperr;
  private ContactsHelperr contactHelperr;
  String browser;
  WebDriver wd;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public GroupsHelperr getGroupsHelperr() {
    return groupHelperr;
  }

  public ContactsHelperr getContactsHelperr() {
    return contactHelperr;
  }

  public void init() throws InterruptedException {

    if (browser == BrowserType.CHROME){
      System.setProperty("webdriver.chrome.driver", "D:/WORK/Tester/Java_Selenium/EducRepo2/chromedriver_win32/chromedriver.exe");
      wd = new ChromeDriver();
    } else if (browser == BrowserType.FIREFOX) {
      System.setProperty("webdriver.gecko.driver", "D:/WORK/Tester/Java_Selenium/EducRepo2/geckodriver-v0.27.0-win32/geckodriver.exe");
      wd = new FirefoxDriver();
    } else if (browser == BrowserType.IE) {
      wd = new InternetExplorerDriver();
    }

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
