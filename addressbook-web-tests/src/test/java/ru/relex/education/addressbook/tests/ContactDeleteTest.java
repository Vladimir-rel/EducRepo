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
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("First Name1")
              .withLastName("Last Name1").withCompany("Company 1")
              .withHomePhone("111").withMobilePhone("222").withWorkPhone("333"));
    }
  }

  @Test
  public void testContactDelete() throws InterruptedException {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.db().contacts();
    //compare sets
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
