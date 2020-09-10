package ru.relex.education.addressbook.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.relex.education.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification() throws InterruptedException {
    app.getNavigationHelper().gotoContactPage();
    if (! app.getContactsHelperr().isContactExist()) {
      app.getContactsHelperr().createContact(new ContactData("First Name1", "Middle Name1", "Company 1", null, null, null));
    }
    List<ContactData> before = app.getContactsHelperr().getContactList();
    int contactId = app.getContactsHelperr().selectContact(before.size() - 1);
    app.getContactsHelperr().initContactModification();
    ContactData contact = new ContactData("First NameMod", "Middle Namecontact", "Companycontact", null, null, null, contactId);
    app.getContactsHelperr().fillContactForm(contact, false);
    app.getContactsHelperr().submitContactModification();
    app.getContactsHelperr().returnToContactPage();
    List<ContactData> after = app.getContactsHelperr().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(app.getContactsHelperr().getElementIndex(before, contactId));
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
