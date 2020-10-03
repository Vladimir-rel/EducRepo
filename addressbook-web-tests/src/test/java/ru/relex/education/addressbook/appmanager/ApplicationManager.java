package ru.relex.education.addressbook.appmanager;
import com.beust.jcommander.ParameterException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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

  DbHelper dbHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    prorerties = new Properties();
  }

  public void init() throws InterruptedException, IOException {
    String target = System.getProperty("target", "local");
    prorerties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    dbHelper = new DbHelper();
    if ("".equals(prorerties.getProperty("selenium.server"))) {
      if (browser.equals(BrowserType.CHROME)){
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      } else {
        throw new ParameterException("Unsupported browser");
      }
    } else {
      DesiredCapabilities capabilites = new DesiredCapabilities();
      capabilites.setBrowserName(browser);
      wd = new RemoteWebDriver(new URL(prorerties.getProperty("selenium.server")), capabilites);
    }

    groupHelper = new GroupsHelper(wd);
    contactHelper = new ContactsHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(prorerties.getProperty("web.baseUrl"));
    sessionHelper.login(prorerties.getProperty("web.adminLogin"), prorerties.getProperty("web.adminPassword"));
  }

  public void stop() {
    wd.quit();
  }

  public GroupsHelper group() {
    return groupHelper;
  }

  public ContactsHelper contact() {
    return contactHelper;
  }

  public DbHelper db() {
    return dbHelper;
  }

  public byte[] takeScreenshot() {
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }
}
