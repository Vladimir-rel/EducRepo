package ru.relex.education.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Signup'"));
  }

  public String changePassword(String page, String password) {
    wd.get(app.getProperty("web.baseUrl") + page);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    String username = wd.findElement(By.xpath("//tr[@class='row-1']/td[2]")).getText();
    click(By.cssSelector("input[value='Update User'"));
    return username;
  }

  public String login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login'"));
    return wd.findElement(By.xpath("//span[@class='italic']")).getText();
  }

  public String getConfUrl(String message) {
    Pattern regexp = Pattern.compile("verify.php\\?id=[\\w\\?&=]+");
    Matcher m = regexp.matcher(message);
    String confPage = null;
    m.find();
    confPage = m.group();
    return confPage;
  }

  public void resetUserPasswordUI(String admin) {
    click(By.xpath("//a[@href='/mantisbt-1.2.19/manage_user_page.php']"));
    List<WebElement> list1 = wd.findElements(By.xpath("//tr[@class='row-1']/td/a"));
    List<WebElement> list2 = wd.findElements(By.xpath("//tr[@class='row-2']/td/a"));
    list1.addAll(list2);
    String username = null;
    for (WebElement elem : list1) {
      username = elem.getText();
      if (username != admin) {
        click(By.linkText(username));
        break;
      }
    }
    click(By.xpath("//input[@value='Reset Password']"));
    click(By.xpath("//a[@href='/mantisbt-1.2.19/logout_page.php']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User'"));
  }
}
