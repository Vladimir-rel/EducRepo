package ru.relex.education.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.relex.education.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import static org.testng.Assert.*;

public class ChangeUserPasswordTest extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException {
    // сброс пароля пользователя админом
    String loginAdmin = "administrator";
    String passwordAdmin = "root";
    app.registration().login(loginAdmin, passwordAdmin);
    app.registration().resetUserPasswordUI(loginAdmin);

    // получить письмо о сбросе пароля, разобрать его (найти ссылку для смены пароля), перейти по ссылке и сменить пароль
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String message = mailMessages.get(mailMessages.size() - 1).text;
    String confPage = app.registration().getConfUrl(message);
    String userpassword = "password";
    String username = app.registration().changePassword(confPage, userpassword);

    // проверить успешный вход с новым паролем и проверить совпадение имен
    String usernameAfterLogin = app.registration().login(username, userpassword);
    assertEquals(usernameAfterLogin, username);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
