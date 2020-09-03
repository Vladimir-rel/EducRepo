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
    click(By.name("home"));
  }

}
