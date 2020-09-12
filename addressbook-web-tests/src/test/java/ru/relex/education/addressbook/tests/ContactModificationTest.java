package ru.relex.education.addressbook.tests;

import org.junit.Assert;
//import org.junit.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.relex.education.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirst_name("First Name1").withMiddle_name("Middle Name1").withCompany("Company 1"));
    }
  }

  @Test
  public void testContactModification() throws InterruptedException {
    ensurePreconditions();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirst_name("First NameMod").withMiddle_name("Middle NameContactMod").withCompany("CompanyContactMod");
    int index = before.size() - 1;
    int contactId = app.contact().modify(contact, index);
    List<ContactData> after = app.contact().list();
    //compare lists count
    Assert.assertEquals(after.size(), before.size());
    //change contact in before list
    before.remove(app.contact().getElementIndex(before, contactId));
    before.add(contact);
    //sort and compare lists
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
