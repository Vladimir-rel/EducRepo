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
      app.contact().create(new ContactData().withFirst_name("First Name1").withMiddle_name("Middle Name1").withCompany("Company 1"));
    }
  }

  @Test
  public void testContactDelete() throws InterruptedException {
    ensurePreconditions();
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    //compare lists count
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(index);
    //compare lists
    Assert.assertEquals(before, after);
  }
}
