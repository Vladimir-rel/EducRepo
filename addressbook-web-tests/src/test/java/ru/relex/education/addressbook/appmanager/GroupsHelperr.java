package ru.relex.education.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.relex.education.addressbook.model.GroupData;

import java.util.HashMap;
import java.util.Map;

public class GroupsHelperr extends HelperBase {

  JavascriptExecutor js;
  private Map<String, Object> vars;

  GroupsHelperr(WebDriver wd){
    super(wd);
    js = (JavascriptExecutor) wd;
    vars = new HashMap<String, Object>();
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreate() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type("group_name", groupData.getName());
    type("group_header", groupData.getHeader());
    type("group_footer", groupData.getFooret());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void seectGroup() {
    click(By.name("selected[]"));
  }
}
