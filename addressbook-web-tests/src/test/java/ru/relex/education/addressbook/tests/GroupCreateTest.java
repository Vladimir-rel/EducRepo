package ru.relex.education.addressbook.tests;
// Generated by Selenium IDE
import org.junit.Assert;
//import org.junit.Test;
import org.testng.annotations.Test;
import ru.relex.education.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreateTest extends TestBase{

  @Test
  public void testGropCreation() {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData().withName("test1");
    app.group().create(group);
    List<GroupData> after = app.group().list();
    //compare lists count
    Assert.assertEquals(after.size(), before.size() + 1);
    //set in new group ID for before list
    group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    //sort and compare lists
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
