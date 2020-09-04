package ru.relex.education.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.relex.education.addressbook.model.ContactData;

public class ContactsHelperr extends HelperBase {

  public ContactsHelperr(WebDriver wd) {
    super(wd);
  }

  public void initContactCreation() {
    click(By.linkText("ADD_NEW"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirst_name());
    type(By.name("middlename"), contactData.getMiddle_name());
    type(By.name("address"), contactData.getAddress());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getPhone());
  }

  public void submitContactCreate() {
    click(By.name("submit"));
  }

  public void returnToContactPage() {
    click(By.linkText("HOME"));
  }

  public void selectContact() {
    //click(By.name("selected[]"));
    //click(By.id("6"));
    click(By.xpath("//tr[6]/td/input"));

  }
  public void initContactModification() {
    click(By.xpath ("//table[@id='maintable']/tbody/tr[6]/td[8]/a/img"));
    //click(By.cssSelector("tr:nth-child(6) > .center:nth-child(8) img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void deleteSelectedContact() {
    click(By.cssSelector(".left:nth-child(8) > input"));
  }

  public void closeAlertDelete() {
    wd.switchTo().alert().accept();
  }
}
