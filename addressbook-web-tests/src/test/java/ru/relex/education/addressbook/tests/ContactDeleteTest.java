package ru.relex.education.addressbook.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.relex.education.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactDeleteTest extends TestBase {

  @Test
  public void testContactDelete() throws InterruptedException {
    app.getNavigationHelper().gotoContactPage();
    if (! app.getContactsHelperr().isContactExist()) {
      app.getContactsHelperr().createContact(new ContactData("First Name1", "Middle Name1", "Company 1", null, null, null));
    }
    List<ContactData> before = app.getContactsHelperr().getContactList();
    app.getContactsHelperr().selectContact(before.size() - 1);
    app.getContactsHelperr().deleteSelectedContact();
    app.getContactsHelperr().closeAlertDelete();
    app.getContactsHelperr().returnToContactPage();
    List<ContactData> after = app.getContactsHelperr().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);

    Assert.assertEquals(before, after);
  }
}
