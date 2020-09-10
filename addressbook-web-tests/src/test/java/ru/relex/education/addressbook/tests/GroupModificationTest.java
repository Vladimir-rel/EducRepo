package ru.relex.education.addressbook.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.relex.education.addressbook.model.GroupData;

import java.util.Comparator;
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

    //compare lists element count
    List<GroupData> after = app.getGroupsHelperr().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    //change group in "before" list
    before.remove(before.size() - 1);
    before.add(group);

    //sort and compare lists
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
