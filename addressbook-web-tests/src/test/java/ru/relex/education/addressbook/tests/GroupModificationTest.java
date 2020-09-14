package ru.relex.education.addressbook.tests;

//import org.junit.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.relex.education.addressbook.model.GroupData;
import ru.relex.education.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().count() == 0) {
      app.group().create(new GroupData().withName("test"));
    }
  }

  @Test
  public void testGroupModification(){
    ensurePreconditions();
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("testMod1").withHeader("testMod2").withFooret("testMod3");
    app.group().modify(group);
    //compare lists count
    assertEquals(app.group().count(), before.size());
    //sort and compare sets
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }
}
