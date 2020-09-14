package ru.relex.education.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.relex.education.addressbook.model.ContactData;
import ru.relex.education.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactsHelper extends HelperBase {

  public ContactsHelper(WebDriver wd) {
    super(wd);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public int fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirst_name());
    type(By.name("middlename"), contactData.getMiddle_name());
    type(By.name("address"), contactData.getAddress());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getPhone());
    int contactId = 0;

    if (creation){
      if (contactData.getGroup() == null) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("[none]");
      } else {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      }
    } else {
      contactId = Integer.parseInt(wd.findElement(By.xpath("//input[@name='id']")).getAttribute("value"));
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    return contactId;
  }

  public void submitContactCreate() {
    click(By.name("submit"));
  }

  public void returnToContactPage() {
    click(By.linkText("home"));
  }

  public void selectContact(int index) {
    wd.findElement(By.xpath("//input[@id='" + index + "']")).click();
  }

  public void initContactModification() {
    click(By.xpath ("//img[@title='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeAlertDelete() {
    wd.switchTo().alert().accept();
  }

  public boolean isContactExist() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreate();
    contactCache = null;
    returnToContactPage();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {
      String name = element.findElement(By.xpath("./td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.xpath("./td[1]/input")).getAttribute("value"));
      ContactData contact = new ContactData().withFirst_name(name).withId(id);
      contactCache.add(contact);
    }
    return contactCache;
  }

  public void delete(int index) {
    selectContact(index);
    deleteSelectedContact();
    closeAlertDelete();
    contactCache = null;
    returnToContactPage();
  }

  public void delete(ContactData contact) {
    selectContact(contact.getId());
    deleteSelectedContact();
    closeAlertDelete();
    contactCache = null;
    returnToContactPage();
  }

  public int modify(ContactData contact, int index) {
    selectContact(index);
    initContactModification();
    int contactId = fillContactForm(contact, false);
    contact.withId(contactId);
    submitContactModification();
    contactCache = null;
    returnToContactPage();
    return contactId;
  }

  public void modify(ContactData contact) {
    selectContact(contact.getId());
    initContactModification();
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToContactPage();
  }
}
