package ru.relex.education.addressbook.tests;

//import org.junit.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.relex.education.addressbook.model.ContactData;
import ru.relex.education.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("First Name1")
              .withLastName("Last Name1")
              .withCompany("Company 1")
              .withHomePhone("111")
              .withMobilePhone("222")
              .withWorkPhone("333"));
    }
  }

  @Test
  public void testContactModification() throws InterruptedException {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstName("First Name1").withLastName("Last Name1").withCompany("Company 1")
            .withHomePhone("111").withMobilePhone("222").withWorkPhone("333");
    app.contact().modify(contact);
    Contacts after = app.db().contacts();
    //compare sets
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
