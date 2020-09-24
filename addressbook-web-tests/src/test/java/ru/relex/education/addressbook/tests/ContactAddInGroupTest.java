package ru.relex.education.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.relex.education.addressbook.model.ContactData;
import ru.relex.education.addressbook.model.Contacts;
import ru.relex.education.addressbook.model.GroupData;
import ru.relex.education.addressbook.model.Groups;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddInGroupTest extends TestBase{

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
  public void testContactAddInGroup() throws InterruptedException {
    Contacts before = app.db().contacts();
    // check contact exists
    ContactData changeContact = before.iterator().next();
    GroupData groupAdd;
    if (changeContact.getGroups().size() == 0) {
      GroupData group = new GroupData().withName("group " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).withHeader("group 1").withFooter("group 1");
      app.goTo().groupPage();
      app.group().create(group);
      groupAdd = getNewGroup(changeContact);
    } else {
      groupAdd = changeContact.getGroups().iterator().next();
    }
    app.goTo().contactPage();
    app.contact().addContactInGroup(changeContact, groupAdd);
    //check add group in contact
    Contacts after = app.db().contacts();
    ContactData freshContact = after.getFreshContact(changeContact);
    Groups contactGroups = freshContact.getGroups();
    for (GroupData contactGroup : contactGroups) {
      if (contactGroup.getId() == groupAdd.getId()) {
        assertThat(contactGroup.getId(), equalTo(groupAdd.getId()));
      }
    }
  }

  private GroupData getNewGroup(ContactData changeContact) {
    Groups groups = app.db().groups();
    Groups groupsIn = changeContact.getGroups();
    for(GroupData group: groupsIn) {
      groups.remove(group);
    }
    if (groups.size() == 0) {
      return null;
    } else {
      return groups.iterator().next();
    }
  }
}
