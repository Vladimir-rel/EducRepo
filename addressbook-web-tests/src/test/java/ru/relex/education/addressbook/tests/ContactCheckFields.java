package ru.relex.education.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.relex.education.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCheckFields extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("First Name1")
              .withLastName("Last Name1")
              .withCompany("Company 1")
              .withAddress("Address")
              .withEmail1("email1")
              .withEmail2("email2")
              .withEmail3("email1")
              .withHomePhone("111")
              .withMobilePhone("222")
              .withWorkPhone("333"));
    }
  }

  @Test
  public void testContactCheckFields() {
    app.goTo().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getFirstName(), equalTo(contactInfoFromEditForm.getFirstName()));
    assertThat(contact.getLastName(), equalTo(contactInfoFromEditForm.getLastName()));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contact.getAllEmail(), equalTo(mergeEmail(contactInfoFromEditForm)));
    assertThat(contact.getAllPhone(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmail(ContactData contact) {
    return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
