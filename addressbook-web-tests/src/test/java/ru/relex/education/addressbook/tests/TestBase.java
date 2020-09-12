package ru.relex.education.addressbook.tests;

//import org.junit.After;
//import org.junit.Before;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.*;
import ru.relex.education.addressbook.appmanager.ApplicationManager;

public class TestBase {

  protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
  //protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
  //protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

  //@BeforeSuite
  //@BeforeTest
  @BeforeMethod
  public void setUp() throws InterruptedException {
    app.init();
  }

  //@AfterSuite
  //@AfterTest
  @AfterMethod
  public void tearDown() {
    app.stop();
  }

}
