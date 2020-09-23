package ru.relex.education.addressbook.tests;

//import org.junit.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.relex.education.addressbook.model.GroupData;
import ru.relex.education.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
        app.group().create(new GroupData().withName("test").withHeader("test").withFooter("test"));
    }
  }

  @Test
  public void testGroupModification(){
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId())
            .withName("testMod1")
            .withHeader("testMod2")
            .withFooter("testMod3");
    app.group().modify(group);
    // compare sets
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    veryfyContactsListInUI();
  }

  public void veryfyContactsListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertEquals(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }
}
