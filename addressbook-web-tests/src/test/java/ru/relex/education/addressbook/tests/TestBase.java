package ru.relex.education.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.relex.education.addressbook.appmanager.ApplicationManager;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestBase {

  protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
  Logger logger = LoggerFactory.getLogger(TestBase.class);
  @BeforeSuite
  public void setUp() throws InterruptedException, IOException {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m) {
    logger.info("Start test " + m.getName());
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop test "  + m.getName());
  }
}
