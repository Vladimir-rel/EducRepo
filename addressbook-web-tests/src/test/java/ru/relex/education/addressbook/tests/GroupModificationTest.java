package ru.relex.education.addressbook.tests;

import org.junit.Test;
import ru.relex.education.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupsHelperr().selectGroup();
    app.getGroupsHelperr().initGroupModification();
    app.getGroupsHelperr().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupsHelperr().submitGroupModification();
    app.getGroupsHelperr().returnToGroupPage();
  }
}
