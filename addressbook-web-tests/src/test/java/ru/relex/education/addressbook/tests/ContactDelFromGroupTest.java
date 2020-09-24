package ru.relex.education.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.relex.education.addressbook.model.ContactData;
import ru.relex.education.addressbook.model.Contacts;
import ru.relex.education.addressbook.model.GroupData;
import ru.relex.education.addressbook.model.Groups;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDelFromGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    // if need add contact with group
    if (app.db().contacts().size() == 0) {
      ContactData contact = new ContactData().withFirstName("First Name1")
              .withLastName("Last Name1").withCompany("Company 1")
              .withHomePhone("111").withMobilePhone("222").withWorkPhone("333");
      Groups groupsAll = app.db().groups();
      // if need add group
      if (groupsAll.size() == 0) {
        app.goTo().groupPage();
        GroupData group = new GroupData().withName("group test name").withHeader("group test header").withFooter("group test footer");
        app.group().create(group);
        contact.withGroup(group);
        app.goTo().contactPage();
      }
      app.contact().create(contact);
    }
  }

  @Test
  public void testContactDeleteFromGroup() throws InterruptedException {
    ContactData changeContact = app.db().contacts().iterator().next();
    GroupData groupDel;
    //if no contacts
    if (changeContact.getGroups().size() == 0) {
      Groups groups = app.db().groups();
      // if no groups
      if (groups.size() == 0) {
        groupDel = new GroupData().withName("group " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .withHeader("group header")
                .withFooter("group fooret");
        app.group().create(groupDel);
      }
      groupDel = app.db().groups().iterator().next();
      app.contact().addContactInGroup(changeContact, groupDel);
      Contacts contacts = app.db().contacts();
      // get fresh contact to check groups
      changeContact = app.contact().getFreshContact(changeContact.getId(), contacts);
    }
    groupDel = changeContact.getGroups().iterator().next();
    app.contact().delContactFromGroupUI(changeContact.getId(), groupDel.getId());
    //check del group in contact
    Contacts after = app.db().contacts();
    assert(app.contact().isContactNotInGroup(changeContact.getId(), groupDel, after));
  }
}
