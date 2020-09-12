package ru.relex.education.addressbook.tests;

import org.junit.Assert;
//import org.junit.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.relex.education.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("test"));
    }
  }
  @Test
  public void testGroupModification(){
    ensurePreconditions();
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    GroupData group = new GroupData().withId(before.get(index).getId()).withName("testMod1").withHeader("testMod2").withFooret("testMod3");
    app.group().modify(index, group);
    //compare lists count
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());
    //change group in "before" list
    before.remove(index);
    before.add(group);
    //sort and compare lists
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
