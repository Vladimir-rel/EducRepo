package ru.relex.education.addressbook.tests;
// Generated by Selenium IDE
import org.junit.Assert;
import org.junit.Test;
import ru.relex.education.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreateTest extends TestBase{

  @Test
  public void testGropCreation() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupsHelperr().getGroupList();
    GroupData group = new GroupData("test1Mod", null, "test3Mod");
    app.getGroupsHelperr().createGroup(group);
    List<GroupData> after = app.getGroupsHelperr().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.add(group);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
