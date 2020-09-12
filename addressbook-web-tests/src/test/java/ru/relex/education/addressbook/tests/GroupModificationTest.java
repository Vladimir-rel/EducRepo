package ru.relex.education.addressbook.tests;

import org.junit.Assert;
//import org.junit.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.relex.education.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test"));
    }
  }

  @Test
  public void testGroupModification(){
    ensurePreconditions();
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("testMod1").withHeader("testMod2").withFooret("testMod3");
    app.group().modify(group);
    //compare lists count
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());
    //change group in "before" set
    before.remove(modifiedGroup);
    before.add(group);
    //sort and compare sets
    Assert.assertEquals(before, after);
  }
}
