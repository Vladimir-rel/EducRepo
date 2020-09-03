package ru.relex.education.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class SessionHelper {

  WebDriver wd;

  public SessionHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void login(String username, String password) {
    wd.manage().window().setSize(new Dimension(942, 576));
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.cssSelector("input:nth-child(7)")).click();
  }
}
