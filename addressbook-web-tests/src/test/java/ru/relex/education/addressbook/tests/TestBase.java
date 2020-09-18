package ru.relex.education.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.*;
import ru.relex.education.addressbook.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

  protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws InterruptedException, IOException {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }
}
