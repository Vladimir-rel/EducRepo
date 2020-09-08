package ru.relex.education.addressbook.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.relex.education.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTest extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupsHelperr().isGroupExist()) {
      app.getGroupsHelperr().createGroup(new GroupData("testMod1", "testMod2", "testMod3"));
    }
    List<GroupData> before = app.getGroupsHelperr().getGroupList();
    app.getGroupsHelperr().selectGroup(before.size() - 1);
    app.getGroupsHelperr().initGroupModification();
    app.getGroupsHelperr().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupsHelperr().submitGroupModification();
    app.getGroupsHelperr().returnToGroupPage();
    List<GroupData> after = app.getGroupsHelperr().getGroupList();
    Assert.assertEquals(after.size(), before.size());
    Assert.assertEquals(after, before);
  }
}
