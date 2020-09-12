package ru.relex.education.addressbook.tests;
// Generated by Selenium IDE
import org.junit.Assert;
//import org.junit.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.relex.education.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeleteTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test"));
    }
  }

  @Test
  public void testGroupDelete() {
    ensurePreconditions();
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all();
    //compare sets count
    Assert.assertEquals(after.size(), before.size() - 1);
    //delete group in before set and compare sets
    before.remove(deletedGroup);
    Assert.assertEquals(before, after);
  }
}
