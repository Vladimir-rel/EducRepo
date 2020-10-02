package ru.relex.education.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.relex.education.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

public class TestBase {

  protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
  //protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws IOException {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.bac");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.bac", "config_inc.php");
    app.stop();
  }

  public boolean isIssueOpen(int issueId) {
    return false;
  }

  @BeforeMethod
  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
