package ru.relex.education.addressbook.tests;

import org.junit.After;
import org.junit.Before;
import ru.relex.education.addressbook.appmanager.ApplicationManager;

public class TestBase {

  protected final ApplicationManager app = new ApplicationManager();

  @Before
  public void setUp() throws InterruptedException {
    app.init();
  }

  @After
  public void tearDown() {
    app.stop();
  }

}
