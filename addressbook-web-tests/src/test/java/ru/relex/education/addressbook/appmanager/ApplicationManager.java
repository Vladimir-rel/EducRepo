package ru.relex.education.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupsHelper groupHelperr;
  private ContactsHelper contactHelperr;
  String browser;
  WebDriver wd;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public GroupsHelper group() {
    return groupHelperr;
  }

  public ContactsHelper contact() {
    return contactHelperr;
  }

  public void init() throws InterruptedException {
    if (browser == BrowserType.CHROME){
      wd = new ChromeDriver();
    } else if (browser == BrowserType.FIREFOX) {
      wd = new FirefoxDriver();
    } else if (browser == BrowserType.IE) {
      wd = new InternetExplorerDriver();
    }
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    groupHelperr = new GroupsHelper(wd);
    contactHelperr = new ContactsHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    //TimeUnit.SECONDS.sleep(5);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }
}
