package ru.relex.education.mantis.tests;

import org.testng.annotations.Test;
import ru.relex.education.mantis.appmanager.HttpSession;
import java.io.IOException;
import static org.testng.Assert.*;

public class LoginTest extends TestBase {

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    boolean res = session.login("administrator", "root");
    assertTrue(res);
    assertTrue(session.isLoggedInAs("administrator"));
  }
}
