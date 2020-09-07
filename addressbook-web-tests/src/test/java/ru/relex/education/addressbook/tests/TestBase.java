package ru.relex.education.addressbook.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.BrowserType;
import ru.relex.education.addressbook.appmanager.ApplicationManager;

public class TestBase {

  protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
  //protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

  @Before
  public void setUp() throws InterruptedException {
    app.init();
  }

  @After
  public void tearDown() {
    app.stop();
  }

}
