package ru.relex.education.addressbook.tests;

import org.junit.Test;
import ru.relex.education.addressbook.model.ContactData;
import ru.relex.education.addressbook.model.GroupData;

import java.util.concurrent.TimeUnit;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification() throws InterruptedException {
    app.getNavigationHelper().gotoContactPage();
    if (! app.getContactsHelperr().isContactExist()) {
      app.getContactsHelperr().createContact(new ContactData("First Name1", "Middle Name1", "Company 1", "Address 1", "899999999999999", "test1"));
    }
    app.getContactsHelperr().selectContact();
    app.getContactsHelperr().initContactModification();
    app.getContactsHelperr().fillContactForm(new ContactData("First Name1", "Middle Name1", "Company 1", "Address 1", "899999999999999", null), false);
    app.getContactsHelperr().submitContactModification();
    app.getContactsHelperr().returnToContactPage();
  }
}
