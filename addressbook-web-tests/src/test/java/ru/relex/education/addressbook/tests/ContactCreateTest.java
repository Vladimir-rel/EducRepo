package ru.relex.education.addressbook.tests;
// Generated by Selenium IDE
//import org.junit.Test;
import org.testng.annotations.Test;
import ru.relex.education.addressbook.model.ContactData;
import ru.relex.education.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ContactCreateTest extends TestBase {

  //@Test(enabled=false)
  @Test(enabled=true)
  public void testContactCreation() {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/1.jpg");
    ContactData contact = new ContactData().withFirstName("First Name1")
            .withLastName("Last Name1")
            .withCompany("Company 1")
            .withHomePhone("111")
            .withMobilePhone("222")
            .withWorkPhone("333")
            .withPhoto(photo);
    app.contact().create(contact);
    Contacts after = app.contact().all();
    //compare sets count
    assertThat(after.size(), equalTo(before.size() + 1));
    //compare sets
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()))));
  }
}
