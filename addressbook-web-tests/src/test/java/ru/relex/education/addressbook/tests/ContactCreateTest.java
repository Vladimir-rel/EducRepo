package ru.relex.education.addressbook.tests;// Generated by Selenium IDE
import org.junit.Test;
import ru.relex.education.addressbook.model.ContactData;

public class ContactCreateTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoContactPage();
    app.getContactsHelperr().initContactCreation();
    app.getContactsHelperr().fillContactForm(new ContactData("First Name1", "Middle Name1", "Company 1", "Address 1", "899999999999999"));
    app.getContactsHelperr().returnToContactPage();
  }
}