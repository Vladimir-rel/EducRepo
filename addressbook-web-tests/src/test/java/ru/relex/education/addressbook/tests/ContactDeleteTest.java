package ru.relex.education.addressbook.tests;

//import org.junit.Test;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import ru.relex.education.addressbook.model.ContactData;
import ru.relex.education.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().сount() == 0) {
      app.contact().create(new ContactData().withFirstName("First Name1")
              .withLastName("Last Name1").withCompany("Company 1")
              .withHomePhone("111").withMobilePhone("222").withWorkPhone("333"));
    }
  }

  @Test
  public void testContactDelete() throws InterruptedException {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    //compare sets count
    assertThat(after.size(), equalTo(before.size() - 1));
    //compare sets
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
