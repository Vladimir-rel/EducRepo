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
      wd = new ChromeDriver();
    } else if (browser == BrowserType.FIREFOX) {
      wd = new FirefoxDriver();
    } else if (browser == BrowserType.IE) {
      wd = new InternetExplorerDriver();
    }
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    groupHelperr = new GroupsHelperr(wd);
    contactHelperr = new ContactsHelperr(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    //TimeUnit.SECONDS.sleep(5);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
