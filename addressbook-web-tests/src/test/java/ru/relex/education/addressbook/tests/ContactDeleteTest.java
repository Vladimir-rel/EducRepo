package ru.relex.education.addressbook.tests;

import org.junit.Assert;
//import org.junit.Test;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import ru.relex.education.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData("First Name1", "Middle Name1", "Company 1", null, null, null));
    }
  }

  @Test
  public void testContactDelete() throws InterruptedException {
    ensurePreconditions();
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(index);
    Assert.assertEquals(before, after);
  }
}
