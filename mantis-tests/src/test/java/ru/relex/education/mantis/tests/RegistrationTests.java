package ru.relex.education.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration() {
    app.registration().start("user", "user@localhost.localdomain");
  }
}
