package ru.relex.education.addressbook.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.relex.education.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTest extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();

    if (! app.getGroupsHelperr().isGroupExist()) {
      app.getGroupsHelperr().createGroup(new GroupData("test", null, null));
    }
    List<GroupData> before = app.getGroupsHelperr().getGroupList();
    app.getGroupsHelperr().selectGroup(before.size() - 1);
    app.getGroupsHelperr().initGroupModification();
    GroupData group = new GroupData("testMod1", "testMod2", "testMod3", before.get(before.size() - 1).getId());
    app.getGroupsHelperr().fillGroupForm(group);
    app.getGroupsHelperr().submitGroupModification();
    app.getGroupsHelperr().returnToGroupPage();
    List<GroupData> after = app.getGroupsHelperr().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
