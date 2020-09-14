package ru.relex.education.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.relex.education.addressbook.model.ContactData;
import ru.relex.education.addressbook.model.Contacts;

import java.util.List;
import java.util.Set;

public class ContactsHelper extends HelperBase {

  public ContactsHelper(WebDriver wd) {
    super(wd);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public int fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
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

  public void initContactModification(int index) {
    wd.findElement(By.xpath("//input[@id='" + index + "']/../../td[8]/a/img[@title='Edit']")).click();
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

  public int сount() {
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

  public Contacts all2() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {
      String name = element.findElement(By.xpath("./td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.xpath("./td[1]/input")).getAttribute("value"));
      ContactData contact = new ContactData().withFirstName(name).withId(id);
      contactCache.add(contact);
    }
    return contactCache;
  }

  public Set<ContactData> all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String[] phones = cells.get(5).getText().split("\n");
      contactCache.add(new ContactData().withId(id).withFirstName(firstname).withMiddleName(lastname)
              .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
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

  public void modify(ContactData contact) {
    selectContact(contact.getId());
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToContactPage();
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModification(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(contact.getFirstName())
            .withMiddleName(contact.getMiddleName()).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }
}
