package ru.relex.education.addressbook.tests;
// Generated by Selenium IDE
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
//import org.junit.Test;
import org.testng.annotations.Test;
import ru.relex.education.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreateTest extends TestBase{

  @Test
  public void testGropCreation() {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("test1");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    //compare lists count
    assertThat(after.size(), equalTo(before.size() + 1));
    //set in new group ID for before list
    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    //compare sets
    assertThat(after, equalTo(before));
  }
}
