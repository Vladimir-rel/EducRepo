package ru.relex.education.addressbook.tests;
// Generated by Selenium IDE
import org.junit.Assert;
//import org.junit.Test;
import org.testng.annotations.Test;
import ru.relex.education.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreateTest extends TestBase {

  //@Test(enabled=false)
  @Test(enabled=true)
  public void testContactCreation() {
    app.goTo().contactPage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("First Name1", "Middle Name1", "Company 1", null, null, null);
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
