package ru.relex.education.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.*;
import ru.relex.education.addressbook.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

  protected static ApplicationManager app = null;

  static {
    try {
      app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  //protected final static ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
  //protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

  @BeforeSuite
  public void setUp() throws InterruptedException, IOException {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }
}
