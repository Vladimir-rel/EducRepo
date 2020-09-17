package ru.relex.education.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private final Properties prorerties;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupsHelper groupHelper;
  private ContactsHelper contactHelper;
  String browser;
  WebDriver wd;

  public ApplicationManager(String browser) throws IOException {
    this.browser = browser;
    prorerties = new Properties();
  }

  public GroupsHelper group() {
    return groupHelper;
  }

  public ContactsHelper contact() {
    return contactHelper;
  }

  public void init() throws InterruptedException, IOException {
    String target = System.getProperty("target", "local");
    prorerties.load(new FileReader(new File(String.format("src/test/resources/%s.prorerties", target))));
    if (browser == BrowserType.CHROME){
      wd = new ChromeDriver();
    } else if (browser == BrowserType.FIREFOX) {
      wd = new FirefoxDriver();
    } else if (browser == BrowserType.IE) {
      wd = new InternetExplorerDriver();
    }
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(prorerties.getProperty("web.baseUrl"));
    groupHelper = new GroupsHelper(wd);
    contactHelper = new ContactsHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login(prorerties.getProperty("web.adminLogin"), prorerties.getProperty("web.adminPassword"));
  }

  public void stop() {
    wd.quit();
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }
}
