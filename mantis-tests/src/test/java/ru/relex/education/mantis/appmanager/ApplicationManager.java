package ru.relex.education.mantis.appmanager;

import com.beust.jcommander.ParameterException;
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
  String browser;
  WebDriver wd;

  public ApplicationManager(String browser) {
    this.browser = browser;
    prorerties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    prorerties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    if (browser.equals(BrowserType.CHROME)){
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    } else {
      throw new ParameterException("Unsupported browser");
    }

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(prorerties.getProperty("web.baseUrl"));
  }

  public void stop() {
    wd.quit();
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public  String getProperty (String key) {
    return prorerties.getProperty(key);
  }
}
