package ru.relex.education.addressbook.tests;

import org.junit.Test;
import ru.relex.education.addressbook.model.ContactData;
import ru.relex.education.addressbook.model.GroupData;

public class ContactModificationTest extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoContactPage();
    app.getContactsHelperr().selectContact();
    app.getContactsHelperr().initContactModification();
    app.getContactsHelperr().fillContactForm(new ContactData("First Name1", "Middle Name1", "Company 1", "Address 1", "899999999999999", null), false);
    app.getContactsHelperr().submitContactModification();
    app.getContactsHelperr().returnToContactPage();
  }
}
