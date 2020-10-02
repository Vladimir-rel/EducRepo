package ru.relex.education.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.relex.education.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstName("First Name1").withLastName("Last Name1").withCompany("Company 1").withHomePhone("111").withMobilePhone("222").withWorkPhone("333"));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().contactPage();
    ContactData contact = app.db().contacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    //assertThat(contact.getAllPhone(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllPhone(), equalTo(contactInfoFromEditForm.getAllPhone()));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
