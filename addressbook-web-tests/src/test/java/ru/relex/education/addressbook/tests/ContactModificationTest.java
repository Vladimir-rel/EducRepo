package ru.relex.education.addressbook.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.relex.education.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification() throws InterruptedException {
    app.getNavigationHelper().gotoContactPage();
    if (! app.getContactsHelperr().isContactExist()) {
      app.getContactsHelperr().createContact(new ContactData("First Name1", "Middle Name1", "Company 1", null, null, null));
    }
    List<ContactData> before = app.getContactsHelperr().getContactList();
    app.getContactsHelperr().selectContact();
    app.getContactsHelperr().initContactModification();
    app.getContactsHelperr().fillContactForm(new ContactData("First Name1", "Middle Name1", "Company 1", null, null, null), false);
    app.getContactsHelperr().submitContactModification();
    app.getContactsHelperr().returnToContactPage();
    List<ContactData> after = app.getContactsHelperr().getContactList();
    Assert.assertEquals(after.size(), before.size());

    Assert.assertEquals(after, before);
  }
}
